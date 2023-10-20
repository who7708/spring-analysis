package org.spring.boot.test.io;

import java.util.ArrayList;
import java.util.List;

// 抽象主题
interface Subject {
    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers();
}

// 具体主题
class ConcreteSubject implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}

// 抽象观察者
interface Observer {
    void update(Subject subject);
}

// 具体观察者
class ConcreteObserver implements Observer {

    private int state;

    @Override
    public void update(Subject subject) {
        if (subject instanceof ConcreteSubject) {
            state = ((ConcreteSubject) subject).getState();
            System.out.println("State changed to " + state);
        }
    }
}

// 示例代码
public class ExampleObserver {

    public static void main(String[] args) {
        // 创建主题和观察者
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserver();
        Observer observer2 = new ConcreteObserver();

        // 注册观察者
        subject.attach(observer1);
        subject.attach(observer2);

        // 改变主题状态，观察者将收到通知并更新自己的状态
        subject.setState(1);
        subject.setState(2);

        // 注销观察者
        subject.detach(observer2);

        // 再次改变主题状态，此时只有 observer1 收到通知
        subject.setState(3);
    }
}
