package com.note.common.springdata.dao;

import com.note.common.springdata.pojo.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 多对多关联关系讲解
 */
public interface RolesDao extends JpaRepository<Roles, Integer> {

}
