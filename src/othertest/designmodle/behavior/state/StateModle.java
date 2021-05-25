package src.othertest.designmodle.behavior.state;

public class StateModle {
}


/**
 * 状态模式
 */
interface IMario { //所有状态类的接口
    State getName();
    //以下是定义的事件
    void obtainMushRoom();
    void obtainCape();
    void obtainFireFlower();
    void meetMonster();
}

class SmallMario implements IMario {
    private MarioStateMachine3 stateMachine;

    public SmallMario(MarioStateMachine3 stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public State getName() {
        return State.SMALL;
    }

    @Override
    public void obtainMushRoom() {
//        stateMachine.setCurrentState(new SuperMario(stateMachine));
//        stateMachine.setScore(stateMachine.getScore() + 100);
    }

    @Override
    public void obtainCape() {
//        stateMachine.setCurrentState(new CapeMario(stateMachine));
//        stateMachine.setScore(stateMachine.getScore() + 200);
    }

    @Override
    public void obtainFireFlower() {
//        stateMachine.setCurrentState(new FireMario(stateMachine));
//        stateMachine.setScore(stateMachine.getScore() + 300);
    }

    @Override
    public void meetMonster() {
        // do nothing...
    }
}

class SuperMario implements IMario {
    private MarioStateMachine3 stateMachine;

    public SuperMario(MarioStateMachine3 stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public State getName() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRoom() {
        // do nothing...
    }

    @Override
    public void obtainCape() {
//        stateMachine.setCurrentState(new CapeMario(stateMachine));
//        stateMachine.setScore(stateMachine.getScore() + 200);
    }

    @Override
    public void obtainFireFlower() {
//        stateMachine.setCurrentState(new FireMario(stateMachine));
//        stateMachine.setScore(stateMachine.getScore() + 300);
    }

    @Override
    public void meetMonster() {
//        stateMachine.setCurrentState(new SmallMario(stateMachine));
//        stateMachine.setScore(stateMachine.getScore() - 100);
    }
}

// 省略CapeMario、FireMario类...

class MarioStateMachine3 {
    private int score;
    private IMario currentState; // 不再使用枚举来表示状态

    public MarioStateMachine3() {
        this.score = 0;
        this.currentState = new SmallMario(this);
    }

    public void obtainMushRoom() {
        this.currentState.obtainMushRoom();
    }

    public void obtainCape() {
        this.currentState.obtainCape();
    }

    public void obtainFireFlower() {
        this.currentState.obtainFireFlower();
    }

    public void meetMonster() {
        this.currentState.meetMonster();
    }

    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState.getName();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCurrentState(IMario currentState) {
        this.currentState = currentState;
    }
}