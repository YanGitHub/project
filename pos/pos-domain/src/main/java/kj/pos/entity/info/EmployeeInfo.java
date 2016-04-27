package kj.pos.entity.info;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("EmployeeInfo")
public class EmployeeInfo extends BaseEntity{
	//创建字段
	private Long id;//主键
	private String createDate;//创建日期
	private String modifyDate;//最后更新日期
	private String code;// 代码
	private String name;//名称
	private String orgCode;//组织code(organization_info code)
    private String orgName;
	private Integer sex;//性别 0 未定义 1 男 2 女
	private Boolean isJob;//在职 0 离职 1
	private String birthday;//生日
	private String identityCard;//身份证
	private String phone;//联系电话
	private String mobile;//联系手机
	private String qq;//QQ
	private String email;//Email
	private String wangwang;//旺旺
	private String weixin;//微信
	private String address;//地址
	private String note;//
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

	public String getOrgCode(){
		return this.orgCode;
	}

	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
	}

	public Integer getSex(){
		return this.sex;
	}

	public void setSex(Integer sex){
		this.sex = sex;
	}

	public Boolean getIsJob(){
		return this.isJob;
	}

	public void setIsJob(Boolean isJob){
		this.isJob = isJob;
	}

	public String getBirthday(){
		return this.birthday;
	}

	public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	public String getIdentityCard(){
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard){
		this.identityCard = identityCard;
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

	public String getQq(){
		return this.qq;
	}

	public void setQq(String qq){
		this.qq = qq;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getWangwang(){
		return this.wangwang;
	}

	public void setWangwang(String wangwang){
		this.wangwang = wangwang;
	}

	public String getWeixin(){
		return this.weixin;
	}

	public void setWeixin(String weixin){
		this.weixin = weixin;
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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}