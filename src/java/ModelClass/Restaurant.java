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

@ManagedBean(name="Restaurant")
@RequestScoped
public class Restaurant implements Serializable{

private int RESTAURANT_ID;
private int REGION_ID;
private String REGION_NAME;
private int AVERAGE_COST;
private int RATING_COUNT;
private int RATING;
private String HOUSE_NO;
private String ROAD_NO;
private String AREA_NAME;
private String DISTRICT;
private String CONTACT_INFO;
private String RESTAURANT_NAME;
private final DBConnection dbconnection = new DBConnection();

    public int getRESTAURANT_ID() {
        return RESTAURANT_ID;
    }

    public void setRESTAURANT_ID(int RESTAURANT_ID) {
        this.RESTAURANT_ID = RESTAURANT_ID;
    }

    public int getREGION_ID() {
        return REGION_ID;
    }

    public void setREGION_ID(int REGION_ID) {
        this.REGION_ID = REGION_ID;
    }
    
    public String getREGION_NAME() {
        return REGION_NAME;
    }

    public void setREGION_NAME(String REGION_NAME) {
        this.REGION_NAME = REGION_NAME;
    }

    public int getAVERAGE_COST() {
        return AVERAGE_COST;
    }

    public void setAVERAGE_COST(int AVERAGE_COST) {
        this.AVERAGE_COST = AVERAGE_COST;
    }

    public int getRATING_COUNT() {
        return RATING_COUNT;
    }

    public void setRATING_COUNT(int RATING_COUNT) {
        this.RATING_COUNT = RATING_COUNT;
    }

    public int getRATING() {
        return RATING;
    }

    public void setRATING(int RATING) {
        this.RATING = RATING;
    }

    public String getHOUSE_NO() {
        return HOUSE_NO;
    }

    public void setHOUSE_NO(String HOUSE_NO) {
        this.HOUSE_NO = HOUSE_NO;
    }

    public String getROAD_NO() {
        return ROAD_NO;
    }

    public void setROAD_NO(String ROAD_NO) {
        this.ROAD_NO = ROAD_NO;
    }

    public String getAREA_NAME() {
        return AREA_NAME;
    }

    public void setAREA_NAME(String AREA_NAME) {
        this.AREA_NAME = AREA_NAME;
    }

    public String getDISTRICT() {
        return DISTRICT;
    }

    public void setDISTRICT(String DISTRICT) {
        this.DISTRICT = DISTRICT;
    }

    public String getCONTACT_INFO() {
        return CONTACT_INFO;
    }

    public void setCONTACT_INFO(String CONTACT_INFO) {
        this.CONTACT_INFO = CONTACT_INFO;
    }

    public String getRESTAURANT_NAME() {
        return RESTAURANT_NAME;
    }

    public void setRESTAURANT_NAME(String RESTAURANT_NAME) {
        this.RESTAURANT_NAME = RESTAURANT_NAME;
    }

public void insert() throws SQLException
{
    System.out.println(getRESTAURANT_NAME());
    Statement stmt = null,stmt2 = null;
    Connection con = null;
    ResultSet rs = null,rs1 = null;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
    String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
    con = DriverManager.getConnection(dbURL2,dbconnection.user, dbconnection.password);
            if (con!= null) {
                stmt = con.createStatement();
                stmt2 = con.createStatement();
            try {
            rs = stmt.executeQuery("SELECT * FROM RESTAURANTS WHERE RESTAURANT_NAME = '"+getRESTAURANT_NAME()+"'");
                if(!rs.next())
                {
                    
                   rs1 = stmt2.executeQuery("SELECT REGION_ID FROM REGION WHERE REGION_NAME ='"+getREGION_NAME()+"'");
                   if(rs1.next()){
                       
                   setREGION_ID(rs1.getInt(1));
                   System.out.print(getREGION_NAME()+" "+getREGION_ID());
                   
                   String query = "INSERT INTO RESTAURANTS(RESTAURANT_ID,REGION_ID,HOUSE_NO,"
                           + "ROAD_NO,AREA_NAME,DISTRICT,AVERAGE_COST,CONTACT_INFO,RATING,RATING_COUNT,RESTAURANT_NAME)"
                           + "VALUES(RESTAURANT_ID_VAL.NEXTVAL"
                           +","+getREGION_ID()+",'"+getHOUSE_NO()+"','"+getROAD_NO()+"','"
                           +getAREA_NAME()+"','"+getDISTRICT()+"',"+getAVERAGE_COST()+",'"+getCONTACT_INFO()
                           +"',"+getRATING()+","+getRATING_COUNT()+",'"+getRESTAURANT_NAME()
                           +"')";
                   System.out.print(query);
                   stmt.executeUpdate(query);
                   }
                 
                }
                else
                {
                    //UPDATE ENTRY
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
    System.out.println(getRESTAURANT_NAME());
    Statement stmt = null;
    Connection con = null;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
    String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
    con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
    System.out.print("In delete function "+getRESTAURANT_NAME());
    if (con!= null) {
            System.out.println("Connected with connection #2");
            stmt = con.createStatement();
            try {
            stmt.executeUpdate("DELETE FROM RESTAURANTS WHERE RESTAURANT_NAME = '"+getRESTAURANT_NAME()
                    +"'");
            System.out.print("In delete function "+getRESTAURANT_NAME());
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