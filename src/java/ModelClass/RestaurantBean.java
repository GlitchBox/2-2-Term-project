    package ModelClass;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.util.Date;
import java.util.List;

@ManagedBean(name="RestaurantBean")
@RequestScoped
public class RestaurantBean implements Serializable{

    private final DBConnection dbconnection = new DBConnection();

public List<Restaurant> getRestaurantList() throws ClassNotFoundException, SQLException
{
List<Restaurant> list = new ArrayList<>();

Statement stmt = null,stmt2=null;
PreparedStatement ps = null;
Connection con = null;
ResultSet rs = null,rs1= null;
System.out.print("In RegionBean");

try {
            
 
            //Class.forName("oracle.jdbc.driver.OracleDriver"); 
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2,dbconnection.user, dbconnection.password);
            if (con!= null) {
                System.out.println("Connected with connection #2 in RestaurantBean");
                stmt = con.createStatement();
                //stmt2 = con.createStatement();
            try {
            rs = stmt.executeQuery("SELECT * FROM RESTAURANTS");
            System.out.print("Printing...");
            while(rs.next())
            {
                Restaurant res = new Restaurant();
                res.setRESTAURANT_ID(rs.getInt(1));
                res.setREGION_ID(rs.getInt(2));
                
                res.setHOUSE_NO(rs.getString(3));
                
                
                res.setROAD_NO(rs.getString(4));
                res.setAREA_NAME(rs.getString(5));
                res.setDISTRICT(rs.getString(6));
                res.setAVERAGE_COST(rs.getInt(7));
                res.setCONTACT_INFO(rs.getString(8));
                res.setRATING(rs.getInt(9));
                res.setRATING_COUNT(rs.getInt(10));
                res.setRESTAURANT_NAME(rs.getString(11));
                
                stmt2 = con.createStatement();
                rs1 = stmt2.executeQuery("SELECT REGION_NAME FROM REGION WHERE REGION_ID ="+res.getREGION_ID());
                if(rs1.next())
                {
                    res.setREGION_NAME(rs1.getString(1));
                }
                
                
                list.add(res);
            }
            }
            catch(SQLException e)
            {
                System.out.println("RS ERROR");
            }
            } 
            
            }catch (SQLException e) {
            System.out.println(e.toString());
        }

finally
{
try
{
con.close();
stmt.close();
}
catch(Exception e)
{
e.printStackTrace();
}
}

        return list;
}

public List<Restaurant> getRestaurant() throws ClassNotFoundException, SQLException
{
List<Restaurant> list = new ArrayList<>();

Statement stmt = null,stmt2=null;
PreparedStatement ps = null;
Connection con = null;
ResultSet rs = null,rs1= null;
System.out.print("In RegionBean");

try {
            
 
            //Class.forName("oracle.jdbc.driver.OracleDriver"); 
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2,dbconnection.user, dbconnection.password);
            if (con!= null) {
                System.out.println("Connected with connection #2 in RestaurantBean");
                stmt = con.createStatement();
                //stmt2 = con.createStatement();
           try {
                    int id1;
                    String sql = "SELECT REGION_ID FROM REGION WHERE REGION_NAME='"
                            + Gallery.LOCATION + "'";
                    System.out.println(sql);
                    rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        id1 = rs.getInt(1);
                        rs = stmt.executeQuery("SELECT * FROM RESTAURANTS WHERE REGION_ID = " + id1);
                        System.out.println("Got location id: " + id1);
                    } else {
                        rs = stmt.executeQuery("SELECT * FROM RESTAURANTS");
                    }
            System.out.print("Printing...");
            while(rs.next())
            {
                Restaurant res = new Restaurant();
                res.setRESTAURANT_ID(rs.getInt(1));
                res.setREGION_ID(rs.getInt(2));
                
                res.setHOUSE_NO(rs.getString(3));
                
                
                res.setROAD_NO(rs.getString(4));
                res.setAREA_NAME(rs.getString(5));
                res.setDISTRICT(rs.getString(6));
                res.setAVERAGE_COST(rs.getInt(7));
                res.setCONTACT_INFO(rs.getString(8));
                res.setRATING(rs.getInt(9));
                res.setRATING_COUNT(rs.getInt(10));
                res.setRESTAURANT_NAME(rs.getString(11));
                
                stmt2 = con.createStatement();
                rs1 = stmt2.executeQuery("SELECT REGION_NAME FROM REGION WHERE REGION_ID ="+res.getREGION_ID());
                if(rs1.next())
                {
                    res.setREGION_NAME(rs1.getString(1));
                }
                
                
                list.add(res);
            }
            }
            catch(SQLException e)
            {
                System.out.println("RS ERROR");
            }
            } 
            
            }catch (SQLException e) {
            System.out.println(e.toString());
        }

finally
{
try
{
con.close();
stmt.close();
}
catch(Exception e)
{
e.printStackTrace();
}
}

        return list;
}
}