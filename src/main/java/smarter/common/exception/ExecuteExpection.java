package smarter.common.exception;

import santr.parser.exception.ExecuteInvaildException;

public class ExecuteExpection
  extends ExecuteInvaildException
{
  private static final long serialVersionUID = -8335727841998719852L;
  
  public ExecuteExpection(String messgae)
  {
    super(messgae);
  }
  
  public ExecuteExpection(String pattern, Exception e)
  {
    super("Exetute the: [" + pattern + "] error ", e);
  }
  
  public ExecuteExpection(Exception e, String message)
  {
    super(message, e);
  }
}
