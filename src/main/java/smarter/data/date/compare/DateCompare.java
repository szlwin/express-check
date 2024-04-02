package smarter.data.date.compare;

import java.util.Date;
import java.util.GregorianCalendar;
import smarter.common.compare.AbstractCompare;
import smarter.common.util.CheckUtil;
import smarter.data.date.check.factory.DateCheckFactory;

public class DateCompare
  extends AbstractCompare<Date>
{
  private Date convert(String str)
  {
    GregorianCalendar gregorianCalendar = null;
    String[] dateArray = str.split(" ");
    if (dateArray.length > 1)
    {
      String[] dateYearInfo = dateArray[0].split("-");
      
      String[] dateHourInfo = dateArray[1].split(":");
      
      gregorianCalendar = new GregorianCalendar(
        Integer.valueOf(dateYearInfo[0]).intValue(), 
        Integer.valueOf(dateYearInfo[1]).intValue() - 1, 
        Integer.valueOf(dateYearInfo[2]).intValue(), 
        Integer.valueOf(dateHourInfo[0]).intValue(), 
        Integer.valueOf(dateHourInfo[1]).intValue(), 
        Integer.valueOf(dateHourInfo[2]).intValue());
    }
    else
    {
      String[] dateYearInfo = dateArray[0].split("-");
      
      gregorianCalendar = new GregorianCalendar(
        Integer.valueOf(dateYearInfo[0]).intValue(), 
        Integer.valueOf(dateYearInfo[1]).intValue() - 1 , 
        Integer.valueOf(dateYearInfo[2]).intValue());
    }
    return gregorianCalendar.getTime();
  }
  
  public boolean compare(String src, String compareTag, String dest)
  {
    Date srcDate = convert(src);
    
    Date destDate = convert(dest);
    
    return CheckUtil.getDateCheck(compareTag)
      .check(srcDate, destDate);
  }
  
  public boolean compare(Date src, String complareTag, Date dest)
  {

    return DateCheckFactory.getInstance().getCheck(complareTag).check(src, dest);
  }
}
