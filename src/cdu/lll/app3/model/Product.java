package cdu.lll.app3.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

    private int id;
    private String name;//商品名称
    private BigDecimal price;//价格
    private int sale;//折扣
    private long stock;//库存
    private long marketDate;//上市时间
    private String imgUrl;//商品图片
    private String info;//商品详情

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public BigDecimal getSalePrice(){
        return price.multiply(new BigDecimal(sale)).divide(new BigDecimal(100));
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public long getMarketDate() {
        return marketDate;
    }

    public void setMarketDate(long marketDate) {
        this.marketDate = marketDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sale=" + sale +
                ", stock=" + stock +
                ", marketDate=" + marketDate +
                ", imgUrl='" + imgUrl + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Product){
            return this.id==((Product) obj).getId();
        }
        return false;
    }
}
