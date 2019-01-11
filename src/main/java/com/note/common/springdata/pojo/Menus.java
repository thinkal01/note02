package com.note.common.springdata.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_menus")
public class Menus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menusid")
    private Integer menusid;

    @Column(name = "menusname")
    private String menusname;

    @Column(name = "menusurl")
    private String menusurl;

    @Column(name = "fatherid")
    private Integer fatherid;

    @ManyToMany(mappedBy = "menus")
    private Set<Roles> roles = new HashSet<>();

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Integer getMenusid() {
        return menusid;
    }

    public void setMenusid(Integer menusid) {
        this.menusid = menusid;
    }

    public String getMenusname() {
        return menusname;
    }

    public void setMenusname(String menusname) {
        this.menusname = menusname;
    }

    public String getMenusurl() {
        return menusurl;
    }

    public void setMenusurl(String menusurl) {
        this.menusurl = menusurl;
    }

    public Integer getFatherid() {
        return fatherid;
    }

    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
    }

    @Override
    public String toString() {
        return "Menus [menusid=" + menusid + ", menusname=" + menusname + ", menusurl=" + menusurl + ", fatherid="
                + fatherid + "]";
    }
}
