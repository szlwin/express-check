package smarter.data.string.check.factory;

import java.util.HashMap;
import java.util.Map;
import smarter.common.check.Check;
import smarter.data.string.check.Equal;
import smarter.data.string.check.Match;
import smarter.data.string.check.UnEmpty;
import smarter.data.string.check.UnEqual;
import smarter.data.string.check.UnMatch;

public class StringCheckFactory
{
  private final Map<String, Check<String>> checkMap = new HashMap<>();
  private static final StringCheckFactory stringCheckFactory = new StringCheckFactory();
  
  private StringCheckFactory()
  {
    this.checkMap.put("!|=", new UnMatch());
    this.checkMap.put("|=", new Match());
    this.checkMap.put("=", new Equal());
    this.checkMap.put("!=", new UnEqual());
    this.checkMap.put("!", new UnEmpty());
  }
  
  public static StringCheckFactory getInstance()
  {
    return stringCheckFactory;
  }
  
  public Check<String> getCheck(String checkName)
  {
    return 
      (Check<String>)stringCheckFactory.checkMap.get(checkName);
  }
}
