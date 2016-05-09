package kj.pos.service.admin;

import kj.pos.dao.mysql.admin.RoleDao;
import kj.pos.entity.admin.Role;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 16-5-3.
 */
@Service("roleService")
public class RoleService {

    Log logger = LogFactory.getLog(OrganizationService.class);

    @Autowired
    private RoleDao roleDao;

    public List<Role> getList(Role role)throws SQLException{
        return roleDao.getList(role);
    }

    public Integer getTotal(Role role)throws SQLException{
        return roleDao.getTotal(role);
    }

}
