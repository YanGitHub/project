package kj.pos.service.stock;

import kj.pos.dao.mysql.stock.PurchaseReturnTypeDao;
import kj.pos.entity.stock.PurchaseReturnType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 16-5-16.
 */
@Service("purchaseReturnTypeService")
public class PurchaseReturnTypeService {

    Log logger = LogFactory.getLog(PurchaseReturnTypeService.class);

    @Autowired
    private PurchaseReturnTypeDao purchaseReturnTypeDao;

    public Integer getTotal(PurchaseReturnType purchaseReturnType)throws SQLException{
        return purchaseReturnTypeDao.getTotal(purchaseReturnType);
    }

    public List<PurchaseReturnType> getList(PurchaseReturnType purchaseReturnType)throws SQLException{
        return purchaseReturnTypeDao.getList(purchaseReturnType);
    }
}
