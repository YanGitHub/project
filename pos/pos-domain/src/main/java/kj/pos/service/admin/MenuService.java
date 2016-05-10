package kj.pos.service.admin;

import com.sun.swing.internal.plaf.metal.resources.metal_de;
import kj.pos.dao.mysql.admin.MenuDao;
import kj.pos.entity.admin.Menu;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yan on 16-5-3.
 */
@Service("menuService")
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    Log logger = LogFactory.getLog(MenuService.class);

    public List<Menu> getMenu()throws SQLException{
        List<Menu> menuList = menuDao.getListByPid(null);
        return menuList;
    }
    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public void add(Menu menu)throws SQLException{
        menu.setNo(menuDao.getMaxNo());
        menuDao.create(menu);
    }
}
