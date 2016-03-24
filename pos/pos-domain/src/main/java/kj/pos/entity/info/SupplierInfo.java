package kj.pos.entity.info;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

@Alias("SupplierInfo")
public class SupplierInfo extends BaseEntity{
	//创建字段
	private Long id;//
	private String createDate;//创建日期
	private String modifyDate;//修改日期
	private String code;//供应商代码
	private String name;//供应商名称
	private String typeCode;//供应商类型(supplier_type code)
    private String typeName;//供应商类型名称
	private String person;//联系人
	private String phone;//手机
	private String mobile;//电话
	private String email;//邮箱
	private String website;//网址
	private String address;//地址
	private String note;//备注
	//创建getter和setter方法

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

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

	public String getCode(){
		return this.code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getTypeCode(){
		return this.typeCode;
	}

	public void setTypeCode(String typeCode){
		this.typeCode = typeCode;
	}

	public String getPerson(){
		return this.person;
	}

	public void setPerson(String person){
		this.person = person;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getMobile(){
		return this.mobile;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getWebsite(){
		return this.website;
	}

	public void setWebsite(String website){
		this.website = website;
	}

	public String getAddress(){
		return this.address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getNote(){
		return this.note;
	}

	public void setNote(String note){
		this.note = note;
	}

}