package kj.pos.dao.mysql.admin;

import kj.pos.entity.admin.UserInfo;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;
@MyBatisRepository
public interface UserInfoDao{

    public Integer getTotal(UserInfo userInfo);

	public List<UserInfo> getList(UserInfo userInfo);

	public void create(UserInfo userInfo);

	public void update(UserInfo userInfo);

    public Integer getCountByCodyAndId(UserInfo userInfo);

    public void status(UserInfo userInfo);
}