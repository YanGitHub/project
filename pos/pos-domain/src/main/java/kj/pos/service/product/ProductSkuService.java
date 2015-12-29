package kj.pos.service.product;


import kj.pos.dao.mysql.product.ProductSkuDao;
import kj.pos.entity.product.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 15-12-3.
 */
@Service("productSkuService")
public class ProductSkuService {

    @Autowired
    private ProductSkuDao productSkuDao;

    public List<ProductSku> getList(ProductSku productSku)throws SQLException{
        return productSkuDao.getList(productSku);
    }

    public Integer getTotal(ProductSku productSku)throws SQLException{
        return productSkuDao.getTotal(productSku);
    }

}
