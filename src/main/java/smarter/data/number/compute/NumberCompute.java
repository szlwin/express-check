package smarter.data.number.compute;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


public class NumberCompute {

	private static final Map<String, Complute> compluteMap  = new HashMap<String, Complute>();
   
	static{
	    Complute[] allComplute = Complute.values ();
	
	    for (Complute complute : allComplute) {
	
		   compluteMap.put(complute.getOperator(), complute);
	    }
	    
	}

	public enum Complute{
		
	    ADD("+"){double eval(double x,double y){return x+y;}},   
	    SUB("-"){double eval(double x,double y){return x-y;}},   
	    MUL("*"){double eval(double x,double y){return x*y;}},   
	    DIV("/"){double eval(double x,double y){return x/y;}};
	    abstract double eval(double x,double y);
	    
	    private String token;
	    
	    Complute(String token){
	    	this.token = token;
	    }
	    
	    public String getOperator(){
	    	return token;
	    }
}

	public static Number compute(Number src,String tag, Number dest){
		return compute(src,tag,dest, -1, -1);
	}
	
	public static Number compute(Number src,String tag, Number dest, int type, int num){
		
		Object newSrc = null;
		Object newDest = null;
		
		if(src == null || dest == null){
			throw new RuntimeException("The value is null,src:"+src+",dest:"+dest);
		}
		
		boolean isSrcBigNum = src instanceof BigDecimal 
				|| src instanceof BigInteger;
		
		boolean isDestBigNum = dest instanceof BigDecimal 
				|| dest instanceof BigInteger;
		
		if(!isSrcBigNum && !isDestBigNum){
			
			/*if(src.getClass() != dest.getClass()){
				throw new RuntimeException("The big num is can't compare,src:"+src+",dest:"+dest);
			}*/
			double value = compluteMap.get(tag).eval(src.doubleValue(),dest.doubleValue());
			if(type != -1 && num !=-1){
				return BigDecimal.valueOf(value).setScale(num, getType(type)).doubleValue();
			}else{
				return value;
			}
			
		}

		if(isSrcBigNum){
			
			newSrc = src;
			
			if(src instanceof BigDecimal){
				
				if(dest instanceof BigInteger){
					newDest = new BigDecimal((BigInteger)dest);
				}else{
					newDest = new BigDecimal(dest.toString());
				}
				
			}else{
			
				newDest = BigInteger.valueOf(dest.longValue());
				
			}
			
			
		}else{
			newDest = dest;
			
			if(dest instanceof BigDecimal){
				
				if(src instanceof BigInteger){
					newSrc = new BigDecimal((BigInteger)src);
				}else{
					newSrc = new BigDecimal(src.toString());
				}
				
				
			}else{
				newSrc = BigInteger.valueOf(src.longValue());
				
				
			}
		}
		
		if(newSrc instanceof BigDecimal){
			BigDecimal value = computBigDecimal((BigDecimal)newSrc, tag, (BigDecimal)newDest, num, type);
			
			if(tag.equals("*") && num != -1 && type != -1){

				return value.setScale(num, getType(type));

			}else{
				return value;
			}
		}else{
			return computBigInteger((BigInteger)newSrc, tag, (BigInteger)newDest);
		}
		
	}

	//1:去除小数 2:去除小数进一 3:四舍五入进一
	private static int getType(int type){
		switch(type){
			case 1:
				return BigDecimal.ROUND_HALF_DOWN;
			case 2:
				return BigDecimal.ROUND_UP;
			case 3:
				return BigDecimal.ROUND_HALF_UP;
			
		}
		return -1;
	}
	
	private static BigDecimal computBigDecimal(BigDecimal src, String tag, BigDecimal dest,int type,int num) {
		switch(tag){
		 case "+" :
			 return src.add(dest);
		 case "-" :
			 return src.subtract(dest);
		 case "*" :
			 return src.multiply(dest);
		 case "/" :
			 return src.divide(dest,num,type);					 
		}
		return null;
	}
	
	private static BigInteger computBigInteger(BigInteger src, String tag, BigInteger dest) {
		switch(tag){
		 case "+" :
			 return src.add(dest);
		 case "-" :
			 return src.subtract(dest);
		 case "*" :
			 return src.multiply(dest);
		 case "/" :
			 return src.divide(dest);					 
		}
		return null;
	}
}
