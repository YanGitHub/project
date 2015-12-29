package kj.pos.entity.admin;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;
import java.io.Serializable;

@Alias("SysParameters")
public class SysParameters extends BaseEntity{
	//创建字段
	private Long id;//
	private String sysKey;//参数
	private String sysValue;//参数值
	private String note;//备注
	private Boolean status;//状态 0 不可用 1 可用
	//创建getter和setter方法
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getSysKey(){
		return this.sysKey;
	}

	public void setSysKey(String sysKey){
		this.sysKey = sysKey;
	}

	public String getSysValue(){
		return this.sysValue;
	}

	public void setSysValue(String sysValue){
		this.sysValue = sysValue;
	}

	public String getNote(){
		return this.note;
	}

	public void setNote(String note){
		this.note = note;
	}

	public Boolean getStatus(){
		return this.status;
	}

	public void setStatus(Boolean status){
		this.status = status;
	}

}