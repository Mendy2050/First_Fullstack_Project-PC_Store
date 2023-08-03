package com.cy.store.entity;

import java.io.Serializable;
import java.util.Date;

/** The entity class of the order data*/
public class Order extends BaseEntity implements Serializable {
    private Integer oid;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvCountry;
    private String recvCityOrCounty;
    private String recvAddress;
    private Long totalPrice;
    private Integer status;
    private Date orderTime;
    private Date payTime;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getRecvName() {
        return recvName;
    }

    public void setRecvName(String recvName) {
        this.recvName = recvName;
    }

    public String getRecvPhone() {
        return recvPhone;
    }

    public void setRecvPhone(String recvPhone) {
        this.recvPhone = recvPhone;
    }

    public String getRecvCountry() {
        return recvCountry;
    }

    public void setRecvCountry(String recvCountry) {
        this.recvCountry = recvCountry;
    }

    public String getRecvCityOrCounty() {
        return recvCityOrCounty;
    }

    public void setRecvCityOrCounty(String recvCityOrCounty) {
        this.recvCityOrCounty = recvCityOrCounty;
    }

    public String getRecvAddress() {
        return recvAddress;
    }

    public void setRecvAddress(String recvAddress) {
        this.recvAddress = recvAddress;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (oid != null ? !oid.equals(order.oid) : order.oid != null) return false;
        if (uid != null ? !uid.equals(order.uid) : order.uid != null) return false;
        if (recvName != null ? !recvName.equals(order.recvName) : order.recvName != null) return false;
        if (recvPhone != null ? !recvPhone.equals(order.recvPhone) : order.recvPhone != null) return false;
        if (recvCountry != null ? !recvCountry.equals(order.recvCountry) : order.recvCountry != null) return false;
        if (recvCityOrCounty != null ? !recvCityOrCounty.equals(order.recvCityOrCounty) : order.recvCityOrCounty != null) return false;
        if (recvAddress != null ? !recvAddress.equals(order.recvAddress) : order.recvAddress != null) return false;
        if (totalPrice != null ? !totalPrice.equals(order.totalPrice) : order.totalPrice != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        if (orderTime != null ? !orderTime.equals(order.orderTime) : order.orderTime != null) return false;
        return payTime != null ? payTime.equals(order.payTime) : order.payTime == null;
    }

    @Override
    public int hashCode() {
        int result = oid != null ? oid.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (recvName != null ? recvName.hashCode() : 0);
        result = 31 * result + (recvPhone != null ? recvPhone.hashCode() : 0);
        result = 31 * result + (recvCountry != null ? recvCountry.hashCode() : 0);
        result = 31 * result + (recvCityOrCounty != null ? recvCityOrCounty.hashCode() : 0);
        result = 31 * result + (recvAddress != null ? recvAddress.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", recvName='" + recvName + '\'' +
                ", recvPhone='" + recvPhone + '\'' +
                ", recvCountry='" + recvCountry + '\'' +
                ", recvCityOrCounty='" + recvCityOrCounty + '\'' +
                ", recvAddress='" + recvAddress + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", orderTime=" + orderTime +
                ", payTime=" + payTime +
                '}';
    }
}
