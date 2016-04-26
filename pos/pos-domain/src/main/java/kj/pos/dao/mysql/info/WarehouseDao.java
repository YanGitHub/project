package kj.pos.dao.mysql.info;

import kj.pos.entity.info.Warehouse;
import kj.pos.util.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface WarehouseDao{

    public Integer getTotal(Warehouse warehouse);

	public List<Warehouse> getList(Warehouse warehouse);

	public void create(Warehouse warehouse);

	public void update(Warehouse warehouse);

    public Integer getCode(@Param(value = "code")String code,@Param(value = "id")Long id);

    public void setDeliver(Warehouse warehouse);

    public void updateSetDeliver(Warehouse warehouse);

    public void setReceive(Warehouse warehouse);

    public void updateSetReceive(Warehouse warehouse);

    public Warehouse getWarehouseByOrg(Warehouse warehouse);
}