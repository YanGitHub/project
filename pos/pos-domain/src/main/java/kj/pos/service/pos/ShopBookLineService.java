package kj.pos.service.pos;

import kj.pos.dao.mysql.pos.ShopBookLineDao;
import kj.pos.entity.pos.ShopBookLine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 16-4-28.
 */
@Service("shopBookLineService")
public class ShopBookLineService {

    Log logger = LogFactory.getLog(ShopSalesLineService.class);

    @Autowired
    private ShopBookLineDao shopBookLineDao;

    public Integer getTotal(ShopBookLine shopBookLine)throws SQLException {
        return shopBookLineDao.getTotal(shopBookLine);
    }

    public List<ShopBookLine> getList(ShopBookLine shopBookLine)throws SQLException{
        return shopBookLineDao.getList(shopBookLine);
    }

}
