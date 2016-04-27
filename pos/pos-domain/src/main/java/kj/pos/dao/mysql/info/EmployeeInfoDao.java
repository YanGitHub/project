package kj.pos.dao.mysql.info;

import kj.pos.entity.info.EmployeeInfo;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface EmployeeInfoDao{

	public List<EmployeeInfo> getList(EmployeeInfo employeeInfo);

	public void create(EmployeeInfo employeeInfo);

	public void update(EmployeeInfo employeeInfo);

    public Integer getTotal(EmployeeInfo employeeInfo);
}