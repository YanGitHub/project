package kj.pos.dao.mysql.product;

import kj.pos.entity.product.Category;
import kj.pos.util.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface CategoryDao{
    public Integer getTotal(Category category);
	public List<Category> getList(Category category);
	public void create(Category category);
	public void update(Category category);
    public Integer getCode(@Param(value = "code")String code,@Param(value = "id")Long id);
}