package com.note.common.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;

import java.util.Collections;
import java.util.List;

public class DemoServer {
    private ZooKeeper zkCli = null;
    boolean haveLock = false;
    private final String groupNode = "/locks";
    private String myNodePath;
    private static String hostname;

    public void gainLockAndDoSomething() throws Exception {
        zkCli = new ZooKeeper("hdp-server-01:2181", 2000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() != EventType.NodeChildrenChanged) return;
                try {
                    haveLock = gainLock();
                    if (haveLock) {
                        System.out.println(hostname + " gained the lock....");
                        doSomeThing();
                        releaseLock();
                        registerLock();
                    }
                } catch (Exception e) {
                }
            }
        });

        registerLock();
        Thread.sleep((long) (Math.random() * 500 + 500));
        haveLock = gainLock();

        if (haveLock) {
            System.out.println(hostname + " gained the lock....");
            doSomeThing();
            releaseLock();
            registerLock();
        }
    }

    public void registerLock() throws KeeperException, InterruptedException {
        myNodePath = zkCli.create(groupNode + "/lock", null, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    public boolean gainLock() throws KeeperException, InterruptedException {
        List<String> children = zkCli.getChildren(groupNode, true);
        if (children.size() == 1)
            return true;

        Collections.sort(children);
        // myNodePath : /locks/lock0000001
        if (children.get(0).equals(myNodePath.substring(groupNode.length() + 1))) {
            return true;
        } else {
            return false;
        }
    }

    public void doSomeThing() throws InterruptedException {
        System.out.println("begin working .......");
        Thread.sleep((long) (Math.random() * 1000 + 500));
        System.out.println("work has complished.....");
    }

    public void releaseLock() throws InterruptedException, KeeperException {
        zkCli.delete(myNodePath, -1);
    }

    public static void main(String[] args) throws Exception {
        hostname = args[0];
        DemoServer demoServer = new DemoServer();
        demoServer.gainLockAndDoSomething();
        Thread.sleep(Long.MAX_VALUE);
    }

}
