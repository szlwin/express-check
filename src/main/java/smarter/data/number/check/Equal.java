package smarter.data.number.check;

public class Equal
  extends NumberCheck
{
  public boolean check(Number o, Number e)
  {
    if (o == null) {
      return o == e;
    }
    return o.doubleValue() == e.doubleValue();
  }
}