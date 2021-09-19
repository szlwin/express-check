/*  1:   */ package smarter.common.express.execute;
/*  2:   */ 
/*  3:   */ import java.util.Map;
/*  4:   */ import santr.v4.parser.ParserTree;
/*  5:   */ import santr.v4.parser.RuleContext;
/*  6:   */ import smarter.common.exception.ExecuteExpection;
/*  7:   */ 
/*  8:   */ public class ParamArrExecuter
/*  9:   */   extends AbstractExecuter
/* 10:   */ {
/* 11:   */   public void execute(RuleContext context, Object param)
/* 12:   */     throws ExecuteExpection
/* 13:   */   {
/* 14:12 */     int size = context.getChildCount();
/* 15:13 */     Map<String, Object> value = (Map)param;
/* 16:15 */     for (int i = 0; i < size - 1;)
/* 17:   */     {
/* 18:17 */       value = (Map)value.get(context.getChild(i).getRuleContext().getText());
/* 19:   */       
/* 20:19 */       i += 2;
/* 21:   */     }
/* 22:21 */     context.setValue(value.get(context.getChild(size - 1).getRuleContext().getText()));
/* 23:   */   }
/* 24:   */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.common.express.execute.ParamArrExecuter
 * JD-Core Version:    0.7.0.1
 */