package net.famousfingers.forms;

public class ConsultantInformation
{
  private String first_name;
  private String last_name;
  private String email;
  private String mobile;
  private String phone_no;
  private String city;
  private String pin_code;
  private String address;
  private String country;
  
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
  
  public String getMobile()
  {
    return this.mobile;
  }
  
  public void setMobile(String mobile)
  {
    this.mobile = mobile;
  }
  
  public String getPhone_no()
  {
    return this.phone_no;
  }
  
  public void setPhone_no(String phone_no)
  {
    this.phone_no = phone_no;
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
  
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  public String getCountry()
  {
    return this.country;
  }
  
  public void setCountry(String country)
  {
    this.country = country;
  }
  
  public String toString()
  {
    return "ConsultantInformation{first_name=" + this.first_name + ", last_name=" + this.last_name + ", email=" + this.email + ", mobile=" + this.mobile + ", phone_no=" + this.phone_no + ", city=" + this.city + ", pin_code=" + this.pin_code + ", address=" + this.address + ", country=" + this.country + '}';
  }
}
