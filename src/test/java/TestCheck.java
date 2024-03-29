
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import santr.common.context.LexerUtil;
import smarter.common.exception.ExecuteExpection;
import smarter.common.express.check.PatternCheck;





public class TestCheck {

	/**
	 * @param args
	 * void
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		//LexerUtil.load("expr", smarter.common.express.execute.ExprExecuter.class.getClassLoader()
		//		.getResourceAsStream("Expr.ls"));
		
		testOther();
		
		testPattenCheck2();
		testPattenCheck();
		testPattenCheck_1();
		testPattenCheck_3();
		//testPropertyCheck_String();
		//testPropertyCheck_Number();
		//testPropertyCheck_Date();
		//testPropertyCheck_Collection();
		//testMap();
		testBoolean();
		
		testRegString();
	}
	
	public static void testOther() throws ExecuteExpection {
		
		PatternCheck pattenCheck = new PatternCheck();
		
		Map<String,Object> map = new HashMap<String,Object>(20);
		
		map.put("u_id", false);
		map.put("userId", -16);
		map.put("uId", true);
		map.put("num", new BigDecimal(123));
		map.put("num1", 123);
		map.put("count", new BigInteger("123"));
		//pattenCheck.setPattern("(!u_id and uId=true) or userId=-16");
		
		pattenCheck.setCheckValue(map);
		
		
		pattenCheck.setPattern("num1 =num");
		System.out.println("Test3:"+pattenCheck.check());
		
		pattenCheck.setPattern("num1 =count");
		System.out.println("Test3:"+pattenCheck.check());
		
		pattenCheck.setPattern("userId=-16");
		System.out.println(pattenCheck.check());
		
		pattenCheck.setPattern("num = null");
		pattenCheck.setCheckValue(map);
		
		
		
		System.out.println("Test:"+pattenCheck.check());
		pattenCheck.setPattern("num = 123");
		System.out.println("Test1:"+pattenCheck.check());
		
		pattenCheck.setPattern("count =123");
		System.out.println("Test1:"+pattenCheck.check());
		
		pattenCheck.setPattern("num1 =123");
		System.out.println("Test2:"+pattenCheck.check());
		

	}
	
	public static void testPattenCheck2() throws ExecuteExpection {

		PatternCheck pattenCheck = new PatternCheck();
		
		Map<String,Object> map = new HashMap<String,Object>(20);
		
		map.put("u_id", null);
		map.put("userId", -16);
		map.put("uId", null);
		pattenCheck.setPattern("userId = -15)");
		pattenCheck.setCheckValue(map);
		System.out.println(pattenCheck.check());
		
		
		pattenCheck.setPattern("userId = u_id)");
		pattenCheck.setCheckValue(map);
		System.out.println(pattenCheck.check());
		
		pattenCheck.setPattern("uId = u_id)");
		pattenCheck.setCheckValue(map);
		System.out.println(pattenCheck.check());
		
		pattenCheck.setPattern("uId = NULL)");
		pattenCheck.setCheckValue(map);
		
		System.out.println(pattenCheck.check());
	}

	private static void testMap() throws ExecuteExpection {
		PatternCheck pattenCheck = new PatternCheck();
		Map<String,Object> map = new HashMap<String,Object>(20);
		map.put("category", "电影看吧");
		//map.put("subType", "电影");
		Map<String,Object> map1 = new HashMap<String,Object>(20);
		
		map1.put("subType", "电影");
		map.put("sub", map1);
		map.put("test.type", "3100");
		//map.put("hd", "false");
		pattenCheck.setPattern("test.type = '3100'");
		pattenCheck.setCheckValue(map);

		System.out.println(pattenCheck.check());
		
		
	}
	
	private static void testBoolean() throws ExecuteExpection {
		PatternCheck pattenCheck = new PatternCheck();
		Map<String,Object> map = new HashMap<String,Object>(20);
		map.put("category", "电影看吧");
		map.put("subType", "电影");
		//map.put("hd", "false");
		pattenCheck.setPattern("category = '电影看吧' and subType = '电影'");
		pattenCheck.setCheckValue(map);
		System.out.println(pattenCheck.check());
		
	}
	private static void testReg() throws ExecuteExpection {
		PatternCheck pattenCheck = new PatternCheck();
		Map<String,Object> map = new HashMap<String,Object>(20);
		map.put("productCount", 10);
		pattenCheck.setPattern("productCount |= '^[0-9]*$'");
		pattenCheck.setCheckValue(map);
		System.out.println(pattenCheck.check());
		System.out.println(Pattern.matches("^[0-9]*$", "9000"));
	}

	private static void testRegString() throws ExecuteExpection {
		PatternCheck pattenCheck = new PatternCheck();
		Map<String,Object> map = new HashMap<String,Object>(20);
		map.put("productCount", "test");
		pattenCheck.setPattern("productCount |= '.*动漫.*'");
		pattenCheck.setCheckValue(map);
		System.out.println(pattenCheck.check());
		System.out.println(Pattern.matches("^[0-9]*$", "9000"));
	}
	
	public static void testPattenCheck() throws ExecuteExpection {

		PatternCheck pattenCheck = new PatternCheck();
		
		Map<String,Object> map = new HashMap<String,Object>(20);
		map.put("productCount", 10);
		map.put("totalPrice", 20);
		map.put("userId", 2);
		pattenCheck.setPattern("( productCount > 0 or totalPrice >= 0 ) or ( productCount*(totalPrice+10) > 200 and userId != 1 )");
		pattenCheck.setCheckValue(map);
		System.out.println(pattenCheck.check());
	}
	
	
	public static void testPattenCheck_1() throws ExecuteExpection {

		PatternCheck pattenCheck = new PatternCheck();
		
		Map<String,Object> map = new HashMap<String,Object>(20);
		map.put("productCount", 10);
		map.put("totalPrice", 20);
		map.put("userId", 2);
		pattenCheck.setPattern("( productCount > 0 or totalPrice >= 0 ) or ( productCount*(totalPrice+10) > 200 and userId != 1 )");
		pattenCheck.setCheckValue(map);
		System.out.println(pattenCheck.check());
	}
	
	public static void testPattenCheck_3() throws ExecuteExpection {

		PatternCheck pattenCheck = new PatternCheck();
		
		Map<String,Object> map = new HashMap<String,Object>(20);
		Map<String,Object> map1 = new HashMap<String,Object>(20);
		map1.put("userName", "u");
		
		Map<String,Object> map2 = new HashMap<String,Object>(20);
		map2.put("uName", "u");
		
		map.put("user", map1);
		map.put("u", map2);
		
		pattenCheck.setPattern("user.userName=u.uName");
		pattenCheck.setCheckValue(map);
		System.out.println(pattenCheck.check());
	}
	
	/*public static void testPropertyCheck_String() {

		PropertyCheck propertyCheck = new PropertyCheck();
		
		
		propertyCheck.setCheckValue("3");
		propertyCheck.setPattern("NOTNULL;NOTEMPTY;NOTEQUAL:'2';EQUAL:'3';LETTER:4;ELETTER:3;GREATER:2;EGREATER:3");
		//propertyCheck.setPatten("NOTNULL;NOTEMPTY;NOTEQUAL:'2';EQUAL:'3';LETTER:4;ELETTER:3");
		//propertyCheck.setPatten("NOTEQUAL:'2'");
		System.out.println(propertyCheck.check());
	}
	
	public static void testPropertyCheck_Number() {

		PropertyCheck propertyCheck = new PropertyCheck();
		
		
		propertyCheck.setCheckValue(null);
		propertyCheck.setPattern("NULL");
		//propertyCheck.setPatten("NOTNULL;NOTEMPTY;NOTEQUAL:'2';EQUAL:'3';LETTER:4;ELETTER:3");
		//propertyCheck.setPatten("NOTEQUAL:'2'");
		System.out.println(propertyCheck.check());
	}
	
	public static void testPropertyCheck_Date() {

		PropertyCheck propertyCheck = new PropertyCheck();
		
		
		//propertyCheck.setCheckValue("2011-12-31");
		propertyCheck.setCheckValue(new Date());
		propertyCheck.setPattern("NOTNULL;NOTEQUAL:#2011-12-30#;EQUAL:#2011-12-31#");
		//propertyCheck.setPatten("NOTNULL;NOTEMPTY;NOTEQUAL:'2';EQUAL:'3';LETTER:4;ELETTER:3");
		//propertyCheck.setPatten("NOTEQUAL:'2'");
		System.out.println(propertyCheck.check());
	}
	
	public static void testPropertyCheck_Collection() {

		PropertyCheck propertyCheck = new PropertyCheck();
		
		
		//propertyCheck.setCheckValue("2011-12-31");
		propertyCheck.setCheckValue(new ArrayList(3));
		propertyCheck.setPattern("GREATER:0");
		//propertyCheck.setPatten("NOTNULL;NOTEMPTY;NOTEQUAL:'2';EQUAL:'3';LETTER:4;ELETTER:3");
		//propertyCheck.setPatten("NOTEQUAL:'2'");
		System.out.println(propertyCheck.check());
	}*/
}
