package kj.pos.controller.admin;

import kj.pos.entity.admin.Menu;
import kj.pos.service.admin.MenuService;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 16-5-3.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    Log logger = LogFactory.getLog(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String menu(){
        return "admin/menu";
    }

    @RequestMapping(value = "/getMenu",method = RequestMethod.POST)
    @ResponseBody
    public List<Menu> getMenu()throws SQLException{
        List<Menu> menu = new ArrayList<Menu>();
        try {
            menu = menuService.getMenu();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return menu;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> add(@RequestBody Menu menu)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            menuService.add(menu);
            map.put("status",Boolean.TRUE);
            map.put("msg","添加成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("status",Boolean.FALSE);
            map.put("msg","添加失败");
        }
        return map;
    }

}
