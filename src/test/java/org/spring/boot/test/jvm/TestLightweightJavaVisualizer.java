package org.spring.boot.test.jvm;

import com.google.common.collect.Maps;
import org.atpfivt.ljv.LJV;

import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 可视化java存储结构，非常牛逼的一个项目
 * https://graphviz.gitlab.io/
 */
public class TestLightweightJavaVisualizer {
    public static void main(String[] args) {
        Map<String, String> map = Maps.newHashMap();
        map.put("key_a", "val_a");
        map.put("key_b", "val_b");
        browse(new LJV(), map);

        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        browse(new LJV(), list);

    }

    public static void browse(LJV ljv, Object obj) {
        try {
            String dot = URLEncoder.encode(ljv.drawGraph(obj), "UTF8")
                    .replaceAll("\\+", "%20");
            Desktop.getDesktop().browse(
                    new URI("https://dreampuf.github.io/GraphvizOnline/#"
                            + dot));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}