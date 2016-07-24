package net.famousfingers.domain;

import java.io.Serializable;

public class FFConsultant
  implements Serializable
{
  private String role = null;
  private String consultant_id = null;
  private String first_name = null;
  private String last_name = null;
  private String location_code = null;
  
  public String getPins_left()
  {
    return this.pins_left;
  }
  
  public void setPins_left(String pins_left)
  {
    this.pins_left = pins_left;
  }
  
  public String getReports_completed()
  {
    return this.reports_completed;
  }
  
  public void setReports_completed(String reports_completed)
  {
    this.reports_completed = reports_completed;
  }
  
  public String getReports_pending()
  {
    return this.reports_pending;
  }
  
  public void setReports_pending(String reports_pending)
  {
    this.reports_pending = reports_pending;
  }
  
  private String pins_left = null;
  private String reports_completed = null;
  private String reports_pending = null;
  
  public String getLocation_code()
  {
    return this.location_code;
  }
  
  public void setLocation_code(String location_code)
  {
    this.location_code = location_code;
  }
  
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
  private String location = null;
  private String added_by_id = null;
  
  public String getFullTimestamp()
  {
    return this.fullTimestamp;
  }
  
  public void setFullTimestamp(String fullTimestamp)
  {
    this.fullTimestamp = fullTimestamp;
  }
  
  public String getOnlyDate()
  {
    return this.onlyDate;
  }
  
  public void setOnlyDate(String onlyDate)
  {
    this.onlyDate = onlyDate;
  }
  
  public String getTimeWithoutDay()
  {
    return this.timeWithoutDay;
  }
  
  public void setTimeWithoutDay(String timeWithoutDay)
  {
    this.timeWithoutDay = timeWithoutDay;
  }
  
  private String fullTimestamp = null;
  private String onlyDate = null;
  private String timeWithoutDay = null;
  
  public String getAdded_by_id()
  {
    return this.added_by_id;
  }
  
  public void setAdded_by_id(String added_by_id)
  {
    this.added_by_id = added_by_id;
  }
  
  public Boolean getIsDisabled()
  {
    return this.isDisabled;
  }
  
  public void setIsDisabled(Boolean isDisabled)
  {
    this.isDisabled = isDisabled;
  }
  
  private Boolean isDisabled = Boolean.FALSE;
  
  public String getFull_name()
  {
    return this.full_name;
  }
  
  public void setFull_name(String full_name)
  {
    this.full_name = full_name;
  }
  
  private String full_name = null;
  
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  private String is_disabled = null;
  private String address = null;
  
  public String getFirst_name()
  {
    return this.first_name;
  }
  
  public void setFirst_name(String first_name)
  {
    this.first_name = first_name;
  }
  
  public String getLast_name()
  {
    return this.last_name;
  }
  
  public void setLast_name(String last_name)
  {
    this.last_name = last_name;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getMobile_no()
  {
    return this.mobile_no;
  }
  
  public void setMobile_no(String mobile_no)
  {
    this.mobile_no = mobile_no;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getPin_code()
  {
    return this.pin_code;
  }
  
  public void setPin_code(String pin_code)
  {
    this.pin_code = pin_code;
  }
  
  public String getCountry()
  {
    return this.country;
  }
  
  public void setCountry(String country)
  {
    this.country = country;
  }
  
  public String getDate_added()
  {
    return this.date_added;
  }
  
  public void setDate_added(String date_added)
  {
    this.date_added = date_added;
  }
  
  public String getAdded_by()
  {
    return this.added_by;
  }
  
  public void setAdded_by(String added_by)
  {
    this.added_by = added_by;
  }
  
  public String getLast_activity()
  {
    return this.last_activity;
  }
  
  public void setLast_activity(String last_activity)
  {
    this.last_activity = last_activity;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getPhone_no()
  {
    return this.phone_no;
  }
  
  public void setPhone_no(String phone_no)
  {
    this.phone_no = phone_no;
  }
  
  public String getIs_disabled()
  {
    return this.is_disabled;
  }
  
  public void setIs_disabled(String is_disabled)
  {
    this.is_disabled = is_disabled;
  }
  
  public String getLocation()
  {
    return this.location;
  }
  
  public void setLocation(String location)
  {
    this.location = location;
  }
  
  public String getRole()
  {
    return this.role;
  }
  
  public void setRole(String role)
  {
    this.role = role;
  }
  
  public String getConsultant_id()
  {
    return this.consultant_id;
  }
  
  public void setConsultant_id(String consultant_id)
  {
    this.consultant_id = consultant_id;
  }
  
  public String toString()
  {
    return "FFConsultant{role=" + this.role + ", consultant_id=" + this.consultant_id + ", first_name=" + this.first_name + ", last_name=" + this.last_name + ", location_code=" + this.location_code + ", pins_left=" + this.pins_left + ", reports_completed=" + this.reports_completed + ", reports_pending=" + this.reports_pending + ", email=" + this.email + ", mobile_no=" + this.mobile_no + ", city=" + this.city + ", pin_code=" + this.pin_code + ", country=" + this.country + ", date_added=" + this.date_added + ", added_by=" + this.added_by + ", last_activity=" + this.last_activity + ", status=" + this.status + ", phone_no=" + this.phone_no + ", location=" + this.location + ", added_by_id=" + this.added_by_id + ", fullTimestamp=" + this.fullTimestamp + ", onlyDate=" + this.onlyDate + ", timeWithoutDay=" + this.timeWithoutDay + ", isDisabled=" + this.isDisabled + ", full_name=" + this.full_name + ", is_disabled=" + this.is_disabled + ", address=" + this.address + '}';
  }
}
