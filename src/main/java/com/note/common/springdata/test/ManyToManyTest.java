package com.note.common.springdata.test;

import com.note.common.springdata.dao.RolesDao;
import com.note.common.springdata.pojo.Menus;
import com.note.common.springdata.pojo.Roles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * 多对多关联关系测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ManyToManyTest {
    @Autowired
    private RolesDao rolesDao;

    /**
     * 添加角色同时添加菜单
     */
    @Test
    public void test1() {
        //创建角色对象
        Roles roles = new Roles();
        roles.setRolename("超级管理员");

        //创建菜单对象    XXX管理平台 --->用户管理
        Menus menus = new Menus();
        menus.setMenusname("XXX管理平台");
        menus.setFatherid(-1);
        menus.setMenusurl(null);

        //用户管理菜单
        Menus menus1 = new Menus();
        menus1.setMenusname("用户管理");
        menus1.setFatherid(1);
        menus1.setMenusurl(null);

        //建立关系
        roles.getMenus().add(menus);
        roles.getMenus().add(menus1);

        menus.getRoles().add(roles);
        menus1.getRoles().add(roles);

        //保存数据
        rolesDao.save(roles);
    }

    /**
     * 查询Roles
     */
    @Test
    public void test2() {
        Roles roles = rolesDao.findOne(3);
        System.out.println("角色信息：" + roles);
        Set<Menus> menus = roles.getMenus();
        for (Menus menus2 : menus) {
            System.out.println("菜单信息：" + menus2);
        }
    }

}
