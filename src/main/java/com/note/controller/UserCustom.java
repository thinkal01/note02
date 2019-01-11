package com.note.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCustom {

    private User user;

    private List<User> userList;

    private Map<String, Object> maps = new HashMap<>();

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
