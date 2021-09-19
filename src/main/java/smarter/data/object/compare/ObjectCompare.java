package smarter.data.object.compare;

import smarter.common.compare.AbstractCompare;
import smarter.data.object.check.factory.ObjectCheckFactory;

public class ObjectCompare
  extends AbstractCompare<Object>
{
  public boolean compare(Object src, String compareTag, Object dest)
  {
    return 
    
      ObjectCheckFactory.getInstance().getCheck(compareTag).check(src, dest);
  }
}
