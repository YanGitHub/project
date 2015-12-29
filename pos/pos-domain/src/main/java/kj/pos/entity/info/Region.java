package kj.pos.entity.info;

import org.apache.ibatis.type.Alias;
import java.io.Serializable;

@Alias("Region")
public class Region implements Serializable{
	//创建字段
	private Long id;//
	private String createDate;//
	private String modifyDate;//
	private String regionId;//
	private String parentId;//
	private String regionCode;//
	private String regionName;//
	private Integer level;//
	private String zip;//
	private Boolean status;//
	private String note;//
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

    public String getRegionId(){
		return this.regionId;
	}

	public void setRegionId(String regionId){
		this.regionId = regionId;
	}

	public String getParentId(){
		return this.parentId;
	}

	public void setParentId(String parentId){
		this.parentId = parentId;
	}

	public String getRegionCode(){
		return this.regionCode;
	}

	public void setRegionCode(String regionCode){
		this.regionCode = regionCode;
	}

	public String getRegionName(){
		return this.regionName;
	}

	public void setRegionName(String regionName){
		this.regionName = regionName;
	}

	public Integer getLevel(){
		return this.level;
	}

	public void setLevel(Integer level){
		this.level = level;
	}

	public String getZip(){
		return this.zip;
	}

	public void setZip(String zip){
		this.zip = zip;
	}

	public Boolean getStatus(){
		return this.status;
	}

	public void setStatus(Boolean status){
		this.status = status;
	}

	public String getNote(){
		return this.note;
	}

	public void setNote(String note){
		this.note = note;
	}

}