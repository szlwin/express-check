package smarter.data.object.check;

import smarter.common.check.Check;

public class Equal
  implements Check<Object>
{
  public boolean check(Object o, Object e)
  {
    if (o == null) {
      return o == e;
    }
    return o.equals(e);
  }
}
