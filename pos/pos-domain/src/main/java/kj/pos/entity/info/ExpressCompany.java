package kj.pos.entity.info;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;
import java.io.Serializable;

@Alias("ExpressCompany")
public class ExpressCompany extends BaseEntity{
	//创建字段
	private Long id;//id
	private String createDate;//创建日期
	private String modifyDate;//修改日期
	private String code;//代码
    private String outCode;//对应快递100代码
	private String name;//名称
	private String contact;//联系人
	private String tel;//电话
	private String fax;//传真
	private String email;//邮箱
	private String website;//网址
	private String address;//地址
	private Boolean del;//是否删除
	private String note;//备注
	private String expreeMode;//快递模板
	//创建getter和setter方法

    public String getOutCode() {
        return outCode;
    }

    public void setOutCode(String outCode) {
        this.outCode = outCode;
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

	public String getContact(){
		return this.contact;
	}

	public void setContact(String contact){
		this.contact = contact;
	}

	public String getTel(){
		return this.tel;
	}

	public void setTel(String tel){
		this.tel = tel;
	}

	public String getFax(){
		return this.fax;
	}

	public void setFax(String fax){
		this.fax = fax;
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

	public Boolean getDel(){
		return this.del;
	}

	public void setDel(Boolean del){
		this.del = del;
	}

	public String getNote(){
		return this.note;
	}

	public void setNote(String note){
		this.note = note;
	}

	public String getExpreeMode(){
		return this.expreeMode;
	}

	public void setExpreeMode(String expreeMode){
		this.expreeMode = expreeMode;
	}

}