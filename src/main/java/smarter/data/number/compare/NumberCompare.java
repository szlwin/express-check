/*  1:   */ package smarter.data.number.compare;
import java.math.BigDecimal;
import java.math.BigInteger;

/*  2:   */ 
/*  4:   */ import smarter.common.compare.AbstractCompare;
import smarter.data.number.check.factory.NumberCheckFactory;
/*  6:   */ 
/*  7:   */ public class NumberCompare
/*  8:   */   extends AbstractCompare<Number>
/*  9:   */ {
/* 10:   */   public boolean compare(Number src, String compareTag, Number dest)
/* 11:   */   {

				if(src == null || dest == null){
					throw new RuntimeException("The value is null,src:"+src+",dest:"+dest);
				}
				
				boolean isSrcBigNum = src instanceof BigDecimal 
						|| src instanceof BigInteger;
				
				boolean isDestBigNum = dest instanceof BigDecimal 
						|| dest instanceof BigInteger;
				
				if(isSrcBigNum && isDestBigNum){
					
					if(src.getClass() != dest.getClass()){
						throw new RuntimeException("The big num is can't compare,src:"+src+",dest:"+dest);
					}
					
					if( src instanceof BigDecimal ){
						return compare((BigDecimal)src, compareTag, (BigDecimal)dest);
					}else{
						return compare((BigInteger)src, compareTag, (BigInteger)dest);
					}
					
				}
				
				Object newSrc = null;
				Object newDest = null;
				
				if(isSrcBigNum){
					
					newSrc = src;
					
					if(src instanceof BigDecimal){
						newDest = new BigDecimal(dest.toString());
						
						
					}else{
					
						newDest = BigInteger.valueOf(dest.longValue());
						
						
					}
					
					
				}else{
					newDest = dest;
					
					if(dest instanceof BigDecimal){
						newSrc = new BigDecimal(src.toString());
						
						
					}else{
						newSrc = new BigInteger(src.toString());
						
						
					}
				}
				
				if(isSrcBigNum || isDestBigNum){
					return compareBigNum(newSrc,compareTag, newDest);
				}
				
/* 12:10 */     return NumberCheckFactory.getInstance().getCheck(compareTag).check(src, dest);
/* 15:   */   }


				private boolean compareBigNum(Object src, String compareTag, Object dest){
					if(src instanceof BigDecimal){
						return compareBigDecimal((BigDecimal)src, compareTag, (BigDecimal)dest);
					}else{
						return compareBigInteger((BigInteger)src, compareTag, (BigInteger)dest);
					}

				}
				
				private boolean compareBigDecimal(BigDecimal src, String compareTag, BigDecimal dest){
					
						switch(compareTag){
						 case "=" :
							 return src.compareTo(dest) == 0;
						 case ">" :
							 return src.compareTo(dest) > 0;
						 case ">=" :
							 return src.compareTo(dest) >= 0;
						 case "<" :
							 return src.compareTo(dest) < 0;
						 case "<=" :
							 return src.compareTo(dest) <= 0;
						 case "!=" :
							 return src.compareTo(dest) != 0;					 
						}
						
						throw new RuntimeException("UnSuport compare tag:"+compareTag);
				}
				
				private boolean compareBigInteger(BigInteger src, String compareTag, BigInteger dest){
					
					switch(compareTag){
					 case "=" :
						 return src.compareTo(dest) == 0;
					 case ">" :
						 return src.compareTo(dest) > 0;
					 case ">=" :
						 return src.compareTo(dest) >= 0;
					 case "<" :
						 return src.compareTo(dest) < 0;
					 case "<=" :
						 return src.compareTo(dest) <= 0;
					 case "!=" :
						 return src.compareTo(dest) != 0;					 
					}
					
					throw new RuntimeException("UnSuport compare tag:"+compareTag);
			}
/* 16:   */ }



