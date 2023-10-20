package com.example.rxjava;

import rx.Observable;
import rx.Subscriber;

/**
 * @author Chris
 * @version 1.0.0
 * @date 2020/10/31
 */
public class Helloworld {
    public static void main(String[] args) {
        // 创建被观察者
        final Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello world");
                int i = 5 / 0;
                subscriber.onCompleted();
            }
        });
        // 创建观察者
        final Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext : " + s);
            }
        };
        // 订阅事件
        myObservable.subscribe(subscriber);
    }
}
