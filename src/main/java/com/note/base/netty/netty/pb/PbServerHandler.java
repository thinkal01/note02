package com.note.base.netty.netty.pb;

import com.note.base.netty.netty.media.Media;
import com.note.base.netty.netty.pb.protocol.RequestMsgProbuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class PbServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RequestMsgProbuf.RequestMsg requestMsg = (RequestMsgProbuf.RequestMsg) msg;
        String cmd = requestMsg.getCmd();
        System.out.println("cmd" + cmd);

        // ByteString buf = requestMsg.getRequestParam();
        // ItemUser user = ItemUser.parseFrom(buf);
        // System.out.println(user.getUserName());
        // ctx.writeAndFlush(user);
        // Media media = new Media();

        Object response = Media.execute(requestMsg);
        ctx.writeAndFlush(response);
    }


}
