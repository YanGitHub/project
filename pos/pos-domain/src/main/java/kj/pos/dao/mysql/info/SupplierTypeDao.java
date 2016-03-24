package kj.pos.dao.mysql.info;

import kj.pos.entity.info.SupplierType;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface SupplierTypeDao{

    public Integer getTotal(SupplierType supplierType);

	public List<SupplierType> getList(SupplierType supplierType);

	public void create(SupplierType supplierType);

	public void update(SupplierType supplierType);
}