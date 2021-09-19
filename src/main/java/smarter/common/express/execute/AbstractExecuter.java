package smarter.common.express.execute;

import java.util.Map;
import santr.v4.parser.RuleContext;
import smarter.common.exception.ExecuteExpection;

public abstract class AbstractExecuter
  extends ExprVisitor2
  implements CheckExecuter
{



  public void execute(RuleContext context, Object param)
    throws ExecuteExpection
  {
    setParamer((Map)param);
  }
}