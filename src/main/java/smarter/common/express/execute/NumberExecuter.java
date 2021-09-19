package smarter.common.express.execute;

import santr.v4.parser.RuleContext;
import smarter.common.exception.ExecuteExpection;

public class NumberExecuter
  extends AbstractExecuter
{
  public void execute(RuleContext context, Object param)
    throws ExecuteExpection
  {
    context.setValue(Double.valueOf(context.getText()));
  }
}
