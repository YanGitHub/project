package kj.pos.controller.admin;

import kj.pos.entity.PageUtil;
import kj.pos.entity.admin.Role;
import kj.pos.service.admin.RoleMenuService;
import kj.pos.service.admin.RoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 16-5-3.
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

    Log logger = LogFactory.getLog(RoleController.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String role(){
        return "admin/role";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(Role role,
                                            @RequestParam(value = "page",defaultValue = "1")int page,
                                            @RequestParam(value = "rows",defaultValue = "0")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = roleService.getTotal(role);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            role.setPageUtil(pageUtil);
            List<Role> list = roleService.getList(role);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            map.put("total",0);
            map.put("rows",null);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/addMenu",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addMenu(@RequestParam(value = "id",required = false)Long id,
                                      @RequestParam(value = "data",required = false)String data)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            roleMenuService.addMenu(id,data);
            map.put("status",Boolean.TRUE);
            map.put("msg","保存成功");
        }catch (Exception e){
            map.put("status",Boolean.TRUE);
            map.put("msg","保存失败");
            e.printStackTrace();
        }
        return map;
    }
}
