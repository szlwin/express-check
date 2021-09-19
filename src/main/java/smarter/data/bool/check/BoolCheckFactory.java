package smarter.data.bool.check;

import java.util.HashMap;
import java.util.Map;
import smarter.common.check.Check;

public class BoolCheckFactory
{
  private final Map<String, Check<Boolean>> checkMap = new HashMap();
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
      (Check)boolCheckFactory.checkMap.get(checkName);
  }
}
