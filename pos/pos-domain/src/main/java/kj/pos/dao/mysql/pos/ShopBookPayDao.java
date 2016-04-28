package kj.pos.dao.mysql.pos;

import kj.pos.entity.pos.ShopBookPay;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

/**
 * Created by Yan on 16-4-28.
 */
@MyBatisRepository
public interface ShopBookPayDao {

    public Integer getTotal(ShopBookPay shopBookPay);

    public List<ShopBookPay> getList(ShopBookPay shopBookPay);

    public void create(List<ShopBookPay> list);

    public void update(ShopBookPay shopBookPay);
}
