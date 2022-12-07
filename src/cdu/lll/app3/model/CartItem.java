package cdu.lll.app3.model;

import java.io.Serializable;

public class CartItem implements Serializable {

    //-Model（模型）。是应用程序中用于处理应用程序数据逻辑的部分。

    private int id;
    private int customerId;
    private int productId;
    private int productNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", productNum=" + productNum +
                '}';
    }
}
