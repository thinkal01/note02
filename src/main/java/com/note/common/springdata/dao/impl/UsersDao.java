package com.note.common.springdata.dao.impl;

import com.note.common.springdata.pojo.Users;

import java.util.List;

public interface UsersDao {

    void insertUsers(Users users);

    void updateUsers(Users users);

    void deleteUsers(Users users);

    Users selectUsersById(Integer userid);

    List<Users> selectUserByName(String username);

    List<Users> selectUserByNameUseSQL(String username);

    List<Users> selectUserByNameUseCriteria(String username);
}
