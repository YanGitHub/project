package kj.pos.entity.stock;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

@Alias("Inventory")
public class Inventory extends BaseEntity{
	//创建字段
	private Long id;//
	private String createDate;//创建日期
	private String modifyDate;//修改日期
	private String warehouseCode;//仓库代码
	private String productCode;//商品代码
	private String skuCode;//规格代码
	private Double qty;//数量
	private Boolean del;//是否删除
	//创建getter和setter方法
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getCreateDate(){
		return this.createDate;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	public String getModifyDate(){
		return this.modifyDate;
	}

	public void setModifyDate(String modifyDate){
		this.modifyDate = modifyDate;
	}

	public String getWarehouseCode(){
		return this.warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode){
		this.warehouseCode = warehouseCode;
	}

	public String getProductCode(){
		return this.productCode;
	}

	public void setProductCode(String productCode){
		this.productCode = productCode;
	}

	public String getSkuCode(){
		return this.skuCode;
	}

	public void setSkuCode(String skuCode){
		this.skuCode = skuCode;
	}

	public Double getQty(){
		return this.qty;
	}

	public void setQty(Double qty){
		this.qty = qty;
	}

	public Boolean getDel(){
		return this.del;
	}

	public void setDel(Boolean del){
		this.del = del;
	}

}