package src.othertest.designmodle.behavior.observer;


import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * Guava EventBus框架来实现观察者模式
 *
 */
public class EventBuss {
}



class UserController2 {
    private UserService userService; // 依赖注入

    private EventBus eventBus;
    private static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 20;

    public UserController2() {
        //eventBus = new EventBus(); // 同步阻塞模式
        eventBus = new AsyncEventBus(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE)); // 异步非阻塞模式
    }

    public void setRegObservers(List<Object> observers) {
        for (Object observer : observers) {
            eventBus.register(observer);
        }
    }

    public Long register(String telephone, String password) {
        //省略输入参数的校验代码
        //省略userService.register()异常的try-catch代码
        long userId = userService.register(telephone, password);

        eventBus.post(userId);

        return userId;
    }
}

class RegPromotionObserver2 {

    @Subscribe
    public void handleRegSuccess(Long userId) {
        System.out.println("promotionService.issueNewUserExperienceCash(userId)");
    }
}

class RegNotificationObserver2 {

    @Subscribe
    public void handleRegSuccess(Long userId) {
        System.out.println("notificationService.sendInboxMessage(userId)");
    }
}