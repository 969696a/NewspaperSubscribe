package com.yijie.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by barbara on 2016/12/5.
 */
@Entity
@Table(name = "users", schema = "newspaper_subscribe", catalog = "")
public class UsersEntity {
    private int uId;
    private String uPassword;
    private String uName;
    private String uIdcard;
    private String uPhone;
    private String uAddress;
    private int dId;
    private Collection<OrdersEntity> ordersesByUId;

    @Id
    @Column(name = "u_id", nullable = false)
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "u_password", nullable = false, length = 40)
    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    @Basic
    @Column(name = "u_name", nullable = false, length = 40)
    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Basic
    @Column(name = "u_idcard", nullable = false, length = 18)
    public String getuIdcard() {
        return uIdcard;
    }

    public void setuIdcard(String uIdcard) {
        this.uIdcard = uIdcard;
    }

    @Basic
    @Column(name = "u_phone", nullable = false, length = 11)
    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    @Basic
    @Column(name = "u_address", nullable = false, length = 100)
    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    @Basic
    @Column(name = "d_id", nullable = false)
    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (uId != that.uId) return false;
        if (dId != that.dId) return false;
        if (uPassword != null ? !uPassword.equals(that.uPassword) : that.uPassword != null) return false;
        if (uName != null ? !uName.equals(that.uName) : that.uName != null) return false;
        if (uIdcard != null ? !uIdcard.equals(that.uIdcard) : that.uIdcard != null) return false;
        if (uPhone != null ? !uPhone.equals(that.uPhone) : that.uPhone != null) return false;
        if (uAddress != null ? !uAddress.equals(that.uAddress) : that.uAddress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uId;
        result = 31 * result + (uPassword != null ? uPassword.hashCode() : 0);
        result = 31 * result + (uName != null ? uName.hashCode() : 0);
        result = 31 * result + (uIdcard != null ? uIdcard.hashCode() : 0);
        result = 31 * result + (uPhone != null ? uPhone.hashCode() : 0);
        result = 31 * result + (uAddress != null ? uAddress.hashCode() : 0);
        result = 31 * result + dId;
        return result;
    }

    @OneToMany
    @JoinColumn(name = "u_id")
    public Collection<OrdersEntity> getOrdersesByUId() {
        return ordersesByUId;
    }

    public void setOrdersesByUId(Collection<OrdersEntity> ordersesByUId) {
        this.ordersesByUId = ordersesByUId;
    }
}
