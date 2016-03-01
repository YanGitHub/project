package kj.pos.dao.mysql.info;

import kj.pos.entity.info.ExpressCompany;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface ExpressCompanyDao{
    public Integer getTotal(ExpressCompany expressCompany);

	public List<ExpressCompany> getList(ExpressCompany expressCompany);

	public void create(ExpressCompany expressCompany);

	public void update(ExpressCompany expressCompany);
}