package smarter.common.express.execute;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import santr.parser.exception.ExecuteInvaildException;
import santr.v4.execute.AbstractVisitor;
import santr.v4.parser.ParserTree;
import santr.v4.parser.RuleContext;
import smarter.common.exception.ExecuteExpection;
import smarter.common.express.execute.ExprExecuter.Complute;
import smarter.common.util.CheckUtil;

public class ExprVisitor
  extends AbstractVisitor<Map<String, Object>>
{
  private static final Map<String, Complute> compluteMap = new HashMap<>();
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
  
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
	
  public void execute(RuleContext context)
    throws ExecuteInvaildException
  {
    String name = context.getName();
    //char firstChar = name.charAt(0);
    switch (name)
    {
    case "expr": 
      try
      {
        executeExpr(context);
      }
      catch (ExecuteExpection e)
      {
        throw new ExecuteInvaildException(e.getMessage(), e);
      }
    case "INT": 
      executeINT(context);
      break;
    case "ID": 
        executeID(context);
        break;
    case "STRING": 
      executeString(context);
      break;
    case "BOOLEAN": 
      executeBOOLEAN(context);
      break;
    case "DATE": 
      executeDate(context);
      break;
    case "NULL": 
      executeNULL(context);
      break;
    case "param": 
      executeParamArr(context);
    }
  }
  
  private void executeID(RuleContext context) {
	 
	  context.setValue(this.getParamer().get(context.getText()));
  }

  private void executeExpr(RuleContext context)
    throws ExecuteInvaildException, ExecuteExpection
  {
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
        if (token.equals("."))
        {
          Map<String, Object> subParam = (Map)getParamer();
          for (int i = 0; i < context.getChildCount() - 1; i++)
          {
            ParserTree tmpTree = context.getChild(i);
            if (!tmpTree.isToken())
            {
              String key = tmpTree.getRuleContext().getText();
              subParam = (Map)subParam.get(key);
            }
          }
          ParserTree lastTree = context.getChild(context.getChildCount() - 1);
          String lastKey = lastTree.getRuleContext().getText();
          Object object = subParam.get(lastKey);
          context.setValue(object);
          return;
        }
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
  
  private void executeFun(RuleContext context)
    throws ExecuteInvaildException
  {
    List<Double> list = (List)getChildValue(context, 2);
    
    int flag = 0;
    String funName = (String)getChildValue(context, 0);
    if (funName.equals("max")) {
      flag = 1;
    } else if (funName.equals("min")) {
      flag = -1;
    }
    double value = ((Double)list.get(0)).doubleValue();
    for (int i = 1; i < list.size(); i++)
    {
      double num = ((Double)list.get(i)).doubleValue();
      if ((num - value) * flag > 0.0D) {
        value = num;
      }
    }
    context.setValue(Double.valueOf(value));
  }
  
  private void executeArray(RuleContext context)
    throws ExecuteInvaildException
  {
    List<Object> list = new ArrayList();
    ParserTree[] treeInfoList = getAllChild(context);
    for (int i = 0; i < treeInfoList.length; i++) {
      if (!treeInfoList[i].isToken()) {
        list.add(getChildValue(treeInfoList[i]));
      }
    }
    context.setValue(list);
  }
  
  private void executeParam(RuleContext context)
    throws ExecuteInvaildException
  {
    if (context.getChildCount() == 1)
    {
      context.setValue(getChildValue(context, 0));
    }
    else
    {
      List<Double> paramList = (List)getChildValue(context, 0);
      context.setValue(paramList.get(((Integer)getChildValue(context, 2)).intValue()));
    }
  }
  
  private void executeINT(RuleContext context)
  {
    context.setValue(Double.valueOf(context.getText()));
  }
  
  private void executeString(RuleContext context)
  {
    context.setValue(context.getText());
  }
  
  private void executeBOOLEAN(RuleContext context)
    throws ExecuteInvaildException
  {
    String text = (String)getChildValue(context, 0);
    context.setValue(Boolean.valueOf(text));
  }
  
  private boolean compare(Object value, String tag, Object checkValue)
    throws ExecuteExpection
  {
    return CheckUtil.compareByTag(value, tag, checkValue);
  }
  
  private void executeDate(RuleContext context)
    throws ExecuteInvaildException
  {
    String dateStr = context.getText();
    try
    {
      if (dateStr.length() > 10) {
        context.setValue(timeFormat.parse(dateStr));
      } else {
        context.setValue(dateFormat.parse(dateStr));
      }
    }
    catch (ParseException e)
    {
      throw new ExecuteInvaildException("Parser the [" + dateStr + "] is error!", e);
    }
  }
  
  private void executeNULL(RuleContext context)
  {
    context.setValue(null);
  }
  
  private void executeParamArr(RuleContext context)
  {
    int size = context.getChildCount();
    Map<String, Object> value = (Map)getParamer();
    for (int i = 0; i < size - 1;)
    {
      value = (Map)value.get(context.getChild(i).getRuleContext().getText());
      
      i += 2;
    }
    context.setValue(value.get(context.getChild(size - 1).getRuleContext().getText()));
  }
}
