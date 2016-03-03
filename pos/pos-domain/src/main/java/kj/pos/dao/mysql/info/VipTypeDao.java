package kj.pos.dao.mysql.info;

import kj.pos.entity.info.VipType;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface VipTypeDao{

    public Integer getTotal(VipType vipType);

	public List<VipType> getList(VipType vipType);

	public void create(VipType vipType);

	public void update(VipType vipType);
}