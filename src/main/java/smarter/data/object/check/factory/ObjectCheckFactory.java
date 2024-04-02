package smarter.data.object.check.factory;

import java.util.HashMap;
import java.util.Map;
import smarter.common.check.Check;
import smarter.data.object.check.Equal;
import smarter.data.object.check.UnEqual;

public class ObjectCheckFactory
{
  private final Map<String, Check<Object>> checkMap = new HashMap<>();
  private static final ObjectCheckFactory objectCheckFactory = new ObjectCheckFactory();
  
  private ObjectCheckFactory()
  {
    this.checkMap.put("=", new Equal());
    this.checkMap.put("!=", new UnEqual());
  }
  
  public static ObjectCheckFactory getInstance()
  {
    return objectCheckFactory;
  }
  
  public Check<Object> getCheck(String checkName)
  {
    return 
      (Check<Object>)objectCheckFactory.checkMap.get(checkName);
  }
}
