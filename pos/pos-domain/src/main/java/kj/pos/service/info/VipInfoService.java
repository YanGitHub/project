package kj.pos.service.info;

import kj.pos.dao.mysql.info.VipInfoDao;
import kj.pos.entity.info.VipInfo;
import kj.pos.util.web.WebContextUtil;
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

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> create(VipInfo vipInfo)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        Integer n = vipInfoDao.getCode(vipInfo);
        if(n > 0){
            map.put("status",Boolean.FALSE);
            map.put("msg","此代码已经存在");
        }else{
            vipInfo.setIssuingPerson(WebContextUtil.getCurrentUser().getName());
            if(vipInfo.getBirthday().equals("")){
                vipInfo.setBirthday(null);
            }
            vipInfoDao.create(vipInfo);
            map.put("status",Boolean.TRUE);
            map.put("msg","添加成功");
        }
        return map;
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public void update(VipInfo vipInfo)throws SQLException{
        vipInfoDao.update(vipInfo);
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public void delete(VipInfo vipInfo)throws SQLException{
        vipInfoDao.delete(vipInfo);
    }
}
