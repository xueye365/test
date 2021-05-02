package src.othertest.designmodle.behavior.observer;


import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 *
 * 经典模版
 *
 */
public class Observe {
}


interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}

interface Observer {
    void update(String message);
}

class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

}

class ConcreteObserverOne implements Observer {
    @Override
    public void update(String message) {
        System.out.println(message);
    }
}

class ConcreteObserverTwo implements Observer {
    @Override
    public void update(String message) {
        System.out.println(message);
    }
}

class Demo {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserverOne());
        subject.registerObserver(new ConcreteObserverTwo());
        subject.notifyObservers("message");
    }
}





/**
 * 观察者模式优化后的代码
 */
interface RegObserver {
    void handleRegSuccess(long userId);
}
class RegPromotionObserver implements RegObserver {
    @Override
    public void handleRegSuccess(long userId) {
        System.out.println("doSomething");
    }
}
class RegNotificationObserver implements RegObserver {
    @Override
    public void handleRegSuccess(long userId) {
        System.out.println("doSomething");
    }
}

class UserController {
    private UserService userService; // 依赖注入
    private List<RegObserver> regObservers = new ArrayList<>();

    // 一次性设置好，之后也不可能动态的修改
    public void setRegObservers(List<RegObserver> observers) {
        regObservers.addAll(observers);
    }

    public Long register(String telephone, String password) {
        //省略输入参数的校验代码
        //省略userService.register()异常的try-catch代码
        long userId = userService.register(telephone, password);

        for (RegObserver observer : regObservers) {
            observer.handleRegSuccess(userId);
        }

        return userId;
    }
}

class UserService {
    public Long register(String telephone, String password) {
        System.out.println("register ok");
        return 1L;
    }
}

