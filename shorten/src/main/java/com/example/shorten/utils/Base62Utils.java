package com.example.shorten.utils;

public class Base62Utils {
    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Base62Utils() {
    }

    public static String idToShortKey(long id) {
        StringBuilder stringBuilder = new StringBuilder();
        while (id > 0) {
            stringBuilder.append(BASE62.charAt((int) (id % 62)));
            id = id / 62;
        }

        while (stringBuilder.length() < 6) {
            stringBuilder.append(0);
        }

        return stringBuilder.reverse().toString();
    }

    public static long shortKeyToId(String shortKey) {
        long id = 0;
        for (int i = 0; i < shortKey.length(); i++) {
            id = id * 62 + BASE62.indexOf(shortKey.charAt(i));
        }

        return id;
    }

    // public static void main(String[] args) {
    //     System.out.println(Long.MAX_VALUE);
    //     System.out.println(idToShortKey(Long.MAX_VALUE));
    //     System.out.println(shortKeyToId("aZl8N0y58M7"));
    // }
}