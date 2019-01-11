package com.note.common.fastjson;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;
    private Date birth;
    private int age;
    private boolean isdead;

    public User() {
        super();
    }

    public User(long id, String name, Date birth, int age, boolean isdead) {
        super();
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.age = age;
        this.isdead = isdead;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isIsdead() {
        return isdead;
    }

    public void setIsdead(boolean isdead) {
        this.isdead = isdead;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", birth=" + birth
                + ", age=" + age + ", isdead=" + isdead + "]";
    }

}
