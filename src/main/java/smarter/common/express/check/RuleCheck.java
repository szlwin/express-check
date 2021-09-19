package smarter.common.express.check;

import smarter.common.exception.ExecuteExpection;

public  interface RuleCheck<E>
{
  public  boolean check() throws ExecuteExpection;
  
  public  boolean check(String paramString, E paramE) throws ExecuteExpection;
  
  public  void setCheckValue(E paramE);
  
  public  void setPattern(String paramString);
}