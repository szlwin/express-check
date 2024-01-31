import santr.parser.exception.ExecuteInvaildException;
import santr.v4.parser.ExpressParser;
import smarter.common.express.execute.SimpleExprParam;
import smarter.common.express.execute.SimpleExprVisitor;

import java.util.HashMap;
import java.util.Map;

public class TestGExpress {
    public static void main(String args[]) {
        SimpleExprParam simpleExprParam = new SimpleExprParam();
        Map<String, Object> map = new HashMap<>();
        map.put("totalPrice",350);
        simpleExprParam.setParamMap(map);

        simpleExprParam.setExternalMap(map);

        SimpleExprVisitor simpleExprVisitor = new SimpleExprVisitor();

        simpleExprVisitor.setParamer(simpleExprParam);

        ExpressParser lexerExecuter = new ExpressParser();

        lexerExecuter.addVisitor(simpleExprVisitor);

        try {
            lexerExecuter.parser("SimpleExpr",
                    "     #num : if !(totalPrice>10) then totalPrice*1.1  else totalPrice*1.2;\n" +
                    "     totalPrice : #num*totalPrice;");
        } catch (ExecuteInvaildException e) {
            e.printStackTrace();

        }
        System.out.println(map.get("totalPrice"));
    }
}
