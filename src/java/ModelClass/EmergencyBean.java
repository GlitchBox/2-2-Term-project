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

@ManagedBean(name="EmergencyBean")
@RequestScoped
public class EmergencyBean implements Serializable{

    private final DBConnection dbconnection = new DBConnection();

public List<Emergency> getEmergencyList() throws ClassNotFoundException, SQLException
{
List<Emergency> list = new ArrayList<>();
Statement stmt = null,stmt2=null;
PreparedStatement ps = null;
Connection con = null;
ResultSet rs = null,rs1= null;
System.out.print("In EmergencyBean");

try {
            
 
            //Class.forName("oracle.jdbc.driver.OracleDriver"); 
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2,dbconnection.user, dbconnection.password);
            if (con!= null) {
                System.out.println("Connected with connection #2 in EMERGENCYBean");
                stmt = con.createStatement();
                stmt2 = con.createStatement();
            try {
            rs = stmt.executeQuery("SELECT * FROM EMERGENCY_SERVICES");
            System.out.print("Printing...");
            while(rs.next())
            {
                Emergency em = new Emergency();
                em.setEMERGENCY_ID(rs.getInt(1));
                em.setREGION_ID(rs.getInt(2));
                em.setKIND(rs.getString(3));
                em.setHOUSE_NO(rs.getString(4));
                em.setROAD_NO(rs.getString(5));
                em.setAREA_NAME(rs.getString(6));
                em.setDISTRICT(rs.getString(7));
                em.setEMERGENCY_NAME(rs.getString(8));
                em.setCONTACT_INFO(rs.getString(9));
                
                
                //stmt2 = con.createStatement();
                rs1 = stmt2.executeQuery("SELECT REGION_NAME FROM REGION WHERE REGION_ID ="+em.getREGION_ID());
                if(rs1.next())
                {
                    em.setREGION_NAME(rs1.getString(1));
                }
                
                
                list.add(em);
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