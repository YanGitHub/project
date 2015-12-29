package kj.pos.service.product;

import kj.pos.dao.mysql.product.CategoryDao;
import kj.pos.entity.product.Brand;
import kj.pos.entity.product.Category;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 15-12-24.
 */
@Service("categoryService")
public class CategoryService {

    Log logger = LogFactory.getLog(CategoryService.class);

    @Autowired
    private CategoryDao categoryDao;

    public Integer getTotal(Category category)throws SQLException {
        return categoryDao.getTotal(category);
    }

    public List<Category> getList(Category category)throws SQLException{
        return categoryDao.getList(category);
    }

    public Map<String,Object> add(Category category)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        Integer n = categoryDao.getCode(category.getCode(),null);
        if(n > 0){
            map.put("status",Boolean.FALSE);
            map.put("msg","此商品类别代码已经存在");
        }else{
            categoryDao.create(category);
            map.put("status",Boolean.TRUE);
            map.put("msg","添加成功");
        }
        return map;
    }
}
