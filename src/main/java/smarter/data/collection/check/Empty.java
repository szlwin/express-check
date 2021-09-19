package smarter.data.collection.check;

import java.util.Collection;

public class Empty
  extends CollectionCheck
{
  public boolean check(Collection<?> o, Collection<?> e)
  {
    return (o != null) && (o.isEmpty());
  }
}