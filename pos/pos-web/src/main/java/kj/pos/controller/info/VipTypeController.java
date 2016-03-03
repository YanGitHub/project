package kj.pos.controller.info;

import kj.pos.entity.PageUtil;
import kj.pos.entity.info.VipType;
import kj.pos.service.info.VipTypeService;
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
 * Created by Yan on 16-3-2.
 */
@Controller
@RequestMapping("/vipType")
public class VipTypeController {

    Log logger = LogFactory.getLog(VipTypeController.class);

    @Autowired
    private VipTypeService vipTypeService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String vipType(){
        return "info/vipType";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(VipType vipType,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = vipTypeService.getTotal(vipType);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            vipType.setPageUtil(pageUtil);
            List<VipType> list = vipTypeService.getList(vipType);
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
