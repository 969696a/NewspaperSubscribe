package com.yijie.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by barbara on 2016/12/5.
 */
@Entity
@Table(name = "newspaperclass", schema = "newspaper_subscribe", catalog = "")
public class NewspaperclassEntity {
    private int cId;
    private String cName;
    private Collection<NewspaperEntity> newspapersByCId;

    @Id
    @Column(name = "c_id", nullable = false)
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "c_name", nullable = false, length = 40)
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewspaperclassEntity that = (NewspaperclassEntity) o;

        if (cId != that.cId) return false;
        if (cName != null ? !cName.equals(that.cName) : that.cName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cName != null ? cName.hashCode() : 0);
        return result;
    }

    @OneToMany
    @JoinColumn(name = "c_id")
    public Collection<NewspaperEntity> getNewspapersByCId() {
        return newspapersByCId;
    }

    public void setNewspapersByCId(Collection<NewspaperEntity> newspapersByCId) {
        this.newspapersByCId = newspapersByCId;
    }
}
