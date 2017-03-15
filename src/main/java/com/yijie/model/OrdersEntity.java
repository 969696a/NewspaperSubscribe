package com.yijie.model;

import javax.persistence.*;

/**
 * Created by barbara on 2016/12/5.
 */
@Entity
@Table(name = "orders", schema = "newspaper_subscribe", catalog = "")
public class OrdersEntity {
    private int oId;
    private int uId;
    private int nId;
    private int oNum;
    private int oMonth;

    @Id
    @Column(name = "o_id", nullable = false)
    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    @Basic
    @Column(name = "u_id", nullable = false)
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "n_id", nullable = false)
    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }

    @Basic
    @Column(name = "o_num", nullable = false)
    public int getoNum() {
        return oNum;
    }

    public void setoNum(int oNum) {
        this.oNum = oNum;
    }

    @Basic
    @Column(name = "o_month", nullable = false)
    public int getoMonth() {
        return oMonth;
    }

    public void setoMonth(int oMonth) {
        this.oMonth = oMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (oId != that.oId) return false;
        if (uId != that.uId) return false;
        if (nId != that.nId) return false;
        if (oNum != that.oNum) return false;
        if (oMonth != that.oMonth) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oId;
        result = 31 * result + uId;
        result = 31 * result + nId;
        result = 31 * result + oNum;
        result = 31 * result + oMonth;
        return result;
    }
}
