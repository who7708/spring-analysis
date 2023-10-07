package com.example.demo;

import java.net.URI;
import java.util.Properties;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/06/14
 */
public class TestUri {
    public static void main(String[] args) {

        final Properties properties = System.getProperties();
        System.out.println(properties);

        String trojanURLStr = "trojan://WpdvDYVuyqUfeZu68M@au1-1.sstr-api.xyz:443";

        URI trojanUri = null;
        try {
            trojanUri = new URI(trojanURLStr);
        } catch (java.net.URISyntaxException e) {
            e.printStackTrace();
        }
        String scheme = trojanUri.getScheme();
        // if (scheme == null) {
        // }
        // if (!scheme.equals("trojan"))
        //     return null;
        String host = trojanUri.getHost();
        int port = trojanUri.getPort();
        String userInfo = trojanUri.getUserInfo();
        String serverName = trojanUri.getPath();

        System.out.println(host);
        System.out.println(port);
        System.out.println(userInfo);
        System.out.println(serverName);

    }
}
