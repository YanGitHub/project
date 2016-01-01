package kj.pos.service.pos;

import kj.pos.dao.mysql.pos.ShopPaymentDao;
import kj.pos.entity.pos.ShopPayment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 15-12-15.
 */
@Service("shopPaymentService")
public class ShopPaymentService {

    Log logger = LogFactory.getLog(ShopPaymentService.class);

    @Autowired
    private ShopPaymentDao shopPaymentDao;

    public List<ShopPayment> getList(ShopPayment shopPayment)throws SQLException{
        return shopPaymentDao.getList(shopPayment);
    }

    public Integer getTotal(ShopPayment shopPayment)throws SQLException{
        return shopPaymentDao.getTotal(shopPayment);
    }
    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public void op(ShopPayment shopPayment)throws SQLException{
        shopPaymentDao.update(shopPayment);
    }
}
