package kj.pos.entity.pos;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

/**
 * Created by Yan on 16-4-28.
 */
@Alias("ShopBookDetail")
public class ShopBookDetail extends BaseEntity {
    //创建字段
    private Long id;//id
    private String flowNo;//小票号
    private String saleDate;//销售日期 到时分秒
    private String vipNo;//会员卡 会员资料(vip_info code)
    private Long cashId;//收银员(employee_info id)
    private Boolean isOnline;//是否网上订单 0 否 1是
    private String isOnlineName;
    private Integer saleType;//1 销售 2 退货 3 补单
    private String saleTypeName;
    private Double fundAmount;//找零
    private String note;//备注
    private String createUser;//创建人
    private String createDate;//创建日期
    private String modifyUser;//修改人
    private String modifyDate;//修改日期
    //创建getter和setter方法

    private String startDate;
    private String endDate;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getFlowNo(){
        return this.flowNo;
    }

    public void setFlowNo(String flowNo){
        this.flowNo = flowNo;
    }

    public String getVipNo(){
        return this.vipNo;
    }

    public void setVipNo(String vipNo){
        this.vipNo = vipNo;
    }

    public Long getCashId(){
        return this.cashId;
    }

    public void setCashId(Long cashId){
        this.cashId = cashId;
    }

    public Boolean getIsOnline(){
        return this.isOnline;
    }

    public void setIsOnline(Boolean isOnline){
        this.isOnline = isOnline;
    }

    public Integer getSaleType(){
        return this.saleType;
    }

    public void setSaleType(Integer saleType){
        this.saleType = saleType;
    }

    public Double getFundAmount(){
        return this.fundAmount;
    }

    public void setFundAmount(Double fundAmount){
        this.fundAmount = fundAmount;
    }

    public String getNote(){
        return this.note;
    }

    public void setNote(String note){
        this.note = note;
    }

    public String getCreateUser(){
        return this.createUser;
    }

    public void setCreateUser(String createUser){
        this.createUser = createUser;
    }

    public String getModifyUser(){
        return this.modifyUser;
    }

    public void setModifyUser(String modifyUser){
        this.modifyUser = modifyUser;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getSaleTypeName() {
        return saleTypeName;
    }

    public void setSaleTypeName(String saleTypeName) {
        this.saleTypeName = saleTypeName;
    }

    public String getIsOnlineName() {
        return isOnlineName;
    }

    public void setIsOnlineName(String isOnlineName) {
        this.isOnlineName = isOnlineName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
