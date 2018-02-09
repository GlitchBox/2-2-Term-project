package ModelClass;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean(name="Region")
@RequestScoped
public class Region implements Serializable{

private int regionID;
private String regionName;
private final DBConnection dbconnection = new DBConnection();

public int getRegionID() {
return regionID;
}
public void setRegionID(int regionID) {
this.regionID = regionID;
}
public String getRegionName() {
return regionName;
}

public void setRegionName(String regionName) {
this.regionName = regionName;
}

public void insert() throws SQLException
{
    
    Statement stmt = null;
    Connection con = null;
    ResultSet rs = null;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
    String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
    con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
         if (con!= null) {
                stmt = con.createStatement();
            try {
            rs = stmt.executeQuery("SELECT * FROM REGION WHERE REGION_NAME = '"+getRegionName()+"'");
                if(!rs.next())
                {
                stmt.executeUpdate("INSERT INTO REGION (REGION_ID, REGION_NAME)"+" VALUES(REGION_ID_VAL.NEXTVAL"+", "+"'"+getRegionName()+"')");
                }
                
                else
                {
                    FacesContext.getCurrentInstance().addMessage("regionList", new FacesMessage("Entry already present"));
                }
            }
            catch(SQLException e)
            {
                System.out.println("No data found while inserting");
            }
            finally
            {
                try
                {
                    con.close();
                    stmt.close();
                }
                catch(SQLException e)
                {
                           System.out.println(e); 
                }
            }
            }  

}

public void delete() throws SQLException
{
    
    Statement stmt = null;
    Connection con = null;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
    String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
    con = DriverManager.getConnection(dbURL2,  dbconnection.user, dbconnection.password);
    if (con!= null) {
            stmt = con.createStatement();
            try {
            stmt.executeUpdate("DELETE FROM REGION WHERE REGION_NAME = '"+getRegionName()+"'");
            }
            catch(SQLException e)
            {
                System.out.println("No data found during deletion");
            }
            finally
            {
                try
                {
                    con.close();
                    stmt.close();
                }
                catch(SQLException e)
                {
                           System.out.println(e); 
                }
            }
            } 

}



}