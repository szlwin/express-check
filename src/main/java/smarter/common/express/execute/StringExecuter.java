/*  1:   */ package smarter.common.express.execute;
/*  2:   */ 
/*  3:   */ import santr.v4.parser.RuleContext;
/*  4:   */ import smarter.common.exception.ExecuteExpection;
/*  5:   */ 
/*  6:   */ public class StringExecuter
/*  7:   */   extends AbstractExecuter
/*  8:   */ {
/*  9:   */   public void execute(RuleContext context, Object param)
/* 10:   */     throws ExecuteExpection
/* 11:   */   {
/* 12:10 */     context.setValue(context.getText());
/* 13:   */   }
/* 14:   */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.common.express.execute.StringExecuter
 * JD-Core Version:    0.7.0.1
 */