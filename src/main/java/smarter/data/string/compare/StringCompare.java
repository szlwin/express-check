package smarter.data.string.compare;

import smarter.common.compare.AbstractCompare;
import smarter.data.string.check.factory.StringCheckFactory;

public class StringCompare
  extends AbstractCompare<String>
{
  public boolean compare(String src, String compareTag, String dest)
  {
    return 
    
      StringCheckFactory.getInstance().getCheck(compareTag).check(src, dest);
  }
  

}

