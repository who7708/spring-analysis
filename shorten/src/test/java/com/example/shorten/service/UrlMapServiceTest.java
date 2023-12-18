package com.example.shorten.service;

import com.example.shorten.dao.UrlMapDao;
import com.example.shorten.model.UrlMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023/12/18
 */
@RunWith(MockitoJUnitRunner.class)
public class UrlMapServiceTest {

    @InjectMocks
    UrlMapService urlMapService;

    // @Mock
    @Spy
    UrlMapDao urlMapDao;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void encode() {
        String longUrl = "https://www.baidu.com";
        // Mockito.mock(UrlMapDao.class);
        Mockito.when(urlMapDao.save(Mockito.any()))
                .thenReturn(new UrlMap(1L, longUrl, new Date(), new Date()));

        String encode = urlMapService.encode(longUrl);
        System.out.println("encode = " + encode);
    }

    @Test
    public void encode2() {
        String longUrl = "https://www.baidu.com";
        // Mockito.mock(UrlMapDao.class);
        UrlMap urlMap = new UrlMap(1L, longUrl, new Date(), new Date());
        // Mockito.when(urlMapDao.findFirstByLongUrl(longUrl))
        //         .thenReturn(new UrlMap(1L, longUrl, new Date(), new Date()));
        Mockito.doReturn(urlMap)
                .when(urlMapDao)
                .findFirstByLongUrl(longUrl);

        String encode = urlMapService.encode(longUrl);
        System.out.println("encode = " + encode);
    }

    @Test
    public void decode() {
    }

    @Test
    public void mockitoTest2() {
        // 生成一个mock对象
        List<String> mockedList = Mockito.mock(ArrayList.class);
        // 打印mock对象的类名，看看mock对象为何物
        System.out.println("mock List===========" + mockedList.getClass().getName());
        // 操作mock对象
        mockedList.add("one");
        System.out.println("0 agr:" + mockedList.get(0));
        System.out.println("10 agr:" + mockedList.get(10));


        // 生成一个spy对象
        List<String> spyList = Mockito.spy(ArrayList.class);
        // 打印mock对象的类名，看看spy对象为何物
        System.out.println("spy List============" + spyList.getClass().getName());
        // 操作mock对象
        spyList.add("one");
        System.out.println("0 agr:" + spyList.get(0));
        System.out.println("10 agr:" + spyList.get(10));
    }

}