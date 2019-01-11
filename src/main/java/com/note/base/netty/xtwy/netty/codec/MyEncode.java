package com.note.base.netty.xtwy.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.Delimiters;

import java.util.List;

public class MyEncode extends ByteToMessageCodec<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.getBytes("UTF-8"));
        out.writeBytes(Delimiters.lineDelimiter()[0]);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Integer length = in.readInt();
        byte[] response = new byte[length];
        in.skipBytes(4).readBytes(response);
        out.add(response);
    }

}
