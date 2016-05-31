package kj.pos.entity.product;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yan on 15-9-16.
 */
@Alias("ProductInfo")
public class ProductInfo extends BaseEntity implements Serializable {
    //id
    private Long id;
    //创建日期
    private String createDate;
    //修改日期
    private String modifyDate;
    //商品代码
    private String code;
    //商品名称
    private String name;
    //商品简称
    private String shortName;
    //图片url
    private String attachHttp;
    //图片名称
    private String attachName;
    //上传时间
    private String attachDate;
    //标准售价
    private Double untPrice;
    //成本价
    private Double costPrice;
    //商品类别code
    private String categoryCode;
    //商品类别
    private String categoryName;
    //是否删除 0否1是
    private int del;
    //备注
    private String note;
    //品牌code
    private String brandCode;
    //品牌
    private String brandName;

    private List<ProductSku> productSkuList;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public Double getUntPrice() {
        return untPrice;
    }

    public void setUntPrice(Double untPrice) {
        this.untPrice = untPrice;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<ProductSku> getProductSkuList() {
        return productSkuList;
    }

    public void setProductSkuList(List<ProductSku> productSkuList) {
        this.productSkuList = productSkuList;
    }
}
