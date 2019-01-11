package com.note.common.freemarker.crud.dao;

import com.note.common.freemarker.crud.orm.Position;
import com.note.common.freemarker.crud.orm.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao {

    private static Map<Integer, User> users = null;

    @Autowired
    private PositionDao positionDao;

    // 模拟数据库查询数据
    static {
        users = new HashMap<Integer, User>();
        users.put(1, new User(1, "ITDragon", "11@xl.com", 1, new Position(1, "架构师"), new Date(), 18888.88));
        users.put(2, new User(2, "Blog", "22@xl.com", 1, new Position(2, "高级工程师"), new Date(), 15555.55));
        users.put(3, new User(3, "Welcome", "33@xl.com", 0, new Position(3, "中级工程师"), new Date(), 8888.88));
        users.put(4, new User(4, "To", "44@xl.com", 0, new Position(4, "初级工程师"), new Date(), 5555.55));
        users.put(5, new User(5, "You", "55@xl.com", 1, new Position(5, "java实习生"), new Date(), 2222.22));
    }

    // 下一次存储的下标id
    private static Integer initId = 6;

    public void save(User user) {
        if (user.getId() == null) {
            user.setId(initId++);
        }

        user.setPosition(positionDao.getPositionById(user.getPosition().getId()));
        users.put(user.getId(), user);
    }

    public Collection<User> queryAllUsers() {
        return users.values();
    }

    public User getUserById(Integer id) {
        return users.get(id);
    }

    public void deleteUserById(Integer id) {
        users.remove(id);
    }
}
