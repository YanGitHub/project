package kj.pos.controller.stock;

import kj.pos.entity.PageUtil;
import kj.pos.entity.stock.Inventory;
import kj.pos.service.stock.InventoryService;
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
 * Created by Yan on 16-3-22.
 */
@Controller
@RequestMapping("/inventory")
public class InventoryController {

    Log logger = LogFactory.getLog(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String inventory(){
        return "stock/inventory";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(Inventory inventory,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = inventoryService.getTotal(inventory);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            inventory.setPageUtil(pageUtil);
            List<Inventory> list = inventoryService.getList(inventory);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            map.put("total",0);
            map.put("rows",null);
            e.printStackTrace();
        }
        return map;
    }
}
