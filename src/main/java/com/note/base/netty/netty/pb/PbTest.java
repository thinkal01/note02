package com.note.base.netty.netty.pb;

import com.note.base.netty.netty.pb.protocol.EmailProbuf;
import com.note.base.netty.netty.pb.protocol.UserProbuf;
import com.note.base.netty.netty.user.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// @ContextConfiguration(locations = "classpath:netty/applicationContext.xml")
// @RunWith(SpringJUnit4ClassRunner.class)
public class PbTest {
    @Test
    public void testAddUser() throws Exception {
        UserService userService = new UserService();
        UserProbuf.User user = userService.save();
        System.out.println(user.getPhone());
    }

    @Test
    public void getEmailByUser() throws Exception {
        UserService userService = new UserService();
        EmailProbuf.Email email = userService.getEmail();
        System.out.println(email.getContent());
    }

    @Test
    public void test02() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("netty/applicationContext.xml");
        String applicationName = ctx.getApplicationName();
        System.out.println(applicationName);
    }
}
