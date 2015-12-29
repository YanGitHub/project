package kj.pos.service.pos;

import kj.pos.dao.mysql.pos.ShopSalesPayDao;
import kj.pos.entity.pos.ShopSalesPay;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 15-12-17.
 */
@Service("shopSalesPayService")
public class ShopSalesPayService {

    Log logger = LogFactory.getLog(ShopSalesPayService.class);

    @Autowired
    private ShopSalesPayDao shopSalesPayDao;

    public Integer getTotal(ShopSalesPay shopSalesPay)throws SQLException {
        return shopSalesPayDao.getTotal(shopSalesPay);
    }

    public List<ShopSalesPay> getList(ShopSalesPay shopSalesPay)throws SQLException{
        return shopSalesPayDao.getList(shopSalesPay);
    }
}
