package com.note.common.springdata.dao.impl;

import com.note.common.springdata.pojo.Users;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

// 测试时需要注释掉一个,否则冲突无法注入UserDao
// @Repository
public class UsersDaoImpl implements UsersDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void insertUsers(Users users) {
        this.hibernateTemplate.save(users);
    }

    @Override
    public void updateUsers(Users users) {
        this.hibernateTemplate.update(users);
    }

    @Override
    public void deleteUsers(Users users) {
        this.hibernateTemplate.delete(users);
    }

    @Override
    public Users selectUsersById(Integer userid) {
        return this.hibernateTemplate.get(Users.class, userid);
    }

    /**
     * HQL：Hibernate Query Language
     * HQL 的语法：就是将原来的 sql 语句中的表与字段名称换成对象与属性的名称就可以了
     */
    @Override
    public List<Users> selectUserByName(String username) {
        //getCurrentSession:当前session必须要有事务边界，且只能处理唯一的一个事务。当事务提交或者回滚后session自动失效
        //openSession:打开一个新的session.使用多次获得不同session对象。使用后需要手动调用close方法关闭session
        Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
        //sql:select * from t_users where username =
        Query query = session.createQuery("from Users where username = :abc");
        Query queryTemp = query.setParameter("abc", username);
        return queryTemp.list();
    }

    // 原生sql语句
    @Override
    public List<Users> selectUserByNameUseSQL(String username) {
        Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
        Query query = session.createSQLQuery("select * from t_users where username = ?").addEntity(Users.class).setString(0, username);
        return query.list();
    }

    @Override
    public List<Users> selectUserByNameUseCriteria(String username) {
        Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
        //sql:select * from t_users where username = 张三
        Criteria c = session.createCriteria(Users.class);
        c.add(Restrictions.eq("username", username));
        return c.list();
    }

}
