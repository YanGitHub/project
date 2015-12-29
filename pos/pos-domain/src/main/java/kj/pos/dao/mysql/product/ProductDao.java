package kj.pos.dao.mysql.product;

import kj.pos.entity.product.ProductInfo;
import kj.pos.entity.product.ProductSku;
import kj.pos.util.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;
import org.eclipse.core.internal.runtime.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 15-9-18.
 */
@MyBatisRepository
public interface ProductDao {

    public Integer getTotal(ProductInfo productInfo)throws SQLException;

    public List<ProductInfo> getList(ProductInfo productInfo)throws SQLException;

    public Integer getCountByCode(@Param(value = "code") String code)throws SQLException;

    public void add(ProductInfo productInfo)throws SQLException;

    public void addMx(List<ProductSku> list)throws SQLException;

    public void update(ProductInfo productInfo)throws SQLException;
}
