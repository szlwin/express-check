/* 1:  */ package smarter.data.string.check;
/* 2:  */ 
/* 3:  */ import java.util.regex.Pattern;
/* 4:  */ 
/* 5:  */ public class UnMatch
/* 6:  */   extends StringCheck
/* 7:  */ {
/* 8:  */   public boolean check(String o, String regex)
/* 9:  */   {
/* ::8 */     return !Pattern.matches(regex, o);
/* ;:  */   }
/* <:  */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.string.check.UnMatch
 * JD-Core Version:    0.7.0.1
 */