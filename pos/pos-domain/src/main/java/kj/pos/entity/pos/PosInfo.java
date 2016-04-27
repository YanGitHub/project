package kj.pos.entity.pos;

import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * Created by Yan on 15-12-16.
 */
@Alias("PosInfo")
public class PosInfo {
    //收银时间
    private String saleDate;
    //小票号
    private String flowNo;
    //条码
    private String barcode;
    //skucode
    private Long skuId;
    private String skuCode;
    private String skuName;
    //商品代码
    private String productCode;
    private String productName;
    //单价
    private Double price;
    //数量
    private Double qty;
    //折扣
    private Double discount;
    //实售价
    private Double relPrice;
    //金额
    private Double amount;
    //找零
    private Double change;

    //赠品
    private Boolean isGift;
    //导购员代码
    private String employeeCode;
    ///////////////////////支付方式
    private List<ShopPayment> paymentList;

    ///////////////////////会员信息
    //会员代码
    private String vipCode;

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getRelPrice() {
        return relPrice;
    }

    public void setRelPrice(Double relPrice) {
        this.relPrice = relPrice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public String getVipCode() {
        return vipCode;
    }

    public void setVipCode(String vipCode) {
        this.vipCode = vipCode;
    }

    public List<ShopPayment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<ShopPayment> paymentList) {
        this.paymentList = paymentList;
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

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Boolean getIsGift() {
        return isGift;
    }

    public void setIsGift(Boolean isGift) {
        this.isGift = isGift;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
}
