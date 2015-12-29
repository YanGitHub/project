package kj.pos.entity.admin;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;


@Alias("OrganizationInfo")
public class OrganizationInfo extends BaseEntity{
	//创建字段
	private Long id;//
	private String createDate;//创建日期
	private String modifyDate;//修改日期
	private String code;//组织代码(店铺代码)
	private String name;//组织名称(店铺名称)
	private Integer status;//状态 1可用 0禁用
	private String person;//联系人
	private String phone;//手机
	private String mobile;//电话
	private String email;//邮箱
	private String website;//网址
	private String address;//地址
	private Integer starLevel;//组织联系人
	private Integer property;//1组织(店铺) 2服务商
	private String provinceCode;//省
	private String cityCode;//市
	private String districtCode;//区
	private String note;//备注
	//创建getter和setter方法
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

	public Integer getStatus(){
		return this.status;
	}

	public void setStatus(Integer status){
		this.status = status;
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

	public Integer getStarLevel(){
		return this.starLevel;
	}

	public void setStarLevel(Integer starLevel){
		this.starLevel = starLevel;
	}

	public Integer getProperty(){
		return this.property;
	}

	public void setProperty(Integer property){
		this.property = property;
	}

	public String getProvinceCode(){
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode){
		this.provinceCode = provinceCode;
	}

	public String getCityCode(){
		return this.cityCode;
	}

	public void setCityCode(String cityCode){
		this.cityCode = cityCode;
	}

	public String getDistrictCode(){
		return this.districtCode;
	}

	public void setDistrictCode(String districtCode){
		this.districtCode = districtCode;
	}

	public String getNote(){
		return this.note;
	}

	public void setNote(String note){
		this.note = note;
	}

}