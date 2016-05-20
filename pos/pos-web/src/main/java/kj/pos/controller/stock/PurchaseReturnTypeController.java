package kj.pos.controller.stock;

import kj.pos.entity.PageUtil;
import kj.pos.entity.stock.PurchaseReturnType;
import kj.pos.service.stock.PurchaseReturnTypeService;
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
 * Created by Administrator on 16-5-16.
 */
@Controller
@RequestMapping("/stock/purchaseReturnType")
public class PurchaseReturnTypeController {

    Log logger = LogFactory.getLog(PurchaseReturnTypeController.class);

    @Autowired
    private PurchaseReturnTypeService purchaseReturnTypeService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String purchaseType(){
        return "stock/purchaseReturnType";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(PurchaseReturnType purchaseReturnType,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = purchaseReturnTypeService.getTotal(purchaseReturnType);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            purchaseReturnType.setPageUtil(pageUtil);
            List<PurchaseReturnType> list = purchaseReturnTypeService.getList(purchaseReturnType);
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
