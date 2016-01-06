package kj.pos.entity.info;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;
import java.io.Serializable;

@Alias("Warehouse")
public class Warehouse extends BaseEntity{
	//创建字段
	private Long id;//
	private String createDate;//创建时间
	private String modifyDate;//修改时间
	private String pcode;//上级组织(organzation code)
    private String pname;//上级组织(organzation name)
	private String code;//仓库代码
	private String name;//仓库名称
	private String contactName;//联系人
	private String contactPhone;//联系人电话
	private String contactMobile;//联系人手机
	private String address;//仓库地址
	private String fax;//传真
	private String post;//邮编
	private Integer del;//是否删除 0否 1是
	private Integer isDefaultDeliver;//默认发货仓 0 不是 1 是默认仓
	private Integer isDefaultReceive;//默认收货仓 0 不是 1 是默认仓
	private String provinceCode;//省
	private String cityCode;//市
	private String districtCode;//区
	private Integer isNegativeStock;//是否允许负库存 0不允许 1允许
	private String note;//备注
	//创建getter和setter方法
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
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

    public String getPcode(){
		return this.pcode;
	}

	public void setPcode(String pcode){
		this.pcode = pcode;
	}

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
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

	public String getContactName(){
		return this.contactName;
	}

	public void setContactName(String contactName){
		this.contactName = contactName;
	}

	public String getContactPhone(){
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone){
		this.contactPhone = contactPhone;
	}

	public String getContactMobile(){
		return this.contactMobile;
	}

	public void setContactMobile(String contactMobile){
		this.contactMobile = contactMobile;
	}

	public String getAddress(){
		return this.address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getFax(){
		return this.fax;
	}

	public void setFax(String fax){
		this.fax = fax;
	}

	public String getPost(){
		return this.post;
	}

	public void setPost(String post){
		this.post = post;
	}

	public Integer getDel(){
		return this.del;
	}

	public void setDel(Integer del){
		this.del = del;
	}

	public Integer getIsDefaultDeliver(){
		return this.isDefaultDeliver;
	}

	public void setIsDefaultDeliver(Integer isDefaultDeliver){
		this.isDefaultDeliver = isDefaultDeliver;
	}

	public Integer getIsDefaultReceive(){
		return this.isDefaultReceive;
	}

	public void setIsDefaultReceive(Integer isDefaultReceive){
		this.isDefaultReceive = isDefaultReceive;
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

	public Integer getIsNegativeStock(){
		return this.isNegativeStock;
	}

	public void setIsNegativeStock(Integer isNegativeStock){
		this.isNegativeStock = isNegativeStock;
	}

	public String getNote(){
		return this.note;
	}

	public void setNote(String note){
		this.note = note;
	}

}