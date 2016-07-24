package net.famousfingers.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import net.famousfingers.domain.FFConsultant;
import net.famousfingers.util.CommonUtil;

public class ConsultantListBuilder
{
  private String role = null;
  private String consultant_id = null;
  private String full_name = null;
  private String last_name = null;
  private String email = null;
  private String mobile_no = null;
  private String city = null;
  private String pin_code = null;
  private String country = null;
  private String date_added = null;
  private String added_by = null;
  private String last_activity = null;
  private String status = null;
  private String phone_no = null;
  private Boolean is_disabled = null;
  private String address = null;
  private String location = null;
  private String added_by_id = null;
  private String added_by_name = null;
  private String completeDatePosted = null;
  private String datePosted = null;
  private String dateWithoutDay = null;
  private Timestamp date_posted = null;
  FFConsultant consultantInfo = null;
  
  public List<FFConsultant> buildConsultantList(ResultSet consultantResultSet)
    throws SQLException, ParseException
  {
    List<FFConsultant> consultantList = null;
    if (consultantResultSet != null)
    {
      consultantList = new ArrayList();
      while (consultantResultSet.next())
      {
        this.consultantInfo = new FFConsultant();
        this.role = consultantResultSet.getString("role");
        this.consultantInfo.setRole(this.role);
        
        this.consultant_id = consultantResultSet.getString("cons_id");
        this.consultantInfo.setConsultant_id(this.consultant_id);
        
        this.full_name = consultantResultSet.getString("cons_name");
        this.consultantInfo.setFull_name(this.full_name);
        
        this.date_posted = consultantResultSet.getTimestamp("date_added");
        this.consultantInfo.setDate_added(this.date_added);
        
        this.email = consultantResultSet.getString("email");
        this.consultantInfo.setEmail(this.email);
        
        this.mobile_no = consultantResultSet.getString("mob_no");
        this.consultantInfo.setMobile_no(this.mobile_no);
        
        this.datePosted = CommonUtil.returnTimeFormat("onlydate", this.date_posted);
        this.consultantInfo.setOnlyDate(this.datePosted);
        this.completeDatePosted = CommonUtil.returnTimeFormat("fulltimestamp", this.date_posted);
        this.consultantInfo.setFullTimestamp(this.completeDatePosted);
        this.dateWithoutDay = CommonUtil.returnTimeFormat("withoutday", this.date_posted);
        this.consultantInfo.setTimeWithoutDay(this.dateWithoutDay);
        
        this.address = consultantResultSet.getString("addr");
        this.consultantInfo.setAddress(this.address);
        
        this.is_disabled = Boolean.valueOf(consultantResultSet.getBoolean("is_disabled"));
        this.consultantInfo.setIsDisabled(this.is_disabled);
        
        this.consultantInfo.setPins_left(consultantResultSet.getString("pins_left"));
        this.consultantInfo.setReports_completed(consultantResultSet.getString("rprt_completed"));
        this.consultantInfo.setReports_pending(consultantResultSet.getString("rprt_pending"));
        this.location = consultantResultSet.getString("location");
        this.consultantInfo.setLocation(this.location);
        this.status = consultantResultSet.getString("status_desc");
        this.consultantInfo.setStatus(this.status);
        
        this.role = consultantResultSet.getString("role");
        this.consultantInfo.setRole(this.role);
        
        this.added_by_id = consultantResultSet.getString("added_by_id");
        this.consultantInfo.setAdded_by_id(this.added_by_id);
        
        this.added_by_name = consultantResultSet.getString("added_by_name");
        this.consultantInfo.setAdded_by(this.added_by_name);
        
        consultantList.add(this.consultantInfo);
      }
    }
    return consultantList;
  }
}
