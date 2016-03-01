package kj.pos.controller.info;

import kj.pos.entity.PageUtil;
import kj.pos.entity.info.ExpressCompany;
import kj.pos.service.info.ExpressCompanyService;
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
@RequestMapping("/expressCompany")
public class ExpressCompanyContrller {

    Log logger = LogFactory.getLog(ExpressCompanyContrller.class);

    @Autowired
    private ExpressCompanyService expressCompanyService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String expressCompany(){
        return "info/expressCompany";
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search(){
        return "info/expressSearch";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(ExpressCompany expressCompany,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = expressCompanyService.getTotal(expressCompany);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            expressCompany.setPageUtil(pageUtil);
            List<ExpressCompany> list = expressCompanyService.getList(expressCompany);
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
