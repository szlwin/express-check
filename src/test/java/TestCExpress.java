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

	public static String expr = ""+
			//"numer : decimal*binteger*double*int*long+binteger-decimal-double-long-int-decimal;"+
			//"amount : if amount>10 then amount*1.1  else amount*1.2"+
			//"samount,slist[1...].(n,c),amount.am1.am2,an.list[1...2],an.list1.(num,count),ann.list[...2].(price,numcount) : amount;"+
			//"amount.amount[2222...].(num,nb),count : if acmount>0 then amount*1.1  else amount*1.2;"+
	"productList{} : this.filter(this.num >0);"+
	"productList{1} : this[...2].filter(this.num >0);"+
	"productList : productList.filter(this.num >0);"+
	"productList[1...] : this[1...2].filter(this.num >0);"+
	//"productList.(num,count) : productList[...1].filter(this.num >0);"+
	//"#totalCount,#totalPrice : productList.filter(if this.size>1 then this.num>1 and this.price>1 else this.num>1).sum(this.num,this.price);"+
	//"#totalamount : multiply(#totalPrice,#totalCount,2,1);"+

	//"productList.num,productList.price : productList.filter(this.num>1 and this.price>1).compute(this.num+1,this.price+1);"+

	//"error('100','id²»ÄÜÎª¿Õ') : id != null;"+
	"";
	
	public static void main(String args[]) throws Exception{
		
	     LexerUtil.load("SimpleExpr", 
	    	       smarter.common.express.execute.ExprCVisitor.class
	    	       	.getClassLoader()
	    	        .getResourceAsStream("SimpleExpr.ls"));
		//LexerUtil.load("SimpleExpr", "D:/work_pub_work/express-check/src/main/resources/ComputeExpr.ls");
		
		
	     
	    //GTreeViewer GTreeViewer = new GTreeViewer("test",LexerUtil.getGrammar("SimpleExpr").getgTree());
	    //GTreeViewer.open();
	   
	    //testIf();
	    //testIfOnly();
	     //testNumber();
	    //testError();
	    //testError1();
		 //System.out.println(lexerExecuter.getTree().getRuleContext().getValue());
	    ExpressParser lexerExecuter = new ExpressParser();
	    lexerExecuter.parser("SimpleExpr", expr);
	    TreeViewer viewer = new TreeViewer(lexerExecuter.getTree());
	    viewer.open();


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
