package smarter.data.date.check;

import java.util.Date;

public class Letter
  extends DateCheck
{
  public boolean check(Date o, Date e)
  {

	  return o.compareTo(e) < 0;
  }
}