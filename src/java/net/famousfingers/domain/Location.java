package net.famousfingers.domain;

public class Location
{
  private String location_code = null;
  private String location_name = null;
  
  public String getLocation_code()
  {
    return this.location_code;
  }
  
  public void setLocation_code(String location_code)
  {
    this.location_code = location_code;
  }
  
  public String getLocation_name()
  {
    return this.location_name;
  }
  
  public void setLocation_name(String location_name)
  {
    this.location_name = location_name;
  }
  
  public String toString()
  {
    return "Location{location_code=" + this.location_code + ", location_name=" + this.location_name + '}';
  }
}
