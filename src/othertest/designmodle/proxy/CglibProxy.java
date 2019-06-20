package src.othertest.designmodle.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy {

    public static void main(String[] args) {
        CglibProxy.getUserServiceProxy().login("admin", "admin");
    }

    private CglibProxy(){}

    public static UserService getUserServiceProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new MyCglibProxy());
        return (UserService) enhancer.create();
    }

}



class MyCglibProxy implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("---前置---");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("---后置---");
        return result;
    }
}









