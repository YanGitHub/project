package kj.pos.entity.stock;

import org.apache.ibatis.type.Alias;
import java.io.Serializable;

@Alias("PurchaseOrderDetail")
public class PurchaseOrderDetail implements Serializable{
	//创建字段
	private Long id;//id
	private Long pid;//采购订单主表(purchase_order_main id)
    private String productCode;
    private String productName;
	private Long productSkuId;//商品明细表(product_sku id)
	private String skuCode;//商品明细表(product_sku code)
    private String skuName;
	private Double untPrice;//标准单价
	private Double discount;//折扣
	private Double cosPrice;//入库单价
	private Double qty;//采购数量
	private Double untAmount;//标准金额=采购数量*标准单价
	private Double comAmount;//采购金额=采购数量*采购单价
	private String note;//备注
	//创建getter和setter方法
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getPid(){
		return this.pid;
	}

	public void setPid(Long pid){
		this.pid = pid;
	}

	public Long getProductSkuId(){
		return this.productSkuId;
	}

	public void setProductSkuId(Long productSkuId){
		this.productSkuId = productSkuId;
	}

	public String getSkuCode(){
		return this.skuCode;
	}

	public void setSkuCode(String skuCode){
		this.skuCode = skuCode;
	}

	public Double getUntPrice(){
		return this.untPrice;
	}

	public void setUntPrice(Double untPrice){
		this.untPrice = untPrice;
	}

	public Double getDiscount(){
		return this.discount;
	}

	public void setDiscount(Double discount){
		this.discount = discount;
	}

	public Double getCosPrice(){
		return this.cosPrice;
	}

	public void setCosPrice(Double cosPrice){
		this.cosPrice = cosPrice;
	}

	public Double getQty(){
		return this.qty;
	}

	public void setQty(Double qty){
		this.qty = qty;
	}

	public Double getUntAmount(){
		return this.untAmount;
	}

	public void setUntAmount(Double untAmount){
		this.untAmount = untAmount;
	}

	public Double getComAmount(){
		return this.comAmount;
	}

	public void setComAmount(Double comAmount){
		this.comAmount = comAmount;
	}

	public String getNote(){
		return this.note;
	}

	public void setNote(String note){
		this.note = note;
	}

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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
}