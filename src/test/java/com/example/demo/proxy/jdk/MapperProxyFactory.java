package com.example.demo.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author Chris
 * @date 2020/05/21
 * @since 1.0.0
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    protected T newInstance(MapperProxy<T> mapperProxy) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }

    public T newInstance() {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(mapperInterface);
        return newInstance(mapperProxy);
    }
}
