package net.famousfingers.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class AccessControl
{
  public static Boolean controlAccess(String page, String key)
    throws IOException
  {
    String propertyKey = CommonUtil.readProperties("connection.properties").getProperty(page);
    String[] arr = propertyKey.split(",");
    List list = Arrays.asList(arr);
    if (list.contains(key)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
}
