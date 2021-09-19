package smarter.common.express.execute;

import java.util.Map;

import santr.v4.parser.RuleContext;
import smarter.common.exception.ExecuteExpection;

public class ParamArrExecuter
  extends AbstractExecuter
{
  public void execute(RuleContext context, Object param)
    throws ExecuteExpection
  {
    int size = context.getChildCount();
    Map<String, Object> value = (Map)param;
    for (int i = 0; i < size - 1;)
    {
      value = (Map)value.get(context.getChild(i).getRuleContext().getText());
      
      i += 2;
    }
    context.setValue(value.get(context.getChild(size - 1).getRuleContext().getText()));
  }
}
