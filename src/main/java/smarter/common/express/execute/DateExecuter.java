package smarter.common.express.execute;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import santr.v4.parser.RuleContext;
import smarter.common.exception.ExecuteExpection;

public class DateExecuter
  extends AbstractExecuter
{
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
  
  public void execute(RuleContext context, Object param)
    throws ExecuteExpection
  {
    String dateStr = context.getText();
    try
    {
      if (dateStr.length() > 10) {
        context.setValue(timeFormat.parse(dateStr));
      } else {
        context.setValue(dateFormat.parse(dateStr));
      }
    }
    catch (ParseException e)
    {
      throw new ExecuteExpection(e, "Parser the [" + dateStr + "] is error!");
    }
  }
}
