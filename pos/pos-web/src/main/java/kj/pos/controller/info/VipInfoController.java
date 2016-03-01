package kj.pos.controller.info;

import kj.pos.entity.PageUtil;
import kj.pos.entity.info.VipInfo;
import kj.pos.service.info.VipInfoService;
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
 * Created by Yan on 16-3-1.
 */
@Controller
@RequestMapping("/vipInfo")
public class VipInfoController {

    Log logger = LogFactory.getLog(VipInfoController.class);

    @Autowired
    private VipInfoService vipInfoService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String vipInfo(){
        return "info/vipInfo";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(VipInfo vipInfo,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = vipInfoService.getTotal(vipInfo);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            vipInfo.setPageUtil(pageUtil);
            List<VipInfo> list = vipInfoService.getList(vipInfo);
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
