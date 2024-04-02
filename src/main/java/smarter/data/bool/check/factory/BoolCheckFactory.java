package smarter.data.bool.check.factory;

import java.util.HashMap;
import java.util.Map;
import smarter.common.check.Check;
import smarter.data.bool.check.Equal;
import smarter.data.bool.check.UnEqual;

public class BoolCheckFactory
{
  private final Map<String, Check<Boolean>> checkMap = new HashMap<>();
  private static final BoolCheckFactory boolCheckFactory = new BoolCheckFactory();
  
  private BoolCheckFactory()
  {
    this.checkMap.put("=", new Equal());
    this.checkMap.put("!=", new UnEqual());
  }
  
  public static BoolCheckFactory getInstance()
  {
    return boolCheckFactory;
  }
  
  public Check<Boolean> getCheck(String checkName)
  {
    return 
      (Check<Boolean>)boolCheckFactory.checkMap.get(checkName);
  }
}
