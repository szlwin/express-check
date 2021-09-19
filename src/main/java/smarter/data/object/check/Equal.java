/*  1:   */ package smarter.data.object.check;
/*  2:   */ 
/*  3:   */ import smarter.common.check.Check;
/*  4:   */ 
/*  5:   */ public class Equal
/*  6:   */   implements Check<Object>
/*  7:   */ {
/*  8:   */   public boolean check(Object o, Object e)
/*  9:   */   {
/* 10: 8 */     if (o == null) {
/* 11: 9 */       return o == e;
/* 12:   */     }
/* 13:10 */     return o.equals(e);
/* 14:   */   }
/* 15:   */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.object.check.Equal
 * JD-Core Version:    0.7.0.1
 */