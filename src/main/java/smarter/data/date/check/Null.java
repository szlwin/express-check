/* 1:  */ package smarter.data.date.check;
/* 2:  */ 
/* 3:  */ import java.util.Date;
/* 4:  */ 
/* 5:  */ public class Null
/* 6:  */   extends DateCheck
/* 7:  */ {
/* 8:  */   public boolean check(Date o, Date e)
/* 9:  */   {
/* ::8 */     return o == null;
/* ;:  */   }
/* <:  */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.date.check.Null
 * JD-Core Version:    0.7.0.1
 */