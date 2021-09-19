package smarter.common.express.check;

import smarter.common.exception.ExecuteExpection;
import smarter.common.util.CheckUtil;

public class PropertyCheck
  extends AbstractCheck<Object>
{
  public boolean check()
    throws ExecuteExpection
  {
    return checkPatten();
  }
  
  private boolean checkPatten()
    throws ExecuteExpection
  {
    boolean checkResult = true;
    String[] checkArray = this.pattern.split(";");
    for (int i = 0; i < checkArray.length; i++)
    {
      String[] checkInfo = checkArray[i].split(":");
      String checkValue = null;
      if (checkInfo.length > 1) {
        checkValue = checkInfo[1].trim();
      }
      checkResult &= checkValue(checkInfo[0].trim(), checkValue);
      if (!checkResult) {
        break;
      }
    }
    return checkResult;
  }
  
  private boolean checkValue(Object valueForCompare, String tag, String checkValue)
		    throws ExecuteExpection
  {

    //String strValue = CheckUtil.convertObjectToString(this.value);
	Object compareValue = CheckUtil.convertStringToValue(valueForCompare,checkValue); 

    tag = CheckUtil.convertTag(tag);
    return CheckUtil.compareByTag(valueForCompare, tag, compareValue);
  }
  
  private boolean checkValue(String tag, String checkValue)
    throws ExecuteExpection
  {

   return checkValue(this.value, tag, checkValue);
	//Object compareValue = CheckUtil.convertStringToValue(this.value,checkValue); 

    //tag = CheckUtil.convertTag(tag);
    //return CheckUtil.compareByTag(this.value, tag, compareValue);
  }

	@Override
	public boolean check(String paramString, Object valueForCompare) throws ExecuteExpection {
	    boolean checkResult = true;
	    String[] checkArray = paramString.split(";");
	    for (int i = 0; i < checkArray.length; i++)
	    {
	      String[] checkInfo = checkArray[i].split(":");
	      String checkValue = null;
	      if (checkInfo.length > 1) {
	        checkValue = checkInfo[1].trim();
	      }
	      checkResult &= checkValue(valueForCompare, checkInfo[0].trim(), checkValue);
	      if (!checkResult) {
	        break;
	      }
	    }
	    return checkResult;
	}
}
