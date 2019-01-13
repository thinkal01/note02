package com.note.base.netty.netty.pb;

import com.note.base.netty.netty.pb.protocol.ResponseMsgProbuf.ResponseMsg;
import com.note.base.netty.xtwy.netty.CommonConstant;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class PbClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ResponseMsg responseMsg = (ResponseMsg) msg;
        ctx.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).set(responseMsg.getResponse());
        ctx.channel().close();
    }

}
