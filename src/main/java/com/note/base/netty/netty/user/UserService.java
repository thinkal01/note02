package com.note.base.netty.netty.user;


import com.note.base.netty.netty.pb.PbClient;
import com.note.base.netty.netty.pb.protocol.EmailProbuf.Email;
import com.note.base.netty.netty.pb.protocol.RequestMsgProbuf.RequestMsg;
import com.note.base.netty.netty.pb.protocol.UserProbuf.User;

public class UserService {

    public User save() throws Exception {
        User.Builder user = User.newBuilder();
        user.setId(1);
        user.setPhone("138xxxxxx");
        user.setUserName("zhangsan");
        RequestMsg.Builder requestMsg = RequestMsg.newBuilder();
        requestMsg.setCmd("saveUser");
        requestMsg.setRequestParam(user.build().toByteString());
        return User.parseFrom(PbClient.startClient(requestMsg));
    }

    public Email getEmail() throws Exception {
        User.Builder user = User.newBuilder();
        user.setId(1);
        user.setPhone("138xxxxxx");
        user.setUserName("zhangsan");
        RequestMsg.Builder requestMsg = RequestMsg.newBuilder();
        requestMsg.setCmd("getEmailByUser");
        requestMsg.setRequestParam(user.build().toByteString());
        return Email.parseFrom(PbClient.startClient(requestMsg));
    }

}
