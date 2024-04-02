package smarter.data.string.check;

public class Null
  extends StringCheck
{
  public boolean check(String str, String e)
  {
    return str == null;
  }
}