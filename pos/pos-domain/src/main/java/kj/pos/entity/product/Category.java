package kj.pos.entity.product;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;
import java.io.Serializable;

@Alias("Category")
public class Category extends BaseEntity{
	//创建字段
	private Long id;//id
	private String createDate;//创建日期
	private String modifyDate;//修改日期
	private String code;//代码
	private String name;//名称
	private Integer sortNo;//序号
	private String parent;//父节点 顶级目录为0
	private Boolean isParent;//是否父节类目(即：该类目是否还有子类目) 0 没有子类目 1 有子类目
	private Boolean del;//是否删除 0 正常 1 已删除
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

	public Integer getSortNo(){
		return this.sortNo;
	}

	public void setSortNo(Integer sortNo){
		this.sortNo = sortNo;
	}

	public String getParent(){
		return this.parent;
	}

	public void setParent(String parent){
		this.parent = parent;
	}

	public Boolean getIsParent(){
		return this.isParent;
	}

	public void setIsParent(Boolean isParent){
		this.isParent = isParent;
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