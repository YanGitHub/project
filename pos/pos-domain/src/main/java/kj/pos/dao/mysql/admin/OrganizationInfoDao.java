package kj.pos.dao.mysql.admin;

import kj.pos.entity.admin.OrganizationInfo;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface OrganizationInfoDao{
    public Integer getTotal(OrganizationInfo organizationInfo);
	public List<OrganizationInfo> getList(OrganizationInfo organizationInfo);
	public void create(OrganizationInfo organizationInfo);
	public void update(OrganizationInfo organizationInfo);
}