package net.famousfingers.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.famousfingers.domain.FFConsultant;

public class ConsultantBuilder
{
  private String role = null;
  private String consultant_id = null;
  private String first_name = null;
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
  private String is_disabled = null;
  private String address = null;
  private String location = null;
  FFConsultant consultantInfo = null;
  private String location_code = null;
  
  public FFConsultant getConsultantDetails(ResultSet entityDetails)
    throws SQLException
  {
    if ((entityDetails != null) && 
      (entityDetails.next()))
    {
      this.consultantInfo = new FFConsultant();
      this.role = entityDetails.getString("role");
      this.consultantInfo.setRole(this.role);
      
      this.consultant_id = entityDetails.getString("consultant_id");
      this.consultantInfo.setConsultant_id(this.consultant_id);
      
      this.first_name = entityDetails.getString("first_name");
      this.consultantInfo.setFirst_name(this.first_name);
      
      this.last_name = entityDetails.getString("last_name");
      this.consultantInfo.setLast_name(this.last_name);
      
      this.email = entityDetails.getString("email");
      this.consultantInfo.setEmail(this.email);
      
      this.mobile_no = entityDetails.getString("mobile_no");
      this.consultantInfo.setMobile_no(this.mobile_no);
      
      this.location_code = entityDetails.getString("location_code");
      this.consultantInfo.setLocation_code(this.location_code);
      
      this.phone_no = entityDetails.getString("phone_no");
      this.consultantInfo.setPhone_no(this.phone_no);
      
      this.city = entityDetails.getString("city");
      this.consultantInfo.setCity(this.city);
      
      this.address = entityDetails.getString("address");
      this.consultantInfo.setAddress(this.address);
      
      this.pin_code = entityDetails.getString("pin_code");
      this.consultantInfo.setPin_code(this.pin_code);
      
      this.is_disabled = entityDetails.getString("is_disabled");
      this.consultantInfo.setIs_disabled(this.is_disabled);
      
      this.location = entityDetails.getString("location");
      this.consultantInfo.setLocation(this.location);
      
      this.status = entityDetails.getString("status");
      this.consultantInfo.setStatus(this.status);
      
      this.role = entityDetails.getString("role");
      this.consultantInfo.setRole(this.role);
    }
    return this.consultantInfo;
  }
  
  public String toString()
  {
    return "ConsultantBuilder{role=" + this.role + ", consultant_id=" + this.consultant_id + ", first_name=" + this.first_name + ", last_name=" + this.last_name + ", email=" + this.email + ", mobile_no=" + this.mobile_no + ", city=" + this.city + ", pin_code=" + this.pin_code + ", country=" + this.country + ", date_added=" + this.date_added + ", added_by=" + this.added_by + ", last_activity=" + this.last_activity + ", status=" + this.status + ", phone_no=" + this.phone_no + ", is_disabled=" + this.is_disabled + ", address=" + this.address + ", consultantInfo=" + this.consultantInfo + '}';
  }
}
