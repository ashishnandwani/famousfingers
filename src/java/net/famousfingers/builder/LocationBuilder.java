package net.famousfingers.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.famousfingers.domain.Location;

public class LocationBuilder
{
  private String location_code = null;
  private String location_name = null;
  List<Location> listOfLocations = null;
  
  public List<Location> getLocations(ResultSet locationsResultSet)
    throws SQLException
  {
    if (locationsResultSet != null)
    {
      this.listOfLocations = new ArrayList();
      while (locationsResultSet.next())
      {
        Location location = new Location();
        
        this.location_code = locationsResultSet.getString("location_code");
        location.setLocation_code(this.location_code);
        this.location_name = locationsResultSet.getString("location_name");
        location.setLocation_name(this.location_name);
        this.listOfLocations.add(location);
      }
    }
    return this.listOfLocations;
  }
}
