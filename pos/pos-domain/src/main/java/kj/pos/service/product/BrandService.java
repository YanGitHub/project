package kj.pos.service.product;

import kj.pos.dao.mysql.product.BrandDao;
import kj.pos.entity.product.Brand;
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
@Service("brandService")
public class BrandService {

    Log logger = LogFactory.getLog(BrandService.class);

    @Autowired
    private BrandDao brandDao;

    public Integer getTotal(Brand brand)throws SQLException{
        return brandDao.getTotal(brand);
    }

    public List<Brand> getList(Brand brand)throws SQLException{
        return brandDao.getList(brand);
    }

    public Map<String,Object> add(Brand brand)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        Integer n = brandDao.getCode(brand.getCode(),null);
        if(n > 0){
            map.put("status",Boolean.FALSE);
            map.put("msg","此品牌代码已经存在");
        }else{
            brandDao.create(brand);
            map.put("status",Boolean.TRUE);
            map.put("msg","添加成功");
        }
        return map;
    }
}
