package kj.pos.service.pos;

import kj.pos.dao.mysql.pos.ShopSalesDetailDao;
import kj.pos.entity.pos.ShopSalesDetail;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 15-12-17.
 */
@Service("shopSalesDetailService")
public class ShopSalesDetailService {

    Log logger = LogFactory.getLog(ShopSalesDetailService.class);

    @Autowired
    private ShopSalesDetailDao shopSalesDetailDao;

    public Integer getTotal(ShopSalesDetail shopSalesDetail)throws SQLException{
        return shopSalesDetailDao.getTotal(shopSalesDetail);
    }

    public List<ShopSalesDetail> getList(ShopSalesDetail shopSalesDetail)throws SQLException{
        return shopSalesDetailDao.getList(shopSalesDetail);
    }
}
