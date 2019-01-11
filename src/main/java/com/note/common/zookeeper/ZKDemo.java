package com.note.common.zookeeper;

import org.apache.commons.io.FileUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class ZKDemo {
    private ZooKeeper zk = null;

    @Before
    public void init() throws Exception {
        zk = new ZooKeeper("192.168.100.155:2181", 2000, new Watcher() {
            /**
             * 监听事件发生时的回调方法
             */
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == EventType.None)
                    return;
                System.out.println(event.getType());
                System.out.println(event.getPath());
                try {
                    zk.getData("/sh18qi2", true, null);
                    zk.getChildren("/sh18qi2", true);
                } catch (KeeperException | InterruptedException e) {
                }
            }
        });
    }

    /**
     * 向zookeeper服务集群中注册数据，添加znode
     */
    @Test
    public void testCreateZnode() throws Exception {
        // zk.create("/sh18qi2", "这是我的处女作".getBytes("utf-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // sequential的顺序维护是在一个父节点的范围之内
        // [banhua0000000001, banzhang0000000000]
        zk.create("/sh18qi2/banzhang", "草草".getBytes("utf-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        zk.create("/sh18qi2/banhua", "如画".getBytes("utf-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        // 换一个父节点，序号的递增顺序重新开始
        // zk.create("/sh18qi/banzhuren", "如诗".getBytes("utf-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        zk.close();
    }

    /**
     * 从zkeeper中删除znode
     */
    @Test
    public void testDeleteZnode() throws Exception {
        // 参数1：要删除的节点的路径 参数2：要删除的节点的版本，-1匹配所有版本
        zk.delete("/sh18qi", -1);
        Stat exists = zk.exists("/sh18qi", false);
        System.out.println(exists);
    }

    @Test
    public void testUpdateZnode() throws Exception {
        byte[] data = zk.getData("/sh18qi2", false, null);
        System.out.println(new String(data, "utf-8"));
        zk.setData("/sh18qi2", "我有一头小毛驴我从来也不骑".getBytes("utf-8"), -1);

        data = zk.getData("/sh18qi2", false, null);
        System.out.println(new String(data, "utf-8"));
    }

    /**
     * 获取子节点信息
     */
    @Test
    public void testGetChildren() throws Exception {
        List<String> children = zk.getChildren("/sh18qi2", false);
        for (String child : children) {
            System.out.println(child);
        }
    }

    /**
     * zk的监听机制：
     * 1、事先定义好监听的回调函数
     * 2、在对znode进行各种访问操作时可以注册监听
     * 3、监听的znode上发生相应事件时，客户端zk会接收到zookeeper集群的事件通知
     * 4、客户端zk根据事件调用我们事先定义好的回调函数
     */
    @Test
    public void testWatch() throws Exception {
        // 在获取znode数据时注册了监听
        // 监听器是一次性，只要监听到一次事件，就失效了
        // getData监听的事件是数据的更改
        byte[] data = zk.getData("/sh18qi2", true, null);

        // 在做查询子节点操作时注册监听
        // 监听的事件就是监听节点下的子节点变化事件
        List<String> children = zk.getChildren("/sh18qi2", true);

        Thread.sleep(Long.MAX_VALUE);
    }


    /**
     * 模拟solr云中的配置文件集中管理
     * 将配置文件上传到zookeeper中
     *
     * @throws Exception
     */
    @Test
    public void testUploadConfigFileToZookeeper() throws Exception {
        String schema_xml = FileUtils.readFileToString(new File("g:/schema.xml"));
        zk.create("/conf", null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create("/conf/schema.xml", schema_xml.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.close();
    }

}
