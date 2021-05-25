package src.othertest.designmodle.behavior.state;

import static src.othertest.designmodle.behavior.state.State.*;

/**
 * 状态模式
 *
 * 马里奥的不同形态就是状态机中的“状态”，
 * 游戏情节（比如吃了蘑菇）就是状态机中的“事件”，
 * 加减积分就是状态机中的“动作”
 *
 * 超级马里奥
 */
public enum State {
    SMALL(0),
    SUPER(1),
    FIRE(2),
    CAPE(3);

    private int value;

    private State(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

/**
 * 分支逻辑法，有大量if else
 */
class MarioStateMachine {
    private int score;
    private State currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = SMALL;
    }

    // 吃蘑菇
    public void obtainMushRoom() {
        if (currentState.equals(SMALL)) {
            this.currentState = SUPER;
            this.score += 100;
        }
    }

    // 获得斗篷
    public void obtainCape() {
        if (currentState.equals(SMALL) || currentState.equals(SUPER) ) {
            this.currentState = CAPE;
            this.score += 200;
        }
    }

    // 获得火焰
    public void obtainFireFlower() {
        if (currentState.equals(SMALL) || currentState.equals(SUPER) ) {
            this.currentState = State.FIRE;
            this.score += 300;
        }
    }

    // 遇到怪物
    public void meetMonster() {
        if (currentState.equals(SUPER)) {
            this.currentState = SMALL;
            this.score -= 100;
            return;
        }

        if (currentState.equals(CAPE)) {
            this.currentState = SMALL;
            this.score -= 200;
            return;
        }

        if (currentState.equals(State.FIRE)) {
            this.currentState = SMALL;
            this.score -= 300;
            return;
        }
    }

    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState;
    }
}

class ApplicationDemo {
    public static void main(String[] args) {
        MarioStateMachine mario = new MarioStateMachine();
        mario.obtainMushRoom();
        int score = mario.getScore();
        State state = mario.getCurrentState();
        System.out.println("mario score: " + score + "; state: " + state);
    }
}


/**
 * 查表法，有一定的局限性，如果要执行的动作并非这么简单，而是一系列复杂的逻辑操作（比如加减积分、写数据库，还有可能发送消息通知等等），我们就没法用如此简单的二维数组来表示了。
 */
enum Event {
    GOT_MUSHROOM(0),
    GOT_CAPE(1),
    GOT_FIRE(2),
    MET_MONSTER(3);

    private int value;

    private Event(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

class MarioStateMachine2 {
    private int score;
    private State currentState;

    private static final State[][] transitionTable = {
            {SUPER, CAPE, FIRE, SMALL},
            {SUPER, CAPE, FIRE, SMALL},
            {CAPE, CAPE, CAPE, SMALL},
            {FIRE, FIRE, FIRE, SMALL}
    };

    private static final int[][] actionTable = {
            {+100, +200, +300, +0},
            {+0, +200, +300, -100},
            {+0, +0, +0, -200},
            {+0, +0, +0, -300}
    };

    public void MarioStateMachine() {
        this.score = 0;
        this.currentState = SMALL;
    }

    public void obtainMushRoom() {
        executeEvent(Event.GOT_MUSHROOM);
    }

    public void obtainCape() {
        executeEvent(Event.GOT_CAPE);
    }

    public void obtainFireFlower() {
        executeEvent(Event.GOT_FIRE);
    }

    public void meetMonster() {
        executeEvent(Event.MET_MONSTER);
    }

    private void executeEvent(Event event) {
        int stateValue = currentState.getValue();
        int eventValue = event.getValue();
        this.currentState = transitionTable[stateValue][eventValue];
        this.score += actionTable[stateValue][eventValue];
    }

    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState;
    }

}
