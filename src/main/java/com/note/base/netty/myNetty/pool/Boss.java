package com.note.base.netty.myNetty.pool;

import java.nio.channels.ServerSocketChannel;

/**
 * boss接口
 */
public interface Boss {

    /**
     * 加入一个新的ServerSocket
     */
    void registerAcceptChannelTask(ServerSocketChannel serverChannel);
}
