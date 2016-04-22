package kj.pos.controller.stock;

import kj.pos.entity.PageUtil;
import kj.pos.entity.stock.PurchaseEntryMain;
import kj.pos.service.stock.PurchaseEntryMainService;
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
 * Created by Yan on 16-4-20.
 */
@Controller
@RequestMapping("/stock/purchaseEntryMain")
public class PurchaseEntryMainController {

    Log logger = LogFactory.getLog(PurchaseEntryMainController.class);

    @Autowired
    private PurchaseEntryMainService purchaseEntryMainService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String purchaseEntryMain(){
        return "stock/purchaseEntryMain";
    }

    //新增页面
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView purchaseEntryMainAdd(Long id){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",id);
        return new ModelAndView("stock/purchaseEntryMainAdd",map);
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(PurchaseEntryMain purchaseEntryMain,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = purchaseEntryMainService.getTotal(purchaseEntryMain);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            purchaseEntryMain.setPageUtil(pageUtil);
            List<PurchaseEntryMain> list = purchaseEntryMainService.getList(purchaseEntryMain);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            map.put("total",0);
            map.put("rows",null);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> create(@RequestBody PurchaseEntryMain purchaseEntryMain)throws SQLException{
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            purchaseEntryMainService.create(purchaseEntryMain);
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

    @RequestMapping(value = "/getEdit",method = RequestMethod.POST)
    @ResponseBody
    public PurchaseEntryMain getEdit(PurchaseEntryMain purchaseEntryMain)throws SQLException {
        try {
            purchaseEntryMain = purchaseEntryMainService.getEdit(purchaseEntryMain);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return purchaseEntryMain;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> update(@RequestBody PurchaseEntryMain purchaseEntryMain)throws SQLException{
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            result = purchaseEntryMainService.update(purchaseEntryMain);
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
            result = purchaseEntryMainService.audit(id);
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
            result = purchaseEntryMainService.cancel(id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            result.put("status",Boolean.FALSE);
            result.put("msg","终止失败");
        }
        return result;
    }

    @RequestMapping(value = "/enterStock",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> enterStock(String id)throws SQLException{
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            result = purchaseEntryMainService.enterStock(id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            result.put("status",Boolean.FALSE);
            result.put("msg","入库失败");
        }
        return result;
    }
}
