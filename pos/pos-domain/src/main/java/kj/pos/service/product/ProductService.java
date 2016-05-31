package kj.pos.service.product;

import com.googlecode.ehcache.annotations.Cacheable;
import kj.pos.dao.mysql.product.ProductDao;
import kj.pos.entity.product.ProductInfo;
import kj.pos.entity.product.ProductSku;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 15-9-18.
 */
@Service("productService")
public class ProductService {

    Log logger = LogFactory.getLog(ProductService.class);
    @Autowired
    private ProductDao productDao;

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> add(ProductInfo productInfo)throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        Integer num = productDao.getCountByCode(productInfo.getCode());
        if(num > 0){
            map.put("status",Boolean.FALSE);
            map.put("msg","此商品代码已经存在");
            return map;
        }
        productDao.add(productInfo);
        Long id = productInfo.getId();
        List<ProductSku> list = productInfo.getProductSkuList();
        List<ProductSku> param = new ArrayList<ProductSku>();
        ProductSku productSku = new ProductSku();
        for(ProductSku p : list){
            productSku = p;
            productSku.setPid(id);
            productSku.setProductCode(productInfo.getCode());
            param.add(productSku);
        }
        productDao.addMx(param);
        map.put("status",Boolean.TRUE);
        map.put("msg","添加成功");
        return map;
    }

    /**
     * 加入ehcache
     * @param productInfo
     * @return Integer
     * @throws SQLException
     */
    @Cacheable(cacheName="userCache")
    public Integer getTotal(ProductInfo productInfo)throws SQLException{
        return productDao.getTotal(productInfo);
    }
    /**
     * 加入ehcache
     * @param productInfo
     * @return List<ProductInfo>
     * @throws SQLException
     */
    @Cacheable(cacheName="userCache")
    public List<ProductInfo> getList(ProductInfo productInfo)throws SQLException{
        return productDao.getList(productInfo);
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public void update(ProductInfo productInfo)throws SQLException{
        productDao.update(productInfo);
    }
}
