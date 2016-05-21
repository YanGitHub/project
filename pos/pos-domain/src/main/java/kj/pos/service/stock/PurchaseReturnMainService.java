package kj.pos.service.stock;

import kj.pos.dao.mysql.stock.PurchaseReturnDetailDao;
import kj.pos.dao.mysql.stock.PurchaseReturnMainDao;
import kj.pos.entity.stock.PurchaseReturnDetail;
import kj.pos.entity.stock.PurchaseReturnMain;
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
 * Created by Administrator on 16-5-20.
 */
@Service("purchaseReturnMainService")
public class PurchaseReturnMainService {

    Log logger = LogFactory.getLog(PurchaseReturnMainService.class);

    @Autowired
    private PurchaseReturnMainDao purchaseReturnMainDao;
    @Autowired
    private PurchaseReturnDetailDao purchaseReturnDetailDao;

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public void create(PurchaseReturnMain purchaseReturnMain)throws SQLException {
        purchaseReturnMain.setCreateUser(WebContextUtil.getCurrentUser().getName());
        purchaseReturnMainDao.create(purchaseReturnMain);
        Long id = purchaseReturnMain.getId();
        List<PurchaseReturnDetail> d = purchaseReturnMain.getPurchaseReturnDetailList();
        List<PurchaseReturnDetail> details = new ArrayList<PurchaseReturnDetail>();
        for(PurchaseReturnDetail p : d){
            p.setPid(id);
            details.add(p);
        }
        purchaseReturnDetailDao.create(details);
    }

    public List<PurchaseReturnMain> getList(PurchaseReturnMain purchaseReturnMain)throws SQLException{
        return purchaseReturnMainDao.getList(purchaseReturnMain);
    }

    public Integer getTotal(PurchaseReturnMain purchaseReturnMain)throws SQLException{
        return purchaseReturnMainDao.getTotal(purchaseReturnMain);
    }

    public PurchaseReturnMain getEdit(PurchaseReturnMain purchaseReturnMain)throws SQLException{
        List<PurchaseReturnMain> mainList = purchaseReturnMainDao.getList(purchaseReturnMain);
        PurchaseReturnDetail purchaseOrderDetail = new PurchaseReturnDetail();
        purchaseOrderDetail.setPid(purchaseReturnMain.getId());
        List<PurchaseReturnDetail> detailList = purchaseReturnDetailDao.getList(purchaseOrderDetail);
        purchaseReturnMain = mainList.get(0);
        purchaseReturnMain.setPurchaseReturnDetailList(detailList);
        return purchaseReturnMain;
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> update(PurchaseReturnMain purchaseReturnMain)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        purchaseReturnMain.setModifyUser(WebContextUtil.getCurrentUser().getName());
        purchaseReturnMainDao.update(purchaseReturnMain);
        //删除明细
        purchaseReturnDetailDao.delete(purchaseReturnMain.getId());
        //插入明细
        List<PurchaseReturnDetail> list = new ArrayList<PurchaseReturnDetail>();
        for(PurchaseReturnDetail p : purchaseReturnMain.getPurchaseReturnDetailList()){
            p.setPid(purchaseReturnMain.getId());
            list.add(p);
        }
        purchaseReturnDetailDao.create(list);
        map.put("status",Boolean.TRUE);
        map.put("msg","更新成功");
        return map;
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> audit(String id)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        String[] idStr = id.split(",");
        for(String s : idStr){
            PurchaseReturnMain purchaseReturnMain = new PurchaseReturnMain();
            purchaseReturnMain.setAuditUser(WebContextUtil.getCurrentUser().getName());
            purchaseReturnMain.setId(Long.parseLong(s));
            purchaseReturnMainDao.audit(purchaseReturnMain);
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
            PurchaseReturnMain purchaseReturnMain = new PurchaseReturnMain();
            purchaseReturnMain.setCancelUser(WebContextUtil.getCurrentUser().getName());
            purchaseReturnMain.setId(Long.parseLong(s));
            purchaseReturnMainDao.cancel(purchaseReturnMain);
        }
        map.put("status",Boolean.TRUE);
        map.put("msg","终止成功");
        return map;
    }
}
