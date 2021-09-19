/*  1:   */ package smarter.data.string.check;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;
/*  4:   */ import java.util.Map;
/*  5:   */ import smarter.common.check.Check;
/*  6:   */ 
/*  7:   */ public class StringCheckFactory
/*  8:   */ {
/*  9:11 */   private final Map<String, Check<String>> checkMap = new HashMap();
/* 10:13 */   private static final StringCheckFactory stringCheckFactory = new StringCheckFactory();
/* 11:   */   
/* 12:   */   private StringCheckFactory()
/* 13:   */   {
/* 14:16 */     this.checkMap.put("!|=", new UnMatch());
/* 15:17 */     this.checkMap.put("|=", new Match());
/* 16:18 */     this.checkMap.put("=", new Equal());
/* 17:19 */     this.checkMap.put("!=", new UnEqual());
/* 18:20 */     this.checkMap.put("!", new UnEmpty());
/* 19:   */   }
/* 20:   */   
/* 21:   */   public static StringCheckFactory getInstance()
/* 22:   */   {
/* 23:24 */     return stringCheckFactory;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public Check<String> getCheck(String checkName)
/* 27:   */   {
/* 28:28 */     return 
/* 29:29 */       (Check)stringCheckFactory.checkMap.get(checkName);
/* 30:   */   }
/* 31:   */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.string.check.StringCheckFactory
 * JD-Core Version:    0.7.0.1
 */