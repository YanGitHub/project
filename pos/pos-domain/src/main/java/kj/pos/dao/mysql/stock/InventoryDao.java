package kj.pos.dao.mysql.stock;

import kj.pos.entity.stock.Inventory;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface InventoryDao{

    public Integer getTotal(Inventory inventory);

	public List<Inventory> getList(Inventory inventory);

	public void create(Inventory inventory);

	public void update(Inventory inventory);
}