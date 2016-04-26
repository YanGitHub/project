package kj.pos.controller.info;

import kj.pos.entity.PageUtil;
import kj.pos.entity.info.Warehouse;
import kj.pos.service.info.WarehouseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 15-12-24.
 */
@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    Log logger = LogFactory.getLog(WarehouseController.class);
    
    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ModelAndView warehouse()throws SQLException{
        return new ModelAndView("info/warehouse") ;
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(Warehouse warehouse,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = warehouseService.getTotal(warehouse);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            warehouse.setPageUtil(pageUtil);
            List<Warehouse> list = warehouseService.getList(warehouse);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            map.put("total",0);
            map.put("rows",null);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> add(@RequestBody Warehouse warehouse){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            map = warehouseService.add(warehouse);
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",Boolean.FALSE);
            map.put("msg","添加失败");
        }
        return map;
    }

    @RequestMapping(value = "/setDeliver",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> setDeliver(Warehouse warehouse)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            warehouseService.setDeliver(warehouse);
            map.put("status",Boolean.TRUE);
            map.put("msg","设置成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",Boolean.FALSE);
            map.put("msg","设置失败");
        }
        return map;
    }

    @RequestMapping(value = "/setReceive",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> setReceive(Warehouse warehouse)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            warehouseService.setReceive(warehouse);
            map.put("status",Boolean.TRUE);
            map.put("msg","设置成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",Boolean.FALSE);
            map.put("msg","设置失败");
        }
        return map;
    }
}
