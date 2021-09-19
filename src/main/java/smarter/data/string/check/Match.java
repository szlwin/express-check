package smarter.data.string.check;

import java.util.regex.Pattern;

public class Match
  extends StringCheck
{
  public boolean check(String o, String regex)
  {
    return Pattern.matches(regex, o);
  }
}
