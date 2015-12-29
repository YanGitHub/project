package kj.pos.entity.pos;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import java.io.Serializable;

@Alias("ShopSalesPay")
public class ShopSalesPay extends BaseEntity{
	//创建字段
	private Long id;//id
	private Long pid;//小票id:门店销售明细表(shop_sales_detail id)
	private String flowNo;//小票号：门店销售明细表(shop_sales_detail flow_no)
	private Long payId;//支付ID(shop_payment id)
	private String payNo;//支付代码(shop_payment code)
    private String payName;//支付名称
	private Double amount;//支付金额
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

	public String getFlowNo(){
		return this.flowNo;
	}

	public void setFlowNo(String flowNo){
		this.flowNo = flowNo;
	}

	public Long getPayId(){
		return this.payId;
	}

	public void setPayId(Long payId){
		this.payId = payId;
	}

	public String getPayNo(){
		return this.payNo;
	}

	public void setPayNo(String payNo){
		this.payNo = payNo;
	}

	public Double getAmount(){
		return this.amount;
	}

	public void setAmount(Double amount){
		this.amount = amount;
	}

	public String getNote(){
		return this.note;
	}

	public void setNote(String note){
		this.note = note;
	}

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }
}