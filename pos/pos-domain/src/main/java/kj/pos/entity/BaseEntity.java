package kj.pos.entity;

/**
 * Created by Yan on 15-12-3.
 */
public class BaseEntity {

    public String uid; //当前登录用户ID

    public PageUtil pageUtil;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public PageUtil getPageUtil() {
        return pageUtil;
    }

    public void setPageUtil(PageUtil pageUtil) {
        this.pageUtil = pageUtil;
    }
}
