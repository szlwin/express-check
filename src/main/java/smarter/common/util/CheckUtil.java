package smarter.common.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javolution.util.FastMap;
import smarter.common.check.Check;
import smarter.common.compare.Compare;
import smarter.common.exception.ExecuteExpection;
import smarter.data.bool.compare.BoolCompare;
import smarter.data.date.check.DateCheck;
import smarter.data.date.compare.DateCompare;
import smarter.data.number.check.NumberCheck;
import smarter.data.number.compare.NumberCompare;
import smarter.data.object.compare.ObjectCompare;
import smarter.data.string.check.StringCheck;
import smarter.data.string.compare.StringCompare;

public class CheckUtil
{
  public static int DATA_TYPE_NUMBER = 0;
  public static int DATA_TYPE_STRING = 1;
  public static int DATA_TYPE_DATE = 2;
  private static final Map<String, String> flagMap = new FastMap<String, String>();
  private static final Map<Class<?>, Compare<?>> dataMap = new FastMap<Class<?>, Compare<?>>();
  
  private static final Compare compareArray[] = new Compare[]{
		  new NumberCompare(),
		  new StringCompare(),
		  new DateCompare(),
		  new BoolCompare(),
		  new ObjectCompare()};
  
  static
  {
    dataMap.put(String.class, compareArray[1]);
    dataMap.put(Date.class, compareArray[2]);
    dataMap.put(Boolean.class, compareArray[3]);
    dataMap.put(NullData.class, compareArray[4]);
    

    flagMap.put("=", "EQUAL");
    flagMap.put("!=", "NOTEQUAL");
    flagMap.put(">", "GREATER");
    flagMap.put(">=", "EGREATER");
    flagMap.put("<", "LETTER");
    flagMap.put("<=", "ELETTER");
    flagMap.put("|=", "MATCH");
    flagMap.put("!|=", "UNMATCH");
    
    flagMap.put("EQUAL", "=");
    flagMap.put("NOTEQUAL", "!=");
    flagMap.put("GREATER", ">");
    flagMap.put("EGREATER", ">=");
    flagMap.put("LETTER", "<");
    flagMap.put("ELETTER", "<=");
    
    flagMap.put("NOTNULL", "!=");
    flagMap.put("NULL", "=");
    
    flagMap.put("NOTEMPTY", "!=");
    flagMap.put("EMPTY", "=");
    
    flagMap.put("MATCH", "|=");
    flagMap.put("UNMATCH", "!|=");
  }
  
  public static Check<?> getSimpleCheck(int type, String tag)
  {
    if (type == DATA_TYPE_NUMBER) {
      return getNumberCheck(tag);
    }
    if (type == DATA_TYPE_STRING) {
      return getStringCheck(tag);
    }
    if (type == DATA_TYPE_DATE) {
      return getDateCheck(tag);
    }
    return null;
  }
  
  public static NumberCheck getNumberCheck(String tag)
  {
    String checkName = convertTag(tag);
    return (NumberCheck)SimpleCheckFactory.getSimpleCheck(DATA_TYPE_NUMBER, checkName);
  }
  
  public static StringCheck getStringCheck(String tag)
  {
    String checkName = convertTag(tag);
    return (StringCheck)SimpleCheckFactory.getSimpleCheck(DATA_TYPE_STRING, checkName);
  }
  
  public static DateCheck getDateCheck(String tag)
  {
    String checkName = convertTag(tag);
    return (DateCheck)SimpleCheckFactory.getSimpleCheck(DATA_TYPE_DATE, checkName);
  }
  
  /*public static boolean compare(String value, String tag, String checkValue)
  {
    int type = 0;

    type = getValueType(checkValue);
    
    return compare(value, tag, checkValue, type);
  }
  
  public static boolean compare(String value, String tag, String checkValue, int type)
  {
    Compare compare = getCompare(type);
    
    return compare.compare(value, tag, checkValue);
  }*/
  
  public static boolean compareByTag(Object value, String tag, Object checkValue)
    throws ExecuteExpection
  {
    try
    {

			//if(value == null || checkValue == null){
			//	return value == checkValue;
			//}
     
	 //if(value == null){
	 //	 return  checkValue == null;
	 //}
	 
	 
      if (value == null || checkValue == null) {
        return new ObjectCompare().compare(value, tag, checkValue);
      }
	  Compare compare = dataMap.get(value.getClass());
      if (compare == null) {
    	  return compareArray[0].compare(value, tag, checkValue);
      }else{
    	  return compare.compare(value, tag, checkValue);
      }
      //System.out.println(value + ":" + checkValue);
      //return ((Compare)cla.newInstance())
      //  .compare(value, tag, checkValue);
    }
    catch (Exception e)
    {
      throw new ExecuteExpection(e, "The " + value.getClass() + " is unspport!");
    }
  }
  
  public static boolean compareByTag(Object value, String tag, Object checkValue, int type)
  {
    Compare compare = getCompare(type);
    
    return compare.compare(value, tag, checkValue);
  }
  
  public static Compare getCompare(int type)
  {
	return compareArray[type];
    /*if (type == DATA_TYPE_NUMBER) {
      return new NumberCompare();
    }
    if (type == DATA_TYPE_STRING) {
      return new StringCompare();
    }
    if (type == DATA_TYPE_DATE) {
      return new DateCompare();
    }
    return null;*/
  }
  
  public static String convertTag(String tag)
  {
    return (String)flagMap.get(tag);
  }
  
  public static String convertObjectToString(Object obj)
  {
    if (obj == null) {
      return null;
    }
    if ((obj instanceof NullData)) {
      return null;
    }
    if ((obj instanceof Collection))
    {
      int length = ((Collection)obj).size();
      
      String strValue = String.valueOf(length);
      return strValue;
    }
    if ((obj instanceof String)) {
      return "'" + (String)obj + "'";
    }
    if ((obj instanceof Date))
    {
      SimpleDateFormat format = new SimpleDateFormat("#yyyy-MM-dd#HH:mm:ss#");
      String strDate = format.format((Date)obj);
      return strDate;
    }
    return String.valueOf(obj);
  }
  
  public static Map<String, Object> convertObjectToMap(Object obj)
  {
    return null;
  }
  
  public static Object getValueByKey(String key, Map<String, Object> map)
  {
    String[] keyArray = key.split("\\.");
    if (keyArray.length == 0)
    {
      if (map.containsKey(key))
      {
        Object value = map.get(key);
        if (value == null) {
          return new NullData();
        }
        return value;
      }
      return null;
    }
    Map<String, Object> valueMap = map;
    for (int i = 0; i < keyArray.length - 1; i++) {
      if (valueMap.containsKey(keyArray[i])) {
        valueMap = (Map)valueMap.get(keyArray[i]);
      }
    }
    if (valueMap.containsKey(keyArray[(keyArray.length - 1)]))
    {
      Object value = valueMap.get(keyArray[(keyArray.length - 1)]);
      if (value == null) {
        return new NullData();
      }
      return value;
    }
    return null;
  }
  
  public static Object convertStringToValue(Object value,String strValue){
	 
	if(value == null ||  strValue == null ){
		return null;
	}
	if(value instanceof Number ){
		
		if(value instanceof BigDecimal){
			return new BigDecimal(strValue);
		}
		
		return Double.valueOf(strValue);
		
	}else if(value instanceof Boolean){
		return Boolean.valueOf(strValue);
	}else if(value instanceof Date){
		throw new RuntimeException("Date not supported");
	}
	return strValue;
	  
  }
}