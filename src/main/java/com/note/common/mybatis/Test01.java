package com.note.common.mybatis;

import com.note.util.FileUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class Test01 {
    //会话工厂
    private SqlSessionFactory factory;

    @Before
    public void createSqlSessionFactory() {
        // 配置文件
        String resource = "SqlMapConfig.xml";
        // InputStream inputStream = Resources.getResourceAsStream(resource);
        // FileInputStream inputStream = new FileInputStream(FileUtil.getCurrentPath(resource, this.getClass()));
        InputStream inputStream = FileUtil.getCurrentResource(resource, this.getClass());
        // 使用SqlSessionFactoryBuilder从xml配置文件中创建SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /*
     * 一级缓存: 也就Session级的缓存(默认开启)
     */
    @Test
    public void testCache1() {
        SqlSession sqlSession = factory.openSession();
        String statement = "com.note.common.mybatis.UserMapper.selectByPrimaryKey";
        User user = sqlSession.selectOne(statement, 1L);
        System.out.println(user);
        /*
         * 一级缓存默认就会被使用
         * 查询条件是一样的
         * 必须是同一个Session,如果session对象已经close()过了就不可能用了
         * 没有执行过session.clearCache()清理缓存
         * 没有执行过增删改的操作(这些操作都会清理缓存)
         */
        // sqlSession.clearCache();
        // sqlSession.close();
        // sqlSession.update("com.note.common.mybatis.userMapper.updateUser",new User(2, "user", 23));
        user = sqlSession.selectOne(statement, 1L);
        System.out.println(user);
    }

    @Test
    public void testUserMapper() {
        // 获得SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 获得dao实体
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 进行两次相同的查询
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
        user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);

        // 注意，使用二级缓存，sqlSession需要commit才会生效
        sqlSession.commit();

        System.out.println("======================二级缓存=========================");
        // 获得一个新的SqlSession 对象
        SqlSession sqlSession2 = factory.openSession();
        // 进行相同的查询操作
        user = sqlSession2.getMapper(UserMapper.class).selectByPrimaryKey(1);
        System.out.println(user);
        sqlSession2.commit();
    }

    /*
     * 二级缓存
     * 使用两个不同的SqlSession对象去执行相同查询条件的查询，第二次查询时不会再发送SQL语句，而是直接从缓存中取出数据
     */
    @Test
    public void testCache2() {
        String statement = "com.note.common.mybatis.UserMapper.selectByPrimaryKey";
        //开启两个不同的SqlSession
        SqlSession session1 = factory.openSession();
        SqlSession session2 = factory.openSession();
        //使用二级缓存时，User类必须实现Serializable接口
        User user = session1.selectOne(statement, 1l);
        System.out.println("user=" + user);
        //一定要提交事务之后二级缓存才会起作用
        session1.commit();

        //由于使用的是两个不同的SqlSession对象，所以即使查询条件相同，一级缓存也不会开启使用
        user = session2.selectOne(statement, 1l);
        //session2.commit();
        System.out.println("user2=" + user);
    }

}
