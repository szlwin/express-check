package smarter.data.date.check.factory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import smarter.common.check.Check;
import smarter.data.date.check.Equal;
import smarter.data.date.check.Greater;
import smarter.data.date.check.GreaterE;
import smarter.data.date.check.Letter;
import smarter.data.date.check.LetterE;
import smarter.data.date.check.NotNull;
import smarter.data.date.check.UnEqual;

public class DateCheckFactory
{
  private final Map<String, Check<Date>> checkMap = new HashMap<>();
  private static final DateCheckFactory dataCheckFactory = new DateCheckFactory();
  
  private DateCheckFactory()
  {
    this.checkMap.put("!", new NotNull());
    this.checkMap.put("<=", new LetterE());
    this.checkMap.put("<", new Letter());
    this.checkMap.put(">=", new GreaterE());
    this.checkMap.put(">", new Greater());
    this.checkMap.put("=", new Equal());
    this.checkMap.put("!=", new UnEqual());
  }
  
  public static DateCheckFactory getInstance()
  {
    return dataCheckFactory;
  }
  
  public Check<Date> getCheck(String checkName)
  {
    return 
      (Check<Date>)dataCheckFactory.checkMap.get(checkName);
  }
}
