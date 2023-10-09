package org.spring.boot.test.niuke.mt52007812;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
// 7.
// 美团商家注册系统
// 请你开发一个美团商家测试系统，并用等价划分法确认商家注册信息是否成功。
// 商家信息必须满足以下条件：
// 1. 系统中第一次注册的商家名字，被视为主店。
// 2. 系统中若出现重名商家，需要判断地址是否已存在该商家。若存在，则注册失败。否则注册成功，该商家被视为分店。
// 3. 商家的名字和地址必须由小写的英文字母组成，否则注册失败。
// 请你输出每个商家的信息，按商家名字的字典序升序输出。需要输出商家名字，商家主店地址，商家分店数量。
// 示例
// 7
// pzwktfbgtmeecug trbsmkymujdivli
// 231A3D3C3CCD212 hjgbqcqgbydbhpi
// pzwktfbgtmeecug xnretautkcnhiuo
// hitlgeabzofwotu qynxuwjvvxznhea
// 231A3D3C3CCD212 xnretautkcnhiuo
// 231A3D3C3CCD212 qynxuwjvvxznhea
// pzwktfbgtmeecug hjgbqcqgbydbhpi
// 预计
// hitlgeabzofwotu qynxuwjvvxznhea 0
// pzwktfbgtmeecug trbsmkymujdivli 2
public class TestNiuKeMain7 {

    public static final Pattern PATTERN = Pattern.compile("[a-z]{1,20}");

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n < 1 || n > 1000) {
            System.out.println("输入的商家注册信息数量有误");
            return;
        }

        TreeMap<String, List<String>> nameAddrListMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            String name = in.next();
            String addr = in.next();
            if (invalidNameOrAddr(name) || invalidNameOrAddr(addr)) {
                continue;
            }
            put(nameAddrListMap, name, addr);
        }
        for (Map.Entry<String, List<String>> entry : nameAddrListMap.entrySet()) {
            String name = entry.getKey();
            List<String> addrList = entry.getValue();//.stream().distinct().collect(Collectors.toList());
            // 输出 商家名字，商家主店地址，商家分店数量，用空格隔开
            System.out.println(name + " " + addrList.stream().findFirst().orElse("") + " " + (addrList.size() - 1));
        }
    }

    private static void put(TreeMap<String, List<String>> nameAddrListMap, String name, String addr) {
        List<String> addrList = nameAddrListMap.getOrDefault(name, new ArrayList<>());
        if (addrList.isEmpty()) {
            List<String> addrListTmp = new ArrayList<>();
            addrListTmp.add(addr);
            nameAddrListMap.put(name, addrListTmp);
        }

        // 地址去重
        if (!addrList.contains(addr)) {
            addrList.add(addr);
        }
    }

    private static boolean invalidNameOrAddr(String input) {
        return !PATTERN.matcher(input).matches();
    }
}