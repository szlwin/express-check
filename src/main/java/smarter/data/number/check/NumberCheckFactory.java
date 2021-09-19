/*  1:   */ package smarter.data.number.check;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;
/*  4:   */ import java.util.Map;
/*  5:   */ import smarter.common.check.Check;
/*  6:   */ 
/*  7:   */ public class NumberCheckFactory
/*  8:   */ {
/*  9:11 */   private final Map<String, Check<Number>> checkMap = new HashMap<>();
/* 10:13 */   private static final NumberCheckFactory stringCheckFactory = new NumberCheckFactory();
/* 11:   */   
/* 12:   */   private NumberCheckFactory()
/* 13:   */   {
/* 14:16 */     this.checkMap.put("!", new NotNull());
/* 15:17 */     this.checkMap.put("<=", new LetterE());
/* 16:18 */     this.checkMap.put("<", new Letter());
/* 17:19 */     this.checkMap.put(">=", new GreaterE());
/* 18:20 */     this.checkMap.put(">", new Greater());
/* 19:21 */     this.checkMap.put("=", new Equal());
/* 20:22 */     this.checkMap.put("!=", new UnEqual());
/* 21:   */   }
/* 22:   */   
/* 23:   */   public static NumberCheckFactory getInstance()
/* 24:   */   {
/* 25:26 */     return stringCheckFactory;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public Check<Number> getCheck(String checkName)
/* 29:   */   {
/* 30:30 */     return 
/* 31:31 */       (Check<Number>)stringCheckFactory.checkMap.get(checkName);
/* 32:   */   }
/* 33:   */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.number.check.NumberCheckFactory
 * JD-Core Version:    0.7.0.1
 */