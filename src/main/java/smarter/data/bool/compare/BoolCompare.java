/*  1:   */ package smarter.data.bool.compare;
/*  2:   */ 
/*  3:   */ import smarter.common.check.Check;
/*  4:   */ import smarter.common.compare.AbstractCompare;
/*  5:   */ import smarter.data.bool.check.BoolCheckFactory;
/*  6:   */ 
/*  7:   */ public class BoolCompare
/*  8:   */   extends AbstractCompare<Boolean>
/*  9:   */ {
/* 10:   */   public boolean compare(Boolean src, String compareTag, Boolean dest)
/* 11:   */   {
/* 12:10 */     return 
/* 13:   */     
/* 14:12 */       BoolCheckFactory.getInstance().getCheck(compareTag).check(src, dest);
/* 15:   */   }
/* 16:   */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.bool.compare.BoolCompare
 * JD-Core Version:    0.7.0.1
 */