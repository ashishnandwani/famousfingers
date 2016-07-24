package net.famousfingers.forms;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

public class MessagesForm
{
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String message)
  {
    this.message = message;
  }
  
  public String getHeading()
  {
    return this.heading;
  }
  
  public void setHeading(String heading)
  {
    this.heading = heading;
  }
  
  public String getEntityId()
  {
    return this.entityId;
  }
  
  public void setEntityId(String entityId)
  {
    this.entityId = entityId;
  }
  
  @Length(max=1024, message="<li>The message cannot exceed more than 1024 characters</li>")
  private String message = null;
  @NotBlank(message="<li>Please enter the heading</li>")
  @Length(max=256, message="<li>The heading cannot exceed more than 256 characters</li>")
  private String heading = null;
  private String entityId = null;
}
