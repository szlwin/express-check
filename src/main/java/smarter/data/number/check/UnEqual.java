package smarter.data.number.check;

public class UnEqual
  extends NumberCheck
{
  public boolean check(Number o, Number e)
  {
    if (o == null) {
      return e != null;
    }
    return o.doubleValue() != e.doubleValue();
  }
}
