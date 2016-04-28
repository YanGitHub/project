package kj.pos.dao.mysql.pos;

import kj.pos.entity.pos.ShopBookDetail;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

/**
 * Created by Yan on 16-4-28.
 */
@MyBatisRepository
public interface ShopBookDetailDao {

    public Integer getTotal(ShopBookDetail shopBookDetail);

    public List<ShopBookDetail> getList(ShopBookDetail shopBookDetail);

    public void create(ShopBookDetail shopBookDetail);


}
