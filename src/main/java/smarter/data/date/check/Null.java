package smarter.data.date.check;

import java.util.Date;

public class Null
  extends DateCheck
{
  public boolean check(Date o, Date e)
  {
    return o == null;
  }
}
