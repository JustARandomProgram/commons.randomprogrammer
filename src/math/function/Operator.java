package math.function;

public class Operator {
    public static final String[] VALID_OPERATORS = {
        "sin","cos","tan","asin","acos","atan","log","ln"
    };
    public static final String[] PARAM_OPERATORS = {
        "log_base","*","/","-","+"
    };

    public static int operatorType(String operator) {
        for (String string : VALID_OPERATORS) {
            if (operator.equalsIgnoreCase(string)) return 1;
        }
        for (String string : PARAM_OPERATORS) {
            if (operator.equalsIgnoreCase(string)) return 2;
        }
        return 0;
    }

    public String operation;
    public double param;

    Operator(String operation) {
        this.operation = operation;
    }

    Operator(String operation, double param) {
        this.operation = operation;
        this.param = param;
    }

    public String parse() {
        int operatorType = Operator.operatorType(operation);
        if (operatorType == 1) {
            return operation;
        } else if (operatorType == 2) {
            return operation + ":" + param;
        }
        return "";
    }
}