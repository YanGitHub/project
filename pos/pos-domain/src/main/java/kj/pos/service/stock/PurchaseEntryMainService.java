package kj.pos.service.stock;

import kj.pos.dao.mysql.stock.InventoryDao;
import kj.pos.dao.mysql.stock.PurchaseEntryDetailDao;
import kj.pos.dao.mysql.stock.PurchaseEntryMainDao;
import kj.pos.entity.stock.Inventory;
import kj.pos.entity.stock.PurchaseEntryDetail;
import kj.pos.entity.stock.PurchaseEntryMain;
import kj.pos.entity.stock.PurchaseOrderMain;
import kj.pos.util.web.WebContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 16-4-21.
 */
@Service("purchaseEntryMainService")
public class PurchaseEntryMainService {

    Log logger = LogFactory.getLog(PurchaseEntryMainService.class);

    @Autowired
    private PurchaseEntryMainDao purchaseEntryMainDao;
    @Autowired
    private PurchaseEntryDetailDao purchaseEntryDetailDao;
    @Autowired
    private InventoryDao inventoryDao;

    public Integer getTotal(PurchaseEntryMain purchaseEntryMain)throws SQLException{
        return purchaseEntryMainDao.getTotal(purchaseEntryMain);
    }

    public List<PurchaseEntryMain> getList(PurchaseEntryMain purchaseEntryMain)throws SQLException{
        return purchaseEntryMainDao.getList(purchaseEntryMain);
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public void create(PurchaseEntryMain purchaseEntryMain)throws SQLException{
        purchaseEntryMain.setCreateUser(WebContextUtil.getCurrentUser().getName());
        purchaseEntryMainDao.create(purchaseEntryMain);
        Long id = purchaseEntryMain.getId();
        List<PurchaseEntryDetail> d = purchaseEntryMain.getPurchaseEntryDetailList();
        List<PurchaseEntryDetail> details = new ArrayList<PurchaseEntryDetail>();
        for(PurchaseEntryDetail p : d){
            p.setPid(id);
            details.add(p);
        }
        purchaseEntryDetailDao.create(details);
    }

    public PurchaseEntryMain getEdit(PurchaseEntryMain purchaseEntryMain)throws SQLException{
        List<PurchaseEntryMain> mainList = purchaseEntryMainDao.getList(purchaseEntryMain);
        PurchaseEntryDetail purchaseEntryDetail = new PurchaseEntryDetail();
        purchaseEntryDetail.setPid(purchaseEntryMain.getId());
        List<PurchaseEntryDetail> detailList = purchaseEntryDetailDao.getList(purchaseEntryDetail);
        purchaseEntryMain = mainList.get(0);
        purchaseEntryMain.setPurchaseEntryDetailList(detailList);
        return purchaseEntryMain;
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> update(PurchaseEntryMain purchaseEntryMain)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        purchaseEntryMain.setModifyUser(WebContextUtil.getCurrentUser().getName());
        purchaseEntryMainDao.update(purchaseEntryMain);
        //删除明细
        purchaseEntryDetailDao.delete(purchaseEntryMain.getId());
        //插入明细
        List<PurchaseEntryDetail> list = new ArrayList<PurchaseEntryDetail>();
        for(PurchaseEntryDetail p : purchaseEntryMain.getPurchaseEntryDetailList()){
            p.setPid(purchaseEntryMain.getId());
            list.add(p);
        }
        purchaseEntryDetailDao.create(list);
        map.put("status",Boolean.TRUE);
        map.put("msg", "更新成功");
        return map;
    }

    /**
     * 审核 status = 3
     * @param id
     * @return
     * @throws SQLException
     */
    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> audit(String id)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        String[] idStr = id.split(",");
        for(String s : idStr){
            PurchaseEntryMain purchaseEntryMain = new PurchaseEntryMain();
            purchaseEntryMain.setAuditUser(WebContextUtil.getCurrentUser().getName());
            purchaseEntryMain.setId(Long.parseLong(s));
            purchaseEntryMainDao.audit(purchaseEntryMain);
        }
        map.put("status",Boolean.TRUE);
        map.put("msg","审核成功");
        return map;
    }

    /**
     * 终止 status = 2
     * @param id
     * @return
     * @throws SQLException
     */
    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> cancel(String id)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        String[] idStr = id.split(",");
        for(String s : idStr){
            PurchaseEntryMain purchaseEntryMain = new PurchaseEntryMain();
            purchaseEntryMain.setCancelUser(WebContextUtil.getCurrentUser().getName());
            purchaseEntryMain.setId(Long.parseLong(s));
            purchaseEntryMainDao.cancel(purchaseEntryMain);
        }
        map.put("status",Boolean.TRUE);
        map.put("msg","终止成功");
        return map;
    }

    /**
     * 入库 status = 4
     * @param id
     * @return
     * @throws SQLException
     */
    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> enterStock(String id)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        String[] idStr = id.split(",");
        for(String s : idStr){
            PurchaseEntryMain purchaseEntryMain = new PurchaseEntryMain();
            purchaseEntryMain.setFaAuditUser(WebContextUtil.getCurrentUser().getName());
            purchaseEntryMain.setId(Long.parseLong(s));
            //修改单据状态 入库 status = 4
            purchaseEntryMainDao.enterStock(purchaseEntryMain);
            //更改库存
            changeInventory(Long.parseLong(s));
        }
        map.put("status",Boolean.TRUE);
        map.put("msg","入库成功");
        return map;
    }

    /**
     * 更新库存信息
     * @param mainId 入库单主表id
     * @throws SQLException
     */
    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public void changeInventory(Long mainId)throws SQLException{
        //主表
        PurchaseEntryMain purchaseEntryMain = new PurchaseEntryMain();
        purchaseEntryMain.setId(mainId);
        purchaseEntryMain = purchaseEntryMainDao.getList(purchaseEntryMain).get(0);
        //明细表
        PurchaseEntryDetail purchaseEntryDetail = new PurchaseEntryDetail();
        purchaseEntryDetail.setPid(mainId);
        List<PurchaseEntryDetail> detailList = purchaseEntryDetailDao.getList(purchaseEntryDetail);
        for(PurchaseEntryDetail p : detailList){
            Inventory inventory = new Inventory();
            inventory.setSkuId(p.getProductSkuId());
            inventory.setWarehouseCode(purchaseEntryMain.getWarehouseCode());
            inventory.setQty(p.getQty());
            List<Inventory> inventoryList = inventoryDao.getList(inventory);
            if(inventoryList.size() > 0){
                inventory.setId(inventoryList.get(0).getId());
                inventoryDao.update(inventory);
            }else{
                inventoryDao.create(inventory);
            }
        }
    }
}
