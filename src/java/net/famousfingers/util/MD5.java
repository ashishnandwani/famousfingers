package net.famousfingers.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5
{
  public static String hex(byte[] array)
  {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < array.length; i++) {
      sb.append(Integer.toHexString(array[i] & 0xFF | 0x100).toUpperCase().substring(1, 3));
    }
    return sb.toString();
  }
  
  public static String md5(String message)
  {
    try
    {
      MessageDigest md = MessageDigest.getInstance("MD5");
      return hex(md.digest(message.getBytes("CP1252")));
    }
    catch (NoSuchAlgorithmException e) {}catch (UnsupportedEncodingException e) {}
    return null;
  }
}
