package kj.pos.service.admin;

import kj.pos.dao.mysql.admin.OrganizationInfoDao;
import kj.pos.entity.admin.OrganizationInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 15-12-28.
 */
@Service("organizationService")
public class OrganizationService {

    Log logger = LogFactory.getLog(OrganizationService.class);

    @Autowired
    private OrganizationInfoDao organizationInfoDao;

    public Integer getTotal(OrganizationInfo organizationInfo)throws SQLException{
        return organizationInfoDao.getTotal(organizationInfo);
    }

    public List<OrganizationInfo> getList(OrganizationInfo organizationInfo)throws SQLException{
        return organizationInfoDao.getList(organizationInfo);
    }

    public Map<String,Object> save(OrganizationInfo organizationInfo)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        if(organizationInfo.getId() != null){
            int num = organizationInfoDao.getCountByCodyAndId(organizationInfo);
            if(num > 0){
                map.put("status",Boolean.FALSE);
                map.put("msg","店铺代码已存在");
            }else{
                organizationInfoDao.update(organizationInfo);
                map.put("status",Boolean.TRUE);
                map.put("msg","更新成功");
            }
        }else{
            OrganizationInfo parameters = new OrganizationInfo();
            parameters.setCode(organizationInfo.getCode());
            List<OrganizationInfo> list = organizationInfoDao.getList(parameters);
            if(list.size() > 0){
                map.put("status",Boolean.FALSE);
                map.put("msg","店铺代码已存在");
            }else{
                organizationInfoDao.create(organizationInfo);
                map.put("status",Boolean.TRUE);
                map.put("msg","新增成功");
            }
        }
        return map;
    }
}
