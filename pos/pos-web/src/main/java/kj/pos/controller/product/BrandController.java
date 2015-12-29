package kj.pos.controller.product;

import kj.pos.dao.mysql.product.BrandDao;
import kj.pos.entity.PageUtil;
import kj.pos.entity.product.Brand;
import kj.pos.service.product.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 15-12-24.
 */
@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String brand(){
        return "product/brand";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(Brand brand,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = brandService.getTotal(brand);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            brand.setPageUtil(pageUtil);
            List<Brand> list = brandService.getList(brand);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            map.put("total",0);
            map.put("rows",null);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> add(@RequestBody Brand brand){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            map = brandService.add(brand);
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",Boolean.FALSE);
            map.put("msg","添加失败");
        }
        return map;
    }
}
