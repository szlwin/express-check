/*  1:   */ package smarter.data.string.compare;
/*  2:   */ 
/*  3:   */ import java.io.PrintStream;
/*  4:   */ import smarter.common.check.Check;
/*  5:   */ import smarter.common.compare.AbstractCompare;
/*  6:   */ import smarter.data.string.check.StringCheckFactory;
/*  7:   */ 
/*  8:   */ public class StringCompare
/*  9:   */   extends AbstractCompare<String>
/* 10:   */ {
/* 11:   */   public boolean compare(String src, String compareTag, String dest)
/* 12:   */   {
/* 13:10 */     return 
/* 14:   */     
/* 15:12 */       StringCheckFactory.getInstance().getCheck(compareTag).check(src, dest);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public static void main(String[] args)
/* 19:   */   {
/* 20:16 */     System.out.println(StringCheckFactory.getInstance().getCheck("=")
/* 21:17 */       .check("1", "b"));
/* 22:   */   }
/* 23:   */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.string.compare.StringCompare
 * JD-Core Version:    0.7.0.1
 */