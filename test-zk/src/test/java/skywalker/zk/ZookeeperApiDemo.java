package skywalker.zk;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/20
 */
public class ZookeeperApiDemo {

    //创建Logger对象，按照文件log4j.properties中指定的格式输出日志到控制台
    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperApiDemo.class);

    // 用于等待 SyncConnected 事件触发后继续执行当前线程
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    //session的失效时间
    private static final int SESSION_TIMEOUT = 30000;

    //zookeeper连接对象
    private ZooKeeper zooKeeper;

    private final Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            if (Event.KeeperState.SyncConnected == event.getState()) {
                countDownLatch.countDown();
                String msg = String.format("process info,eventType:%s,eventState:%s,eventPath:%s", event.getType(), event.getState(), event.getPath());
                LOGGER.info(msg);
            }

        }
    };

    @Before
    public void connect() throws IOException {
        zooKeeper = new ZooKeeper("127.0.0.1:12181,127.0.0.1:22181,127.0.0.1:32181",
                SESSION_TIMEOUT, watcher);
        try {
            countDownLatch.await();
            LOGGER.info("Zookeeper session establish success,sessionID=" + Long.toHexString(zooKeeper.getSessionId()));
        } catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.debug("Zookeeper session establish fail");
        }
    }

    @After
    public void close() {
        if (zooKeeper != null) {
            try {
                zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // ===========================

    /**
     * 创建节点：
     * CreateMode：
     * PERSISTENT 普通持久节点
     * PERSISTENT_SEQUENTIAL:顺序持久节点
     * EPHEMERAL ：普通临时
     * EPHEMERAL_SEQUENTIAL：顺序临时节点
     * Access Control List: 访问控制列表
     * OPEN_ACL_UNSAFE: ANYONE CAN VISIT
     */
    @Test
    public void createNode() {
        try {
            // 普通临时
            String result = zooKeeper.create("/zk001",//节点的全路径
                    "zk001-data".getBytes(),//节点中的数据->字节数据
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,//指定访问控制列表
                    CreateMode.EPHEMERAL //指定创建节点的类型
            );
            if (result != null) {
                LOGGER.info("create node success,result={}", result);
            } else {
                System.out.println("创建失败");
            }
            Thread.sleep(10000);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

    @Test
    public void getNodeData() {
        try {
            //注意，是结点的全路径
            if (zooKeeper.exists("/zk001", false) != null) {
                byte[] data = zooKeeper.getData("/zk001", null, null);
                String result = new String(data);
                LOGGER.info("getNodeData={}", result);
            } else {
                System.out.println("节点不存在");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            Assert.fail();
        }

    }

    @Test
    public void getNodeDataAsync() {
        try {
            zooKeeper.getData("/zk01", null, new AsyncCallback.DataCallback() {
                @Override
                public void processResult(int i, String path, Object o, byte[] data, Stat stat) {
                    LOGGER.info("getNodeDataAsync={}", new String(data));
                }
            }, null);
            Thread.sleep(3000);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            Assert.fail();
        }
    }

    //获取所有的子节点
    @Test
    public void getChildren() {
        try {
            // 仅能获取到节点下的子节点，孙子节点不会被获取
            List<String> children = zooKeeper.getChildren("/zk01", true);
            for (String node : children) {
                LOGGER.info("================{}", node);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Test
    public void getChildrenWithData() {
        try {
            List<String> children = zooKeeper.getChildren("/zk01", true);
            for (String node : children) {
                Stat stat = new Stat();//封装结点的信息
                LOGGER.info("================{}", node);
                byte[] data = zooKeeper.getData("/zk01/" + node, null, stat);
                System.out.println(new String(data, StandardCharsets.UTF_8) + ", stat:" + stat);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Test
    public void deleteNode() {
        try {
            //-1指的是无论你的结点是什么版本，都将删除
            zooKeeper.delete("/zk06/test-0000000008", -1);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void testGetDataWatcher() {
        String result = "";
        try {
            //在读取数据时添加一个监听事件
            byte[] data = zooKeeper.getData("/zk01", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    LOGGER.info("testGetDataWatcher watch type:{}", event.getType());
                    //只能监听一次，想要持续监听可以通过循环或递归的方式
                    //testGetDataWatcher();
                }
            }, null);
            result = new String(data);
            Thread.sleep(30000);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            Assert.fail();
        }
        LOGGER.info("result = {}", result);
    }

    /**
     * watch:true:表示使用系统默认的Watcher是在zookeeper的构造函数中传递的Watcher
     * watch:false:不使用Watcher
     */
    @Test
    public void isExistWatcher1() {
        Stat stat = null;
        try {
            stat = zooKeeper.exists("/zk01/node1", true);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        //如果stat不为null，继续往后执行
        Assert.assertNotNull(stat);
        try {
            zooKeeper.delete("/zk01/node1", -1);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * 注册了自定义的监听对象，走自定义的。
     */
    @Test
    public void isExistWatcher2() {
        Stat stat = null;
        try {
            stat = zooKeeper.exists("/zk01/node2", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    LOGGER.info("isExistWatcher2  wather type:{}", event.getType());
                }
            });
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        //如果stat不为null，继续往后执行
        Assert.assertNotNull(stat);
        try {
            zooKeeper.setData("/zk01/node2", "isExistWatcher2_edited".getBytes(), -1);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            zooKeeper.delete("/zk01/node2", -1);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
