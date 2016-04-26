package kj.pos.entity.product;

import kj.pos.entity.BaseEntity;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import java.io.Serializable;

@Alias("ProductSku")
public class ProductSku extends BaseEntity{
    //创建字段
    private Long id;//skuId
    private Long pid;//商品信息表(product id)
    private String productCode;//
    private String productName;
    private String code;//规格代码
    private String name;//规格名称
    private String gbCode;//国标码
    private Double untPrice;//标准售价
    private Double costPrice;//成本价
    private Double usePrice;//代理价
    private Boolean del;//是否删除 0 未删除 1删除
    private String memo;//备注
    //创建getter和setter方法
    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getPid(){
        return this.pid;
    }

    public void setPid(Long pid){
        this.pid = pid;
    }

    public String getProductCode(){
        return this.productCode;
    }

    public void setProductCode(String productCode){
        this.productCode = productCode;
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

    public String getGbCode(){
        return this.gbCode;
    }

    public void setGbCode(String gbCode){
        this.gbCode = gbCode;
    }

    public Double getUntPrice(){
        return this.untPrice;
    }

    public void setUntPrice(Double untPrice){
        this.untPrice = untPrice;
    }

    public Double getCostPrice(){
        return this.costPrice;
    }

    public void setCostPrice(Double costPrice){
        this.costPrice = costPrice;
    }

    public Double getUsePrice(){
        return this.usePrice;
    }

    public void setUsePrice(Double usePrice){
        this.usePrice = usePrice;
    }

    public Boolean getDel(){
        return this.del;
    }

    public void setDel(Boolean del){
        this.del = del;
    }

    public String getMemo(){
        return this.memo;
    }

    public void setMemo(String memo){
        this.memo = memo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}