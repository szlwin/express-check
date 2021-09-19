/*  1:   */ package smarter.common.express.execute;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;
/*  4:   */ import java.util.Map;
/*  5:   */ import santr.parser.exception.ExecuteInvaildException;
/*  6:   */ import santr.v4.execute.AbstractVisitor;
/*  7:   */ import santr.v4.parser.RuleContext;
/*  8:   */ 
/*  9:   */ public class ExprVisitor2
/* 10:   */   extends AbstractVisitor<Map<String, Object>>
/* 11:   */ {
/* 12:13 */   private static final Map<String, Class<? extends CheckExecuter>> checkExecuterMap = new HashMap<>();
/* 13:   */   
/* 14:   */   static
/* 15:   */   {
/* 16:18 */     checkExecuterMap.put("expr", ExprExecuter.class);
/* 17:19 */     checkExecuterMap.put("INT", NumberExecuter.class);
/* 18:20 */     checkExecuterMap.put("DATE", DateExecuter.class);
/* 19:21 */     checkExecuterMap.put("BOOLEAN", BooleanExecuter.class);
/* 20:22 */     checkExecuterMap.put("STRING", StringExecuter.class);
/* 21:23 */     checkExecuterMap.put("NULL", NullExecuter.class);
/* 22:24 */     checkExecuterMap.put("paramArr", ParamArrExecuter.class);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void execute(RuleContext context)
/* 26:   */     throws ExecuteInvaildException
/* 27:   */   {
/* 28:31 */     String name = context.getName();
/* 29:32 */     CheckExecuter checkExecuter = null;
/* 30:   */     try
/* 31:   */     {
/* 32:34 */       checkExecuter = (CheckExecuter)((Class)checkExecuterMap.get(name)).newInstance();
/* 33:   */     }
/* 34:   */     catch (Exception e)
/* 35:   */     {
/* 36:37 */       e.printStackTrace();
/* 37:   */     }
/* 38:39 */     checkExecuter.execute(context, getParamer());
/* 39:   */   }
/* 40:   */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.common.express.execute.ExprVisitor2
 * JD-Core Version:    0.7.0.1
 */