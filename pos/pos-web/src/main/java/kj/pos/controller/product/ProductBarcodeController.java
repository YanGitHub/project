package kj.pos.controller.product;

import kj.pos.entity.PageUtil;
import kj.pos.entity.product.ProductBarcode;
import kj.pos.service.product.ProductBarcodeService;
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
 * Created by Yan on 15-12-9.
 */
@Controller
@RequestMapping(value = "/product/barcode")
public class ProductBarcodeController {

    Log logger = LogFactory.getLog(ProductBarcodeController.class);

    @Autowired
    private ProductBarcodeService productBarcodeService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String barcode(){
        return "product/productBarcode";
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(ProductBarcode productBarcode,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = productBarcodeService.getTotal(productBarcode);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            productBarcode.setPageUtil(pageUtil);
            List<ProductBarcode> list = productBarcodeService.getList(productBarcode);
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
    public Map<String,Object> create(ProductBarcode productBarcode)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            productBarcodeService.create(productBarcode);
            map.put("status",Boolean.TRUE);
            map.put("msg","条码生成成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("status",Boolean.FALSE);
            map.put("msg","条码生成失败");
        }
        return map;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delete(ProductBarcode productBarcode)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            productBarcodeService.delete(productBarcode);
            map.put("status",Boolean.TRUE);
            map.put("msg","删除成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("status",Boolean.FALSE);
            map.put("msg","删除失败");
        }
        return map;
    }

    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modify(ProductBarcode productBarcode)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            map = productBarcodeService.modify(productBarcode);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("status",Boolean.FALSE);
            map.put("msg","修改失败");
        }
        return map;
    }
}
