/* 1:  */ package smarter.data.string.check;
/* 2:  */ 
/* 3:  */ public class UnEmpty
/* 4:  */   extends StringCheck
/* 5:  */ {
/* 6:  */   public boolean check(String o, String e)
/* 7:  */   {
/* 8:6 */     return (o != null) && (!"".equals(o));
/* 9:  */   }
/* ::  */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.string.check.UnEmpty
 * JD-Core Version:    0.7.0.1
 */