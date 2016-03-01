package kj.pos.dao.mysql.info;

import kj.pos.entity.info.VipInfo;
import kj.pos.util.mybatis.MyBatisRepository;
import java.util.List;

@MyBatisRepository
public interface VipInfoDao{

	public List<VipInfo> getList(VipInfo vipInfo);

    public Integer getTotal(VipInfo vipInfo);

	public void create(VipInfo vipInfo);

	public void update(VipInfo vipInfo);
}