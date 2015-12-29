package kj.pos.dao.mysql.admin;


import kj.pos.entity.admin.SysParameters;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface SysParametersDao{

    public Integer getTotal(SysParameters sysParameters);

	public List<SysParameters> getList(SysParameters sysParameters);

	public void create(SysParameters sysParameters);

	public void update(SysParameters sysParameters);

    public void status(SysParameters sysParameters);
}