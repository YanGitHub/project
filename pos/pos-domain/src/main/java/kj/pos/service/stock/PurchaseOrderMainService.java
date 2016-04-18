package kj.pos.service.stock;

import kj.pos.dao.mysql.stock.PurchaseOrderDetailDao;
import kj.pos.dao.mysql.stock.PurchaseOrderMainDao;
import kj.pos.entity.stock.PurchaseOrderDetail;
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
 * Created by Yan on 16-3-30.
 */
@Service("purchaseOrderMainService")
public class PurchaseOrderMainService {

    Log logger = LogFactory.getLog(PurchaseOrderMainService.class);

    @Autowired
    private PurchaseOrderMainDao purchaseOrderMainDao;
    @Autowired
    private PurchaseOrderDetailDao purchaseOrderDetailDao;

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public void create(PurchaseOrderMain purchaseOrderMain)throws SQLException{
        purchaseOrderMain.setCreateUser(WebContextUtil.getCurrentUser().getName());
        purchaseOrderMainDao.create(purchaseOrderMain);
        Long id = purchaseOrderMain.getId();
        List<PurchaseOrderDetail> d = purchaseOrderMain.getPurchaseOrderDetailList();
        List<PurchaseOrderDetail> details = new ArrayList<PurchaseOrderDetail>();
        for(PurchaseOrderDetail p : d){
            p.setPid(id);
            details.add(p);
        }
        purchaseOrderDetailDao.create(details);
    }

    public List<PurchaseOrderMain> getList(PurchaseOrderMain purchaseOrderMain)throws SQLException{
        return purchaseOrderMainDao.getList(purchaseOrderMain);
    }

    public Integer getTotal(PurchaseOrderMain purchaseOrderMain)throws SQLException{
        return purchaseOrderMainDao.getTotal(purchaseOrderMain);
    }

    public PurchaseOrderMain getEdit(PurchaseOrderMain purchaseOrderMain)throws SQLException{
        List<PurchaseOrderMain> mainList = purchaseOrderMainDao.getList(purchaseOrderMain);
        PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
        purchaseOrderDetail.setPid(purchaseOrderMain.getId());
        List<PurchaseOrderDetail> detailList = purchaseOrderDetailDao.getList(purchaseOrderDetail);
        purchaseOrderMain = mainList.get(0);
        purchaseOrderMain.setPurchaseOrderDetailList(detailList);
        return purchaseOrderMain;
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> update(PurchaseOrderMain purchaseOrderMain)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        purchaseOrderMain.setModifyUser(WebContextUtil.getCurrentUser().getName());
        purchaseOrderMainDao.update(purchaseOrderMain);
        //删除明细
        purchaseOrderDetailDao.delete(purchaseOrderMain.getId());
        //插入明细
        List<PurchaseOrderDetail> list = new ArrayList<PurchaseOrderDetail>();
        for(PurchaseOrderDetail p : purchaseOrderMain.getPurchaseOrderDetailList()){
            p.setPid(purchaseOrderMain.getId());
            list.add(p);
        }
        purchaseOrderDetailDao.create(list);
        map.put("status",Boolean.TRUE);
        map.put("msg","更新成功");
        return map;
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> audit(String id)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        String[] idStr = id.split(",");
        for(String s : idStr){
            PurchaseOrderMain purchaseOrderMain = new PurchaseOrderMain();
            purchaseOrderMain.setAuditUser(WebContextUtil.getCurrentUser().getName());
            purchaseOrderMain.setId(Long.parseLong(s));
            purchaseOrderMainDao.audit(purchaseOrderMain);
        }
        map.put("status",Boolean.TRUE);
        map.put("msg","审核成功");
        return map;
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> cancel(String id)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        String[] idStr = id.split(",");
        for(String s : idStr){
            PurchaseOrderMain purchaseOrderMain = new PurchaseOrderMain();
            purchaseOrderMain.setCancelUser(WebContextUtil.getCurrentUser().getName());
            purchaseOrderMain.setId(Long.parseLong(s));
            purchaseOrderMainDao.cancel(purchaseOrderMain);
        }
        map.put("status",Boolean.TRUE);
        map.put("msg","终止成功");
        return map;
    }
}
