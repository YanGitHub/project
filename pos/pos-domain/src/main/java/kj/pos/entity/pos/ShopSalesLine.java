package kj.pos.entity.pos;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import java.io.Serializable;

@Alias("ShopSalesLine")
public class ShopSalesLine extends BaseEntity{
	//创建字段
	private Long id;//id
	private Long pid;//门店销售明细表(shop_sales_detail id)
    private Long skuId;
	private String skuCode;//商品明细表(product_sku code)
    private String skuName;
    private String productCode;//商品代码
    private String productName;
	private Long saleId;//导购员(employee_info id)
	private Double untPrice;//标准单价
	private Double realPrice;//实售价
	private Double saleDiscount;//销售折扣
	private Double qty;//销售数量
	private Double disAmount;//折扣金额
	private Double realAmount;//实售金额
	private Boolean isGift;//是否赠品 0 否 1是
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

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuCode(){
		return this.skuCode;
	}

	public void setSkuCode(String skuCode){
		this.skuCode = skuCode;
	}

	public Long getSaleId(){
		return this.saleId;
	}

	public void setSaleId(Long saleId){
		this.saleId = saleId;
	}

	public Double getUntPrice(){
		return this.untPrice;
	}

	public void setUntPrice(Double untPrice){
		this.untPrice = untPrice;
	}

	public Double getRealPrice(){
		return this.realPrice;
	}

	public void setRealPrice(Double realPrice){
		this.realPrice = realPrice;
	}

	public Double getSaleDiscount(){
		return this.saleDiscount;
	}

	public void setSaleDiscount(Double saleDiscount){
		this.saleDiscount = saleDiscount;
	}

	public Double getQty(){
		return this.qty;
	}

	public void setQty(Double qty){
		this.qty = qty;
	}

	public Double getDisAmount(){
		return this.disAmount;
	}

	public void setDisAmount(Double disAmount){
		this.disAmount = disAmount;
	}

	public Double getRealAmount(){
		return this.realAmount;
	}

	public void setRealAmount(Double realAmount){
		this.realAmount = realAmount;
	}

	public Boolean getIsGift(){
		return this.isGift;
	}

	public void setIsGift(Boolean isGift){
		this.isGift = isGift;
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

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}