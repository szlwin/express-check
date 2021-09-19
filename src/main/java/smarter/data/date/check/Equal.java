package smarter.data.date.check;

import java.util.Date;

public class Equal
  extends DateCheck
{
  public boolean check(Date o, Date e)
  {
    if (o == null) {
      return o == e;
    }
    return o.getTime() == e.getTime();
  }
}