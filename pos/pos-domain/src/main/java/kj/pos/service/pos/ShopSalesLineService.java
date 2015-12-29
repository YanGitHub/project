package kj.pos.service.pos;

import kj.pos.dao.mysql.pos.ShopSalesLineDao;
import kj.pos.entity.pos.ShopSalesLine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 15-12-17.
 */
@Service("shopSalesLineService")
public class ShopSalesLineService {

    Log logger = LogFactory.getLog(ShopSalesLineService.class);

    @Autowired
    private ShopSalesLineDao shopSalesLineDao;

    public Integer getTotal(ShopSalesLine shopSalesLine)throws SQLException {
        return shopSalesLineDao.getTotal(shopSalesLine);
    }

    public List<ShopSalesLine> getList(ShopSalesLine shopSalesLine)throws SQLException{
        return shopSalesLineDao.getList(shopSalesLine);
    }

}
