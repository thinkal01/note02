package com.note.common.springdata.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_users")
public class Users implements Serializable {
    @Id
    //strategy=GenerationType.IDENTITY 自增长
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Integer userid;

    @Column(name = "username")
    private String username;

    @Column(name = "userage")
    private Integer userage;

    // CascadeType.PERSIST 级联保存
    // 一对一
    // @OneToOne(cascade = CascadeType.PERSIST)
    // 多对一
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "roles_id")
    private Roles roles;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserage() {
        return userage;
    }

    public void setUserage(Integer userage) {
        this.userage = userage;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Users [userid=" + userid + ", username=" + username + ", userage=" + userage + "]";
    }

}
