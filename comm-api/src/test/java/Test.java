import org.apache.commons.lang3.StringEscapeUtils;
import org.mvel2.MVEL;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yushaobo
 * @create 2019-04-02 20:55
 **/
public class Test {
    public static void main(String[] args) {
        String expression = "if (\"美团外卖\".contains(param)) {return \"Greater than zero!\"; } else if (param == -1) { return \"Minus one!\"; } else { return \"Something else!\"; }";
//        expression=expression.replace("\"", "\\"+"\"");
        Map<String, Object> paramMap = new  HashMap();
        paramMap.put("param", "美团外卖");
        Object object = MVEL.eval(expression, paramMap);
        System.out.println(object); //
        String str = "{\\\"name\\\":\\\"spy\\\",\\\"id\\\"}";
        System.out.println("原始 str = " + str);
        String str1 = StringEscapeUtils.unescapeJava(expression);
        System.out.println("目标 str1 = " + str1);
//对应方法的StringEscapeUtils.escapeJava(str1);
//可将str1转义回str
    }

    public static void doMveltest(){
        String expression ="foobar > 99";
        // Compile the expression.
        Serializable compiled =MVEL.compileExpression(expression);
        Map vars = new HashMap();
        vars.put("foobar",new Integer(100));
        // Now we execute it.
        Boolean result = (Boolean)MVEL.executeExpression(compiled, vars);
        if (result.booleanValue()) {
            System.out.println("Itworks!");
        }
    }
}
