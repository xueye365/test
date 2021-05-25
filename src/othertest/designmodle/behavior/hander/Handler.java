package src.othertest.designmodle.behavior.hander;

/**
 * 第一种实现方式：使用链表来保存处理器
 */
public abstract class Handler {
    protected Handler successor = null;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handle();
}



class HandlerA extends Handler {
    @Override
    public void handle() {
        boolean handled = false;
        //...
        if (!handled && successor != null) {
            successor.handle();
        }
    }
}

class HandlerB extends Handler {
    @Override
    public void handle() {
        boolean handled = false;
        //...
        if (!handled && successor != null) {
            successor.handle();
        }
    }
}

class HandlerChain {
    private Handler head = null;
    private Handler tail = null;

    public void addHandler(Handler handler) {
        handler.setSuccessor(null);

        if (head == null) {
            head = handler;
            tail = handler;
            return;
        }

        tail.setSuccessor(handler);
        tail = handler;
    }

    public void handle() {
        if (head != null) {
            head.handle();
        }
    }
}

// 使用举例
class Application {
    public static void main(String[] args) {
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());
        chain.handle();
    }
}


/**
 * 第二种实现方式
 */
abstract class Handler2 {
    protected Handler2 successor = null;

    public void setSuccessor(Handler2 successor) {
        this.successor = successor;
    }

    public final void handle() {
        boolean handled = doHandle();
        if (successor != null && !handled) {
            successor.handle();
        }
    }

    protected abstract boolean doHandle();
}

class HandlerA2 extends Handler2 {
    @Override
    protected boolean doHandle() {
        boolean handled = false;
        //...
        return handled;
    }
}

class HandlerB2 extends Handler2 {
    @Override
    protected boolean doHandle() {
        boolean handled = false;
        //...
        return handled;
    }
}

// HandlerChain和Application代码不变