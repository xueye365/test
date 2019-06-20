package src.othertest.designmodle.decorator;

public interface Decorator {
    void doSomething();
}

class Monkey implements Decorator {
    @Override
    public void doSomething() {
        //代码
        System.out.println("孙悟空");
    }

}

class Change implements Decorator {
    private Decorator sage;

    public Change(Decorator sage){
        this.sage = sage;
    }
    @Override
    public void doSomething() {
        // 代码
        sage.doSomething();
    }

}


class Fish extends Change {

    public Fish(Decorator sage) {
        super(sage);
    }

    @Override
    public void doSomething() {
        // 代码
        System.out.println("Fish Move");
    }
}

class Bird extends Change {

    public Bird(Decorator sage) {
        super(sage);
    }

    @Override
    public void doSomething() {
        // 代码
        System.out.println("Bird Move");
    }
}









