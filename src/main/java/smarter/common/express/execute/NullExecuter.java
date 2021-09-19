package smarter.common.express.execute;

import santr.v4.parser.RuleContext;
import smarter.common.exception.ExecuteExpection;

public class NullExecuter
  extends AbstractExecuter
{
  public void execute(RuleContext context, Object param)
    throws ExecuteExpection
  {
    context.setValue(null);
  }
}