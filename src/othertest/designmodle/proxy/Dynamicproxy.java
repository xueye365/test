package src.othertest.designmodle.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式（JDK自带动态代理）
 */
public class Dynamicproxy {


    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserService proxy = (UserService)new MyInvocationHandler(userService).getProxy();
        proxy.login("admin", "admin");
    }
}


//接口
interface UserService {
    void login(String username,String password);
}


class UserServiceImpl implements UserService{
    //JDK的动态代理必须要有接口，这是和cglib最大不同的地方
    @Override
    public void login(String username, String password) {
        if("admin".equals(username) && "admin".equals(password)) {
            System.out.println("登录成功");
        }
    }
}


class MyInvocationHandler implements InvocationHandler{


    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }


    public Object getProxy(){
        Object o = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
        return o;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---前置---");
        Object result = method.invoke(target, args);
        System.out.println("---后置---");
        return result;
    }
}


