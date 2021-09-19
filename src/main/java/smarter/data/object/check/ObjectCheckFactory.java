/*  1:   */ package smarter.data.object.check;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;
/*  4:   */ import java.util.Map;
/*  5:   */ import smarter.common.check.Check;
/*  6:   */ 
/*  7:   */ public class ObjectCheckFactory
/*  8:   */ {
/*  9:11 */   private final Map<String, Check<Object>> checkMap = new HashMap();
/* 10:13 */   private static final ObjectCheckFactory objectCheckFactory = new ObjectCheckFactory();
/* 11:   */   
/* 12:   */   private ObjectCheckFactory()
/* 13:   */   {
/* 14:17 */     this.checkMap.put("=", new Equal());
/* 15:18 */     this.checkMap.put("!=", new UnEqual());
/* 16:   */   }
/* 17:   */   
/* 18:   */   public static ObjectCheckFactory getInstance()
/* 19:   */   {
/* 20:22 */     return objectCheckFactory;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public Check<Object> getCheck(String checkName)
/* 24:   */   {
/* 25:26 */     return 
/* 26:27 */       (Check)objectCheckFactory.checkMap.get(checkName);
/* 27:   */   }
/* 28:   */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.object.check.ObjectCheckFactory
 * JD-Core Version:    0.7.0.1
 */