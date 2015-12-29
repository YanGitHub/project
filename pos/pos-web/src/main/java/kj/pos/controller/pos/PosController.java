package kj.pos.controller.pos;

import kj.pos.entity.PageUtil;
import kj.pos.entity.pos.*;
import kj.pos.service.pos.*;
import kj.pos.util.web.WebContextUtil;
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
 * Created by Yan on 15-12-2.
 */
@Controller
@RequestMapping("/pos")
public class PosController {

    Log logger = LogFactory.getLog(PosController.class);

    @Autowired
    private PosService posService;
    @Autowired
    private ShopPaymentService shopPaymentService;
    @Autowired
    private ShopSalesDetailService shopSalesDetailService;
    @Autowired
    private ShopSalesLineService shopSalesLineService;
    @Autowired
    private ShopSalesPayService shopSalesPayService;

    /**
     *
     * @return 收银主界面
     * @throws SQLException
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ModelAndView pos()throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        //支付方式
        ShopPayment shopPayment = new ShopPayment();
        shopPayment.setDel(false);
        List<ShopPayment> list = shopPaymentService.getList(shopPayment);
        //系统参数  是否打印小票
        String print_receipts = WebContextUtil.getSysParameter("PRINT_RECEIPTS");
        map.put("shopPaymentList",list);
        map.put("print_receipts",print_receipts);
        return new ModelAndView("pos/pos",map);
    }

    /**
     *
     * @return 历史查询  （订单查询）
     * @throws SQLException
     */
    @RequestMapping(value = "/shopSalesDetail",method = RequestMethod.GET)
    public ModelAndView shopSalesDetail(){
        return new ModelAndView("pos/salesOrder");
    }

    @RequestMapping(value = "/scanBarcode",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> scanBarcode(String barcode)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            map = posService.scanBarcode(barcode);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return map;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> save(@RequestBody List<PosInfo> posInfoList)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            map = posService.save(posInfoList);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("status",Boolean.FALSE);
            map.put("msg","收银失败");
        }
        return map;
    }

    @RequestMapping(value = "/shopSalesDetail/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> shopSalesDetail(ShopSalesDetail shopSalesDetail,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = shopSalesDetailService.getTotal(shopSalesDetail);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            shopSalesDetail.setPageUtil(pageUtil);
            List<ShopSalesDetail> list = shopSalesDetailService.getList(shopSalesDetail);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            map.put("total",0);
            map.put("rows",null);
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 商品名细
     * @param shopSalesLine
     * @param page
     * @param rows
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/shopSalesLine/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> shopSalesLine(ShopSalesLine shopSalesLine,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = shopSalesLineService.getTotal(shopSalesLine);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            shopSalesLine.setPageUtil(pageUtil);
            List<ShopSalesLine> list = shopSalesLineService.getList(shopSalesLine);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            map.put("total",0);
            map.put("rows",null);
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 支付明细
     * @param shopSalesPay
     * @param page
     * @param rows
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/shopSalesPay/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> shopSalesPay(ShopSalesPay shopSalesPay,
                                            @RequestParam(value = "page",defaultValue = "1")int page,
                                            @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = shopSalesPayService.getTotal(shopSalesPay);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            shopSalesPay.setPageUtil(pageUtil);
            List<ShopSalesPay> list = shopSalesPayService.getList(shopSalesPay);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            map.put("total",0);
            map.put("rows",null);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/print/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getPrintDetail(ShopSalesDetail shopSalesDetail)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            List<ShopSalesDetail> listD = shopSalesDetailService.getList(shopSalesDetail);
            ShopSalesLine shopSalesLine = new ShopSalesLine();
            shopSalesLine.setPid(listD.get(0).getId());
            List<ShopSalesLine> listL = shopSalesLineService.getList(shopSalesLine);
            ShopSalesPay shopSalesPay = new ShopSalesPay();
            shopSalesPay.setPid(listD.get(0).getId());
            List<ShopSalesPay> listP = shopSalesPayService.getList(shopSalesPay);
            map.put("listD",listD);
            map.put("listL",listL);
            map.put("listP",listP);
        }catch (Exception e){
            map.put("listD",null);
            map.put("listL",null);
            map.put("listP",null);
            e.printStackTrace();
            logger.error(e);
        }
        return map;
    }
}
