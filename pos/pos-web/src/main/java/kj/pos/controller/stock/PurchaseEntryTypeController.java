package kj.pos.controller.stock;

import kj.pos.entity.PageUtil;
import kj.pos.entity.stock.PurchaseEntryType;
import kj.pos.service.stock.PurchaseEntryTypeService;
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
 * Created by Yan on 16-4-20.
 */
@Controller
@RequestMapping("/stock/purchaseEntryType")
public class PurchaseEntryTypeController {

    Log logger = LogFactory.getLog(PurchaseEntryTypeController.class);

    @Autowired
    private PurchaseEntryTypeService purchaseEntryTypeService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String purchaseEntryType(){
        return "stock/purchaseEntryType";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(PurchaseEntryType purchaseEntryType,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = purchaseEntryTypeService.getTotal(purchaseEntryType);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            purchaseEntryType.setPageUtil(pageUtil);
            List<PurchaseEntryType> list = purchaseEntryTypeService.getList(purchaseEntryType);
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
