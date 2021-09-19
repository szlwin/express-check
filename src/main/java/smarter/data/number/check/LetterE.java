package smarter.data.number.check;

public class LetterE
  extends NumberCheck
{
  public boolean check(Number o, Number e)
  {
    return o.doubleValue() <= e.doubleValue();
  }
}
