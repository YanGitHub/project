package kj.pos.dao.mysql.product;


import kj.pos.entity.product.Brand;
import kj.pos.util.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface BrandDao{
    public Integer getTotal(Brand brand);
	public List<Brand> getList(Brand brand);
	public void create(Brand brand);
	public void update(Brand brand);
    public Integer getCode(@Param(value = "code")String code,@Param(value = "id")Long id);
}