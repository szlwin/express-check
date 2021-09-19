package smarter.common.express.execute;

import santr.parser.exception.ExecuteInvaildException;
import santr.v4.parser.RuleContext;
import smarter.common.exception.ExecuteExpection;

public class BooleanExecuter
  extends AbstractExecuter
{
  public void execute(RuleContext context, Object param)
    throws ExecuteExpection
  {
    String text = null;
    try
    {
      text = (String)getChildValue(context, 0);
    }
    catch (ExecuteInvaildException e)
    {
      throw new ExecuteExpection(e, e.getMessage());
    }
    context.setValue(Boolean.valueOf(text));
  }
}
