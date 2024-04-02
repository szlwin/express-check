package smarter.data.string.check;

public class NotNull
  extends StringCheck
{
  public boolean check(String str, String e)
  {
    return str != null;
  }
}