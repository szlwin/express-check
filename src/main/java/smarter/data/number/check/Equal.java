/* 1:  */ package smarter.data.number.check;
/* 2:  */ 
/* 3:  */ public class Equal
/* 4:  */   extends NumberCheck
/* 5:  */ {
/* 6:  */   public boolean check(Number o, Number e)
/* 7:  */   {
/* 8:6 */     if (o == null) {
/* 9:7 */       return o == e;
/* ::  */     }
/* ;:8 */     return o.doubleValue() == e.doubleValue();
/* <:  */   }
/* =:  */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.number.check.Equal
 * JD-Core Version:    0.7.0.1
 */