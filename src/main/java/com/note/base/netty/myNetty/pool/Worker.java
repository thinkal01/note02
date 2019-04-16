package com.note.base.netty.myNetty.pool;

import java.nio.channels.SocketChannel;

/**
 * worker接口
 */
public interface Worker {

    /**
     * 加入一个新的客户端会话
     */
    void registerNewChannelTask(SocketChannel channel);

}
