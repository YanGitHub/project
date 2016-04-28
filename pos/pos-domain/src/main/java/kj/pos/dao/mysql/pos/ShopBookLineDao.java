package kj.pos.dao.mysql.pos;

import kj.pos.entity.pos.ShopBookLine;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

/**
 * Created by Yan on 16-4-28.
 */
@MyBatisRepository
public interface ShopBookLineDao {

    public List<ShopBookLine> getList(ShopBookLine shopBookLine);

    public Integer getTotal(ShopBookLine shopBookLine);

    public void create(List<ShopBookLine> list);

}
