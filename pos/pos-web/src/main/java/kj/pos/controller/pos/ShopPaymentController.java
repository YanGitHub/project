package kj.pos.controller.pos;

import kj.pos.entity.PageUtil;
import kj.pos.entity.pos.ShopPayment;
import kj.pos.service.pos.ShopPaymentService;
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
 * Created by Yan on 15-12-15.
 */
@Controller
@RequestMapping("/shopPayment")
public class ShopPaymentController {

    Log logger = LogFactory.getLog(ShopPaymentController.class);

    @Autowired
    private ShopPaymentService shopPaymentService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String shopPayment(){
        return "pos/shopPayment";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(ShopPayment shopPayment,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = shopPaymentService.getTotal(shopPayment);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            shopPayment.setPageUtil(pageUtil);
            List<ShopPayment> list = shopPaymentService.getList(shopPayment);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            map.put("total",0);
            map.put("rows",null);
            e.printStackTrace();
        }
        return map;
    }


    @RequestMapping(value = "/op",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> op(ShopPayment shopPayment)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            shopPaymentService.op(shopPayment);
            map.put("status",Boolean.FALSE);
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
