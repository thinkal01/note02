package com.note.base.netty.xtwy.combine;

import org.xtwy.combine.codec.CombineDecoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class CombineChannelInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
//		ch.pipeline().addLast(new HttpRequestDecoder());
//		ch.pipeline().addLast(new HttpObjectAggregator(65536));
//		ch.pipeline().addLast(new HttpResponseEncoder());
		
		ch.pipeline().addLast(new CombineDecoder());
		
		
		ch.pipeline().addLast("CombineServerHandler",new CombineServerHandler());	

	}
	
	
}
