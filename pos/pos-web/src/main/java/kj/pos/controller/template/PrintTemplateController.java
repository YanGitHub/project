package kj.pos.controller.template;

import kj.pos.entity.template.PrintTemplate;
import kj.pos.service.template.PrintTemplateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yan on 15-12-31.
 */
@Controller
@RequestMapping("/printTemplate")
public class PrintTemplateController {

    Log logger = LogFactory.getLog(PrintTemplateController.class);

    @Autowired
    private PrintTemplateService printTemplateService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String printTemplate(){
        return "template/printTemplate";
    }

    @RequestMapping(value = "/getData",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getData(PrintTemplate printTemplate)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            printTemplate = printTemplateService.getList(printTemplate).get(0);
            map.put("printTemplate",printTemplate);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("printTemplate",null);
        }
        return map;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> save(@RequestBody PrintTemplate printTemplate)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            printTemplateService.save(printTemplate);
            map.put("status",Boolean.TRUE);
            map.put("msg","保存成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("status",Boolean.TRUE);
            map.put("msg","保存成功");
        }
        return map;
    }
}
