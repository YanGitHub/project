package kj.pos.entity.product;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import java.io.Serializable;

@Alias("ProductBarcode")
public class ProductBarcode extends BaseEntity{
	//创建字段
	private Integer id;//
	private Date createDate;//创建日期
	private Date modifyDate;//修改日期
	private String barcode;//商品条码
	private String productCode;//商品代码
    private String productName;//商品名称
	private String skuCode;//规格代码
    private String skuName;//规格名称
	private Boolean del;//是否删除 0 未删除 1删除
	private String note;//备注
    private String idArray;
	//创建getter和setter方法
	public Integer getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Date getCreateDate(){
		return this.createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public Date getModifyDate(){
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate){
		this.modifyDate = modifyDate;
	}

	public String getBarcode(){
		return this.barcode;
	}

	public void setBarcode(String barcode){
		this.barcode = barcode;
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

	public Boolean getDel(){
		return this.del;
	}

	public void setDel(Boolean del){
		this.del = del;
	}

	public String getNote(){
		return this.note;
	}

	public void setNote(String note){
		this.note = note;
	}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getIdArray() {
        return idArray;
    }

    public void setIdArray(String idArray) {
        this.idArray = idArray;
    }
}