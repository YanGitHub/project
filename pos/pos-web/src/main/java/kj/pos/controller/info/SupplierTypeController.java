package kj.pos.controller.info;

import kj.pos.entity.PageUtil;
import kj.pos.entity.info.SupplierType;
import kj.pos.service.info.SupplierTypeService;
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
 * Created by Yan on 16-3-23.
 */
@Controller
@RequestMapping("/supplierType")
public class SupplierTypeController {

    Log logger = LogFactory.getLog(SupplierTypeController.class);

    @Autowired
    private SupplierTypeService supplierTypeService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String supplierType(){
        return "info/supplierType";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(SupplierType supplierType,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = supplierTypeService.getTotal(supplierType);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            supplierType.setPageUtil(pageUtil);
            List<SupplierType> list = supplierTypeService.getList(supplierType);
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
    public Map<String,Object> create(SupplierType supplierType)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            supplierTypeService.create(supplierType);
            map.put("status",Boolean.TRUE);
            map.put("msg","保存成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("status",Boolean.FALSE);
            map.put("msg","保存失败");
        }
        return map;
    }
}
