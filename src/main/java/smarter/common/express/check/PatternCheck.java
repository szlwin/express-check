package smarter.common.express.check;

//import java.util.ArrayList;
//import java.util.List;
import java.util.Map;
//import java.util.StringTokenizer;
import smarter.common.exception.ExecuteExpection;
import smarter.common.express.execute.Executer;
//import smarter.common.util.CheckUtil;

public class PatternCheck
  extends AbstractCheck<Map<String, Object>>
{
  //private List<String> list = new ArrayList(30);

  
  public boolean check(String pattern, Map<String,Object> value)  throws ExecuteExpection {
    Boolean result = null;
    Executer executer = new Executer();
    try
    {
    	
      result = Boolean.valueOf(executer.execute(pattern, (Map<String,Object>)value));
    }
    catch (Exception e)
    {
      throw new ExecuteExpection(pattern, e);
    }
    return result.booleanValue();
	  
  }
  
  public boolean check()
    throws ExecuteExpection
  {
    return checkPatten2(this.pattern);
  }
  
  private boolean checkPatten2(String pattern)
    throws ExecuteExpection
  {
    //Executer executer = new Executer();
    Boolean result = null;
    Executer executer = new Executer();
    try
    {
      result = Boolean.valueOf(executer.execute(pattern, (Map<String,Object>)this.value));
    }
    catch (Exception e)
    {
      throw new ExecuteExpection(pattern, e);
    }
    return result.booleanValue();
  }
  
  /*private boolean checkPatten1(String pattern)
  {
    StringTokenizer token = new StringTokenizer(pattern, " ", true);
    
    String left = null;
    String tag = null;
    String right = "";
    boolean isCanCheck = false;
    boolean isNeedSpace = false;
    while (token.hasMoreElements())
    {
      String str = token.nextToken();
      if ((!" ".equals(str)) || (isNeedSpace))
      {
        if (tag == null) {
          this.list.add(str);
        }
        if (checkCompareTag(str))
        {
          left = (String)this.list.get(this.list.size() - 2);
          Object tempValue = CheckUtil.getValueByKey(left, (Map)this.value);
          if (tempValue != null) {
            left = CheckUtil.convertObjectToString(tempValue);
          }
          tag = str;
          if ((left != null) && (!left.startsWith("'"))) {
            left = compluteStr(left);
          }
          this.list.set(this.list.size() - 2, left);
        }
        else if (tag != null)
        {
          right = right + str;
          if ((right.startsWith("'")) && (!right.endsWith("'")))
          {
            isNeedSpace = true;
          }
          else
          {
            Object tempValue = CheckUtil.getValueByKey(right, (Map)this.value);
            if (tempValue != null) {
              right = CheckUtil.convertObjectToString(tempValue);
            } else if (!right.startsWith("'")) {
              right = compluteStr(right);
            }
            isNeedSpace = false;
            isCanCheck = true;
          }
        }
        else if (isCanCheck)
        {
          boolean checkResult = compare(left, tag, right);
          String strCheckResult = String.valueOf(checkResult);
          this.list.set(this.list.size() - 2, strCheckResult);
          this.list.remove(this.list.size() - 1);
          
          isCanCheck = false;
          tag = null;
          right = "";
        }
      }
    }
    return getCheckResult();
  }*/
  /*
  private boolean checkPatten()
  {
    for (int i = 0; i < this.list.size(); i++)
    {
      String str = (String)this.list.get(i);
      if (checkCompareTag(str))
      {
        String left = (String)this.list.get(i - 1);
        
        String right = (String)this.list.get(i + 1);
        
        boolean checkResult = compare(left, str, right);
        String strCheckResult = String.valueOf(checkResult);
        this.list.set(i - 1, strCheckResult);
        this.list.remove(i);
        this.list.remove(i);
      }
    }
    return getCheckResult();
  }*/
  
 // private void convert()
//  {
  //  StringBuffer strBuffer = new StringBuffer();
    //StringTokenizer token = new StringTokenizer(this.pattern, " +-*/()", true);
    //while (token.hasMoreElements())
//    {
  //    String str = token.nextToken();
      
    //  Object tempValue = CheckUtil.getValueByKey(str, (Map)this.value);
      //if (tempValue != null) {
        //str = CheckUtil.convertObjectToString(tempValue);
      //}
      //strBuffer.append(str);
    //}
    //StringTokenizer tokenF = new StringTokenizer(strBuffer.toString(), " ", false);
    //while (tokenF.hasMoreElements())
   // {
     // String str = tokenF.nextToken();
      //str = complute(str);
      //this.list.add(str);
    //}
  //}
  /*
  private boolean checkCompareTag(String tag)
  {
    return CheckUtil.isCompareFlag(tag);
  }
  
  private boolean compare(String value, String tag, String checkValue)
  {
    return CheckUtil.compare(value, tag, checkValue);
  }*/
  /*
  private boolean getCheckResult()
  {
    for (int i = 0; i < this.list.size(); i++)
    {
      String str = (String)this.list.get(i);
      if (isLogicalFlag(str))
      {
        String left = (String)this.list.get(i - 1);
        String right = (String)this.list.get(i + 1);
        if ((isBooleanValue(left)) && (isBooleanValue(right)))
        {
          boolean checkResult = compareBoolean(left, str, right);
          this.list.set(i - 1, String.valueOf(checkResult));
          this.list.remove(i);
          this.list.remove(i);
        }
      }
      else if ((isBooleanValue(str)) && 
        (i - 1 >= 0) && (i + 1 <= this.list.size() - 1))
      {
        String left = (String)this.list.get(i - 1);
        String right = (String)this.list.get(i + 1);
        if ((left.equals("(")) && 
          (right.equals(")")))
        {
          this.list.set(i - 1, str);
          this.list.remove(i);
          this.list.remove(i);
        }
      }
    }
    if (this.list.size() == 1) {
      return Boolean.valueOf((String)this.list.get(0)).booleanValue();
    }
    return getCheckResult();
  }*/
  /*
  private boolean isLogicalFlag(String str)
  {
    str = str.toLowerCase();
    return (str.equals("or")) || (str.equals("and"));
  }
  
  private boolean compareBoolean(String left, String tag, String right)
  {
    boolean result = false;
    tag = tag.toLowerCase();
    if (tag.equals("or")) {
      result = (Boolean.valueOf(left).booleanValue()) || 
        (Boolean.valueOf(right).booleanValue());
    }
    if (tag.equals("and")) {
      result = (Boolean.valueOf(left).booleanValue()) && 
        (Boolean.valueOf(right).booleanValue());
    }
    return result;
  }*/
  /*
  private boolean isBooleanValue(String value)
  {
    return (value.equals("true")) || 
      (value.equals("false"));
  }
  
  private String complute(String str)
  {
    if (str.startsWith("<")) {
      return str;
    }
    if (CheckUtil.checkComplute(str))
    {
      str = str.replace("'", "");
      return String.valueOf(CheckUtil.complute(str));
    }
    return str;
  }*/
  
  //private String compluteStr(String cstr)
  //{
  //  if (CheckUtil.checkComplute(cstr))
  //  {
  //    StringBuffer compluteStr = new StringBuffer();
  //    StringTokenizer tokenF = new StringTokenizer(cstr.toString(), "+-*/()", false);
  //    while (tokenF.hasMoreElements())
  //    {
  //      String str = tokenF.nextToken();
 //       Object tempValue = CheckUtil.getValueByKey(str, (Map)this.value);
  //      if (tempValue != null) {
 //         str = CheckUtil.convertObjectToString(tempValue);
 //       }
 //       compluteStr.append(str);
  //    }
 //     String rCstr = compluteStr.toString().replace("'", "");
  //    return String.valueOf(CheckUtil.complute(rCstr));
  //  }
  //  return cstr;
  //}
}



