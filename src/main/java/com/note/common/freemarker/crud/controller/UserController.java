package com.note.common.freemarker.crud.controller;

import com.note.common.freemarker.crud.dao.PositionDao;
import com.note.common.freemarker.crud.dao.UserDao;
import com.note.common.freemarker.crud.orm.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PositionDao positionDao;

    private static final String INPUT = "input"; // 跳转到编辑页面

    private static final String LIST = "list"; // 跳转到用户列表页面

    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id,
                        Map<String, Object> map) {
        if (id != null) {
            map.put("user", userDao.getUserById(id));
        }
    }

    // 更新用户，用put请求方式区别get请求方式，属于SpringMVC rest 风格的crud
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String updateUser(User user) {
        userDao.save(user);
        return "redirect:/users";
    }

    // 点击编辑跳转编辑页面
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
        map.put("user", userDao.getUserById(id));
        map.put("positions", positionDao.queryAllPositions());
        return INPUT;
    }

    // 通过id删除用户
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id) {
        userDao.deleteUserById(id);
        return "redirect:/users";
    }

    /**
     * 新增用户，若保存成功则跳转到用户列表页面，若失败则跳转到编辑页面
     *
     * @param user   用 @Valid 注解修饰后，可实现数据校验的逻辑
     * @param result 数据校验结果
     * @param map    数据模型
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String save(@Valid User user, Errors result, Map<String, Object> map) {
        if (result.getErrorCount() > 0) {
            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField() + " : " + error.getDefaultMessage());
            }
            map.put("positions", positionDao.queryAllPositions());
            return INPUT;
        }
        userDao.save(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String input(Map<String, Object> map) {
        map.put("positions", positionDao.queryAllPositions());
        map.put("user", new User());
        return INPUT;
    }

    // 跳转用户列表页面
    @RequestMapping("/users")
    public String list(Map<String, Object> map) {
        map.put("users", userDao.queryAllUsers());
        return LIST;
    }

}
