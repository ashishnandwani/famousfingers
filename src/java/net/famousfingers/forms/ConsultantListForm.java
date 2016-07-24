package net.famousfingers.forms;

public class ConsultantListForm
{
  private String sort_by = "cons_name";
  private String order_by = "asc";
  
  public int getPage_no()
  {
    return this.page_no;
  }
  
  public void setPage_no(int page_no)
  {
    this.page_no = page_no;
  }
  
  int page_no = 1;
  
  public String getSort_by()
  {
    return this.sort_by;
  }
  
  public void setSort_by(String sort_by)
  {
    this.sort_by = sort_by;
  }
  
  public String getOrder_by()
  {
    return this.order_by;
  }
  
  public void setOrder_by(String order_by)
  {
    this.order_by = order_by;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  private String status = null;
  private String name = null;
}
