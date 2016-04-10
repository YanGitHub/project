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
import java.util.List;

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
        purchaseOrderMain.setStatus(1);//未审核
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

}
