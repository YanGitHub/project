package kj.pos.entity.stock;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("PurchaseEntryMain")
public class PurchaseEntryMain extends BaseEntity{
	//创建字段
	private Long id;//id
	private String billNo;//单据编号
	private String billDate;//单据日期 2014-11-13
	private String purchaseEntryTypeCode;//入库类型(purchase_entry_type code)
    private String purchaseEntryTypeName;
	private String orgCode;//店铺代码(organization_info code)
    private String orgName;
	private String warehouseCode;//采购店仓(warehouse code)
    private String warehouseName;
	private String supplierInfoCode;//供应商(supplier_info code)
    private String supplierInfoName;
	private Long purchaseOrderMainId;//采购订单(purchase_order_main id)
	private Integer status;//1 未审核 2 终止 3 已审核 4 已入库 5已记帐
    private String statusString;
	private String createUser;//创建人
	private String createDate;//创建日期
	private String modifyUser;//修改人
	private String modifyDate;//修改日期
	private String auditUser;//业务审核人
	private String auditDate;//业务审核日期
	private String faAuditUser;//入库人
	private String faAuditDate;//入库日期
	private String cancelUser;//终止人
	private String cancelDate;//终止日期
	private String accountUser;//记账人
	private String accountDate;//记账日期
	private String note;//备注

    private List<PurchaseEntryDetail> purchaseEntryDetailList;
	//创建getter和setter方法


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getPurchaseEntryTypeCode() {
        return purchaseEntryTypeCode;
    }

    public void setPurchaseEntryTypeCode(String purchaseEntryTypeCode) {
        this.purchaseEntryTypeCode = purchaseEntryTypeCode;
    }

    public String getPurchaseEntryTypeName() {
        return purchaseEntryTypeName;
    }

    public void setPurchaseEntryTypeName(String purchaseEntryTypeName) {
        this.purchaseEntryTypeName = purchaseEntryTypeName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getSupplierInfoName() {
        return supplierInfoName;
    }

    public void setSupplierInfoName(String supplierInfoName) {
        this.supplierInfoName = supplierInfoName;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getSupplierInfoCode() {
        return supplierInfoCode;
    }

    public void setSupplierInfoCode(String supplierInfoCode) {
        this.supplierInfoCode = supplierInfoCode;
    }

    public Long getPurchaseOrderMainId() {
        return purchaseOrderMainId;
    }

    public void setPurchaseOrderMainId(Long purchaseOrderMainId) {
        this.purchaseOrderMainId = purchaseOrderMainId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getFaAuditUser() {
        return faAuditUser;
    }

    public void setFaAuditUser(String faAuditUser) {
        this.faAuditUser = faAuditUser;
    }

    public String getFaAuditDate() {
        return faAuditDate;
    }

    public void setFaAuditDate(String faAuditDate) {
        this.faAuditDate = faAuditDate;
    }

    public String getCancelUser() {
        return cancelUser;
    }

    public void setCancelUser(String cancelUser) {
        this.cancelUser = cancelUser;
    }

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(String accountUser) {
        this.accountUser = accountUser;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<PurchaseEntryDetail> getPurchaseEntryDetailList() {
        return purchaseEntryDetailList;
    }

    public void setPurchaseEntryDetailList(List<PurchaseEntryDetail> purchaseEntryDetailList) {
        this.purchaseEntryDetailList = purchaseEntryDetailList;
    }
}