package kj.pos.service.admin;

import kj.pos.dao.mysql.admin.UserInfoDao;
import kj.pos.entity.admin.UserInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 15-12-15.
 */
@Service("userInfoService")
public class UserInfoService {

    Log logger = LogFactory.getLog(UserInfoService.class);

    @Autowired
    private UserInfoDao userInfoDao;

    public List<UserInfo> getList(UserInfo userInfo)throws SQLException{
        return userInfoDao.getList(userInfo);
    }


}
