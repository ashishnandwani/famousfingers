package net.famousfingers.util;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetaData
{
  @Autowired
  private DataSource ds;
  private Connection con;
  private int totalNoOfReports = 0;
  private int totalConsultants = 0;
  
  public int getTotalNoOfReports()
  {
    return this.totalNoOfReports;
  }
  
  public void setTotalNoOfReports(int totalNoOfReports)
  {
    this.totalNoOfReports = totalNoOfReports;
  }
  
  public int getTotalConsultants()
  {
    return this.totalConsultants;
  }
  
  public void setTotalConsultants(int totalConsultants)
  {
    this.totalConsultants = totalConsultants;
  }
  
  public MetaData calculateMetaInfo(Connection con)
    throws SQLException
  {
    int totalConsultants = CommonUtil.getTotalConsultants(con);
    System.out.println("total" + totalConsultants);
    int totalReports = CommonUtil.getTotalReports(con);
    MetaData data = new MetaData();
    data.setTotalConsultants(totalConsultants);
    data.setTotalNoOfReports(this.totalNoOfReports);
    return data;
  }
}
