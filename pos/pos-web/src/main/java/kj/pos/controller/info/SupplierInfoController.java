package kj.pos.controller.info;

import kj.pos.entity.PageUtil;
import kj.pos.entity.info.SupplierInfo;
import kj.pos.service.info.SupplierInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 16-3-23.
 */
@Controller
@RequestMapping(value = "/supplierInfo")
public class SupplierInfoController {

    Log logger = LogFactory.getLog(SupplierInfoController.class);

    @Autowired
    private SupplierInfoService supplierInfoService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String supplierInfo(){
        return "info/supplierInfo";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(SupplierInfo supplierInfo,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = supplierInfoService.getTotal(supplierInfo);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            supplierInfo.setPageUtil(pageUtil);
            List<SupplierInfo> list = supplierInfoService.getList(supplierInfo);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            map.put("total",0);
            map.put("rows",null);
            e.printStackTrace();
        }
        return map;
    }


    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> add(@RequestBody SupplierInfo supplierInfo){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            map = supplierInfoService.create(supplierInfo);
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",Boolean.FALSE);
            map.put("msg","添加失败");
        }
        return map;
    }
}
