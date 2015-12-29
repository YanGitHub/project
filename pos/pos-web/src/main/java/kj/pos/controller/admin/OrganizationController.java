package kj.pos.controller.admin;

import kj.pos.entity.PageUtil;
import kj.pos.entity.admin.OrganizationInfo;
import kj.pos.service.admin.OrganizationService;
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
 * Created by Yan on 15-12-28.
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {

    Log logger = LogFactory.getLog(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String sysParameters(){
        return "admin/organization";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> shopSalesLine(OrganizationInfo organizationInfo,
                                            @RequestParam(value = "page",defaultValue = "1")int page,
                                            @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = organizationService.getTotal(organizationInfo);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            organizationInfo.setPageUtil(pageUtil);
            List<OrganizationInfo> list = organizationService.getList(organizationInfo);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            map.put("total",0);
            map.put("rows",null);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> save(@RequestBody OrganizationInfo organizationInfo)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            map = organizationService.save(organizationInfo);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("status",Boolean.FALSE);
            map.put("msg","操作失败");
        }
        return map;
    }

}
