package net.famousfingers.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import net.famousfingers.forms.LoginForm;
import net.famousfingers.forms.MessagesForm;
import net.famousfingers.util.CommonUtil;
import net.famousfingers.util.ErrorHandler;
import net.famousfingers.util.MD5;
import net.famousfingers.util.MetaData;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.bean.BeanValidator;
import org.springmodules.validation.bean.conf.loader.annotation.AnnotationBeanValidationConfigurationLoader;

@Controller
@SessionAttributes({"entity"})
public class LoginController
  extends BaseController
{
  private BeanValidator beanValidator = new BeanValidator(new AnnotationBeanValidationConfigurationLoader());
  @Autowired
  private DataSource ds;
  @Autowired
  ServletContext context;
  protected final Log logger = LogFactory.getLog(getClass());
  String email = null;
  String passwordEntered = null;
  String entity = null;
  String passwordActual = null;
  private Connection con;
  
  @ModelAttribute("messageForm")
  public Model putMessageForm(Model m, HttpServletRequest request)
  {
    try
    {
      MessagesForm messageFOrm = new MessagesForm();
      m.addAttribute("messageForm", messageFOrm);
      MetaData metaData = new MetaData();
      this.con = this.ds.getConnection();
      metaData = metaData.calculateMetaInfo(this.con);
      request.setAttribute("totalreports", Integer.valueOf(metaData.getTotalNoOfReports()));
      request.setAttribute("totalconsultants", Integer.valueOf(metaData.getTotalConsultants()));
      this.context.setAttribute("totalconsultants", Integer.valueOf(metaData.getTotalConsultants()));
      this.context.setAttribute("totalreports", Integer.valueOf(metaData.getTotalNoOfReports()));
    }
    catch (SQLException ex)
    {
      Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      DbUtils.closeQuietly(this.con);
    }
    return m;
  }
  
  @RequestMapping(value={"/login"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView validateLoginForm(LoginForm loginForm, BindingResult result, Map model)
  {
    this.logger.info("Inside validate form");
    this.beanValidator.validate(loginForm, result);
    if (result.hasErrors())
    {
      System.out.println("errors are there");
      this.logger.debug("Error during validaiton, Throwing back to login page");
      return new ModelAndView("login");
    }
    return new ModelAndView("forward:/authenticateUser.do");
  }
  
  @RequestMapping(value={"/login"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView loginUser(Map model, HttpServletRequest request)
  {
    LoginForm loginForm = new LoginForm();
    try
    {
      MetaData metaData = new MetaData();
      this.con = this.ds.getConnection();
      metaData = metaData.calculateMetaInfo(this.con);
      System.out.println(metaData.getTotalConsultants() + "consultants");
      request.setAttribute("totalreports", Integer.valueOf(metaData.getTotalNoOfReports()));
      request.setAttribute("totalconsultants", Integer.valueOf(metaData.getTotalConsultants()));
      
      Map<String, Object> msgProperties = ErrorHandler.displayErrorMessages(request);
      String isConsultantError = "false";
      isConsultantError = (String)msgProperties.get("isConsultantError");
      String errorMessage = (String)msgProperties.get("error_message");
      String infoMessage = (String)msgProperties.get("info_message");
      System.out.println("errormessage" + errorMessage);
      System.out.println(isConsultantError);
      if (StringUtils.isNotBlank(errorMessage))
      {
        loginForm.setIsConsultantErrorMsg(isConsultantError);
        loginForm.setErrorMsg(errorMessage);
      }
      else if (StringUtils.isNotBlank(infoMessage))
      {
        loginForm.setInfoMsg(infoMessage);
      }
      HttpSession ses = request.getSession();
      String entity = (String)ses.getAttribute("entity");
      if (entity != null) {
        return new ModelAndView("redirect:/homePage.do");
      }
      this.logger.info("Get request processed");
      model.put("loginForm", loginForm);
    }
    catch (SQLException ex)
    {
      Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      DbUtils.closeQuietly(this.con);
    }
    return new ModelAndView("login", "loginForm", loginForm);
  }
  
  @RequestMapping(value={"/authenticateUser"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView handleRequest(Map model, LoginForm loginForm, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    try
    {
      this.con = this.ds.getConnection();
      this.logger.info("Inside method handleRequest-> authenticateUser()");
      this.email = loginForm.getEmail();
      this.passwordEntered = MD5.md5(loginForm.getPassword());
      System.out.println(this.passwordEntered + "passwordEntered");
      
      this.logger.info("the password is" + this.passwordEntered);
      Boolean isEmailExists = Boolean.valueOf(false);
      if ((StringUtils.isNotBlank(this.email)) && (StringUtils.isNotEmpty(this.email))) {
        isEmailExists = CommonUtil.doesEmailExists(this.email, this.con);
      }
      ModelAndView localModelAndView;
      if (isEmailExists.booleanValue())
      {
        this.entity = CommonUtil.getEntity(CommonUtil.isAnalayst(this.email, this.con), CommonUtil.isConsultant(this.email, this.con), this.con);
        this.logger.info("entity" + this.entity);
        this.passwordActual = CommonUtil.getPassword(this.entity, this.email, this.con);
        if (StringUtils.equals(this.passwordActual, this.passwordEntered))
        {
          if (StringUtils.equals(this.entity, "analyst"))
          {
            request.setAttribute("email", this.email);
            request.setAttribute("con", this.con);
            System.out.println("url is" + loginForm.getFrom());
            if (StringUtils.isNotEmpty(loginForm.getFrom()))
            {
              request.setAttribute("from", loginForm.getFrom());
              System.out.println("url is" + loginForm.getFrom());
            }
            System.out.println("emai isss" + this.email + "***********************");
          }
          else if (StringUtils.equals(this.entity, "consultant"))
          {
            if (!CommonUtil.consultantIsDisabled(this.email, this.con).booleanValue())
            {
              if (StringUtils.isNotEmpty(loginForm.getFrom())) {
                request.setAttribute("from", loginForm.getFrom());
              }
            }
            else
            {
              this.logger.info("Redirect to disabled page");
              return new ModelAndView("redirect:/consultantDisabled.do");
            }
          }
        }
        else
        {
          this.logger.warn("The password is not matching , Throw back to login page");
          System.out.println("emai isss" + this.email + "***********************");
          
          loginForm.setEmail(this.email);
          return new ModelAndView("redirect:/login.do?error_msg=incorrect_emailpwd&email=" + this.email, "loginForm", loginForm);
        }
      }
      else
      {
        loginForm.setEmail(this.email);
        model.put("loginForm", loginForm);
        this.logger.warn("The email does not exist , Throw back to login page");
        return new ModelAndView("redirect:/login.do?error_msg=incorrect_emailpwd&email=" + this.email, "loginForm", loginForm);
      }
      request.setAttribute("email", this.email);
      request.setAttribute("con", this.con);
    }
    catch (SQLException ex)
    {
      Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      DbUtils.closeQuietly(this.con);
    }
    return new ModelAndView("forward:/home.do", "entity", this.entity);
  }
}
