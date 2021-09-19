/* 1:  */ package smarter.data.collection.check;
/* 2:  */ 
/* 3:  */ import java.util.Collection;
/* 4:  */ 
/* 5:  */ public class Empty
/* 6:  */   extends CollectionCheck
/* 7:  */ {
/* 8:  */   public boolean check(Collection<?> o, Collection<?> e)
/* 9:  */   {
/* ::8 */     return (o != null) && (o.isEmpty());
/* ;:  */   }
/* <:  */ }


/* Location:           D:\pub_work\doc-eq-code-Dgremlin-master\lib\express-check-1.0.jar
 * Qualified Name:     smarter.data.collection.check.Empty
 * JD-Core Version:    0.7.0.1
 */