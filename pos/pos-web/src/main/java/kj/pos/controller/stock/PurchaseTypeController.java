package kj.pos.controller.stock;

import kj.pos.entity.PageUtil;
import kj.pos.entity.stock.PurchaseType;
import kj.pos.service.stock.PurchaseTypeService;
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
 * Created by Yan on 16-3-30.
 */
@Controller
@RequestMapping("/stock/purchaseType")
public class PurchaseTypeController {

    Log logger = LogFactory.getLog(PurchaseTypeController.class);

    @Autowired
    private PurchaseTypeService purchaseTypeService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String purchaseType(){
        return "stock/purchaseType";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(PurchaseType purchaseType,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = purchaseTypeService.getTotal(purchaseType);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            purchaseType.setPageUtil(pageUtil);
            List<PurchaseType> list = purchaseTypeService.getList(purchaseType);
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
