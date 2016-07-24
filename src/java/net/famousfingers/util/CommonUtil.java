package net.famousfingers.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import net.famousfingers.constants.ReusableConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommonUtil
{
  protected static final Log logger = LogFactory.getLog(CommonUtil.class);
  
  public static Properties readProperties(String filename)
    throws IOException
  {
    Properties props = new Properties();
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream stream = loader.getResourceAsStream(filename);
    props.load(stream);
    return props;
  }
  
  public static Boolean isAnalayst(String email, Connection con)
    throws SQLException
  {
    String methodName = "isAnalayst";
    logger.info("Inside method" + methodName);
    CallableStatement callIsAnalyst = con.prepareCall("{call isAnalyst(?,?)}");
    callIsAnalyst.setString(1, email);
    logger.info("Calling procedureisAnalyst with" + email + "as parameter");
    callIsAnalyst.registerOutParameter(2, 16);
    callIsAnalyst.execute();
    logger.info("Procedure call successful" + callIsAnalyst.toString());
    Boolean isAnalyst = Boolean.valueOf(callIsAnalyst.getBoolean(2));
    return isAnalyst;
  }
  
  public static Boolean isConsultant(String email, Connection con)
    throws SQLException
  {
    String methodName = "isConsultant";
    logger.info("Inside method" + methodName);
    CallableStatement callIsConsultant = con.prepareCall("{call isConsultant(?,?)}");
    callIsConsultant.setString(1, email);
    logger.info("Calling procedure isConsultant with" + email + "as parameter");
    callIsConsultant.registerOutParameter(2, 16);
    callIsConsultant.execute();
    logger.info("Procedure call successful" + callIsConsultant.toString());
    Boolean isConsultant = Boolean.valueOf(callIsConsultant.getBoolean(2));
    return isConsultant;
  }
  
  public static Boolean doesEmailExists(String email, Connection con)
    throws SQLException
  {
    String methodName = "doesEmailExists";
    logger.info("Inside method" + methodName);
    CallableStatement callDoesEmailExist = con.prepareCall("{call doesEmailExists(?,?)}");
    callDoesEmailExist.setString(1, email);
    logger.info("Calling proceduredoesEmailExists with" + email + "as parameter");
    callDoesEmailExist.registerOutParameter(2, 16);
    callDoesEmailExist.execute();
    logger.info("Procedure call successful" + callDoesEmailExist.toString());
    Boolean doesEmailExists = Boolean.valueOf(callDoesEmailExist.getBoolean(2));
    return doesEmailExists;
  }
  
  public static String getPassword(String object, String id, Connection con)
    throws SQLException
  {
    String methodName = "getPassword";
    logger.info("Inside method" + methodName);
    
    CallableStatement callGetPassword = con.prepareCall("{call getPassword(?,?,?)}");
    callGetPassword.setString(1, object);
    callGetPassword.setString(2, id);
    callGetPassword.registerOutParameter(3, 1);
    logger.info("Calling procedure getPassword with" + object + "and" + id + "as parameters");
    callGetPassword.executeQuery();
    logger.info("Procedure call successful" + callGetPassword.toString());
    String getPassword = callGetPassword.getString(3);
    return getPassword;
  }
  
  public static String getRole(String object, String id, Connection con)
    throws SQLException
  {
    String methodName = "getRole";
    logger.info("Inside method" + methodName);
    CallableStatement callGetRole = con.prepareCall("{call getRole(?,?,?)}");
    callGetRole.setString(1, object);
    callGetRole.setString(2, id);
    callGetRole.registerOutParameter(3, 1);
    logger.info("Calling proceduregetRole with" + object + "and" + id + "as parameters");
    callGetRole.executeQuery();
    logger.info("Procedure call successful" + callGetRole.toString());
    String getRole = callGetRole.getString(3);
    return getRole;
  }
  
  public static String getStatus(String id, String typeToFetch, Connection con)
    throws SQLException
  {
    String methodName = "getStatus";
    logger.info("Inside method" + methodName);
    CallableStatement callGetStatus = con.prepareCall("{call getStatus(?,?,?)}");
    callGetStatus.setString(1, typeToFetch);
    callGetStatus.setString(2, id);
    callGetStatus.registerOutParameter(3, 1);
    logger.info("Calling proceduregetStatus withand" + id + "as parameters");
    callGetStatus.executeQuery();
    logger.info("Procedure call successful" + callGetStatus.toString());
    String getStatus = callGetStatus.getString(3);
    return getStatus;
  }
  
  public static String getEntity(Boolean isAnalyst, Boolean isConsultant, Connection con)
  {
    String methodName = "getEntity";
    String entity = null;
    logger.info("Inside method" + methodName);
    if ((isAnalyst.booleanValue()) && (!isConsultant.booleanValue()))
    {
      entity = "analyst";
      logger.info("The resultant object isanalyst");
    }
    else if ((isConsultant.booleanValue()) && (!isAnalyst.booleanValue()))
    {
      entity = "consultant";
      logger.info("The resultant object isconsultant");
    }
    else
    {
      logger.fatal("Procedure not returing correct values");
    }
    return entity;
  }
  
  public static Boolean consultantIsDisabled(String email, Connection con)
    throws SQLException
  {
    String methodName = "consultantIsDisabled";
    logger.info("Inside method" + methodName);
    CallableStatement callConsultantIsDisabled = con.prepareCall("{call is_consult_disabled(?,?)}");
    logger.info("Calling procedureisConsultantDisabled");
    callConsultantIsDisabled.setString(1, email);
    callConsultantIsDisabled.registerOutParameter(2, 16);
    callConsultantIsDisabled.executeQuery();
    logger.info("Procedure call successful" + callConsultantIsDisabled.toString());
    Boolean consultantIsDisabled = Boolean.valueOf(callConsultantIsDisabled.getBoolean(2));
    logger.info("The result is" + consultantIsDisabled);
    return consultantIsDisabled;
  }
  
  public static ResultSet getEntityDetails(String entity, String email, Connection con)
    throws SQLException
  {
    String methodName = "getEntityDetails";
    logger.info("Inside method" + methodName);
    CallableStatement callEntityDetails = con.prepareCall("{call get_analyst_consultant_details(?,?)}");
    logger.info("Calling procedure get_analyst_consultant_details");
    callEntityDetails.setString(1, entity);
    callEntityDetails.setString(2, email);
    callEntityDetails.execute();
    logger.info(callEntityDetails.toString());
    return callEntityDetails.getResultSet();
  }
  
  public static ResultSet getWhiteboardMsgDetails(int msg_id, Connection con)
    throws SQLException
  {
    String methodName = "getWhiteboardMsgDetails";
    logger.info("Inside method" + methodName);
    CallableStatement callMessageDetails = con.prepareCall("{call whiteboard_msg_details(?)}");
    logger.info("Calling procedure get_analyst_consultant_details");
    callMessageDetails.setInt(1, msg_id);
    callMessageDetails.execute();
    callMessageDetails.toString();
    return callMessageDetails.getResultSet();
  }
  
  public static String insertConsultantDetails(String firstName, String lastName, String email, String mobile_no, String location, String addedbyId, String addedBy, String locationCode, String roleDesc, String isNew, Connection con)
    throws SQLException
  {
    String methodName = "insertConsultantDetails";
    logger.info("Inside method" + methodName);
    CallableStatement callAddConsultant = con.prepareCall("{call add_consultant(?,?,?,?,?,?,?,?,?,?,?)}");
    logger.info("Calling procedure get_analyst_consultant_details");
    callAddConsultant.setString(1, firstName);
    callAddConsultant.setString(2, lastName);
    callAddConsultant.setString(3, email);
    callAddConsultant.setString(4, mobile_no);
    callAddConsultant.setString(5, location);
    callAddConsultant.setString(6, addedbyId);
    callAddConsultant.setString(7, addedBy);
    callAddConsultant.setString(8, locationCode);
    callAddConsultant.setString(9, roleDesc);
    callAddConsultant.setString(10, isNew);
    callAddConsultant.registerOutParameter(11, 1);
    callAddConsultant.execute();
    logger.info(callAddConsultant.toString());
    String consultantId = callAddConsultant.getString(11);
    return consultantId;
  }
  
  public static void updatePassword(String entity, String entityId, String passwordToSet, Connection con)
    throws SQLException
  {
    String methodName = "updatePassword";
    logger.info("Inside method" + methodName);
    CallableStatement callUpdatePassword = con.prepareCall("{call " + ReusableConstants.PROCEDURE_UPDATE_PASSWORD + "(?,?,?)}");
    
    String hashedPassword = MD5.md5(passwordToSet);
    callUpdatePassword.setString(1, entity);
    callUpdatePassword.setString(2, entityId);
    callUpdatePassword.setString(3, passwordToSet);
    callUpdatePassword.execute();
    logger.info("Procedure call successful" + callUpdatePassword.toString());
  }
  
  public static Boolean createPassword(String consultantId, String passwordToSet, Connection con)
    throws SQLException
  {
    String methodName = "createPassword";
    logger.info("Inside method" + methodName);
    CallableStatement callcreatePassword = con.prepareCall("{call edit_consultant_pwd(?,?,?)}");
    
    String hashedPassword = MD5.md5(passwordToSet);
    callcreatePassword.setString(1, consultantId);
    callcreatePassword.setString(2, passwordToSet);
    callcreatePassword.registerOutParameter(3, 16);
    callcreatePassword.execute();
    logger.info("Procedure call successful" + callcreatePassword.toString());
    Boolean passwordCreated = Boolean.valueOf(callcreatePassword.getBoolean("is_pwd_updated"));
    return passwordCreated;
  }
  
  public static String getEmail(String object, String id, Connection con)
    throws SQLException
  {
    String methodName = "getEmail";
    logger.info("Inside method" + methodName);
    CallableStatement callGetEmail = con.prepareCall("{call " + ReusableConstants.PROCEDURE_GET_EMAIL + "(?,?,?)}");
    callGetEmail.setString(1, object);
    callGetEmail.setString(2, id);
    callGetEmail.registerOutParameter(3, 1);
    logger.info("Calling procedure getPassword with" + object + "and" + id + "as parameters");
    callGetEmail.executeQuery();
    logger.info("Procedure call successful" + callGetEmail.toString());
    String getEmail = callGetEmail.getString(3);
    logger.info(getEmail + "email");
    return getEmail;
  }
  
  public static Boolean doesConsultantExists(String id, Connection con)
    throws SQLException
  {
    String methodName = "doesConsultantExists";
    logger.info("Inside method" + methodName);
    CallableStatement calldoesConsultantExists = con.prepareCall("{call " + ReusableConstants.PROCEDURE_DOES_CONSULTANT_EXISTS + "(?,?)}");
    calldoesConsultantExists.setString(1, id);
    logger.info("Calling procedure isConsultant with" + id + "as parameter");
    calldoesConsultantExists.registerOutParameter(2, 16);
    calldoesConsultantExists.execute();
    logger.info("Procedure call successful" + calldoesConsultantExists.toString());
    Boolean isConsultant = Boolean.valueOf(calldoesConsultantExists.getBoolean(2));
    return isConsultant;
  }
  
  public static void saveProfileInformation(String firstName, String lastName, String email, String mobile_no, String phone_no, String city, String pin, String address, String country, String consultantId, Connection con)
    throws SQLException
  {
    String methodName = "saveProfileInformation";
    logger.info("Inside method" + methodName);
    CallableStatement callAddConsultant = con.prepareCall("{call " + ReusableConstants.PROCEDURE_SAVE_CONSULTANT_PROFILE_INFO + "(?,?,?,?,?,?,?,?,?,?)}");
    logger.info("Calling procedure get_analyst_consultant_details");
    callAddConsultant.setString(1, firstName);
    callAddConsultant.setString(2, lastName);
    callAddConsultant.setString(3, email);
    callAddConsultant.setString(4, mobile_no);
    callAddConsultant.setString(5, phone_no);
    callAddConsultant.setString(6, city);
    callAddConsultant.setString(7, pin);
    callAddConsultant.setString(8, address);
    callAddConsultant.setString(9, country);
    callAddConsultant.setString(10, consultantId);
    callAddConsultant.execute();
    logger.info(callAddConsultant.toString());
  }
  
  public static void getConsultantList(String firstName, String lastName, String email, String mobile_no, String phone_no, String city, String pin, String address, String country, String consultantId, Connection con)
    throws SQLException
  {
    String methodName = "saveProfileInformation";
    logger.info("Inside method" + methodName);
    CallableStatement callAddConsultant = con.prepareCall("{call " + ReusableConstants.PROCEDURE_SAVE_CONSULTANT_PROFILE_INFO + "(?,?,?,?,?,?,?,?,?,?)}");
    logger.info("Calling procedure get_analyst_consultant_details");
    callAddConsultant.setString(1, firstName);
    callAddConsultant.setString(2, lastName);
    callAddConsultant.setString(3, email);
    callAddConsultant.setString(4, mobile_no);
    callAddConsultant.setString(5, phone_no);
    callAddConsultant.setString(6, city);
    callAddConsultant.setString(7, pin);
    callAddConsultant.setString(8, address);
    callAddConsultant.setString(9, country);
    callAddConsultant.setString(10, consultantId);
    callAddConsultant.execute();
    logger.info(callAddConsultant.toString());
  }
  
  public static void cancelReport(String entity, String consultantId, String analystId, String reportId, String cancelationReason, Connection con)
    throws SQLException
  {
    String methodName = "cancelReport";
    logger.info("Inside method" + methodName);
    CallableStatement callcancelReport = con.prepareCall("{call " + ReusableConstants.PROCEDURE_CANCEL_REPORT + "(?,?,?,?,?)}");
    logger.info("Calling procedure" + ReusableConstants.PROCEDURE_CANCEL_REPORT);
    callcancelReport.setString(1, entity);
    callcancelReport.setString(2, consultantId);
    callcancelReport.setString(3, analystId);
    callcancelReport.setInt(4, Integer.parseInt(reportId));
    logger.info(callcancelReport.toString());
    callcancelReport.setString(5, cancelationReason);
    callcancelReport.execute();
    logger.info(callcancelReport.toString());
  }
  
  public static ResultSet getWhiteBoardMessages(Connection con)
    throws SQLException
  {
    String methodName = "getWhiteBoardMessages";
    logger.info("Inside method" + methodName);
    CallableStatement callWhiteBoardMessages = con.prepareCall("{call " + ReusableConstants.PROCEDURE_WHITEBOARD_MESSAGES + "()}");
    logger.info("Calling procedure whitebrd_msgs");
    callWhiteBoardMessages.execute();
    logger.info(callWhiteBoardMessages.toString());
    return callWhiteBoardMessages.getResultSet();
  }
  
  public static Map<String, Object> getConsultantList(String isSuperAdmin, String entityId, String status, String sortBy, String orderBy, int noOfRows, int startIndex, String nameToLook, Connection con)
    throws SQLException
  {
    CallableStatement callgetConsultantList = null;
    String methodName = "getConsultantList";
    callgetConsultantList = con.prepareCall("{call consultant_list_for_superadmin (?,?,?,?,?,?,?,?,?,?)}");
    if (isSuperAdmin.equals("n")) {
      callgetConsultantList = con.prepareCall("{call consultant_list_for_franchise (?,?,?,?,?,?,?,?,?,?)}");
    }
    logger.info("Inside method" + methodName);
    logger.info("Calling procedure getConsultantList");
    callgetConsultantList.setString(1, status);
    callgetConsultantList.setString(2, sortBy);
    callgetConsultantList.setString(3, orderBy);
    callgetConsultantList.setInt(4, noOfRows);
    
    callgetConsultantList.setInt(5, startIndex);
    logger.info(callgetConsultantList.toString());
    
    callgetConsultantList.registerOutParameter(6, 4);
    
    callgetConsultantList.registerOutParameter(7, 1);
    callgetConsultantList.setString(8, nameToLook);
    if (isSuperAdmin.equals("n"))
    {
      System.out.println("entityoddddd" + entityId);
      callgetConsultantList.setString(9, entityId);
      callgetConsultantList.registerOutParameter(10, 4);
    }
    else
    {
      callgetConsultantList.registerOutParameter(9, 4);
      callgetConsultantList.registerOutParameter(10, 4);
    }
    callgetConsultantList.execute();
    logger.info(callgetConsultantList.toString());
    int totalConsultants = 0;
    int totalLocations = 0;
    int totalRows = 0;
    if (isSuperAdmin.equals("y"))
    {
      totalLocations = callgetConsultantList.getInt("no_of_loc");
      System.out.println("" + totalConsultants + totalLocations);
    }
    totalConsultants = callgetConsultantList.getInt("no_of_consult");
    
    String no_row = callgetConsultantList.getString("no_rows");
    
    totalRows = callgetConsultantList.getInt("total_rows");
    
    logger.info("Calling procedure Reports");
    Map<String, Object> consultantsMap = new HashMap();
    consultantsMap.put("totalNoOfConsultants", Integer.valueOf(totalConsultants));
    consultantsMap.put("noRows", no_row);
    consultantsMap.put("totalRows", Integer.valueOf(totalRows));
    consultantsMap.put("totalNoOfLocations", Integer.valueOf(totalLocations));
    consultantsMap.put("consultantsResultSet", callgetConsultantList.getResultSet());
    
    return consultantsMap;
  }
  
  public static String returnTimeFormat(String timeStampFotmat, Timestamp timestamp)
    throws ParseException
  {
    SimpleDateFormat simpleDateFormat = null;
    if (StringUtils.equals(timeStampFotmat, "fulltimestamp")) {
      simpleDateFormat = new SimpleDateFormat("HH:mm, EEEE, dd MMMM, yyyy");
    } else if (StringUtils.equals(timeStampFotmat, "onlydate")) {
      simpleDateFormat = new SimpleDateFormat("EEEE, dd MMMM, yyyy");
    } else if (StringUtils.equals(timeStampFotmat, "withoutday")) {
      simpleDateFormat = new SimpleDateFormat("dd MMMM, yyyy");
    }
    return simpleDateFormat.format(timestamp);
  }
  
  public static String returnCurrentTimeFormatted()
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH.mm.ss");
    Date dt = new Date();
    
    return sdf.format(dt);
  }
  
  public static ResultSet getNotifications(String id, Connection con)
    throws SQLException
  {
    String methodName = "getNotifications";
    logger.info("Inside method" + methodName);
    CallableStatement callNotifications = con.prepareCall("{call " + ReusableConstants.PROCEDURE_NOTIFICATIONS_LIST + "(?)}");
    callNotifications.setString(1, id);
    logger.info("Calling procedure whitebrd_msgs");
    callNotifications.execute();
    logger.info(callNotifications.toString());
    return callNotifications.getResultSet();
  }
  
  public static void createReport(String consultantId, String reportOf, String reportType, String filePath, Connection con)
    throws SQLException
  {
    String methodName = "createReport";
    logger.info("Inside method" + methodName);
    CallableStatement callCreateReport = con.prepareCall("{call " + ReusableConstants.PROCEDURE_CREATE_REPORT + "(?,?,?,?)}");
    logger.info("Calling procedure get_analyst_consultant_details");
    callCreateReport.setString(1, consultantId);
    callCreateReport.setString(2, reportOf);
    callCreateReport.setString(3, reportType);
    callCreateReport.setString(4, filePath);
    callCreateReport.execute();
    logger.info(callCreateReport.toString());
  }
  
  public static void submitReport(String consultantId, String reportId, String filePath, String remarks, Connection con)
    throws SQLException
  {
    String methodName = "submitReport";
    logger.info("Inside method" + methodName);
    CallableStatement callCreateReport = con.prepareCall("{call " + ReusableConstants.PROCEDURE_SUBMIT_REPORT + "(?,?,?,?)}");
    logger.info("Calling procedure submitReport");
    callCreateReport.setString(1, consultantId);
    callCreateReport.setString(2, reportId);
    callCreateReport.setString(3, filePath);
    callCreateReport.setString(4, remarks);
    
    callCreateReport.execute();
    logger.info(callCreateReport.toString());
  }
  
  public static Map<String, Object> getReports(String entity, String status, String sortBy, String orderBy, String report_of, int no_of_rows, int start_index, String consultantId, Connection con)
    throws SQLException
  {
    String methodName = "getReports";
    logger.info("Inside method" + methodName);
    String procToExec = "";
    if (StringUtils.equals("consultant", entity)) {
      procToExec = ReusableConstants.PROCEDURE_REPORTS_LIST_CONSULTANT;
    } else if (StringUtils.equals("analyst", entity)) {
      procToExec = ReusableConstants.PROCEDURE_REPORTS_LIST_ANALYST;
    }
    CallableStatement callReports = con.prepareCall("{ call " + procToExec + "(?,?,?,?,?,?,?,?,?,?)}");
    callReports.setString(1, status);
    callReports.setString(2, sortBy);
    callReports.setString(3, orderBy);
    callReports.setString(4, report_of);
    callReports.setInt(5, no_of_rows);
    callReports.setInt(6, start_index);
    callReports.setString(7, consultantId);
    callReports.registerOutParameter(8, 4);
    callReports.registerOutParameter(9, 1);
    
    callReports.registerOutParameter(10, 4);
    
    logger.info(callReports.toString());
    
    callReports.executeUpdate();
    logger.info(callReports.toString());
    
    int totalReports = callReports.getInt("total_rows");
    
    String no_row = callReports.getString("no_rows");
    
    int pendingReports = callReports.getInt("no_of_pending_reports");
    
    logger.info("Calling procedure Reports");
    Map<String, Object> reportsMap = new HashMap();
    reportsMap.put("totalNoOfReports", Integer.valueOf(pendingReports));
    reportsMap.put("totalRows", Integer.valueOf(totalReports));
    reportsMap.put("noRows", no_row);
    System.out.println("totalrows" + totalReports);
    reportsMap.put("reportsResultSet", callReports.getResultSet());
    return reportsMap;
  }
  
  public static String getReportStatus(String reportId, Connection con)
    throws SQLException
  {
    String methodName = "getReportStatus";
    logger.info("Inside method" + methodName);
    CallableStatement callGetReportStatus = con.prepareCall("{call " + ReusableConstants.PROCEDURE_GET_REPORT_STATUS + "(?,?)}");
    callGetReportStatus.setString(1, reportId);
    callGetReportStatus.registerOutParameter(2, 1);
    callGetReportStatus.executeQuery();
    logger.info("Procedure call successful" + callGetReportStatus.toString());
    String getReportStatus = callGetReportStatus.getString(2);
    return getReportStatus;
  }
  
  public static int getPinsLeft(String consultantId, Connection con)
    throws SQLException
  {
    String methodName = "getPinsLeft";
    logger.info("Inside method" + methodName);
    CallableStatement callGetPinsLeft = con.prepareCall("{call " + ReusableConstants.PROCEDURE_GET_PINS_LEFT + "(?,?)}");
    callGetPinsLeft.setString(1, consultantId);
    callGetPinsLeft.registerOutParameter(2, 4);
    callGetPinsLeft.executeQuery();
    logger.info("Procedure call successful" + callGetPinsLeft.toString());
    int totalPins = callGetPinsLeft.getInt(2);
    return totalPins;
  }
  
  public static void postMessages(String heading, String message, String entityId, Connection con)
    throws SQLException
  {
    String methodName = "postMessages";
    logger.info("Inside method" + methodName);
    CallableStatement callPostMessages = con.prepareCall("{call " + ReusableConstants.PROCEDURE_POST_MESAGES + "(?,?,?)}");
    callPostMessages.setString(1, heading);
    callPostMessages.setString(2, message);
    callPostMessages.setString(3, entityId);
    callPostMessages.execute();
    logger.info(callPostMessages.toString());
  }
  
  public static ResultSet getLocations(Connection con)
    throws SQLException
  {
    String methodName = "getLocations";
    logger.info("Inside method" + methodName);
    CallableStatement callGetLocations = con.prepareCall("{call " + ReusableConstants.PROCEDURE_LOCATIONS_LIST + "()}");
    logger.info("Calling procedure getLocations");
    callGetLocations.executeQuery();
    logger.info(callGetLocations.toString());
    return callGetLocations.getResultSet();
  }
  
  public static int getTotalConsultants(Connection con)
    throws SQLException
  {
    String methodName = "getTotalConsultants";
    logger.info("Inside method" + methodName);
    CallableStatement callGetTotalConsultants = con.prepareCall("{call " + ReusableConstants.PROCEDURE_TOTAL_CONSULTANTS + "(?)}");
    logger.info("Calling procedure getTotalNoOfConsultants");
    callGetTotalConsultants.registerOutParameter(1, 4);
    logger.info(callGetTotalConsultants.toString());
    
    callGetTotalConsultants.execute();
    return callGetTotalConsultants.getInt(1);
  }
  
  public static ResultSet getTotalConsultantReports(String consultantId, Connection con)
    throws SQLException
  {
    String methodName = "getTotalConsultantReports";
    logger.info("Inside method" + methodName);
    CallableStatement callgetTotalConsultantReports = con.prepareCall("{call " + ReusableConstants.PROCEDURE_TOTAL_CONSULTANT_REPORTS + "(?)}");
    logger.info("Calling procedure getTotalNoOfConsultants");
    callgetTotalConsultantReports.setString(1, consultantId);
    logger.info(callgetTotalConsultantReports.toString());
    
    callgetTotalConsultantReports.execute();
    return callgetTotalConsultantReports.getResultSet();
  }
  
  public static int getTotalReports(Connection con)
    throws SQLException
  {
    String methodName = "getTotalReports";
    logger.info("Inside method" + methodName);
    CallableStatement getTotalReports = con.prepareCall("{call " + ReusableConstants.PROCEDURE_TOTAL_REPORTS + "(?)}");
    logger.info("Calling procedure getTotalReports");
    getTotalReports.registerOutParameter(1, 4);
    
    getTotalReports.execute();
    logger.info(getTotalReports.toString());
    return getTotalReports.getInt(1);
  }
  
  public static ResultSet getReportDetails(String report_id, Connection con)
    throws SQLException
  {
    String methodName = "getReportDetails";
    logger.info("Inside method" + methodName);
    CallableStatement callReportDetails = con.prepareCall("{call " + ReusableConstants.PROCEDURE_REPORT_DETAILS + "(?)}");
    logger.info("Calling procedure callReportDetails");
    callReportDetails.setString(1, report_id);
    callReportDetails.execute();
    logger.info(callReportDetails.toString());
    return callReportDetails.getResultSet();
  }
  
  public static ResultSet getConsultantDetails(String consultant_id, Connection con)
    throws SQLException
  {
    String methodName = "getConsultantDetails";
    logger.info("Inside method" + methodName);
    CallableStatement callConsultantDetails = con.prepareCall("{call " + ReusableConstants.PROCEDURE_CONSULTANT_DETAILS + "(?)}");
    logger.info("Calling procedure callReportDetails");
    callConsultantDetails.setString(1, consultant_id);
    callConsultantDetails.execute();
    logger.info(callConsultantDetails.toString());
    return callConsultantDetails.getResultSet();
  }
  
  public static void analyseReport(String consId, String analystId, String report_id, Connection con)
    throws SQLException
  {
    String methodName = "analyseReport";
    logger.info("Inside method" + methodName);
    CallableStatement callanalyseReport = con.prepareCall("{call " + ReusableConstants.PROCEDURE_REPORT_ANALYSE + "(?,?,?)}");
    logger.info("Calling procedure callanalyseReport");
    callanalyseReport.setString(1, consId);
    callanalyseReport.setString(2, report_id);
    callanalyseReport.setString(3, analystId);
    callanalyseReport.execute();
    logger.info(callanalyseReport.toString());
  }
  
  public static void completeReport(String consId, String remarks, String report_id, Connection con)
    throws SQLException
  {
    String methodName = "completeReport";
    logger.info("Inside method" + methodName);
    CallableStatement completeReport = con.prepareCall("{call " + ReusableConstants.PROCEDURE_REPORT_COMPLETE + "(?,?,?)}");
    logger.info("Calling procedure callanalyseReport");
    completeReport.setString(1, consId);
    completeReport.setString(2, report_id);
    completeReport.setString(3, remarks);
    completeReport.execute();
    logger.info(completeReport.toString());
  }
  
  public static void enableDisableAccount(String consId, Boolean toEnable, Connection con)
    throws SQLException
  {
    String methodName = "enableDisableAccount";
    logger.info("Inside method" + methodName);
    CallableStatement callEnableDisableAccount = con.prepareCall("{call " + ReusableConstants.PROCEDURE_ENABLE_DISABLE_ACCT + "(?,?)}");
    logger.info("Calling procedure callEnableDisableAccount");
    callEnableDisableAccount.setString(1, consId);
    callEnableDisableAccount.setBoolean(2, toEnable.booleanValue());
    callEnableDisableAccount.execute();
    logger.info(callEnableDisableAccount.toString());
  }
  
  public static void makeIndependent(String consId, Connection con)
    throws SQLException
  {
    String methodName = "makeIndependent";
    logger.info("Inside method" + methodName);
    CallableStatement callmakeIndependent = con.prepareCall("{call " + ReusableConstants.PROCEDURE_MAKE_INDEPENDENT + "(?)}");
    logger.info("Calling procedure callMakeIndependent");
    callmakeIndependent.setString(1, consId);
    callmakeIndependent.execute();
    logger.info(callmakeIndependent.toString());
  }
  
  public static void makeFranchise(String consId, Connection con)
    throws SQLException
  {
    String methodName = "makeFranchise";
    logger.info("Inside method" + methodName);
    CallableStatement callmakeFranchise = con.prepareCall("{call " + ReusableConstants.PROCEDURE_MAKE_FRANCHISE + "(?)}");
    logger.info("Calling procedure makeFranchise");
    callmakeFranchise.setString(1, consId);
    callmakeFranchise.execute();
    logger.info(callmakeFranchise.toString());
  }
  
  public static void makeNormal(String consId, Connection con)
    throws SQLException
  {
    String methodName = "makeNormal";
    logger.info("Inside method" + methodName);
    CallableStatement callmakeNormal = con.prepareCall("{call " + ReusableConstants.PROCEDURE_MAKE_NORMAL + "(?)}");
    logger.info("Calling procedure makeNormal");
    callmakeNormal.setString(1, consId);
    callmakeNormal.execute();
    logger.info(callmakeNormal.toString());
  }
  
  public static void deleteMessages(String message_id, String analyst_id, Connection con)
    throws SQLException
  {
    String methodName = "deleteMessages";
    logger.info("Inside method" + methodName);
    CallableStatement deleteMessages = con.prepareCall("{call " + ReusableConstants.PROCEDURE_DELETE_MESSAGES + "(?,?)}");
    logger.info("Calling procedure deleteMessages");
    deleteMessages.setString(1, message_id);
    deleteMessages.setString(2, analyst_id);
    deleteMessages.execute();
    logger.info(deleteMessages.toString());
  }
  
  public static void deleteReport(String report_id, Connection con)
    throws SQLException
  {
    String methodName = "deleteReport";
    logger.info("Inside method" + methodName);
    CallableStatement deleteReport = con.prepareCall("{call " + ReusableConstants.PROCEDURE_DELETE_REPORTS + "(?)}");
    logger.info("Calling procedure deleteMessages");
    deleteReport.setString(1, report_id);
    deleteReport.execute();
    logger.info(deleteReport.toString());
  }
  
  public static void assignPins(String consultantId, String analystId, int noOfPins, Connection con)
    throws SQLException
  {
    String methodName = "assignPins";
    logger.info("Inside method" + methodName);
    CallableStatement callassignPins = con.prepareCall("{call " + ReusableConstants.PROCEDURE_ASSIGN_PINS + "(?,?,?)}");
    logger.info("Calling procedure makeNormal");
    callassignPins.setString(1, consultantId);
    callassignPins.setString(2, analystId);
    callassignPins.setInt(3, noOfPins);
    callassignPins.execute();
    logger.info(callassignPins.toString());
  }
  
  public static void updateProfileInformation(String entity, String entityId, String first_name, String last_name, String email, String mobile_no, String phone_no, String city, String pin, String address, String country, Connection con)
    throws SQLException
  {
    String methodName = "updateProfileInformation";
    logger.info("Inside method" + methodName);
    CallableStatement callupdateProfileInformation = con.prepareCall("{call " + ReusableConstants.PROCEDURE_UPDATE_PROFILE_INFO + "(?,?,?,?,?,?,?,?,?,?,?)}");
    logger.info("Calling procedure makeNormal");
    callupdateProfileInformation.setString(1, entity);
    callupdateProfileInformation.setString(2, entityId);
    callupdateProfileInformation.setString(3, first_name);
    callupdateProfileInformation.setString(4, last_name);
    callupdateProfileInformation.setString(5, email);
    callupdateProfileInformation.setString(6, mobile_no);
    callupdateProfileInformation.setString(7, phone_no);
    callupdateProfileInformation.setString(8, city);
    callupdateProfileInformation.setString(9, pin);
    callupdateProfileInformation.setString(10, address);
    callupdateProfileInformation.setString(11, country);
    
    callupdateProfileInformation.execute();
    logger.info(callupdateProfileInformation.toString());
  }
}
