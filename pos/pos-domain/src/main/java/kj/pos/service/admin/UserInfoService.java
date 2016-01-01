package kj.pos.service.admin;

import kj.pos.dao.mysql.admin.UserInfoDao;
import kj.pos.entity.admin.UserInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Integer getTotal(UserInfo userInfo)throws SQLException{
        return userInfoDao.getTotal(userInfo);
    }
    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> save(UserInfo userInfo)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        if(userInfo.getId() != null){
            int num = userInfoDao.getCountByCodyAndId(userInfo);
            if(num > 0){
                map.put("status",Boolean.FALSE);
                map.put("msg","用户编码已存在");
            }else{
                userInfoDao.update(userInfo);
                map.put("status",Boolean.TRUE);
                map.put("msg","更新成功");
            }
        }else{
            UserInfo param = new UserInfo();
            param.setCode(userInfo.getCode());
            List<UserInfo> list = userInfoDao.getList(param);
            if(list.size() > 0){
                map.put("status",Boolean.FALSE);
                map.put("msg","用户编码已存在");
            }else{
                userInfoDao.create(userInfo);
                map.put("status",Boolean.TRUE);
                map.put("msg","新增成功");
            }
        }
        return map;
    }
    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public void status(UserInfo userInfo)throws SQLException{
        userInfoDao.status(userInfo);
    }
}
