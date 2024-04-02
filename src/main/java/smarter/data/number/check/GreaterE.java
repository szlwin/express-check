package smarter.data.number.check;

public class GreaterE
  extends NumberCheck
{
  public boolean check(Number o, Number e)
  {
    return o.doubleValue() >= e.doubleValue();
  }
}
