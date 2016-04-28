package kj.pos.service.pos;

import kj.pos.dao.mysql.pos.ShopBookPayDao;
import kj.pos.entity.pos.ShopBookPay;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 16-4-28.
 */
@Service("shopBookPayService")
public class ShopBookPayService {

    Log logger = LogFactory.getLog(ShopSalesPayService.class);

    @Autowired
    private ShopBookPayDao shopBookPayDao;

    public Integer getTotal(ShopBookPay shopBookPay)throws SQLException {
        return shopBookPayDao.getTotal(shopBookPay);
    }

    public List<ShopBookPay> getList(ShopBookPay shopBookPay)throws SQLException{
        return shopBookPayDao.getList(shopBookPay);
    }
}
