package net.famousfingers.forms;

public class PinsForm
{
  private String pin_count = null;
  private String cs_id = null;
  
  public String getPin_count()
  {
    return this.pin_count;
  }
  
  public String getCs_id()
  {
    return this.cs_id;
  }
  
  public void setCs_id(String cs_id)
  {
    this.cs_id = cs_id;
  }
  
  public void setPin_count(String pin_count)
  {
    this.pin_count = pin_count;
  }
}
