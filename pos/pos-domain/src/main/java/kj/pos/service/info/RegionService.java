package kj.pos.service.info;

import kj.pos.dao.mysql.admin.RegionDao;
import kj.pos.entity.info.Region;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 15-12-25.
 */
@Service("regionService")
public class RegionService {

    Log logger = LogFactory.getLog(RegionService.class);

    @Autowired
    private RegionDao regionDao;

    public List<Region> getList(Region region)throws SQLException{
        return regionDao.getList(region);
    }

}
