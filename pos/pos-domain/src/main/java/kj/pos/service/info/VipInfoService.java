package kj.pos.service.info;

import kj.pos.dao.mysql.info.VipInfoDao;
import kj.pos.entity.info.VipInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 16-3-1.
 */
@Service
public class VipInfoService {

    Log logger = LogFactory.getLog(VipInfoService.class);

    @Autowired
    private VipInfoDao vipInfoDao;

    public Integer getTotal(VipInfo vipInfo)throws SQLException{
        return vipInfoDao.getTotal(vipInfo);
    }

    public List<VipInfo> getList(VipInfo vipInfo)throws SQLException{
        return vipInfoDao.getList(vipInfo);
    }
}
