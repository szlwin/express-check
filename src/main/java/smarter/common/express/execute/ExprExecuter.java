package smarter.common.express.execute;

import java.util.HashMap;
import java.util.Map;
import santr.parser.exception.ExecuteInvaildException;
import santr.v4.parser.ParserTree;
import santr.v4.parser.RuleContext;
import smarter.common.exception.ExecuteExpection;
import smarter.common.util.CheckUtil;

public class ExprExecuter
  extends AbstractExecuter
{
  private static final Map<String, Complute> compluteMap = new HashMap<>();
  
  static
  {
    Complute[] allComplute = Complute.values();
    
    Complute[] arrayOfComplute1 = allComplute;int j = allComplute.length;
    for (int i = 0; i < j; i++)
    {
      Complute complute = arrayOfComplute1[i];
      
      compluteMap.put(complute.getOperator(), complute);
    }
  }
  
  public static enum Complute
  {
    ADD("+") {
		@Override
		double eval(Number paramNumber1, Number paramNumber2) {

			return paramNumber1.doubleValue()+paramNumber2.doubleValue();
		}
	},  SUB("-") {
		@Override
		double eval(Number paramNumber1, Number paramNumber2) {

			return paramNumber1.doubleValue()-paramNumber2.doubleValue();
		}
	},  MUL("*") {
		@Override
		double eval(Number paramNumber1, Number paramNumber2) {

			return paramNumber1.doubleValue()*paramNumber2.doubleValue();
		}
	},  DIV("/") {
		@Override
		double eval(Number paramNumber1, Number paramNumber2) {
			
			return paramNumber1.doubleValue()/paramNumber2.doubleValue();
		}
	};
    
    private String token;
    
    abstract double eval(Number paramNumber1, Number paramNumber2);
    
    private Complute(String token)
    {
      this.token = token;
    }
    
    public String getOperator()
    {
      return this.token;
    }
  }
  
  private boolean compare(Object value, String tag, Object checkValue)
    throws ExecuteExpection
  {
    return CheckUtil.compareByTag(value, tag, checkValue);
  }
  
  public void executeExpr(RuleContext context, Object param)
    throws ExecuteInvaildException
  {
    super.execute(context, param);
    if (context.getChildCount() == 1)
    {
      context.setValue(getChildValue(context, 0));
    }
    else if (context.getChildCount() == 2)
    {
      String token = (String)getChildValue(context, 0);
      if (token.equals("!"))
      {
        context.setValue(Boolean.valueOf(!((Boolean)getChildValue(context, 1)).booleanValue()));
      }
      else
      {
        Number num = (Number)getChildValue(context, 1);
        context.setValue(Double.valueOf(0.0D - num.doubleValue()));
      }
    }
    else
    {
      ParserTree leftTree = context.getChild(0);
      ParserTree midTree = context.getChild(1);
      ParserTree rightTree = context.getChild(2);
      if (leftTree.isToken())
      {
        context.setValue(getChildValue(midTree));
      }
      else
      {
        String token = midTree.getToken();
        if (compluteMap.containsKey(token))
        {
          Object left = getChildValue(leftTree);
          Object right = getChildValue(rightTree);
          
          Number leftReal = null;
          Number rightReal = null;
          if (!(left instanceof Number)) {
            leftReal = Double.valueOf((String)left);
          } else {
            leftReal = (Number)left;
          }
          if (!(right instanceof Number)) {
            rightReal = Double.valueOf((String)right);
          } else {
            rightReal = (Number)right;
          }
          context.setValue(
          
            Double.valueOf(((Complute)compluteMap.get(token)).eval(leftReal, rightReal)));
        }
        else if (token.equals("and"))
        {
          context.setValue(
            Boolean.valueOf((((Boolean)getChildValue(leftTree)).booleanValue()) && (((Boolean)getChildValue(rightTree)).booleanValue())));
        }
        else if (token.equals("or"))
        {
          context.setValue(
            Boolean.valueOf((((Boolean)getChildValue(leftTree)).booleanValue()) || (((Boolean)getChildValue(rightTree)).booleanValue())));
        }
        else
        {
          Object left = getChildValue(leftTree);
          Object right = getChildValue(rightTree);
          
          context.setValue(Boolean.valueOf(compare(left, token, right)));
        }
      }
    }
  }
  
  public void execute(RuleContext context, Object param)
    throws ExecuteExpection
  {
    try
    {
      executeExpr(context, param);
    }
    catch (ExecuteInvaildException e)
    {
      throw new ExecuteExpection(e, e.getMessage());
    }
  }
}
