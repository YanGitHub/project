package kj.pos.entity.product;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;
import java.io.Serializable;

@Alias("Brand")
public class Brand extends BaseEntity{
	//创建字段
	private Long id;//id
	private String createDate;//创建日期
	private String modifyDate;//修改日期
	private String code;//代码
	private String name;//名称
	private String attachHttp;// 附件地址
	private String attachName;//附件名称
	private String attachDate;//上传时间
	private Boolean del;//是否删除 0 正常 1 已删除
	private String note;//简介
	//创建getter和setter方法


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttachHttp() {
        return attachHttp;
    }

    public void setAttachHttp(String attachHttp) {
        this.attachHttp = attachHttp;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachDate() {
        return attachDate;
    }

    public void setAttachDate(String attachDate) {
        this.attachDate = attachDate;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}