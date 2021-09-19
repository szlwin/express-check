import java.util.HashMap;
import java.util.Map;

import santr.common.context.LexerUtil;
import santr.v4.parser.ExpressParser;
import santr.view.parser.TreeViewer;
import smarter.common.express.execute.ExprCVisitor;

public class TestExpress {

	public static void main(String[] args) throws Exception {
		//LexerUtil.load("expr", smarter.common.express.execute.ExprExecuter.class.getClassLoader()
		//		.getResourceAsStream("Expr.ls"));
		
		ExpressParser parser = new ExpressParser();
		
		
		Map<String,Object> param = new HashMap<>();
		param.put("userId", 20);
	    //TreeViewer viewer = new TreeViewer(parser.getTree());
	    //viewer.open();


		 ExprCVisitor tExprVisitor = new ExprCVisitor();
         tExprVisitor.setParamer(param);
         parser.addVisitor(tExprVisitor);
         //tExprVisitor.vist(parser.getTree());
         parser.parser("expr", "userId=12");
         
         System.out.println(parser.getTree().getRuleContext().getValue());
	}

}
