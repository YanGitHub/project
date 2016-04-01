package kj.pos.service.stock;

import kj.pos.dao.mysql.stock.PurchaseTypeDao;
import kj.pos.entity.stock.PurchaseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 16-3-30.
 */
@Service("purchaseTypeService")
public class PurchaseTypeService {

    Log logger = LogFactory.getLog(PurchaseTypeService.class);

    @Autowired
    private PurchaseTypeDao purchaseTypeDao;

    public Integer getTotal(PurchaseType purchaseType)throws SQLException {
        return purchaseTypeDao.getTotal(purchaseType);
    }

    public List<PurchaseType> getList(PurchaseType purchaseType)throws SQLException{
        return purchaseTypeDao.getList(purchaseType);
    }
}
