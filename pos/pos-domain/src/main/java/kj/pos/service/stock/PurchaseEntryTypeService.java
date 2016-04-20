package kj.pos.service.stock;

import kj.pos.dao.mysql.stock.PurchaseEntryTypeDao;
import kj.pos.entity.stock.PurchaseEntryType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 16-4-20.
 */
@Service("purchaseEntryTypeService")
public class PurchaseEntryTypeService {

    Log logger = LogFactory.getLog(PurchaseEntryTypeService.class);

    @Autowired
    private PurchaseEntryTypeDao purchaseEntryTypeDao;

    public List<PurchaseEntryType> getList(PurchaseEntryType purchaseEntryType)throws SQLException{
        return purchaseEntryTypeDao.getList(purchaseEntryType);
    }

    public Integer getTotal(PurchaseEntryType purchaseEntryType)throws SQLException{
        return purchaseEntryTypeDao.getTotal(purchaseEntryType);
    }
}
