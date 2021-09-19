package smarter.data.bool.compare;

import smarter.common.compare.AbstractCompare;
import smarter.data.bool.check.factory.BoolCheckFactory;

public class BoolCompare extends AbstractCompare<Boolean>
{
   public boolean compare(Boolean src, String compareTag, Boolean dest)
   {
     return BoolCheckFactory.getInstance().getCheck(compareTag).check(src, dest);
   }
}
