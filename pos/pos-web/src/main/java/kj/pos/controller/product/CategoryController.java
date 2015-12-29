package kj.pos.controller.product;

import kj.pos.entity.PageUtil;
import kj.pos.entity.product.Category;
import kj.pos.service.product.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String category(){
        return "product/category";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(Category category,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = categoryService.getTotal(category);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            category.setPageUtil(pageUtil);
            List<Category> list = categoryService.getList(category);
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
    public Map<String,Object> add(@RequestBody Category category){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            map = categoryService.add(category);
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",Boolean.FALSE);
            map.put("msg","添加失败");
        }
        return map;
    }

}
