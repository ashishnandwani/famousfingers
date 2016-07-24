package net.famousfingers.forms;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Expression;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

public class CreatePasswordForm
{
  public String getSet_password()
  {
    return this.set_password;
  }
  
  public void setSet_password(String set_password)
  {
    this.set_password = set_password;
  }
  
  public String getConfirm_password()
  {
    return this.confirm_password;
  }
  
  public void setConfirm_password(String confirm_password)
  {
    this.confirm_password = confirm_password;
  }
  
  @NotBlank(message="<li>Please enter the password you would like to set</li>")
  private String set_password = null;
  @NotBlank(message="<li>Please enter your password again to confirm</li>")
  @Expression(value="set_password equals confirm_password", message="<li>Both Set Password and Confirm Password should match</li>")
  private String confirm_password = null;
}
