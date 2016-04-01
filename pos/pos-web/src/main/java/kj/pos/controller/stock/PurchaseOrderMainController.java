package kj.pos.controller.stock;

import kj.pos.entity.stock.PurchaseOrderMain;
import kj.pos.service.stock.PurchaseOrderMainService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yan on 16-3-30.
 */
@Controller
@RequestMapping("/stock/purchaseOrderMain")
public class PurchaseOrderMainController {

    Log logger = LogFactory.getLog(PurchaseOrderMainController.class);

    @Autowired
    private PurchaseOrderMainService purchaseOrderMainService;

    //列表页面
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String purchaseOrderMain(){
        return "stock/purchaseOrderMain";
    }
    //新增页面
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String purchaseOrderMainAdd(){
        return "stock/purchaseOrderMainAdd";
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> create(PurchaseOrderMain purchaseOrderMain)throws SQLException{
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            purchaseOrderMainService.create(purchaseOrderMain);
            result.put("status",Boolean.TRUE);
            result.put("msg","添加成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            result.put("status",Boolean.FALSE);
            result.put("msg","添加失败");
        }
        return result;
    }
}
