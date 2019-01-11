package com.note.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller //<bean class="UserController"/>
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/hello.do", method = {RequestMethod.GET,
            RequestMethod.POST})
    public String hello() {
        return "index";
    }

    //跳转到add页面
    @RequestMapping("toAdd")
    public String toAdd() {
        return "add";
    }

    //接受int类型参数
    @RequestMapping("recieveInt")
    public String recieveInt(Integer id) {
        System.out.println(id);
        return "success";
    }

    //接受字符类型参数
    @RequestMapping("recieveStr")
    public String recieveStr(String username) {
        System.out.println(username);
        return "success";
    }

    //接受数组类型参数
    @RequestMapping("recieveArray")
    public String recieveArray(Integer[] ids) {
        System.out.println(ids);
        return "success";
    }

    //接受参数封装User对象
    @RequestMapping("recieveUser")
    public String recieveUser(User user) {
        System.out.println(user);
        return "success";
    }

    //接受包装类型参数
    @RequestMapping("recieveUserCustom")
    public String recieveUserCustom(UserCustom userCustom) {
        System.out.println(userCustom);
        return "success";
    }

    //接受集合类型参数
    @RequestMapping("recieveList")
    public String recieveList(UserCustom userCustom) {
        System.out.println(userCustom);
        return "success";
    }

    //接受集合类型参数
    @RequestMapping("recieveMap")
    public String recieveMap(UserCustom userCustom) {
        System.out.println(userCustom);
        return "success";
    }

    @RequestMapping("list")
    public String list(Model model) {
        //model	相当于application域对象

        List<User> userList = new ArrayList<User>();

        User user1 = new User();
        user1.setId(1);
        user1.setSex("男");
        user1.setUsername("张山峰");
        user1.setAddress("武当山");
        user1.setBirthday(new Date());

        User user2 = new User();
        user2.setId(2);
        user2.setSex("男2");
        user2.setUsername("张山峰222");
        user2.setAddress("武当山222");
        user2.setBirthday(new Date());

        User user3 = new User();
        user3.setId(3);
        user3.setSex("男3");
        user3.setUsername("张山峰333");
        user3.setAddress("武当山333");
        user3.setBirthday(new Date());

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        model.addAttribute("userList", userList);

        return "list";
    }

    //修改
    @RequestMapping("updateByID/{id}")
    public String updateByID(@PathVariable Integer id, Model model) {
        User user1 = new User();
        user1.setId(id);
        user1.setSex("男");
        user1.setUsername("张山峰");
        user1.setAddress("武当山");
        user1.setBirthday(new Date());

        model.addAttribute("user", user1);
        return "edit";
    }

    //测试转发
    @RequestMapping("forward")
    public String forward() {
        return "forward:/items/list.do";
    }

    //测试重定向
    @RequestMapping("redirect")
    public String redirect() {
        return "redirect:/items/list.do";
    }
}
