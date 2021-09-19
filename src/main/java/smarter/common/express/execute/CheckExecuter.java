package smarter.common.express.execute;

import santr.v4.parser.RuleContext;
import smarter.common.exception.ExecuteExpection;

public abstract interface CheckExecuter
{
  public abstract void execute(RuleContext paramRuleContext, Object paramObject)
    throws ExecuteExpection;
}