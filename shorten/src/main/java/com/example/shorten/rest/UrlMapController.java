package com.example.shorten.rest;

import com.example.shorten.common.ResponseResult;
import com.example.shorten.service.UrlMapService;
import com.example.shorten.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UrlMapController {
    private static final String DOMAIN = "http://127.0.0.1:8888/";

    @Resource
    private UrlMapService urlMapService;

    /***
     * 长链接转短链接
     * @param longUrl 长链接
     * @return ResponseResult
     */
    @PostMapping("/shorten")
    public ResponseResult<Map<String, String>> shorten(@RequestParam("url") String longUrl) {
        String encode = urlMapService.encode(longUrl);
        Map<String, String> map = new HashMap<>();
        map.put("shortKey", encode);
        map.put("shortUrl", DOMAIN + encode);
        return ResultUtils.success(map);
    }

    /***
     * 短链接重定向
     * @param shortKey 短链接
     * @return RedirectView
     */
    @GetMapping("/{shortKey}")
    public RedirectView redirect(@PathVariable("shortKey") String shortKey) {
        return urlMapService.decode(shortKey)
                .map(RedirectView::new)
                .orElse(new RedirectView("/sorry"));
    }

    @GetMapping("/sorry")
    public String sorry() {
        return "抱歉，未找到页面！";
    }
}