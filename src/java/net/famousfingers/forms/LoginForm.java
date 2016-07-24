package net.famousfingers.forms;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

public class LoginForm
{
  @NotBlank(message="<li>Please enter your email address</li>")
  @Email(message="<li>Please enter a valid email address</li>")
  @Length(max=254, message="<li>The email address cannot exceed more than 254 characters</li>")
  private String email;
  @NotBlank(message="<li>Please enter your password</li>")
  @Length(max=20, message="<li>The password cannot exceed more than 20 characters</li>")
  private String password;
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public String getFrom()
  {
    return this.from;
  }
  
  public void setFrom(String from)
  {
    this.from = from;
  }
  
  private String from = null;
  private String errorMsg;
  private String infoMsg;
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getErrorMsg()
  {
    return this.errorMsg;
  }
  
  public void setErrorMsg(String errorMsg)
  {
    this.errorMsg = errorMsg;
  }
  
  public String getInfoMsg()
  {
    return this.infoMsg;
  }
  
  public void setInfoMsg(String infoMsg)
  {
    this.infoMsg = infoMsg;
  }
  
  public String getIsConsultantErrorMsg()
  {
    return this.isConsultantErrorMsg;
  }
  
  public void setIsConsultantErrorMsg(String isConsultantErrorMsg)
  {
    this.isConsultantErrorMsg = isConsultantErrorMsg;
  }
  
  private String isConsultantErrorMsg = null;
}
