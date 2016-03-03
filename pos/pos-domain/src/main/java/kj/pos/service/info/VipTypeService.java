package kj.pos.service.info;

import kj.pos.dao.mysql.info.VipTypeDao;
import kj.pos.entity.info.VipType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 16-3-2.
 */
@Service
public class VipTypeService {

    Log logger = LogFactory.getLog(VipInfoService.class);

    @Autowired
    private VipTypeDao vipTypeDao;

    public Integer getTotal(VipType vipType)throws SQLException {
        return vipTypeDao.getTotal(vipType);
    }

    public List<VipType> getList(VipType vipType)throws SQLException{
        return vipTypeDao.getList(vipType);
    }
}
