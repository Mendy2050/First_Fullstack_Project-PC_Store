package com.cy.store.vo;

import com.cy.store.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Mendy
 * @create 2023-07-24 18:37
 */
public class orderVO implements Serializable {

    // Order attributes
    private Integer oid;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvCountry;
    private String recvCityOrCounty;
    private String recvAddress;
    private Long totalPrice;
    private Integer status;

    // OrderItem attributes
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;
    private String createdUser;
    private Date createdTime;

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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        orderVO orderVO = (orderVO) o;

        if (oid != null ? !oid.equals(orderVO.oid) : orderVO.oid != null) return false;
        if (uid != null ? !uid.equals(orderVO.uid) : orderVO.uid != null) return false;
        if (recvName != null ? !recvName.equals(orderVO.recvName) : orderVO.recvName != null) return false;
        if (recvPhone != null ? !recvPhone.equals(orderVO.recvPhone) : orderVO.recvPhone != null) return false;
        if (recvCountry != null ? !recvCountry.equals(orderVO.recvCountry) : orderVO.recvCountry != null) return false;
        if (recvCityOrCounty != null ? !recvCityOrCounty.equals(orderVO.recvCityOrCounty) : orderVO.recvCityOrCounty != null) return false;
        if (recvAddress != null ? !recvAddress.equals(orderVO.recvAddress) : orderVO.recvAddress != null) return false;
        if (totalPrice != null ? !totalPrice.equals(orderVO.totalPrice) : orderVO.totalPrice != null) return false;
        if (status != null ? !status.equals(orderVO.status) : orderVO.status != null) return false;
        if (pid != null ? !pid.equals(orderVO.pid) : orderVO.pid != null) return false;
        if (title != null ? !title.equals(orderVO.title) : orderVO.title != null) return false;
        if (image != null ? !image.equals(orderVO.image) : orderVO.image != null) return false;
        if (price != null ? !price.equals(orderVO.price) : orderVO.price != null) return false;
        if (num != null ? !num.equals(orderVO.num) : orderVO.num != null) return false;
        if (createdUser != null ? !createdUser.equals(orderVO.createdUser) : orderVO.createdUser != null) return false;
        return createdTime != null ? createdTime.equals(orderVO.createdTime) : orderVO.createdTime == null;
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
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (createdUser != null ? createdUser.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "orderVO{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", recvName='" + recvName + '\'' +
                ", recvPhone='" + recvPhone + '\'' +
                ", recvCountry='" + recvCountry + '\'' +
                ", recvCityOrCounty='" + recvCityOrCounty + '\'' +
                ", recvAddress='" + recvAddress + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", createdUser='" + createdUser + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
