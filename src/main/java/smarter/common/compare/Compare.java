package smarter.common.compare;

public abstract interface Compare<E>
{
  public static final int DATA_TYPE_NUMBER = 0;
  public static final int DATA_TYPE_STRING = 1;
  public static final int DATA_TYPE_DATE = 2;
  public static final String COMPARE_FLAG_EQUAL = "=";
  public static final String COMPARE_FLAG_UNEQUAL = "!=";
  public static final String COMPARE_FLAG_NO = "!";
  public static final String COMPARE_FLAG_GREATER = ">";
  public static final String COMPARE_FLAG_GREATERE = ">=";
  public static final String COMPARE_FLAG_LETTER = "<";
  public static final String COMPARE_FLAG_LETTERE = "<=";
  public static final String COMPARE_FLAG_MATCH = "|=";
  public static final String COMPARE_FLAG_UNMATCH = "!|=";
  
  public abstract boolean compare(E paramE1, String paramString, E paramE2);
}