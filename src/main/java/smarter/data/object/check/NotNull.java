package smarter.data.object.check;

import smarter.common.check.Check;

public class NotNull
  implements Check<Object>
{
  public boolean check(Object o, Object e)
  {
    return o != null;
  }
}
