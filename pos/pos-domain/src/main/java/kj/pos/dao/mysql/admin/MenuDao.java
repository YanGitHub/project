package kj.pos.dao.mysql.admin;

import kj.pos.entity.admin.Menu;
import kj.pos.util.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface MenuDao{

	public List<Menu> getList(Menu menu);

	public void create(Menu menu);

	public void update(Menu menu);

    public List<Menu> getListByPid(@Param("pid") Long pid);

    public Long getMaxNo();

}