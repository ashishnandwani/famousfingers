package net.famousfingers.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import net.famousfingers.builder.ConsultantBuilder;
import net.famousfingers.builder.ConsultantListBuilder;
import net.famousfingers.builder.LocationBuilder;
import net.famousfingers.constants.ReusableConstants;
import net.famousfingers.domain.FFConsultant;
import net.famousfingers.domain.Location;
import net.famousfingers.forms.AddConsultantForm;
import net.famousfingers.forms.ConsultantInformation;
import net.famousfingers.forms.ConsultantListForm;
import net.famousfingers.forms.CreatePasswordForm;
import net.famousfingers.forms.PinsForm;
import net.famousfingers.forms.ProfileInformation;
import net.famousfingers.util.CommonUtil;
import net.famousfingers.util.ErrorHandler;
import net.famousfingers.util.MD5;
import net.famousfingers.util.MetaData;
import net.famousfingers.util.PerPageList;
import net.famousfingers.util.SendingEmail;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springmodules.validation.bean.BeanValidator;
import org.springmodules.validation.bean.conf.loader.annotation.AnnotationBeanValidationConfigurationLoader;

@Controller
@SessionAttributes({"entity"})
public class ConsultantController
  extends BaseController
{
  @Autowired
  private DataSource ds;
  private Connection con;
  private String consultantRole = null;
  private String first_name = null;
  String message = null;
  ModelAndView viewName = null;
  private String last_name = null;
  private String mobile_no = null;
  private String email = null;
  String sort_by = "date_added";
  int total_pages = 0;
  int start_index = 0;
  String order_by = "desc";
  FFConsultant entityInformation = null;
  String entity = null;
  protected final Log logger = LogFactory.getLog(getClass());
  private BeanValidator beanValidator = new BeanValidator(new AnnotationBeanValidationConfigurationLoader());
  
  @RequestMapping(value={"/addConsultant"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView validateForm(AddConsultantForm consultantForm, BindingResult result, Map model)
  {
    this.logger.info("Inside validate form");
    this.beanValidator.validate(consultantForm, result);
    if (result.hasErrors())
    {
      this.logger.debug("Error during validaiton, Throwing back to add consultant page");
      return new ModelAndView("add_consultant");
    }
    return new ModelAndView("forward:/processConsultantRequest.do");
  }
  
  @RequestMapping(value={"/processConsultantRequest"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView processAddingConsultant(AddConsultantForm consultantForm, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    try
    {
      String entity_id = null;
      String added_by = null;
      String isNew = "n";
      String location = null;
      String location_code = null;
      String location_new = null;
      this.con = this.ds.getConnection();
      HttpSession session = request.getSession(false);
      
      String role = (String)session.getAttribute("role");
      this.first_name = StringUtils.capitalize(consultantForm.getCs_first_name());
      this.last_name = StringUtils.capitalize(consultantForm.getCs_last_name());
      this.email = consultantForm.getCs_email();
      this.mobile_no = consultantForm.getCs_mobile();
      if (!CommonUtil.doesEmailExists(this.email, this.con))
      {
        if (role.equals("cs_franchise"))
        {
          FFConsultant consultantInfo = (FFConsultant)session.getAttribute("entityInformation");
          this.consultantRole = "normal";
          location_code = consultantInfo.getLocation_code().toUpperCase();
          isNew = "n";
          entity_id = consultantInfo.getConsultant_id();
          added_by = "cs_franchise";
        }
        if (role.equals("super_admin"))
        {
          added_by = "super_admin";
          this.consultantRole = consultantForm.getCs_role();
          entity_id = (String)session.getAttribute("analyst_id");
          location_code = consultantForm.getCs_loc();
          System.out.println("location is" + location + location_code);
          if ((location == null) && (location_code.equals("new_location")))
          {
            isNew = "y";
            location = consultantForm.getNew_loc();
            location_code = consultantForm.getNew_loc_code().toUpperCase();
          }
        }
        consultantId = CommonUtil.insertConsultantDetails(this.first_name, this.last_name, this.email, this.mobile_no, location_code, entity_id, added_by, location, this.consultantRole, isNew, this.con);
        session.setAttribute("name", this.first_name + " " + this.last_name);
        session.setAttribute("heading", consultantId);
        String PASSWORD_CHANGE_MESSAGE = "You have been added as a consultant in FF. Your ID is " + consultantId;
        PASSWORD_CHANGE_MESSAGE = PASSWORD_CHANGE_MESSAGE + "Please use this ID to login.";
        String PASSWORD_CHANGE_SUBJECT = "Welcome to Famous Fingers.";
        Boolean mailSent = SendingEmail.sendMail(this.email, PASSWORD_CHANGE_MESSAGE, PASSWORD_CHANGE_SUBJECT);
        
        this.logger.info("The consultant which just got added has the id as" + consultantId);
      }
      else
      {
        return new ModelAndView("redirect:/homePage.do?info_msg=consultant_exists");
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(ConsultantController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      DbUtils.closeQuietly(this.con);
    }
    return new ModelAndView("redirect:/homePage.do?info_msg=consultant_added");
  }
  
  @RequestMapping(value={"/consultant"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView addConsultant(ConsultantListForm consultantForm, Model model, HttpServletRequest req)
    throws ParseException
  {
    List<Location> locationsList = new ArrayList();
    try
    {
      String subCategory = "consultantPage";
      this.con = this.ds.getConnection();
      List<FFConsultant> consultantList;
      if (!isUnAuthorizedAccess(req, subCategory).booleanValue())
      {
        consultantList = fetchConsultantList(model, this.con, req, consultantForm);
        this.logger.info("Get request for adding consultant processed");
        model.addAttribute("consultantForm", consultantForm);
        model.addAttribute("consultantList", consultantList);
        model.addAttribute("assignPins", new PinsForm());
        
        MetaData metaData = new MetaData();
        metaData = metaData.calculateMetaInfo(this.con);
        req.setAttribute("totalreports", Integer.valueOf(metaData.getTotalNoOfReports()));
        req.setAttribute("totalconsultants", Integer.valueOf(metaData.getTotalConsultants()));
        
        ResultSet locations = CommonUtil.getLocations(this.con);
        LocationBuilder locBuilder = new LocationBuilder();
        locationsList = locBuilder.getLocations(locations);
        model.addAttribute("locationsList", locationsList);
        
        HttpSession ses = req.getSession(false);
        Map<String, Object> msgProperties = ErrorHandler.displayErrorMessages(req);
        String errorMessage = (String)msgProperties.get("error_message");
        String infoMessage = (String)msgProperties.get("info_message");
        System.out.println("info  message os" + infoMessage);
        
        String consultant_name = (String)ses.getAttribute("cons_name");
        String consultant_id = (String)ses.getAttribute("cons_id");
        String cons_other = (String)ses.getAttribute("other_cons");
        String cons_location = (String)ses.getAttribute("location");
        System.out.println("name and id" + consultant_name + consultant_id);
        if ((consultant_name != null) && (cons_location == null) && (infoMessage != null))
        {
          infoMessage = MessageFormat.format(infoMessage, new Object[] { consultant_name, consultant_id });
          System.out.println(infoMessage);
        }
        else if (cons_other != null)
        {
          if (((cons_location != null ? 1 : 0) & (infoMessage != null ? 1 : 0)) != 0)
          {
            System.out.println("nocoming" + infoMessage);
            infoMessage = MessageFormat.format(infoMessage, new Object[] { consultant_name, consultant_id, cons_location, cons_other });
            System.out.println(infoMessage);
          }
        }
        if (StringUtils.isNotBlank(errorMessage)) {
          model.addAttribute("errorMsg", errorMessage);
        } else if (StringUtils.isNotBlank(infoMessage)) {
          model.addAttribute("errorMsg", infoMessage);
        }
      }
      else
      {
        this.logger.info("Redirecting to No Access Page");
        return redirectToUnAuthorizedPage();
      }
    }
    catch (Exception ex)
    {
      Logger.getLogger(ConsultantController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      DbUtils.closeQuietly(this.con);
    }
    return new ModelAndView("add_consultant", "consultantInfo", model);
  }
  
  @RequestMapping(value={"/createPassword"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView validatePasswordForm(HttpServletRequest request, CreatePasswordForm createPasswordForm, BindingResult result, Map model)
  {
    this.logger.info("Inside validate form");
    try
    {
      this.logger.info("Inside validate form");
      MetaData metaData = new MetaData();
      this.con = this.ds.getConnection();
      metaData = metaData.calculateMetaInfo(this.con);
      request.setAttribute("totalreports", Integer.valueOf(metaData.getTotalNoOfReports()));
      request.setAttribute("totalconsultants", Integer.valueOf(metaData.getTotalConsultants()));
      
      this.beanValidator.validate(createPasswordForm, result);
      if (result.hasErrors())
      {
        this.logger.debug("Error during validaiton, Throwing back to add the same page");
        return new ModelAndView("ff.password_create");
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(ConsultantController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      DbUtils.closeQuietly(this.con);
    }
    return new ModelAndView("forward:/processPasswordUpdate.do");
  }
  
  @RequestMapping(value={"/createPassword"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView returnToPasswordPage(Map model, HttpServletRequest request)
    throws IOException, SQLException
  {
    HttpSession ses = request.getSession(false);
    MetaData metaData = new MetaData();
    
    this.con = this.ds.getConnection();
    metaData = metaData.calculateMetaInfo(this.con);
    request.setAttribute("totalreports", Integer.valueOf(metaData.getTotalNoOfReports()));
    request.setAttribute("totalconsultants", Integer.valueOf(metaData.getTotalConsultants()));
    
    String subCategory = "createPassword";
    if (!isUnAuthorizedAccess(request, subCategory).booleanValue())
    {
      CreatePasswordForm createPasswordForm = new CreatePasswordForm();
      this.logger.info("Get request for creating new password");
      model.put("createPasswordForm", createPasswordForm);
      
      String consultantStatus = (String)ses.getAttribute("consultant_status");
      if (!StringUtils.equals(ReusableConstants.CONSULTANT_UNREGISTERED, consultantStatus)) {
        return redirectConsultant(consultantStatus, request);
      }
      return new ModelAndView("ff.password_create");
    }
    this.logger.info("Redirecting to No Access Page");
    return redirectToUnAuthorizedPage();
  }
  
  @ModelAttribute("profileForm")
  public Model putProfileInfo(Model m)
  {
    m.addAttribute("profileInfo", new ProfileInformation());
    return m;
  }
  
  @RequestMapping(value={"/completeProfile"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView completeProfileInformation(HttpServletRequest request, Model model)
    throws IOException
  {
    try
    {
      ConsultantInformation consultantInfo = new ConsultantInformation();
      this.con = this.ds.getConnection();
      HttpSession ses = request.getSession(false);
      String subCategory = "completeProfile";
      ProfileInformation profileInfo = new ProfileInformation();
      model.addAttribute("profileInfo", profileInfo);
      String consultantStatus = (String)ses.getAttribute("consultant_status");
      if (!StringUtils.equals(ReusableConstants.CONSULTANT_INCOMPLETE, consultantStatus)) {
        return redirectConsultant(consultantStatus, request);
      }
      ConsultantBuilder consultantBuilder = new ConsultantBuilder();
      
      String consultantEmail = (String)ses.getAttribute("email");
      ResultSet entityDetails = CommonUtil.getEntityDetails((String)ses.getAttribute("entity"), consultantEmail, this.con);
      
      this.entityInformation = consultantBuilder.getConsultantDetails(entityDetails);
      ses.setAttribute("consultant_id", this.entityInformation.getConsultant_id());
      System.out.println("entityInformation is" + this.entityInformation.getConsultant_id());
      System.out.println(this.entityInformation);
      
      this.logger.info("THe information is" + this.entityInformation);
    }
    catch (SQLException ex)
    {
      Logger.getLogger(ConsultantController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      DbUtils.closeQuietly(this.con);
    }
    return new ModelAndView("ff-incomplete", "entityInformation", this.entityInformation);
  }
  
  @RequestMapping(value={"/completeProfile"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView validateProfileDetails(@ModelAttribute("profileInfo") ProfileInformation profileInfo, BindingResult result, Map model)
  {
    this.logger.info("Inside validate form");
    this.beanValidator.validate(profileInfo, result);
    if (result.hasErrors())
    {
      this.logger.info(result.getAllErrors());
      this.logger.debug("Error during validaiton, Throwing back to add the same page");
      return new ModelAndView("ff-incomplete");
    }
    this.logger.info("Forwardingto saveProfile.do");
    return new ModelAndView("forward:/saveProfileInfo.do", "profileInformation", profileInfo);
  }
  
  @RequestMapping(value={"/registerConsultant"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView registerConsultant(@RequestParam("cs_id") String consultantId, HttpServletRequest request, RedirectAttributes redirectAttributes)
  {
    try
    {
      this.logger.info("Get request for creating new password");
      String consultantStatus = null;
      this.con = this.ds.getConnection();
      String consultant_email;
      if (StringUtils.isNotBlank(consultantId))
      {
        if (consultantId.length() <= 10)
        {
          consultant_email = CommonUtil.getEmail("consultant", consultantId, this.con);
          
          redirectAttributes.addFlashAttribute("email", consultant_email);
          System.out.println(consultantId.length() + "length");
          Boolean consultantExists = CommonUtil.doesConsultantExists(consultantId, this.con);
          Object session;
          if (consultantExists.booleanValue())
          {
            consultantStatus = CommonUtil.getStatus(consultantId, "id", this.con);
            if (!StringUtils.equals(ReusableConstants.CONSULTANT_UNREGISTERED, consultantStatus))
            {
              System.out.println("un registered np");
              return new ModelAndView("redirect:/login.do?info_msg=already_registered");
            }
            session = request.getSession(true);
            ((HttpSession)session).setAttribute("consultant_id", consultantId);
            ((HttpSession)session).setAttribute("entity", "consultant");
            ((HttpSession)session).setAttribute("consultant_status", consultantStatus);
            ((HttpSession)session).setAttribute("email", consultant_email);
            String role = CommonUtil.getRole("consultant", consultantId, this.con);
            ((HttpSession)session).setAttribute("role", role);
            
            this.logger.info("The consultant's status is" + consultantStatus);
          }
          else
          {
            this.logger.info("The consultant does not exist");
            return new ModelAndView("redirect:/login.do?error_msg=auth_failure&consultant_id=" + consultantId);
          }
        }
        else
        {
          return new ModelAndView("redirect:/login.do?error_msg=id_length&consultant_id=" + consultantId);
        }
      }
      else {
        return new ModelAndView("redirect:/login.do?error_msg=blank_null&consultant_id=" + consultantId);
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(ConsultantController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      DbUtils.closeQuietly(this.con);
    }
    return new ModelAndView("redirect:/createPassword.do", "consultant_id", consultantId);
  }
  
  @RequestMapping(value={"/processPasswordUpdate"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView updatePassword(CreatePasswordForm createPasswordForm, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttr)
    throws ServletException, IOException
  {
    try
    {
      String METHOD_NAME = "updatePassword";
      HttpSession ses = request.getSession(false);
      String consultantId = (String)ses.getAttribute("consultant_id");
      this.logger.info("Inside method" + METHOD_NAME);
      this.con = this.ds.getConnection();
      this.logger.info("The consultant  id is " + consultantId);
      Boolean isPasswordUpdated = CommonUtil.createPassword(consultantId, MD5.md5(createPasswordForm.getSet_password()), this.con);
      
      this.logger.info("Inside method name" + METHOD_NAME);
      
      this.logger.info("About to redirect to incomplete page");
      
      String consultantStatus = CommonUtil.getStatus(consultantId, "id", this.con);
      ses.removeAttribute("consultant_status");
      ses.setAttribute("consultant_status", consultantStatus);
      String consultant_email = CommonUtil.getEmail("consultant", consultantId, this.con);
      redirectAttr.addFlashAttribute("email", consultant_email);
    }
    catch (SQLException ex)
    {
      Logger.getLogger(ConsultantController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      DbUtils.closeQuietly(this.con);
    }
    return new ModelAndView("redirect:/completeProfile.do");
  }
  
  @RequestMapping(value={"/saveProfileInfo"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView saveProfileAndRedirect(ProfileInformation consultantInformation, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    try
    {
      this.entity = "consultant";
      HttpSession ses = request.getSession(false);
      this.con = this.ds.getConnection();
      this.consultantId = ((String)ses.getAttribute("consultant_id"));
      System.out.println("consultant id is" + this.consultantId);
      this.logger.info(this.consultantId + "id");
      this.logger.info(consultantInformation);
      CommonUtil.saveProfileInformation(StringUtils.capitalize(consultantInformation.getFirst_name()), StringUtils.capitalize(consultantInformation.getLast_name()), consultantInformation.getEmail(), consultantInformation.getMobile_no(), consultantInformation.getPhone_no(), consultantInformation.getCity(), consultantInformation.getPin_code(), consultantInformation.getAddress(), consultantInformation.getCountry(), this.consultantId, this.con);
      
      String consultantStatus = CommonUtil.getStatus(this.consultantId, "id", this.con);
      ses.removeAttribute("consultant_status");
      ses.setAttribute("consultant_status", consultantStatus);
      this.logger.info(consultantStatus + "status");
      this.logger.info("nowredirecting");
    }
    catch (SQLException ex)
    {
      Logger.getLogger(ConsultantController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      DbUtils.closeQuietly(this.con);
    }
    return new ModelAndView("redirect:/homePage.do", "entity", this.entity);
  }
  
  @RequestMapping(value={"/consultantDisabled"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView redirectToDisabledPage()
  {
    return new ModelAndView("ff.disabled");
  }
  
  @RequestMapping(value={"/noAccess"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView redirectToNoAccessPage()
  {
    return new ModelAndView("no_access");
  }
  
  String cons_name = null;
  String cons_id = null;
  String location_name = null;
  String other_cons = null;
  String cons_role = null;
  
  @RequestMapping(value={"/enableAccount"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView enableCOnsultantAccount(HttpServletRequest request, @RequestParam("cons_id") String consultant_id)
  {
    try
    {
      this.con = this.ds.getConnection();
      String added_by_id = null;
      String entity_id = null;
      HttpSession ses = request.getSession(false);
      ResultSet consultantDetails = CommonUtil.getConsultantDetails(consultant_id, this.con);
      if (consultantDetails.next())
      {
        this.cons_name = consultantDetails.getString("NAME");
        this.cons_id = consultantDetails.getString("consultant_id");
        added_by_id = consultantDetails.getString("added_by_id");
        ses.setAttribute("cons_id", this.cons_id);
        ses.setAttribute("cons_name", this.cons_name);
      }
      this.entity = ((String)ses.getAttribute("entity"));
      if (this.entity.equals("consultant"))
      {
        entity_id = (String)ses.getAttribute("consultant_id");
        if (!entity_id.equals(added_by_id))
        {
          this.message = "You don't have access to perform this action";
          this.viewName = new ModelAndView("ff.no_access", "message", this.message);
          return this.viewName;
        }
        CommonUtil.enableDisableAccount(consultant_id, Boolean.FALSE, this.con);
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(ConsultantController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      DbUtils.closeQuietly(this.con);
    }
    return new ModelAndView("redirect:/consultant.do?info_msg=account_enabled");
  }
  
  @RequestMapping(value={"/disableAccount"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView disableCOnsultantAccount(HttpServletRequest request, @RequestParam("cons_id") String consultant_id)
  {
    try
    {
      this.con = this.ds.getConnection();
      String entity_id = null;
      String added_by_id = null;
      HttpSession ses = request.getSession(false);
      ResultSet consultantDetails = CommonUtil.getConsultantDetails(consultant_id, this.con);
      if (consultantDetails.next())
      {
        this.cons_name = consultantDetails.getString("NAME");
        this.cons_id = consultantDetails.getString("consultant_id");
        this.cons_role = consultantDetails.getString("role");
        added_by_id = consultantDetails.getString("added_by_id");
        
        ses.setAttribute("cons_id", this.cons_id);
        ses.setAttribute("cons_name", this.cons_name);
      }
      this.entity = ((String)ses.getAttribute("entity"));
      if (this.entity.equals("consultant"))
      {
        entity_id = (String)ses.getAttribute("consultant_id");
        if (!entity_id.equals(added_by_id))
        {
          this.message = "You don't have access to perform this action";
          this.viewName = new ModelAndView("ff.no_access", "message", this.message);
          return this.viewName;
        }
      }
      if (this.cons_role.equals("cs_franchise"))
      {
        this.message = "The consultant is also a franchise of his location. Sure disable his account?";
        this.viewName = new ModelAndView("ff.no_access", "message", this.message);
        return this.viewName;
      }
      CommonUtil.enableDisableAccount(consultant_id, Boolean.TRUE, this.con);
    }
    catch (SQLException ex)
    {
      Logger.getLogger(ConsultantController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return new ModelAndView("redirect:/consultant.do?info_msg=account_disabled");
  }
  
  @RequestMapping(value={"/makeIndependent"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView makeIndependent(HttpServletRequest request, @RequestParam("cons_id") String consultant_id)
  {
    try
    {
      this.con = this.ds.getConnection();
      HttpSession ses = request.getSession(false);
      String role = null;
      this.entity = ((String)ses.getAttribute("entity"));
      ResultSet consultantDetails;
      if (this.entity.equals("analyst"))
      {
        role = (String)ses.getAttribute("role");
        if (role.equals("super_admin"))
        {
          consultantDetails = CommonUtil.getConsultantDetails(consultant_id, this.con);
          if (consultantDetails.next())
          {
            this.cons_name = consultantDetails.getString("NAME");
            this.cons_id = consultantDetails.getString("consultant_id");
            System.out.println("cons_name" + this.cons_name);
            ses.setAttribute("cons_id", this.cons_id);
            ses.setAttribute("cons_name", this.cons_name);
          }
          CommonUtil.makeIndependent(consultant_id, this.con);
        }
      }
      else
      {
        this.message = "You don't have access to perform this action";
        this.viewName = new ModelAndView("ff.no_access", "message", this.message);
        return this.viewName;
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(ConsultantController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      DbUtils.closeQuietly(this.con);
    }
    return new ModelAndView("redirect:/consultant.do?info_msg=independent_consultant");
  }
  
  @RequestMapping(value={"/makeFranchise"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView makeFranchise(HttpServletRequest req, @RequestParam("cons_id") String consultant_id)
  {
    try
    {
      this.con = this.ds.getConnection();
      HttpSession ses = req.getSession(false);
      String role = null;
      this.entity = ((String)ses.getAttribute("entity"));
      ResultSet consultantDetails;
      if (this.entity.equals("analyst"))
      {
        role = (String)ses.getAttribute("role");
        if (role.equals("super_admin"))
        {
          consultantDetails = CommonUtil.getConsultantDetails(consultant_id, this.con);
          if (consultantDetails.next())
          {
            this.cons_name = consultantDetails.getString("NAME");
            this.cons_id = consultantDetails.getString("consultant_id");
            this.location_name = consultantDetails.getString("location");
            this.other_cons = consultantDetails.getString("other_cand");
            ses.setAttribute("cons_id", this.cons_id);
            ses.setAttribute("cons_name", this.cons_name);
            ses.setAttribute("location", this.location_name);
            ses.setAttribute("other_cons", this.other_cons);
          }
          CommonUtil.makeFranchise(consultant_id, this.con);
        }
      }
      else
      {
        this.message = "You don't have access to perform this action";
        this.viewName = new ModelAndView("ff.no_access", "message", this.message);
        return this.viewName;
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(ConsultantController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      DbUtils.closeQuietly(this.con);
    }
    return new ModelAndView("redirect:/consultant.do?info_msg=make_franchise");
  }
  
  @RequestMapping(value={"/makeNormalConsultant"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView makeNormalConsultant(HttpServletRequest req, @RequestParam("cons_id") String consultant_id)
  {
    try
    {
      this.con = this.ds.getConnection();
      HttpSession ses = req.getSession(false);
      this.entity = ((String)ses.getAttribute("entity"));
      String role = null;
      ResultSet consultantDetails;
      if (this.entity.equals("analyst"))
      {
        role = (String)ses.getAttribute("role");
        if (role.equals("super_admin"))
        {
          consultantDetails = CommonUtil.getConsultantDetails(consultant_id, this.con);
          if (consultantDetails.next())
          {
            this.cons_name = consultantDetails.getString("NAME");
            this.cons_id = consultantDetails.getString("consultant_id");
            this.location_name = consultantDetails.getString("location");
            this.other_cons = consultantDetails.getString("other_cand");
            ses.setAttribute("cons_id", this.cons_id);
            ses.setAttribute("cons_name", this.cons_name);
            ses.setAttribute("location", this.location_name);
            ses.setAttribute("other_cons", this.other_cons);
          }
          CommonUtil.makeNormal(consultant_id, this.con);
        }
      }
      else
      {
        this.message = "You don't have access to perform this action";
        this.viewName = new ModelAndView("ff.no_access", "message", this.message);
        return this.viewName;
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(ConsultantController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      DbUtils.closeQuietly(this.con);
    }
    return new ModelAndView("redirect:/consultant.do?info_msg=normal_consultant");
  }
  
  private String status = null;
  private String name = null;
  int rows_to_fetch = 10;
  
  private List<FFConsultant> fetchConsultantList(Model model, Connection con, HttpServletRequest request, ConsultantListForm consultantForm)
    throws ParseException
  {
    List<FFConsultant> listOfConsultants = null;
    try
    {
      HttpSession ses = request.getSession(false);
      String entity_id = null;
      int page_no = 1;
      this.sort_by = consultantForm.getSort_by();
      this.order_by = consultantForm.getOrder_by();
      this.status = consultantForm.getStatus();
      if ((this.status != null) && (this.status.equals("null"))) {
        this.status = null;
      }
      this.name = consultantForm.getName();
      page_no = consultantForm.getPage_no();
      if (page_no < 1) {
        page_no = 1;
      }
      PerPageList listing = new PerPageList();
      int end_index = 0;
      int start_index = listing.getStartIndex(page_no);
      
      String isSuperAdmin = "n";
      String entity = (String)ses.getAttribute("entity");
      if (StringUtils.equals("consultant", entity)) {
        entity_id = (String)ses.getAttribute("consultant_id");
      } else if ((StringUtils.equals(entity, "analyst")) && (StringUtils.equals("super_admin", (String)ses.getAttribute("role")))) {
        isSuperAdmin = "y";
      }
      Map<String, Object> consultantsMap = CommonUtil.getConsultantList(isSuperAdmin, entity_id, this.status, this.sort_by, this.order_by, 10, start_index, this.name, con);
      ResultSet consultantsResultSet = (ResultSet)consultantsMap.get("consultantsResultSet");
      int totalConsultants = ((Integer)consultantsMap.get("totalNoOfConsultants")).intValue();
      String no_rows = (String)consultantsMap.get("noRows");
      int totalRows = ((Integer)consultantsMap.get("totalRows")).intValue();
      int totalLocations = ((Integer)consultantsMap.get("totalNoOfLocations")).intValue();
      System.out.println("total lcations an consultnts" + totalConsultants);
      
      ConsultantListBuilder consultantListBuilder = new ConsultantListBuilder();
      listOfConsultants = consultantListBuilder.buildConsultantList(consultantsResultSet);
      if (no_rows.equals("y")) {
        this.total_pages = listing.getNumberOfPages(page_no, totalRows);
      } else if (no_rows.equals("n")) {
        model.addAttribute("total_pages", Integer.valueOf(1));
      }
      model.addAttribute("total_pages", Integer.valueOf(this.total_pages));
      model.addAttribute("current_page", Integer.valueOf(page_no));
      model.addAttribute("total_locations", Integer.valueOf(totalLocations));
      model.addAttribute("total_consultants", Integer.valueOf(totalConsultants));
      
      String url_string = "?" + request.getQueryString();
      if (request.getQueryString() == null) {
        url_string = "consultant.do?submit=GO";
      }
      Pattern replace = Pattern.compile("(?<=[?&;])page_no=.*?($|[&;])");
      
      String url = request.getQueryString();
      Matcher matcher2 = null;
      System.err.println("url" + url);
      if (url != null)
      {
        matcher2 = replace.matcher(url);
        if (matcher2.find()) {
          url_string = url_string.replaceAll("&" + matcher2.group(0), "");
        }
      }
      model.addAttribute("url_string", url_string);
      if (no_rows.equals("n")) {
        listOfConsultants = null;
      }
    }
    catch (SQLException ex)
    {
      ex = 
      
        ex;Logger.getLogger(ConsultantController.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally {}
    return listOfConsultants;
  }
}
