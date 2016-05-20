package kj.pos.service.stock;

import kj.pos.dao.mysql.stock.PurchaseOutboundTypeDao;
import kj.pos.entity.stock.PurchaseOutboundType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 16-5-16.
 */
@Service("purchaseOutboundTypeService")
public class PurchaseOutboundTypeService {

    Log logger = LogFactory.getLog(PurchaseOutboundTypeService.class);

    @Autowired
    private PurchaseOutboundTypeDao purchaseOutboundTypeDao;

    public Integer getTotal( PurchaseOutboundType purchaseOutboundType)throws SQLException {
        return purchaseOutboundTypeDao.getTotal(purchaseOutboundType);
    }

    public List<PurchaseOutboundType> getList( PurchaseOutboundType purchaseOutboundType)throws SQLException{
        return purchaseOutboundTypeDao.getList(purchaseOutboundType);
    }

}
