    package ModelClass;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.util.Date;

@ManagedBean(name="VisRes")
@RequestScoped
public class VisitiedRestaurant implements Serializable{
    
private int USER_ID;
private int Restaurant_ID;
private long RATING ;
private Date VISIT_DATE;

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public int getRestaurant_ID() {
        return Restaurant_ID;
    }

    public void setRestaurant_ID(int Restaurant_ID) {
        this.Restaurant_ID = Restaurant_ID;
    }

    public long getRATING() {
        return RATING;
    }

    public void setRATING(long RATING) {
        this.RATING = RATING;
    }

    public Date getVISIT_DATE() {
        return VISIT_DATE;
    }

    public void setVISIT_DATE(Date VISIT_DATE) {
        this.VISIT_DATE = VISIT_DATE;
    }


public void insert() throws SQLException
{
    
  /*  Statement stmt = null;
    Connection con = null;
    ResultSet rs = null;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
    String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
    con = DriverManager.getConnection(dbURL2, user, password);
    System.out.print("In insert function "+getRegionID()+getRegionName());
            if (con!= null) {
                System.out.println("Connected with connection #2");
                stmt = con.createStatement();
            try {
            rs = stmt.executeQuery("SELECT * FROM REGION WHERE REGION_NAME = '"+getRegionName()+"'");
                if(!rs.next())
                {
                stmt.executeUpdate("INSERT INTO REGION (REGION_ID, REGION_NAME)"+" VALUES(REGION_ID_VAL.NEXTVAL"+", "+"'"+getRegionName()+"')");
                System.out.print("In insert function "+getRegionID()+getRegionName());
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
            }  */

}

public void delete() throws SQLException
{
  /*  Statement stmt = null;
    Connection con = null;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
    String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
    con = DriverManager.getConnection(dbURL2, user, password);
    System.out.print("In delete function "+getRegionName());
    if (con!= null) {
            System.out.println("Connected with connection #2");
            stmt = con.createStatement();
            try {
            stmt.executeUpdate("DELETE FROM REGION WHERE REGION_NAME = '"+getDelRegionName()+"'");
            System.out.print("In delete function "+getRegionName());
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
            }  */

}

}