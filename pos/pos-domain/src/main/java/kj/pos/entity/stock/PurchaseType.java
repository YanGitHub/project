package kj.pos.entity.stock;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;
import java.io.Serializable;

@Alias("PurchaseType")
public class PurchaseType extends BaseEntity implements Serializable{
	//创建字段
	private Long id;//id
	private String createDate;//创建日期
	private String modifyDate;//修改日期
	private String code;//代码
	private String name;//名称
	private Boolean del;//是否删除
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

}