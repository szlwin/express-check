import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import santr.common.context.LexerUtil;
import santr.parser.exception.ExecuteInvaildException;
import santr.v4.parser.ExpressParser;
import santr.view.parser.TreeViewer;
import smarter.common.express.execute.SimpleExprParam;
import smarter.common.express.execute.SimpleExprVisitor;

public class TestCExpress {
	
	public static void main(String args[]) throws Exception{
	    testIf();
	    testIfOnly();
		testNumber();
	    testError();
	    testError1();
	}

	public static void testNumber() throws ExecuteInvaildException{
		String expr = "numer : decimal*binteger*double*int*long+binteger-decimal-double-long-int-decimal;";
		
		//String expr = "numer : decimal+binteger;";
		
		
		SimpleExprVisitor simpleExprVisitor = new SimpleExprVisitor();
		SimpleExprParam simpleExprParam = new SimpleExprParam();
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		paramMap.put("decimal", new BigDecimal("1.1"));
		
		paramMap.put("binteger", new BigInteger("3"));
		
		paramMap.put("double", 1.2);
		
		paramMap.put("int", 2);
		
		paramMap.put("long", 10l);
		
		simpleExprParam.setParamMap(paramMap);
		
		simpleExprVisitor.setParamer(simpleExprParam);
		
		ExpressParser lexerExecuter = new ExpressParser();
		
		lexerExecuter.addVisitor(simpleExprVisitor);
		
		lexerExecuter.parser("SimpleExpr", expr);
		
		System.out.println(paramMap.get("numer"));
	}
	
	public static void testIf() throws ExecuteInvaildException{
		
		String expr = "amount.price : if amount.price>10 then amount.price*1.1  else amount.price*1.2;";
		
		SimpleExprVisitor simpleExprVisitor = new SimpleExprVisitor();
		SimpleExprParam simpleExprParam = new SimpleExprParam();
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		Map<String,Object> paramMap1 = new HashMap<String,Object>();
		
		paramMap.put("amount", paramMap1);
		
		paramMap1.put("price", 11);
		
		simpleExprParam.setParamMap(paramMap);
		
		simpleExprVisitor.setParamer(simpleExprParam);
		
		ExpressParser lexerExecuter = new ExpressParser();
		
		lexerExecuter.addVisitor(simpleExprVisitor);
		
		lexerExecuter.parser("SimpleExpr", expr);
		
		System.out.println(paramMap.get("amount"));
		
		// TreeViewer viewer = new TreeViewer(lexerExecuter.getTree());
		// viewer.open();
	}
	
	public static void testIfOnly() throws ExecuteInvaildException{
		
		String expr = "amount.price : if amount.price>10 then amount.price*1.1;";
		
		SimpleExprVisitor simpleExprVisitor = new SimpleExprVisitor();
		SimpleExprParam simpleExprParam = new SimpleExprParam();
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		Map<String,Object> paramMap1 = new HashMap<String,Object>();
		
		paramMap.put("amount", paramMap1);
		
		paramMap1.put("price", 10);
		
		simpleExprParam.setParamMap(paramMap);
		
		simpleExprVisitor.setParamer(simpleExprParam);
		
		ExpressParser lexerExecuter = new ExpressParser();
		
		lexerExecuter.addVisitor(simpleExprVisitor);
		
		lexerExecuter.parser("SimpleExpr", expr);
		
		System.out.println(paramMap.get("amount"));
		
		// TreeViewer viewer = new TreeViewer(lexerExecuter.getTree());
		// viewer.open();
	}
	
	public static void testError() throws ExecuteInvaildException{
		String expr = "error('1000','amount.price is error') !: amount.price > 10 ;";
		
		SimpleExprVisitor simpleExprVisitor = new SimpleExprVisitor();
		SimpleExprParam simpleExprParam = new SimpleExprParam();
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		Map<String,Object> paramMap1 = new HashMap<String,Object>();
		
		paramMap.put("amount", paramMap1);
		
		paramMap1.put("price", 10);
		
		simpleExprParam.setParamMap(paramMap);
		
		simpleExprVisitor.setParamer(simpleExprParam);
		
		ExpressParser lexerExecuter = new ExpressParser();
		
		lexerExecuter.addVisitor(simpleExprVisitor);
		
		lexerExecuter.parser("SimpleExpr", expr);
		
		System.out.println(simpleExprVisitor.getError().getCode());
	}
	
	public static void testError1() throws ExecuteInvaildException{
		String expr = "error('1000','amount.price is error') : amount.price > 10 ;";
		
		SimpleExprVisitor simpleExprVisitor = new SimpleExprVisitor();
		SimpleExprParam simpleExprParam = new SimpleExprParam();
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		Map<String,Object> paramMap1 = new HashMap<String,Object>();
		
		paramMap.put("amount", paramMap1);
		
		paramMap1.put("price", 11);
		
		simpleExprParam.setParamMap(paramMap);
		
		simpleExprVisitor.setParamer(simpleExprParam);
		
		ExpressParser lexerExecuter = new ExpressParser();
		
		lexerExecuter.addVisitor(simpleExprVisitor);
		
		lexerExecuter.parser("SimpleExpr", expr);
		
		System.out.println(simpleExprVisitor.getError().getCode());
	}
}
