package com.note.common.springdata.test;

import com.note.common.springdata.dao.UserDao;
import com.note.common.springdata.pojo.Roles;
import com.note.common.springdata.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 一对多的关联关系测试
 *
 * @author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OneToManyTest {
    @Autowired
    private UserDao usersDao;

    /**
     * 添加用户同时添加角色
     */
    @Test
    public void test1() {
        //创建角色
        Roles roles = new Roles();
        roles.setRolename("管理员");
        //创建用户
        Users users = new Users();
        users.setUserage(30);
        users.setUsername("小王");
        //建立关系
        roles.getUsers().add(users);
        users.setRoles(roles);
        //保存数据
        this.usersDao.save(users);
    }

    /**
     * 根据用户ID查询用户信息，同时查询角色
     */
    @Test
    public void test2() {
        Users users = this.usersDao.findOne(14);
        System.out.println("用户姓名：" + users.getUsername());
        Roles roles = users.getRoles();
        System.out.println(roles);
    }
}
