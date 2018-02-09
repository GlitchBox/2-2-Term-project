package ModelClass;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.Calendar;
import java.util.GregorianCalendar;

@ManagedBean(name = "LandMarkBean")
@SessionScoped
public class LandMarkBean  implements Serializable{
    
private DBConnection dbconnection = new DBConnection();
private LandMark landl;

    public LandMark getLandl() {
        return landl;
    }

    public void setLandl(LandMark landl) {
        this.landl = landl;
    }

public List<LandMark> getLandMarkList() throws ClassNotFoundException, SQLException
{
List<LandMark> list = new ArrayList<>();

Statement stmt = null,stmt2 = null;
PreparedStatement ps = null;
Connection con = null;
ResultSet rs = null,rs1 = null;
System.out.print("In LandMarkBean");

try {
            
 
            //Class.forName("oracle.jdbc.driver.OracleDriver"); 
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2,dbconnection.user, dbconnection.password);
            if (con!= null) {
                System.out.println("Connected with connection #2 in LandMarkBean");
                stmt = con.createStatement();
                //stmt2 = con.createStatement();
            try {
            rs = stmt.executeQuery("SELECT * FROM LANDMARKS");
            System.out.print("Printing...");
            while(rs.next())
            {
                LandMark landmark = new LandMark();
                landmark.setLANDMARK_ID(rs.getInt(1));
                landmark.setGENRE(rs.getString(2));
                landmark.setADDITIONAL_COST(rs.getInt(3));
                landmark.setREGION_ID(rs.getInt(4));
                
                landmark.setROAD_NO(rs.getString(5));
                landmark.setAREA_NAME(rs.getString(6));
                landmark.setDISTRICT(rs.getString(7));
                landmark.setOPENING_TIME(rs.getString(8));
                landmark.setCLOSING_TIME(rs.getString(9));
                landmark.setRATING_COUNT(rs.getInt(10));
                landmark.setRATING(rs.getInt(11));
                landmark.setLANDMARK_NAME(rs.getString(12));
                landmark.setOPENING_DAYS(rs.getString(13));
                landmark.setDESCRIPTION(rs.getString(14));
                stmt2 = con.createStatement();
                rs1 = stmt2.executeQuery("SELECT REGION_NAME FROM REGION WHERE REGION_ID ="+landmark.getREGION_ID());
                if(rs1.next())
                {
                    landmark.setREGION_NAME(rs1.getString(1));
                }
                
                
                list.add(landmark);
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

public List<LandMark> getLandMark() throws ClassNotFoundException, SQLException
{
List<LandMark> list = new ArrayList<>();

Statement stmt = null,stmt2 = null;
PreparedStatement ps = null;
Connection con = null;
ResultSet rs = null,rs1 = null;
System.out.print("In LandMarkBean");

try {
            
 
            //Class.forName("oracle.jdbc.driver.OracleDriver"); 
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2,dbconnection.user, dbconnection.password);
            if (con!= null) {
                System.out.println("Connected with connection #2 in LandMarkBean");
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
                        rs = stmt.executeQuery("SELECT * FROM LANDMARKS WHERE REGION_ID = "+ id1);
                        System.out.println("Got location id: " + id1);
                    }
                    else
                    rs = stmt.executeQuery("SELECT * FROM LANDMARKS");
            System.out.print("Printing...");
            while(rs.next())
            {
                LandMark landmark = new LandMark();
                landmark.setLANDMARK_ID(rs.getInt(1));
                landmark.setGENRE(rs.getString(2));
                landmark.setADDITIONAL_COST(rs.getInt(3));
                landmark.setREGION_ID(rs.getInt(4));
                
                landmark.setROAD_NO(rs.getString(5));
                landmark.setAREA_NAME(rs.getString(6));
                landmark.setDISTRICT(rs.getString(7));
                landmark.setOPENING_TIME(rs.getString(8));
                landmark.setCLOSING_TIME(rs.getString(9));
                landmark.setRATING_COUNT(rs.getInt(10));
                landmark.setRATING(rs.getInt(11));
                landmark.setLANDMARK_NAME(rs.getString(12));
                landmark.setOPENING_DAYS(rs.getString(13));
                landmark.setDESCRIPTION(rs.getString(14));
                stmt2 = con.createStatement();
                rs1 = stmt2.executeQuery("SELECT REGION_NAME FROM REGION WHERE REGION_ID ="+landmark.getREGION_ID());
                if(rs1.next())
                {
                    landmark.setREGION_NAME(rs1.getString(1));
                }
                
                
                list.add(landmark);
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
