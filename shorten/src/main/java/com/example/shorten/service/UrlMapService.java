package com.example.shorten.service;

import com.example.shorten.dao.UrlMapDao;
import com.example.shorten.model.UrlMap;
import com.example.shorten.utils.Base62Utils;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class UrlMapService {

    @Resource
    UrlMapDao urlMapDao;

    LoadingCache<String, String> loadingCache;

    @PostConstruct
    public void init() {
        CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                long id = Base62Utils.shortKeyToId(s);
                log.info("load cache: {}", s);
                return Optional.ofNullable(urlMapDao.findOne(id))
                        .map(UrlMap::getLongUrl).orElse(null);
            }
        };
        loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000000) // 设置最大缓存大小
                .build(cacheLoader);
    }

    public String encode(String longUrl) {
        UrlMap urlMap = urlMapDao.findFirstByLongUrl(longUrl);
        if (urlMap == null) {
            Date date = DateUtils.addDays(new Date(), 30);
            urlMap = urlMapDao.save(UrlMap.builder()
                    .longUrl(longUrl)
                    .expireTime(date)
                    .build());
            log.info("create urlMap:{}", urlMap);
        }
        return Base62Utils.idToShortKey(urlMap.getId());
    }

    public Optional<String> decode(String shortKey) {
        long id = Base62Utils.shortKeyToId(shortKey);
        // return Optional.ofNullable(urlMapDao.findOne(id)).map(UrlMap::getLongUrl);
        // return urlMapDao.findById(id).map(UrlMap::getLongUrl);
        return Optional.of(loadingCache.getUnchecked(shortKey));
    }
}