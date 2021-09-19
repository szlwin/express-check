/*  1:   */ package smarter.data.date.check;
/*  2:   */ 
/*  3:   */ import java.util.Date;
/*  4:   */ import java.util.HashMap;
/*  5:   */ import java.util.Map;
/*  6:   */ import smarter.common.check.Check;
/*  7:   */ 
/*  8:   */ public class DataCheckFactory
/*  9:   */ {
/* 10:11 */   private final Map<String, Check<Date>> checkMap = new HashMap();
/* 11:13 */   private static final DataCheckFactory dataCheckFactory = new DataCheckFactory();
/* 12:   */   
/* 13:   */   private DataCheckFactory()
/* 14:   */   {
/* 15:16 */     this.checkMap.put("!", new NotNull());
/* 16:17 */     this.checkMap.put("<=", new LetterE());
/* 17:18 */     this.checkMap.put("<", new Letter());
/* 18:19 */     this.checkMap.put(">=", new GreaterE());
/* 19:20 */     this.checkMap.put(">", new Greater());
/* 20:21 */     this.checkMap.put("=", new Equal());
/* 21:22 */     this.checkMap.put("!=", new UnEqual());
/* 22:   */   }
/* 23:   */   
/* 24:   */   public static DataCheckFactory getInstance()
/* 25:   */   {
/* 26:26 */     return dataCheckFactory;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public Check<Date> getCheck(String checkName)
/* 30:   */   {
/* 31:30 */     return 
/* 32:31 */       (Check)dataCheckFactory.checkMap.get(checkName);
/* 33:   */   }
/* 34:   */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.date.check.DataCheckFactory
 * JD-Core Version:    0.7.0.1
 */