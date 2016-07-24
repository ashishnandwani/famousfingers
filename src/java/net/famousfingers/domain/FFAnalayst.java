package net.famousfingers.domain;

import java.io.Serializable;

public class FFAnalayst
  implements Serializable
{
  private String analyst_id;
  private String first_name;
  private String last_name;
  private String email;
  private String mobile_no;
  private String phone_no;
  private String city;
  private String pin_code;
  private String address;
  private String country;
  private String role;
  private String entity;
  
  public String getAnalyst_id()
  {
    return this.analyst_id;
  }
  
  public void setAnalyst_id(String analyst_id)
  {
    this.analyst_id = analyst_id;
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
  
  public String getEntity()
  {
    return this.entity;
  }
  
  public void setEntity(String entity)
  {
    this.entity = entity;
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
  
  public String getRole()
  {
    return this.role;
  }
  
  public void setRole(String role)
  {
    this.role = role;
  }
  
  public String toString()
  {
    return "FFAnalayst{analyst_id=" + this.analyst_id + ", first_name=" + this.first_name + ", last_name=" + this.last_name + ", email=" + this.email + ", mobile_no=" + this.mobile_no + ", phone_no=" + this.phone_no + ", city=" + this.city + ", pin_code=" + this.pin_code + ", address=" + this.address + ", country=" + this.country + ", role=" + this.role + '}';
  }
  
  public int hashCode()
  {
    int hash = 7;
    hash = 73 * hash + (this.analyst_id != null ? this.analyst_id.hashCode() : 0);
    hash = 73 * hash + (this.first_name != null ? this.first_name.hashCode() : 0);
    hash = 73 * hash + (this.last_name != null ? this.last_name.hashCode() : 0);
    hash = 73 * hash + (this.email != null ? this.email.hashCode() : 0);
    hash = 73 * hash + (this.mobile_no != null ? this.mobile_no.hashCode() : 0);
    hash = 73 * hash + (this.phone_no != null ? this.phone_no.hashCode() : 0);
    hash = 73 * hash + (this.city != null ? this.city.hashCode() : 0);
    hash = 73 * hash + (this.pin_code != null ? this.pin_code.hashCode() : 0);
    hash = 73 * hash + (this.address != null ? this.address.hashCode() : 0);
    hash = 73 * hash + (this.country != null ? this.country.hashCode() : 0);
    hash = 73 * hash + (this.role != null ? this.role.hashCode() : 0);
    return hash;
  }
  
  public boolean equals(Object obj)
  {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    FFAnalayst other = (FFAnalayst)obj;
    if (this.analyst_id == null ? other.analyst_id != null : !this.analyst_id.equals(other.analyst_id)) {
      return false;
    }
    if (this.first_name == null ? other.first_name != null : !this.first_name.equals(other.first_name)) {
      return false;
    }
    if (this.last_name == null ? other.last_name != null : !this.last_name.equals(other.last_name)) {
      return false;
    }
    if (this.email == null ? other.email != null : !this.email.equals(other.email)) {
      return false;
    }
    if (this.mobile_no == null ? other.mobile_no != null : !this.mobile_no.equals(other.mobile_no)) {
      return false;
    }
    if (this.phone_no == null ? other.phone_no != null : !this.phone_no.equals(other.phone_no)) {
      return false;
    }
    if (this.city == null ? other.city != null : !this.city.equals(other.city)) {
      return false;
    }
    if (this.pin_code == null ? other.pin_code != null : !this.pin_code.equals(other.pin_code)) {
      return false;
    }
    if (this.address == null ? other.address != null : !this.address.equals(other.address)) {
      return false;
    }
    if (this.country == null ? other.country != null : !this.country.equals(other.country)) {
      return false;
    }
    if (this.role == null ? other.role != null : !this.role.equals(other.role)) {
      return false;
    }
    return true;
  }
}
