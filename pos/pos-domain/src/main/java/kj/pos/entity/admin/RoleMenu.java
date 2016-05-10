package kj.pos.entity.admin;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;
import java.io.Serializable;

@Alias("RoleMenu")
public class RoleMenu extends BaseEntity{
	//创建字段
	private Long id;//
	private String createDate;//
	private String modifyDate;//
	private Long roleId;//
	private Long menuId;//
    private Long[] menuIdArray;
    private Long parentId;
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

	public Long getRoleId(){
		return this.roleId;
	}

	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}

	public Long getMenuId(){
		return this.menuId;
	}

	public void setMenuId(Long menuId){
		this.menuId = menuId;
	}

    public Long[] getMenuIdArray() {
        return menuIdArray;
    }

    public void setMenuIdArray(Long[] menuIdArray) {
        this.menuIdArray = menuIdArray;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}