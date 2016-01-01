package kj.pos.entity.admin;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

/**
 * Created by Yan on 15-11-27.
 */
@Alias("UserInfo")
public class UserInfo extends BaseEntity {
    //id
    private Long id;
    //创建日期
    private String createDate;
    //修改日期
    private String modifyDate;
    //用户编码
    private String code;
    //用户名称
    private String name;
    //上级组织 code
    private String orgCode;
    //上级组织 name
    private String orgName;
    //状态 0 不可用 1 可用
    private int status;
    //密码
    private String password;
    //电话
    private String phone;
    //手机
    private String mobile;
    //电子邮件
    private String email;
    //备注
    private String note;

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

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
