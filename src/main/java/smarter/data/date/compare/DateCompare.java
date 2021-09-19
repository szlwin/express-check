/*  1:   */ package smarter.data.date.compare;
/*  2:   */ 
/*  3:   */ import java.util.Date;
/*  4:   */ import java.util.GregorianCalendar;
/*  5:   */ import smarter.common.check.Check;
/*  6:   */ import smarter.common.compare.AbstractCompare;
/*  7:   */ import smarter.common.util.CheckUtil;
/*  8:   */ import smarter.data.date.check.DataCheckFactory;
/*  9:   */ import smarter.data.date.check.DateCheck;
/* 10:   */ 
/* 11:   */ public class DateCompare
/* 12:   */   extends AbstractCompare<Date>
/* 13:   */ {
/* 14:   */   private Date convert(String str)
/* 15:   */   {
/* 16:28 */     GregorianCalendar gregorianCalendar = null;
/* 17:29 */     String[] dateArray = str.split(" ");
/* 18:31 */     if (dateArray.length > 1)
/* 19:   */     {
/* 20:32 */       String[] dateYearInfo = dateArray[0].split("-");
/* 21:   */       
/* 22:34 */       String[] dateHourInfo = dateArray[1].split(":");
/* 23:   */       
/* 24:36 */       gregorianCalendar = new GregorianCalendar(
/* 25:37 */         Integer.valueOf(dateYearInfo[0]).intValue(), 
/* 26:38 */         Integer.valueOf(dateYearInfo[1]).intValue(), 
/* 27:39 */         Integer.valueOf(dateYearInfo[2]).intValue(), 
/* 28:40 */         Integer.valueOf(dateHourInfo[0]).intValue(), 
/* 29:41 */         Integer.valueOf(dateHourInfo[1]).intValue(), 
/* 30:42 */         Integer.valueOf(dateHourInfo[2]).intValue());
/* 31:   */     }
/* 32:   */     else
/* 33:   */     {
/* 34:45 */       String[] dateYearInfo = dateArray[0].split("-");
/* 35:   */       
/* 36:47 */       gregorianCalendar = new GregorianCalendar(
/* 37:48 */         Integer.valueOf(dateYearInfo[0]).intValue(), 
/* 38:49 */         Integer.valueOf(dateYearInfo[1]).intValue(), 
/* 39:50 */         Integer.valueOf(dateYearInfo[2]).intValue());
/* 40:   */     }
/* 41:53 */     return gregorianCalendar.getTime();
/* 42:   */   }
/* 43:   */   
/* 44:   */   public boolean compare(String src, String compareTag, String dest)
/* 45:   */   {
/* 46:58 */     Date srcDate = convert(src);
/* 47:   */     
/* 48:60 */     Date destDate = convert(dest);
/* 49:   */     
/* 50:62 */     return CheckUtil.getDateCheck(compareTag)
/* 51:63 */       .check(srcDate, destDate);
/* 52:   */   }
/* 53:   */   
/* 54:   */   public boolean compare(Date src, String complareTag, Date dest)
/* 55:   */   {
/* 56:68 */     return 
/* 57:   */     
/* 58:70 */       DataCheckFactory.getInstance().getCheck(complareTag).check(src, dest);
/* 59:   */   }
/* 60:   */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.date.compare.DateCompare
 * JD-Core Version:    0.7.0.1
 */