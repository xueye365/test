package src.othertest.designmodle.behavior.interpreter;


import sun.tools.tree.EqualExpression;
import sun.tools.tree.LessExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlertRuleInterpreter {
}


/**
 * 自定义接口告警规则功能
 */
interface Expression2 {
    boolean interpret(Map<String, Long> stats);
}

class GreaterExpression implements Expression2 {
    private String key;
    private long value;

    public GreaterExpression(String strExpression) {
        String[] elements = strExpression.trim().split("\\s+");
        if (elements.length != 3 || !elements[1].trim().equals(">")) {
            throw new RuntimeException("Expression is invalid: " + strExpression);
        }
        this.key = elements[0].trim();
        this.value = Long.parseLong(elements[2].trim());
    }

    public GreaterExpression(String key, long value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        if (!stats.containsKey(key)) {
            return false;
        }
        long statValue = stats.get(key);
        return statValue > value;
    }
}

// LessExpression/EqualExpression跟GreaterExpression代码类似，这里就省略了

class AndExpression implements Expression2 {
    private List<Expression2> expressions = new ArrayList<>();

    public AndExpression(String strAndExpression) {
        String[] strExpressions = strAndExpression.split("&&");
        for (String strExpr : strExpressions) {
            if (strExpr.contains(">")) {
                expressions.add(new GreaterExpression(strExpr));
            } else if (strExpr.contains("<")) {
//                expressions.add(new LessExpression(strExpr));
            } else if (strExpr.contains("==")) {
//                expressions.add(new EqualExpression(strExpr));
            } else {
                throw new RuntimeException("Expression is invalid: " + strAndExpression);
            }
        }
    }

    public AndExpression(List<Expression2> expressions) {
        this.expressions.addAll(expressions);
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        for (Expression2 expr : expressions) {
            if (!expr.interpret(stats)) {
                return false;
            }
        }
        return true;
    }

}

class OrExpression implements Expression2 {
    private List<Expression2> expressions = new ArrayList<>();

    public OrExpression(String strOrExpression) {
        String[] andExpressions = strOrExpression.split("\\|\\|");
        for (String andExpr : andExpressions) {
            expressions.add(new AndExpression(andExpr));
        }
    }

    public OrExpression(List<Expression2> expressions) {
        this.expressions.addAll(expressions);
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        for (Expression2 expr : expressions) {
            if (expr.interpret(stats)) {
                return true;
            }
        }
        return false;
    }
}

