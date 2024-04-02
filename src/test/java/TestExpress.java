import java.util.HashMap;
import java.util.Map;

import santr.common.context.LexerUtil;
import santr.v4.parser.ExpressParser;
import santr.view.parser.TreeViewer;
import smarter.common.express.execute.ExprCVisitor;

public class TestExpress {

	public static void main(String[] args) throws Exception {
		ExpressParser parser = new ExpressParser();
		
		
		Map<String,Object> param = new HashMap<>();
		param.put("userId", 20);
		 ExprCVisitor tExprVisitor = new ExprCVisitor();
         tExprVisitor.setParamer(param);
         parser.addVisitor(tExprVisitor);
         parser.parser("expr", "-22+userId");
         
         System.out.println(parser.getTree().getRuleContext().getValue());
	}

}
