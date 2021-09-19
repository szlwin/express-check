/* 1:  */ package smarter.data.object.check;
/* 2:  */ 
/* 3:  */ import smarter.common.check.Check;
/* 4:  */ 
/* 5:  */ public class Null
/* 6:  */   implements Check<Object>
/* 7:  */ {
/* 8:  */   public boolean check(Object str, Object e)
/* 9:  */   {
/* ::8 */     return str == null;
/* ;:  */   }
/* <:  */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.object.check.Null
 * JD-Core Version:    0.7.0.1
 */