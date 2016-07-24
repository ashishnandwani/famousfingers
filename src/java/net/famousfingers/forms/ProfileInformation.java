package net.famousfingers.forms;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.RegExp;

public class ProfileInformation
{
  private String consultant_id = null;
  @NotBlank(message="<li>Please enter your first name</li>")
  @Length(max=50, message="<li>The first name cannot exceed more than 50 characters</li>")
  private String first_name = null;
  @NotBlank(message="<li>Please enter your last name</li>")
  @Length(max=50, message="<li>The last name cannot exceed more than 50 characters</li>")
  private String last_name = null;
  
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  @NotBlank(message="<li>Please enter your email address</li>")
  @Email(message="<li>Please enter a valid email address</li>")
  @Length(max=254, message="<li>The email address cannot exceed more than 254 characters</li>")
  private String email = null;
  @NotBlank(message="<li>Please enter your mobile number</li>")
  @RegExp(value="[\\d-()+]*", message="<li>Please enter a valid mobile number. You can use () ,-,and numbers from 0-9.</li>")
  @Length(max=15, message="<li>The mobile number cannot exceed more than 15 characters</li>")
  private String mobile_no = null;
  @NotBlank(message="<li>Please enter the city.</li>")
  @Length(max=64, message="<li>The city  cannot exceed more than 65 characters</li>")
  private String city = null;
  @NotBlank(message="<li>Please enter the pin code.</li>")
  @Length(max=30, message="<li>The pin code  cannot exceed more than 30 characters</li>")
  private String pin_code = null;
  @NotBlank(message="<li>Please select a country.</li>")
  private String country = null;
  @NotBlank(message="<li>Please enter the address.</li>")
  @Length(max=1024, message="<li>The address  cannot exceed more than 1024 characters</li>")
  private String address = null;
  
  public String getConsultant_id()
  {
    return this.consultant_id;
  }
  
  public void setConsultant_id(String consultant_id)
  {
    this.consultant_id = consultant_id;
  }
  
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
  
  public String getLocation()
  {
    return this.location;
  }
  
  public void setLocation(String location)
  {
    this.location = location;
  }
  
  private String date_added = null;
  private String added_by = null;
  private String last_activity = null;
  private String status = null;
  @RegExp(value="[\\d-()+]*", message="<li>Please enter a valid phone number. You can use () ,-,and numbers from 0-9.</li>")
  @Length(max=20, message="<li>The phone number cannot exceed more than 20 characters</li>")
  private String phone_no = null;
  private String location = null;
  
  public String toString()
  {
    return "ProfileInformation{consultant_id=" + this.consultant_id + ", first_name=" + this.first_name + ", last_name=" + this.last_name + ", email=" + this.email + ", mobile_no=" + this.mobile_no + ", city=" + this.city + ", pin_code=" + this.pin_code + ", country=" + this.country + ", address=" + this.address + ", date_added=" + this.date_added + ", added_by=" + this.added_by + ", last_activity=" + this.last_activity + ", status=" + this.status + ", phone_no=" + this.phone_no + ", location=" + this.location + '}';
  }
}
