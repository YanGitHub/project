package kj.pos.entity.template;

import org.apache.ibatis.type.Alias;

@Alias("PrintTemplate")
public class PrintTemplate{
	//创建字段
	private Long id;//id
	private String orgCode;//门店code
	private String createDate;//创建时间
	private String modifyDate;//修改时间
	private Integer type;//1 收银小票  2 标签设置 
	private String name;//模板名称
	private String printer;//打印机名称
	private String backgroundImage;//背景图片地址
	private Double height;//高
	private Double width;//宽
	private String note;//备注
	private String data;//模板数据
	//创建getter和setter方法
	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getOrgCode(){
		return this.orgCode;
	}

	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
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

	public Integer getType(){
		return this.type;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getPrinter(){
		return this.printer;
	}

	public void setPrinter(String printer){
		this.printer = printer;
	}

	public String getBackgroundImage(){
		return this.backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage){
		this.backgroundImage = backgroundImage;
	}

	public Double getHeight(){
		return this.height;
	}

	public void setHeight(Double height){
		this.height = height;
	}

	public Double getWidth(){
		return this.width;
	}

	public void setWidth(Double width){
		this.width = width;
	}

	public String getNote(){
		return this.note;
	}

	public void setNote(String note){
		this.note = note;
	}

	public String getData(){
		return this.data;
	}

	public void setData(String data){
		this.data = data;
	}

}