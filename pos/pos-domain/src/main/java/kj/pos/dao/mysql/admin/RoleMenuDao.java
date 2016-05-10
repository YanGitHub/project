package kj.pos.dao.mysql.admin;

import kj.pos.entity.admin.RoleMenu;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface RoleMenuDao{

	public List<RoleMenu> getList(RoleMenu roleMenu);

	public void create(RoleMenu roleMenu);

	public void update(RoleMenu roleMenu);

    public void delete(RoleMenu roleMenu);
}