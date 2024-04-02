package smarter.data.string.check;

public class Empty
  extends StringCheck
{
  public boolean check(String o, String e)
  {
    return (o != null) && ("".equals(o));
  }
}