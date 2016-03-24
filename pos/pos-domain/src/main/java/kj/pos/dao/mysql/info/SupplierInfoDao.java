package kj.pos.dao.mysql.info;


import kj.pos.entity.info.SupplierInfo;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface SupplierInfoDao{

    public Integer getTotal(SupplierInfo supplierInfo);

	public List<SupplierInfo> getList(SupplierInfo supplierInfo);

	public void create(SupplierInfo supplierInfo);

	public void update(SupplierInfo supplierInfo);

    public Integer getCode(SupplierInfo supplierInfo);
}