package smarter.data.collection.check;

import java.util.Collection;

public class NotEmpty
  extends CollectionCheck
{
  public boolean check(Collection<?> o, Collection<?> e)
  {
    return (o != null) && (!o.isEmpty());
  }
}