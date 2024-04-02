package smarter.data.string.check;

public class Equal
  extends StringCheck
{
  public boolean check(String o, String e)
  {
    if (o == null) {
      return o == e;
    }
    return o.equals(e);
  }
}