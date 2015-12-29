package kj.pos.dao.mysql.pos;

import kj.pos.entity.pos.ShopSalesLine;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;
@MyBatisRepository
public interface ShopSalesLineDao{

    public List<ShopSalesLine> getList(ShopSalesLine shopSalesLine);

    public Integer getTotal(ShopSalesLine shopSalesLine);

	public void create(List<ShopSalesLine> list);

}