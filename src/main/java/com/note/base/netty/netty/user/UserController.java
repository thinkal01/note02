package com.note.base.netty.netty.user;

import com.note.base.netty.netty.media.Remote;
import com.note.base.netty.netty.pb.protocol.EmailProbuf.Email;
import com.note.base.netty.netty.pb.protocol.ResponseMsgProbuf.ResponseMsg;
import com.note.base.netty.netty.pb.protocol.UserProbuf.User;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Remote("saveUser")
    public Object saveUser(User user) {
        //使用mybatis把user数据插入到数据库
        User.Builder newUser = user.newBuilder().setPhone("123456");
        newUser.setId(12);
        newUser.setUserName("ssss");
        return newUser.build();
    }

    @Remote("getEmailByUser")
    public Object getEmailByUser(User user) {
        //使用mybatis把user数据插入到数据库
        Email.Builder email = Email.newBuilder().setContent("test").setFromUser("zhangsan").setId(12).setSubject("test");
        ResponseMsg response = ResponseMsg.newBuilder().setResponse(email.build().toByteString()).build();
        return response;
    }

    @Remote("httpGetEmailByUser")
    public Object getEmail(String email) {
        email = email + "hhhh";
        return email;
    }

    /*@Remote("ThriftGetEmailByContent")
    public Object getEmail(Content content) {
        System.out.println(content.getPhone());
        content.setId(1);
        content.setPhone("15626519062");
        content.setIdIsSet(true);
        return content;
    }*/
}
