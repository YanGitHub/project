package kj.pos.entity.stock;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;
import java.io.Serializable;
import java.util.List;

@Alias("PurchaseOrderMain")
public class PurchaseOrderMain extends BaseEntity implements Serializable{
	//创建字段
	private Long id;//id
	private String billNo;//单据编号
	private String billDate;//单据日期
	private String purchaseTypeCode;//采购类型(purchase_type code)
    private String purchaseTypeName;
	private String orgCode;//店铺代码(organization_info code)
    private String orgName;
	private String warehouseCode;//采购店仓(warehouse code)
    private String warehouseName;
	private String supplierInfoCode;//供应商(supplier_info code)
    private String supplierInfoName;
	private Integer status;//1 未审核 2 业务审核 3 终止
    private String statusStr;
	private String createUser;//创建人
	private String createDate;//创建日期
	private String modifyUser;//修改人
	private String modifyDate;//修改日期
	private String auditUser;//业务审核人
	private String auditDate;//业务审核日期
	private String cancelUser;//终止人
	private String cancelDate;//终止日期
	private String note;//备注

    private String beginDate;
    private String endDate;

    private List<PurchaseOrderDetail> purchaseOrderDetailList;
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

    public String getPurchaseTypeCode() {
        return purchaseTypeCode;
    }

    public void setPurchaseTypeCode(String purchaseTypeCode) {
        this.purchaseTypeCode = purchaseTypeCode;
    }

    public String getPurchaseTypeName() {
        return purchaseTypeName;
    }

    public void setPurchaseTypeName(String purchaseTypeName) {
        this.purchaseTypeName = purchaseTypeName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getSupplierInfoCode() {
        return supplierInfoCode;
    }

    public void setSupplierInfoCode(String supplierInfoCode) {
        this.supplierInfoCode = supplierInfoCode;
    }

    public String getSupplierInfoName() {
        return supplierInfoName;
    }

    public void setSupplierInfoName(String supplierInfoName) {
        this.supplierInfoName = supplierInfoName;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<PurchaseOrderDetail> getPurchaseOrderDetailList() {
        return purchaseOrderDetailList;
    }

    public void setPurchaseOrderDetailList(List<PurchaseOrderDetail> purchaseOrderDetailList) {
        this.purchaseOrderDetailList = purchaseOrderDetailList;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
}