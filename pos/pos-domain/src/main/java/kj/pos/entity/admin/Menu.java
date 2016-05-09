package kj.pos.entity.admin;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;
@Alias("Menu")
public class Menu extends BaseEntity{
	//创建字段
	private Long id;//
	private String createDate;//创建日期
	private String modifyDate;//修改日期
	private String code;//菜单代码
	private String name;//菜单名称
	private Long pid;//上级id
	private String url;//url
	private Boolean del;//是否删除
	private String note;//备注
	private Long no;//顺序小的在上面
	private Long levels;//菜单等级
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

	public Long getPid(){
		return this.pid;
	}

	public void setPid(Long pid){
		this.pid = pid;
	}

	public String getUrl(){
		return this.url;
	}

	public void setUrl(String url){
		this.url = url;
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

	public Long getNo(){
		return this.no;
	}

	public void setNo(Long no){
		this.no = no;
	}

	public Long getLevels(){
		return this.levels;
	}

	public void setLevels(Long levels){
		this.levels = levels;
	}

}