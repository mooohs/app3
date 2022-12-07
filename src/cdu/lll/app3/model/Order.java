package cdu.lll.app3.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable {

    private int id;
    private String orderId;
    private Customer customer;
    private BigDecimal money;
    private Map<Integer,Integer> products;
    private String name;
    private String address;
    private String tel;
    private int statusCode;//订单状态
    private long createTime;
    private long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }



    public String getProductsString(){
        if (products==null||products.isEmpty()){
            return "";
        }
        StringBuilder builder=new StringBuilder();
        for (Integer productId:products.keySet()){
            builder.append(productId + ":"+ products.get(productId)+",");
        }
        if (builder.length()>0){
            builder.deleteCharAt(builder.length()-1);
        }
        return builder.toString();
    }

    public void setProducts(Map<Integer, Integer> products) {
        this.products = products;
    }

    public void setProducts(String products){
        if (products==null||products.equals("")){
            return;
        }
        Map<Integer,Integer>map=new HashMap<>();
        String[] arr1=products.split(",");
        for (String s:arr1){
            String[] arr2=s.split(":");
            map.put(Integer.parseInt(arr2[0]),Integer.parseInt(arr2[1]));
        }
        this.products=map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getStatusCode() {
        return statusCode;
    }
//订单状态
    public String getStatus(){
        switch (statusCode){
            case 0:
                return "未付款";
            case 1:
                return "已付款，未发货";
            case 2:
                return "已发货";
            case 3:
                return "已收货";
            case 4:
                return "退货中";
            case 5:
                return "已退货";
            default:
                return "状态异常";
        }
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", customer=" + customer +
                ", money=" + money +
                ", products=" + products +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", statusCode=" + statusCode +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
