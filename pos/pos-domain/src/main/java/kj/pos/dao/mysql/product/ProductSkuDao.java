package kj.pos.dao.mysql.product;


import kj.pos.entity.product.ProductSku;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;
@MyBatisRepository
public interface ProductSkuDao{

	public List<ProductSku> getList(ProductSku productSku);

    public Integer getTotal(ProductSku productSku);

	public void create(ProductSku productSku);

	public void update(ProductSku productSku);
}