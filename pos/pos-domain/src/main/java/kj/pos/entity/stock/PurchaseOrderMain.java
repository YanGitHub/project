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
	private String orgCode;//店铺代码(organization_info code)
	private String warehouseCode;//采购店仓(warehouse code)
	private String supplierInfoCode;//供应商(supplier_info code)
	private Integer status;//1 未审核 2 业务审核 3 终止
	private String createUser;//创建人
	private String createDate;//创建日期
	private String modifyUser;//修改人
	private String modifyDate;//修改日期
	private String auditUser;//业务审核人
	private String auditDate;//业务审核日期
	private String cancelUser;//终止人
	private String cancelDate;//终止日期
	private String note;//备注

    private List<PurchaseOrderDetail> purchaseOrderDetailList;
	//创建getter和setter方法
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getBillNo(){
		return this.billNo;
	}

	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	public String getBillDate(){
		return this.billDate;
	}

	public void setBillDate(String billDate){
		this.billDate = billDate;
	}

	public String getPurchaseTypeCode(){
		return this.purchaseTypeCode;
	}

	public void setPurchaseTypeCode(String purchaseTypeCode){
		this.purchaseTypeCode = purchaseTypeCode;
	}

	public String getOrgCode(){
		return this.orgCode;
	}

	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
	}

	public String getWarehouseCode(){
		return this.warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode){
		this.warehouseCode = warehouseCode;
	}

	public String getSupplierInfoCode(){
		return this.supplierInfoCode;
	}

	public void setSupplierInfoCode(String supplierInfoCode){
		this.supplierInfoCode = supplierInfoCode;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public String getCreateUser(){
		return this.createUser;
	}

	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}

	public String getCreateDate(){
		return this.createDate;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	public String getModifyUser(){
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser){
		this.modifyUser = modifyUser;
	}

	public String getModifyDate(){
		return this.modifyDate;
	}

	public void setModifyDate(String modifyDate){
		this.modifyDate = modifyDate;
	}

	public String getAuditUser(){
		return this.auditUser;
	}

	public void setAuditUser(String auditUser){
		this.auditUser = auditUser;
	}

	public String getAuditDate(){
		return this.auditDate;
	}

	public void setAuditDate(String auditDate){
		this.auditDate = auditDate;
	}

	public String getCancelUser(){
		return this.cancelUser;
	}

	public void setCancelUser(String cancelUser){
		this.cancelUser = cancelUser;
	}

	public String getCancelDate(){
		return this.cancelDate;
	}

	public void setCancelDate(String cancelDate){
		this.cancelDate = cancelDate;
	}

	public String getNote(){
		return this.note;
	}

	public void setNote(String note){
		this.note = note;
	}

    public List<PurchaseOrderDetail> getPurchaseOrderDetailList() {
        return purchaseOrderDetailList;
    }

    public void setPurchaseOrderDetailList(List<PurchaseOrderDetail> purchaseOrderDetailList) {
        this.purchaseOrderDetailList = purchaseOrderDetailList;
    }
}