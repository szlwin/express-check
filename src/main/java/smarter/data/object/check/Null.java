package smarter.data.object.check;

import smarter.common.check.Check;

public class Null
  implements Check<Object>
{
  public boolean check(Object str, Object e)
  {
    return str == null;
  }
}