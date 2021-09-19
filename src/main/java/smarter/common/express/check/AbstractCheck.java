package smarter.common.express.check;

public abstract class AbstractCheck<E>
  implements RuleCheck<E>
{
  protected String pattern;
  protected E value;
  
  public void setCheckValue(E value)
  {
    this.value = value;
  }
  
  public void setPattern(String pattern)
  {
    this.pattern = pattern;
  }
}