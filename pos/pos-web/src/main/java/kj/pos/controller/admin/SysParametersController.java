package kj.pos.controller.admin;

import kj.pos.entity.PageUtil;
import kj.pos.entity.admin.SysParameters;
import kj.pos.service.admin.SysParametersService;
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
 * Created by Yan on 15-12-22.
 */
@Controller
@RequestMapping("/sysParameters")
public class SysParametersController {

    Log logger = LogFactory.getLog(SysParametersController.class);

    @Autowired
    private SysParametersService sysParametersService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String sysParameters(){
        return "admin/sysParameters";
    }


    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> save(@RequestBody SysParameters sysParameters)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            map = sysParametersService.save(sysParameters);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("status",Boolean.FALSE);
            map.put("msg","操作失败");
        }
        return map;
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> shopSalesLine(SysParameters sysParameters,
                                            @RequestParam(value = "page",defaultValue = "1")int page,
                                            @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = sysParametersService.getTotal(sysParameters);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            sysParameters.setPageUtil(pageUtil);
            List<SysParameters> list = sysParametersService.getList(sysParameters);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            map.put("total",0);
            map.put("rows",null);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/status",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> status(SysParameters sysParameters)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            sysParametersService.status(sysParameters);
            map.put("status",Boolean.TRUE);
            map.put("msg","操作成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("status",Boolean.FALSE);
            map.put("msg","操作失败");
        }
        return map;
    }

}
