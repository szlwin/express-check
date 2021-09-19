/*  1:   */ package smarter.data.object.compare;
/*  2:   */ 
/*  4:   */ import smarter.common.compare.AbstractCompare;
/*  5:   */ import smarter.data.object.check.ObjectCheckFactory;
/*  6:   */ 
/*  7:   */ public class ObjectCompare
/*  8:   */   extends AbstractCompare<Object>
/*  9:   */ {
/* 10:   */   public boolean compare(Object src, String compareTag, Object dest)
/* 11:   */   {
/* 12:10 */     return 
/* 13:   */     
/* 14:12 */       ObjectCheckFactory.getInstance().getCheck(compareTag).check(src, dest);
/* 15:   */   }
/* 16:   */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.object.compare.ObjectCompare
 * JD-Core Version:    0.7.0.1
 */