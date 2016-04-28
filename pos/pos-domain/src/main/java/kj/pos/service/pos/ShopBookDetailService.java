package kj.pos.service.pos;

import kj.pos.dao.mysql.pos.ShopBookDetailDao;
import kj.pos.entity.pos.ShopBookDetail;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 16-4-28.
 */
@Service("shopBookDetailService")
public class ShopBookDetailService {

    Log logger = LogFactory.getLog(ShopBookDetailService.class);

    @Autowired
    private ShopBookDetailDao shopBookDetailDao;

    public Integer getTotal(ShopBookDetail shopBookDetail)throws SQLException {
        return shopBookDetailDao.getTotal(shopBookDetail);
    }

    public List<ShopBookDetail> getList(ShopBookDetail shopBookDetail)throws SQLException{
        return shopBookDetailDao.getList(shopBookDetail);
    }
}
