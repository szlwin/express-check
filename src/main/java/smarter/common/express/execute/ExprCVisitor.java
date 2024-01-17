package smarter.common.express.execute;

import santr.common.context.LexerUtil;
import santr.parser.exception.ExecuteInvaildException;
import santr.v4.execute.AbstractVisitor;
import santr.v4.parser.ParserTree;
import santr.v4.parser.RuleContext;
import smarter.common.util.CheckUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExprCVisitor extends AbstractVisitor<Object> {

    static {
        try {
            LexerUtil.load("expr",
                    smarter.common.express.execute.ExprCVisitor.class
                            .getClassLoader()
                            .getResourceAsStream("Expr.ls"));
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    private static final Map<String, Complute> compluteMap
            = new HashMap<String, Complute>();
    //private static final Map<String, NumberCompare> numberCompareMap
    //    = new HashMap<String, NumberCompare>();
    //private static final Map<String, StringCompare> stringCompareMap
    //   = new HashMap<String, StringCompare>();

    static {
        Complute[] allComplute = Complute.values();

        for (Complute complute : allComplute) {

            compluteMap.put(complute.getOperator(), complute);
        }

        //NumberCompare[] allNumberCompare = NumberCompare.values ();

        //for (NumberCompare numberCompare : allNumberCompare) {

        // 	numberCompareMap.put(numberCompare.getOperator(), numberCompare);
        // }

        //StringCompare[] allStringCompare = StringCompare.values ();

        //for (StringCompare stringCompare : allStringCompare) {
        //
        //	stringCompareMap.put(stringCompare.getOperator(), stringCompare);
        // }
    }

    public enum Complute {

        ADD("+") {
            double eval(double x, double y) {
                return x + y;
            }
        },
        SUB("-") {
            double eval(double x, double y) {
                return x - y;
            }
        },
        MUL("*") {
            double eval(double x, double y) {
                return x * y;
            }
        },
        DIV("/") {
            double eval(double x, double y) {
                return x / y;
            }
        };

        abstract double eval(double x, double y);

        private String token;

        Complute(String token) {
            this.token = token;
        }

        public String getOperator() {
            return token;
        }
    }
    /*public enum NumberCompare{   
        EQUAL("="){boolean eval(Double x,Double y){return x==y;}},   
        NOTEQUAL("!="){boolean eval(Double x,Double y){return x!=y;}},   
        LETTER("<"){boolean eval(Double x,Double y){return x<y;}},   
        LETTERE("<="){boolean eval(Double x,Double y){return x<=y;}},
        GREATER(">"){boolean eval(Double x,Double y){return x>y;}},
        GREATERE(">="){boolean eval(Double x,Double y){return x>=y;}}
        ;
        abstract boolean eval(Double x,Double y);
        
        private String token;
        
        NumberCompare(String token){
        	this.token = token;
        }
        
        public String getOperator(){
        	return token;
        }
    }
    
    public enum StringCompare{   
        EQUAL("="){boolean eval(String x,String y){return x.equals(y);}},   
        NOTEQUAL("!="){boolean eval(String x,String y){return !x.equals(y);}}
        ;
        abstract boolean eval(String x,String y);
        
        private String token;
        
        StringCompare(String token){
        	this.token = token;
        }
        
        public String getOperator(){
        	return token;
        }
    }*/

    //private Map<String,Object> paramMap = new HashMap<String,Object>();
    public void execute(RuleContext context) throws ExecuteInvaildException {
        //Get the tree name.
        String name = context.getName();
        switch (name) {
            case "expr":
                executeExpr(context);
                break;
            case "NULL":
                executeNULL(context);
                break;
            case "fun":
                executeFun(context);
                break;
            case "array":
                executeArray(context);
                break;
            case "param":
                executeParam(context);
                break;
            case "INT":
                executeINT(context);
                break;
            case "ID":
                executeID(context);
                break;
            case "STRING":
                executeString(context);
                break;
        }
        /*if(name.equals("expr")){
        	executeExpr(context);
        }else if(name.equals("fun")){
        	executeFun(context);
        }else if(name.equals("array")){
            executeArray(context);
        }else if(name.equals("param")){
            executeParam(context);
        }else if(name.equals("INT")){
            executeINT(context);
        }else if(name.equals("ID")){
            executeID(context);
        }else if(name.equals("STRING")){
            executeString(context);
        }*/

    }

    //public void setParam(Map<String,Object> paramMap){
    //    this.paramMap.putAll(paramMap);
    //}

    private void executeNULL(RuleContext context) throws ExecuteInvaildException {
        context.setValue(null);
    }

    private void executeExpr(RuleContext context) throws ExecuteInvaildException {
        if (context.getChildCount() == 1) {
            context.setValue(this.getChildValue(context, 0));
        } else if (context.getChildCount() == 2) {
            String token = (String) getChildValue(context, 0);
            if (token.equals("!")) {
                context.setValue(Boolean.valueOf(!((Boolean) getChildValue(context, 1)).booleanValue()));
            } else {
                Number num = (Number) getChildValue(context, 1);
                context.setValue(Double.valueOf(0.0D - num.doubleValue()));
            }
        } else {
            //Get all the tree info.
            ParserTree[] treeInfoList = this.getAllChild(context);

            if (treeInfoList[0].isToken()) {
                //( expr )
                context.setValue(this.getChildValue(treeInfoList[1]));
            } else {
                String token = treeInfoList[1].getToken();
                if (compluteMap.containsKey(token)) {
                    //expr ('+'|'-') expr
                    // expr ('*'|'/') expr
                    context.setValue(
                            compluteMap.get(token).eval(
                                    ((Double) this.getChildValue(treeInfoList[0]))
                                            .doubleValue(),
                                    ((Double) this.getChildValue(treeInfoList[2]))
                                            .doubleValue()));
                } else if (token.equals("and")) {
                    //expr and expr
                    context.setValue((Boolean) this.getChildValue(treeInfoList[0]) &&
                            (Boolean) this.getChildValue(treeInfoList[2]));
                } else if (token.equals("or")) {
                    //expr or expr
                    context.setValue((Boolean) this.getChildValue(treeInfoList[0]) ||
                            (Boolean) this.getChildValue(treeInfoList[2]));
                } else if (token.equals(".")) {
                    if (getParamer() instanceof Map) {
                        Map<String, Object> subParam = (Map) getParamer();
                        for (int i = 0; i < context.getChildCount() - 1; i++) {
                            ParserTree tmpTree = context.getChild(i);
                            if (!tmpTree.isToken()) {
                                String key = tmpTree.getRuleContext().getText();
                                subParam = (Map) subParam.get(key);
                            }
                        }
                        ParserTree lastTree = context.getChild(context.getChildCount() - 1);
                        String lastKey = lastTree.getRuleContext().getText();
                        Object object = subParam.get(lastKey);
                        context.setValue(object);
                    } else {
                        Object subParam = getParamer();
                        for (int i = 0; i < context.getChildCount(); i++) {
                            ParserTree tmpTree = context.getChild(i);
                            if (!tmpTree.isToken()) {
                                String key = tmpTree.getRuleContext().getText();
                                try{
                                    Field field = subParam.getClass().getDeclaredField(key);
                                    field.setAccessible(true);
                                    subParam = field.get(subParam);
                                }catch (Exception e){
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        context.setValue(subParam);
                    }
                    return;
                } else {
                    //expr ('='|'!='|'>='|'<='|'>'|'<') expr
                    Object left = this.getChildValue(treeInfoList[0]);
                    Object right = this.getChildValue(treeInfoList[2]);
            		/*if(left instanceof String){
            			context.setValue(
                    			stringCompareMap.get(token).eval((String)left,(String)right));
            		}else{
            			context.setValue(
                    			numberCompareMap.get(token).eval((Double)left,(Double)right));
            		}*/
                    context.setValue(CheckUtil.compareByTag(left, token, right));
                }
            }
        }
    }

    private void executeFun(RuleContext context) throws ExecuteInvaildException {
        //Get the all param value.
        List<Double> list = (List<Double>) this.getChildValue(context, 2);

        int flag = 0;
        String funName = (String) this.getChildValue(context, 0);

        if (funName.equals("max")) {
            flag = 1;
        } else if (funName.equals("min")) {
            flag = -1;
        }

        double value = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            double num = list.get(i);
            if ((num - value) * flag > 0) {
                value = num;
            }
        }
        context.setValue(value);
    }

    private void executeArray(RuleContext context) throws ExecuteInvaildException {
        List<Object> list = new ArrayList<Object>();
        ParserTree[] treeInfoList = this.getAllChild(context);
        //param (',' param)*;
        for (int i = 0; i < treeInfoList.length; i++) {
            if (!treeInfoList[i].isToken()) {
                list.add(this.getChildValue(treeInfoList[i]));
            }
        }
        context.setValue(list);
    }

    private void executeParam(RuleContext context) throws ExecuteInvaildException {
        if (context.getChildCount() == 1) {
            //ID | INT| fun| expr 
            context.setValue(this.getChildValue(context, 0));
        } else {
            // ID  '[' INT ']'
            List<Double> paramList = (List<Double>) this.getChildValue(context, 0);
            context.setValue(paramList.get((Integer) this.getChildValue(context, 2)));
        }
    }

    private void executeINT(RuleContext context) {
        //Save the value to this tree.
        if (context.getText() != null) {
            context.setValue(Double.valueOf(context.getText()));
        }
    }

    private void executeID(RuleContext context) {
        //Save the value to this tree.
        if (this.getParamer() instanceof Map) {
            context.setValue(((Map<String, Object>) this.getParamer())
                    .get(context.getText()));
        } else {
            try {
                Field field = this.getParamer().getClass().getDeclaredField(context.getText());
                field.setAccessible(true);
                context.setValue(field.get(this.getParamer()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void executeString(RuleContext context) {
        //Save the value to this tree.
        context.setValue(context.getText());
    }
}
