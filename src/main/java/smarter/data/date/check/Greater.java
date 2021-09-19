package smarter.data.date.check;

import java.util.Date;

public class Greater
  extends DateCheck
{
  public boolean check(Date o, Date e)
  {
    return o.getTime() > e.getTime();
  }
}