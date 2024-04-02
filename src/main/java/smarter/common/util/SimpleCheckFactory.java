package smarter.common.util;

import java.util.Map;

import javolution.util.FastMap;
import smarter.common.check.Check;
import smarter.data.string.check.Empty;
import smarter.data.string.check.Match;
import smarter.data.string.check.UnEmpty;
import smarter.data.string.check.UnMatch;

public class SimpleCheckFactory
{
  //private static final Map<String, Check<?>> checkMap = new HashMap<String, Check<?>>();
  
  private static final Map<String, Check<?>> numberCheckMap = new FastMap<String, Check<?>>();
  
  private static final Map<String, Check<?>> stringCheckMap = new FastMap<String, Check<?>>();
  
  private static final Map<String, Check<?>> dateCheckMap = new FastMap<String, Check<?>>();
  
  //private static String[] TYPE_PREFIX = new String[]{"12"};
  
  private static final Map<String,Check<?>>[] checkMapArray = new Map[]{numberCheckMap,dateCheckMap,stringCheckMap};//new HashMap<String, Check<?>>[3];
  
  static
  {
	  //checkMapArray[0] = numberCheckMap;
    //TYPE_PREFIX[CheckUtil.DATA_TYPE_NUMBER] = "N_";
    
    //TYPE_PREFIX[CheckUtil.DATA_TYPE_STRING] = "S_";
    
    //TYPE_PREFIX[CheckUtil.DATA_TYPE_DATE] = "D_";
    
    numberCheckMap.put("EQUAL", new smarter.data.number.check.Equal());
    numberCheckMap.put("GREATER", new smarter.data.number.check.Greater());
    numberCheckMap.put("EGREATER", new smarter.data.number.check.GreaterE());
    numberCheckMap.put("LETTER", new smarter.data.number.check.Letter());
    numberCheckMap.put("ELETTER", new smarter.data.number.check.LetterE());
    numberCheckMap.put("NOTEQUAL", new smarter.data.number.check.UnEqual());
    numberCheckMap.put("NULL", new smarter.data.number.check.Null());
    numberCheckMap.put("NOTNULL", new smarter.data.number.check.NotNull());
    
    dateCheckMap.put("EQUAL", new smarter.data.date.check.Equal());
    dateCheckMap.put("GREATER", new smarter.data.date.check.Greater());
    dateCheckMap.put("EGREATER", new smarter.data.date.check.GreaterE());
    dateCheckMap.put("LETTER", new smarter.data.date.check.Letter());
    dateCheckMap.put("ELETTER", new smarter.data.date.check.LetterE());
    dateCheckMap.put("NOTEQUAL", new smarter.data.date.check.UnEqual());
    dateCheckMap.put("NULL", new smarter.data.date.check.Null());
    dateCheckMap.put("NOTNULL", new smarter.data.date.check.NotNull());
    
    stringCheckMap.put("UNMATCH", new UnMatch());
    stringCheckMap.put("MATCH", new Match());
    stringCheckMap.put("EQUAL", new smarter.data.string.check.Equal());
    stringCheckMap.put("NOTEQUAL", new smarter.data.string.check.UnEqual());
    stringCheckMap.put("EMPTY", new Empty());
    stringCheckMap.put("NOTEMPTY", new UnEmpty());
    stringCheckMap.put("NULL", new smarter.data.string.check.Null());
    stringCheckMap.put("NOTNULL", new smarter.data.string.check.NotNull());
  }
  
  public static Check<?> getSimpleCheck(int type, String checkName)
  {
	return checkMapArray[type].get(checkName);
    //return (Check)checkMap.get(TYPE_PREFIX[type] + checkName);
  }
}