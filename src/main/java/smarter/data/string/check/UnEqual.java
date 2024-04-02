package smarter.data.string.check;

public class UnEqual
  extends StringCheck
{
  public boolean check(String o, String e)
  {
    if (o == null) {
      return e != null;
    }
    return !o.equals(e);
  }
}