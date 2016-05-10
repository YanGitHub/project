package kj.pos.service.admin;

import kj.pos.dao.mysql.admin.RoleMenuDao;
import kj.pos.entity.admin.RoleMenu;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by Yan on 16-5-9.
 */
@Service("roleMenuService")
public class RoleMenuService {

    Log logger = LogFactory.getLog(RoleMenuService.class);

    @Autowired
    private RoleMenuDao roleMenuDao;

    public void addMenu(Long roleId,String data)throws SQLException{
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(roleId);
        String menuId[] = data.split(",");
        Long menuIdArray[] = new Long[menuId.length];
        for (int i = 0;i < menuId.length;i++){
            menuIdArray[i] = Long.parseLong(menuId[i]);
        }
        roleMenu.setMenuIdArray(menuIdArray);
        //清除以前的菜单权限
        roleMenuDao.delete(roleMenu);
        //插入新的菜单权限
        roleMenuDao.create(roleMenu);
    }
}
