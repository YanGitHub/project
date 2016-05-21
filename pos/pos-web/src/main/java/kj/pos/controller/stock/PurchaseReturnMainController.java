package kj.pos.controller.stock;

import kj.pos.entity.PageUtil;
import kj.pos.entity.stock.PurchaseReturnMain;
import kj.pos.service.stock.PurchaseReturnMainService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-5-20.
 */
@Controller
@RequestMapping("/stock/purchaseReturnMain")
public class PurchaseReturnMainController {
    Log logger = LogFactory.getLog(PurchaseOrderMainController.class);

    @Autowired
    private PurchaseReturnMainService purchaseReturnMainService;

    //列表页面
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String purchaseReturnMain(){
        return "stock/purchaseReturnMain";
    }
    //新增页面
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView purchaseOrderMainAdd(Long id){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",id);
        return new ModelAndView("stock/purchaseReturnMainAdd",map);
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(PurchaseReturnMain purchaseReturnMain,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = purchaseReturnMainService.getTotal(purchaseReturnMain);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            purchaseReturnMain.setPageUtil(pageUtil);
            List<PurchaseReturnMain> list = purchaseReturnMainService.getList(purchaseReturnMain);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("total",0);
            map.put("rows",null);
        }
        return map;
    }

    @RequestMapping(value = "/getEdit",method = RequestMethod.POST)
    @ResponseBody
    public PurchaseReturnMain getEdit(PurchaseReturnMain purchaseReturnMain)throws SQLException {
        try {
            purchaseReturnMain = purchaseReturnMainService.getEdit(purchaseReturnMain);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return purchaseReturnMain;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> create(@RequestBody PurchaseReturnMain purchaseReturnMain)throws SQLException{
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            purchaseReturnMainService.create(purchaseReturnMain);
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

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> update(@RequestBody PurchaseReturnMain purchaseReturnMain)throws SQLException{
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            result = purchaseReturnMainService.update(purchaseReturnMain);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            result.put("status",Boolean.FALSE);
            result.put("msg","更新失败");
        }
        return result;
    }

    @RequestMapping(value = "/audit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> audit(String id)throws SQLException{
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            result = purchaseReturnMainService.audit(id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            result.put("status",Boolean.FALSE);
            result.put("msg","审核失败");
        }
        return result;
    }

    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> cancel(String id)throws SQLException{
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            result = purchaseReturnMainService.cancel(id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            result.put("status",Boolean.FALSE);
            result.put("msg","终止失败");
        }
        return result;
    }
}
