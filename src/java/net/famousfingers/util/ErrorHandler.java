package net.famousfingers.util;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class ErrorHandler
{
  public static Map<String, Object> displayErrorMessages(HttpServletRequest request)
  {
    Map<String, Object> messageFromProperties = null;
    String info_message = null;
    String error_message = null;
    String isConsultantErrorMsg = "false";
    try
    {
      String errormessageKey = request.getParameter("error_msg");
      String infomessageKey = request.getParameter("info_msg");
      System.out.println("erro" + errormessageKey + "info" + infomessageKey);
      messageFromProperties = new HashMap();
      if (StringUtils.isNotBlank(errormessageKey))
      {
        if ((errormessageKey.equals("id_length")) || (errormessageKey.equals("auth_failure")) || (errormessageKey.equals("blank_null")))
        {
          isConsultantErrorMsg = "true";
          messageFromProperties.put("isConsultantError", isConsultantErrorMsg);
        }
        else
        {
          messageFromProperties.put("isConsultantError", isConsultantErrorMsg);
        }
        error_message = CommonUtil.readProperties("connection.properties").getProperty(errormessageKey);
        messageFromProperties.put("error_message", error_message);
      }
      else if (StringUtils.isNotBlank(infomessageKey))
      {
        info_message = CommonUtil.readProperties("connection.properties").getProperty(infomessageKey);
        messageFromProperties.put("info_message", info_message);
      }
    }
    catch (IOException ex)
    {
      Logger.getLogger(ErrorHandler.class.getName()).log(Level.SEVERE, "I/O eception while evaluating properties frm property file", ex);
    }
    return messageFromProperties;
  }
}
