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

@ManagedBean(name="StayedAt")
@RequestScoped
public class StayedAt implements Serializable{

private int USER_ID;
private int REGION_ID;
private int PLACE_ID;
private Date CHECK_IN;
private Date CHECK_OUT;
private final String user = "Navid";
private final String password = "1234";

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public int getREGION_ID() {
        return REGION_ID;
    }

    public void setREGION_ID(int REGION_ID) {
        this.REGION_ID = REGION_ID;
    }

    public int getPLACE_ID() {
        return PLACE_ID;
    }

    public void setPLACE_ID(int PLACE_ID) {
        this.PLACE_ID = PLACE_ID;
    }

    public Date getCHECK_IN() {
        return CHECK_IN;
    }

    public void setCHECK_IN(Date CHECK_IN) {
        this.CHECK_IN = CHECK_IN;
    }

    public Date getCHECK_OUT() {
        return CHECK_OUT;
    }

    public void setCHECK_OUT(Date CHECK_OUT) {
        this.CHECK_OUT = CHECK_OUT;
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