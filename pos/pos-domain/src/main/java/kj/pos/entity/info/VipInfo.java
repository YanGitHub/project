package kj.pos.entity.info;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

@Alias("VipInfo")
public class VipInfo extends BaseEntity{
	//创建字段
	private Long id;//id
	private String createDate;//创建日期
	private String modifyDate;//修改日期
	private String code;//会员卡号
	private String name;//会员名称
	private String vipTypeCode;//会员类型
    private String vipTypeName;//会员类型名称
	private String sex;//性别：0、女  1男、2、保密
	private String shopCode;//店铺代码(organization_info code)
	private String shopName;//店铺名称
	private String tel;//电话
	private String phone;//手机
	private String birthday;//生日
	private String identityCard;//身份证
	private String issuingPerson;//发放人
	private String provinceCode;//省
    private String provinceName;//省
	private String cityCode;//市
    private String cityName;//市
	private String districtCode;//区
    private String districtName;//区
	private String post;//邮编
	private String email;//Email
	private String address;//地址
	private String qq;//QQ
	private Integer del;//是否删除  0 为删除  1 已删除
	private String pwd;//会员密码
    private String note;//备注

    private Double vipDiscount;//会员类型折扣
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

	public String getVipTypeCode(){
		return this.vipTypeCode;
	}

	public void setVipTypeCode(String vipTypeCode){
		this.vipTypeCode = vipTypeCode;
	}

	public String getSex(){
		return this.sex;
	}

	public void setSex(String sex){
		this.sex = sex;
	}

	public String getShopCode(){
		return this.shopCode;
	}

	public void setShopCode(String shopCode){
		this.shopCode = shopCode;
	}

	public String getShopName(){
		return this.shopName;
	}

	public void setShopName(String shopName){
		this.shopName = shopName;
	}

	public String getTel(){
		return this.tel;
	}

	public void setTel(String tel){
		this.tel = tel;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
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

	public String getIssuingPerson(){
		return this.issuingPerson;
	}

	public void setIssuingPerson(String issuingPerson){
		this.issuingPerson = issuingPerson;
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

	public String getPost(){
		return this.post;
	}

	public void setPost(String post){
		this.post = post;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getAddress(){
		return this.address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getQq(){
		return this.qq;
	}

	public void setQq(String qq){
		this.qq = qq;
	}

	public Integer getDel(){
		return this.del;
	}

	public void setDel(Integer del){
		this.del = del;
	}

	public String getPwd(){
		return this.pwd;
	}

	public void setPwd(String pwd){
		this.pwd = pwd;
	}

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getVipTypeName() {
        return vipTypeName;
    }

    public void setVipTypeName(String vipTypeName) {
        this.vipTypeName = vipTypeName;
    }

    public Double getVipDiscount() {
        return vipDiscount;
    }

    public void setVipDiscount(Double vipDiscount) {
        this.vipDiscount = vipDiscount;
    }
}