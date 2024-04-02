package smarter.common.check;

import java.util.HashMap;
import java.util.Map;
import smarter.data.string.check.Empty;
import smarter.data.string.check.Match;
import smarter.data.string.check.UnEmpty;
import smarter.data.string.check.UnMatch;

public class CheckFactory
{
  private static final Map<String, Check<?>> checkMap = new HashMap<>();
  private static String[] TYPE_PREFIX = new String[3];
  
  static
  {
    TYPE_PREFIX[smarter.common.util.CheckUtil.DATA_TYPE_NUMBER] = "N_";
    
    TYPE_PREFIX[smarter.common.util.CheckUtil.DATA_TYPE_STRING] = "S_";
    
    TYPE_PREFIX[smarter.common.util.CheckUtil.DATA_TYPE_DATE] = "D_";
    
    checkMap.put("N_EQUAL", new smarter.data.number.check.Equal());
    checkMap.put("N_GREATER", new smarter.data.number.check.Greater());
    checkMap.put("N_EGREATER", new smarter.data.number.check.GreaterE());
    checkMap.put("N_LETTER", new smarter.data.number.check.Letter());
    checkMap.put("N_ELETTER", new smarter.data.number.check.LetterE());
    checkMap.put("N_NOTEQUAL", new smarter.data.number.check.UnEqual());
    checkMap.put("N_NULL", new smarter.data.number.check.Null());
    checkMap.put("N_NOTNULL", new smarter.data.string.check.NotNull());
    
    checkMap.put("D_EQUAL", new smarter.data.date.check.Equal());
    checkMap.put("D_GREATER", new smarter.data.date.check.Greater());
    checkMap.put("D_EGREATER", new smarter.data.date.check.GreaterE());
    checkMap.put("D_LETTER", new smarter.data.date.check.Letter());
    checkMap.put("D_ELETTER", new smarter.data.date.check.LetterE());
    checkMap.put("D_NOTEQUAL", new smarter.data.date.check.UnEqual());
    checkMap.put("D_NULL", new smarter.data.date.check.Null());
    checkMap.put("D_NOTNULL", new smarter.data.date.check.NotNull());
    
    checkMap.put("S_UNMATCH", new UnMatch());
    checkMap.put("S_MATCH", new Match());
    checkMap.put("S_EQUAL", new smarter.data.string.check.Equal());
    checkMap.put("S_NOTEQUAL", new smarter.data.string.check.UnEqual());
    checkMap.put("S_EMPTY", new Empty());
    checkMap.put("S_NOTEMPTY", new UnEmpty());
    checkMap.put("S_NULL", new smarter.data.string.check.Null());
    checkMap.put("S_NOTNULL", new smarter.data.string.check.NotNull());
  }
  
  public static Check<?> getSimpleCheck(String checkName)
  {
    return (Check<?>)checkMap.get(checkName);
  }
}
