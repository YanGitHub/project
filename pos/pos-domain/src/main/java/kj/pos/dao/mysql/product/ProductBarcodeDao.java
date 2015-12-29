package kj.pos.dao.mysql.product;

import kj.pos.entity.product.ProductBarcode;
import kj.pos.util.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisRepository
public interface ProductBarcodeDao{
    public Integer getTotal(ProductBarcode productBarcode);
	public List<ProductBarcode> getList(ProductBarcode productBarcode);
	public void create();
	public void update(ProductBarcode productBarcode);
    public void delete(String idArray[]);
    public Integer getBarcode(ProductBarcode productBarcode);
}