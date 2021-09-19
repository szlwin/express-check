package smarter.data.number.check;

public class Null
  extends NumberCheck
{
  public boolean check(Number o, Number e)
  {
    return o == null;
  }
}