package kj.pos.dao.mysql.admin;

import kj.pos.entity.admin.Role;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface RoleDao{

	public List<Role> getList(Role role);

    public Integer getTotal(Role role);

	public void create(Role role);

	public void update(Role role);

}