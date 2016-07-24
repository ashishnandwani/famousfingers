package net.famousfingers.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import net.famousfingers.constants.ReusableConstants;
import net.famousfingers.domain.FFConsultant;
import net.famousfingers.util.AccessControl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController
{
  protected final Log logger = LogFactory.getLog(getClass());
  String report_of = null;
  Timestamp report_date = null;
  String consultant_name = null;
  String consultantId = null;
  String fullTimestamp = null;
  String onlyDate = null;
  String timeWithoutDay = null;
  String report_status = null;
  String resultSetId = null;
  String message = null;
  Map<String, Object> accessParams = null;
  Boolean isAccessAllowed = Boolean.FALSE;
  @Autowired
  private DataSource ds;
  private Connection con;
  
  public ModelAndView redirectToIncompletePage()
  {
    return new ModelAndView("ff-incomplete");
  }
  
  public ModelAndView redirectToUnAuthorizedPage()
  {
    return new ModelAndView("redirect:/noAccess.do");
  }
  
  public ModelAndView redirectConsultant(String consultantStatus, HttpServletRequest request)
  {
    this.logger.info("Inside redirect consultant method , The status is" + consultantStatus);
    HttpSession ses = request.getSession(false);
    FFConsultant entityInformation = (FFConsultant)ses.getAttribute("entityInformation");
    if (StringUtils.equals(ReusableConstants.CONSULTANT_UNREGISTERED, consultantStatus))
    {
      this.logger.info("Redirecting to create passwordpage");
      return new ModelAndView("redirect:/createPassword.do");
    }
    if (StringUtils.equals(ReusableConstants.CONSULTANT_INCOMPLETE, consultantStatus))
    {
      String param = request.getParameter("param");
      if ((entityInformation == null) && (param == null))
      {
        this.logger.info("Redirecting to  login page with incomplete message");
        return new ModelAndView("redirect:/completeProfile.do");
      }
      if ((param != null) && (param.equals("cancel"))) {
        return new ModelAndView("redirect:/homePage.do");
      }
      return new ModelAndView("redirect:/homePage.do?param=cancel");
    }
    if (StringUtils.equals(ReusableConstants.CONSULTANT_COMPLETE, consultantStatus))
    {
      this.logger.info("Redirecting to  homepage");
      return new ModelAndView("redirect:/homePage.do");
    }
    this.logger.info("Redirecting to  login page" + consultantStatus);
    return new ModelAndView("redirect:/login.do?info_msg=no_access");
  }
  
  public Boolean isUnAuthorizedAccess(HttpServletRequest req, String page)
    throws IOException
  {
    HttpSession ses = req.getSession(false);
    String role = (String)ses.getAttribute("role");
    System.out.println("role is" + role);
    String entity = (String)ses.getAttribute("entity");
    String lookUpKey = entity + ":" + role;
    this.logger.info(lookUpKey);
    Boolean isAccessAllowed = AccessControl.controlAccess(page, lookUpKey);
    return Boolean.valueOf(!isAccessAllowed.booleanValue());
  }
}
