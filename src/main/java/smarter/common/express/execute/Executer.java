package smarter.common.express.execute;

import java.util.Map;
import santr.parser.exception.ExecuteInvaildException;
import santr.v4.parser.ExpressParser;

public class Executer
{
  
  public boolean execute(String expr, Map<String, Object> param)
    throws ExecuteInvaildException
  {
    ExpressParser lexerExecuter = new ExpressParser();
	ExprCVisitor exprVisitor = new ExprCVisitor();
			//ExprVisitor2 exprVisitor = new ExprVisitor2();
    //ExprVisitor exprVisitor = new ExprVisitor();
    exprVisitor.setParamer(param);
    lexerExecuter.addVisitor(exprVisitor);
    lexerExecuter.parser("expr", expr);
    return ((Boolean)lexerExecuter.getTree().getRuleContext().getValue()).booleanValue();
  }
}