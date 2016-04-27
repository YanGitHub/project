package kj.pos.controller.info;

import kj.pos.entity.PageUtil;
import kj.pos.entity.info.EmployeeInfo;
import kj.pos.service.info.EmployeeInfoService;
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
 * Created by Yan on 16-4-27.
 */
@Controller
@RequestMapping("/info/employeeInfo")
public class EmployeeInfoController {

    Log logger = LogFactory.getLog(EmployeeInfoController.class);

    @Autowired
    private EmployeeInfoService employeeInfoService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String employeeInfo(){
        return "info/employeeInfo";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(EmployeeInfo employeeInfo,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = employeeInfoService.getTotal(employeeInfo);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            employeeInfo.setPageUtil(pageUtil);
            List<EmployeeInfo> list = employeeInfoService.getList(employeeInfo);
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
