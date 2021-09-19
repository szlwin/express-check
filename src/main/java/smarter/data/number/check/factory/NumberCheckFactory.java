package smarter.data.number.check.factory;

import java.util.HashMap;
import java.util.Map;
import smarter.common.check.Check;
import smarter.data.number.check.Equal;
import smarter.data.number.check.Greater;
import smarter.data.number.check.GreaterE;
import smarter.data.number.check.Letter;
import smarter.data.number.check.LetterE;
import smarter.data.number.check.NotNull;
import smarter.data.number.check.UnEqual;

public class NumberCheckFactory
{
  private final Map<String, Check<Number>> checkMap = new HashMap<>();
  private static final NumberCheckFactory stringCheckFactory = new NumberCheckFactory();
  
  private NumberCheckFactory()
  {
    this.checkMap.put("!", new NotNull());
    this.checkMap.put("<=", new LetterE());
    this.checkMap.put("<", new Letter());
    this.checkMap.put(">=", new GreaterE());
    this.checkMap.put(">", new Greater());
    this.checkMap.put("=", new Equal());
    this.checkMap.put("!=", new UnEqual());
  }
  
  public static NumberCheckFactory getInstance()
  {
    return stringCheckFactory;
  }
  
  public Check<Number> getCheck(String checkName)
  {
    return 
      (Check<Number>)stringCheckFactory.checkMap.get(checkName);
  }
}
