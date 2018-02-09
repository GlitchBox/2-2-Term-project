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

@ManagedBean(name="HotelsBean")
@RequestScoped
public class HotelsBean implements Serializable{
    
private final DBConnection dbconnection = new DBConnection();

public List<Hotels> getHotelsList() throws ClassNotFoundException, SQLException
{
List<Hotels> list = new ArrayList<>();
Statement stmt = null;
Statement stmt1 = null;
PreparedStatement ps = null;
Connection con = null;
ResultSet rs = null,rs1 = null;
System.out.print("In LandMarkBean");

try {
            
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2,dbconnection.user, dbconnection.password);
            if (con!= null) {
                stmt = con.createStatement();
                stmt1 = con.createStatement();
            try {
            rs = stmt.executeQuery("SELECT * FROM HOTELS_AND_RESORTS");
         
            while(rs.next())
            {
                Hotels hotel = new Hotels();
                hotel.setPLACE_ID(rs.getInt(1));
                hotel.setREGION_ID(rs.getInt(2));
                hotel.setKIND(rs.getString(3));
                hotel.setHOUSE_NO(rs.getString(4));
                hotel.setROAD_NO(rs.getString(5));
                hotel.setAREA_NAME(rs.getString(6));
                hotel.setDISTRICT(rs.getString(7));
                hotel.setMAXIMUM_COST(rs.getInt(8));
                hotel.setMINIMUM_COST(rs.getInt(9));
                hotel.setRATING(rs.getInt(10));
                hotel.setRATING_COUNT(rs.getInt(11));
                hotel.setNAME(rs.getString(12));
                rs1 = stmt1.executeQuery("SELECT REGION_NAME FROM REGION WHERE REGION_ID ="+hotel.getREGION_ID());
                if(rs1.next())
                {
                    hotel.setREGION_NAME(rs1.getString(1));
                }
                
                
                list.add(hotel);
            }
            }
            catch(SQLException e)
            {
                System.out.println("RS ERROR");
            }
            }
        } catch (SQLException e) {
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

public List<Hotels> getHotels() throws ClassNotFoundException, SQLException
{
List<Hotels> list = new ArrayList<>();
Statement stmt = null;
Statement stmt1 = null;
PreparedStatement ps = null;
Connection con = null;
ResultSet rs = null,rs1 = null;
System.out.print("In LandMarkBean");

try {
            
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2,dbconnection.user, dbconnection.password);
            if (con!= null) {
                stmt = con.createStatement();
                stmt1 = con.createStatement();
            try {
                    int id1;
                    String sql = "SELECT REGION_ID FROM REGION WHERE REGION_NAME='"
                            + Gallery.LOCATION + "'";
                    System.out.println(sql);
                    rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        id1 = rs.getInt(1);
                        rs = stmt.executeQuery("SELECT * FROM HOTELS_AND_RESORTS WHERE REGION_ID = "+ id1);
                        System.out.println("Got location id: " + id1);
                    }
                    else
                    rs = stmt.executeQuery("SELECT * FROM HOTELS_AND_RESORTS");
            while(rs.next())
            {
                Hotels hotel = new Hotels();
                hotel.setPLACE_ID(rs.getInt(1));
                hotel.setREGION_ID(rs.getInt(2));
                hotel.setKIND(rs.getString(3));
                hotel.setHOUSE_NO(rs.getString(4));
                hotel.setROAD_NO(rs.getString(5));
                hotel.setAREA_NAME(rs.getString(6));
                hotel.setDISTRICT(rs.getString(7));
                hotel.setMAXIMUM_COST(rs.getInt(8));
                hotel.setMINIMUM_COST(rs.getInt(9));
                hotel.setRATING(rs.getInt(10));
                hotel.setRATING_COUNT(rs.getInt(11));
                hotel.setNAME(rs.getString(12));
                rs1 = stmt1.executeQuery("SELECT REGION_NAME FROM REGION WHERE REGION_ID ="+hotel.getREGION_ID());
                if(rs1.next())
                {
                    hotel.setREGION_NAME(rs1.getString(1));
                }
                
                
                list.add(hotel);
            }
            }
            catch(SQLException e)
            {
                System.out.println("RS ERROR");
            }
            }
        } catch (SQLException e) {
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