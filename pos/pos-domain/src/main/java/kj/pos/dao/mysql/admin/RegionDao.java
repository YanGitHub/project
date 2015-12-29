package kj.pos.dao.mysql.admin;


import kj.pos.entity.info.Region;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface RegionDao{

	public List<Region> getList(Region region);


}