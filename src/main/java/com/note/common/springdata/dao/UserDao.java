package com.note.common.springdata.dao;


import com.note.common.springdata.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 一对多关联关系操作
 */
public interface UserDao extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

}
