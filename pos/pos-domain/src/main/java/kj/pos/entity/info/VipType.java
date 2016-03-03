package kj.pos.entity.info;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

@Alias("VipType")
public class VipType extends BaseEntity{
	//创建字段
	private Long id;//id
	private String createDate;//创建日期
	private String modifyDate;//修改日期
	private String code;//代码
	private String name;//名称
	private Double consDiscount;//折扣 10代表不打折 1代表打1折
	private Boolean del;//是否删除
	private String validDate;//有效期
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

	public Double getConsDiscount(){
		return this.consDiscount;
	}

	public void setConsDiscount(Double consDiscount){
		this.consDiscount = consDiscount;
	}

	public Boolean getDel(){
		return this.del;
	}

	public void setDel(Boolean del){
		this.del = del;
	}

	public String getValidDate(){
		return this.validDate;
	}

	public void setValidDate(String validDate){
		this.validDate = validDate;
	}

	public String getNote(){
		return this.note;
	}

	public void setNote(String note){
		this.note = note;
	}

}