package kj.pos.dao.mysql.pos;

import kj.pos.entity.pos.ShopPayment;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;
@MyBatisRepository
public interface ShopPaymentDao{

	public List<ShopPayment> getList(ShopPayment shopPayment);

    public Integer getTotal(ShopPayment shopPayment);

	public void create(ShopPayment shopPayment);

	public void update(ShopPayment shopPayment);

}