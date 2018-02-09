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

@ManagedBean(name="InterRTBean")
@RequestScoped
public class InterRegionTransportBean implements Serializable{


   private final DBConnection dbconnection = new DBConnection();

public List<InterRegionTransport> getInterRegionTransportList() throws ClassNotFoundException, SQLException
{
List<InterRegionTransport> list = new ArrayList<>();

Statement stmt = null,stmt2=null;
PreparedStatement ps = null;
Connection con = null;
ResultSet rs = null,rs1= null,rs2=null;
System.out.print("In InterRegionBean");

try {
            
 
            //Class.forName("oracle.jdbc.driver.OracleDriver"); 
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2,dbconnection.user, dbconnection.password);
            if (con!= null) {
                System.out.println("Connected with connection #2 in InterRegionBean");
                stmt = con.createStatement();
                stmt2 = con.createStatement();
            try {
            rs = stmt.executeQuery("SELECT * FROM INTER_REGION_TRANSPORT");
            System.out.print("Printing...");
            while(rs.next())
            {
                InterRegionTransport inter = new InterRegionTransport();
                inter.setINTER_REGION_TRANSPORT_ID(rs.getInt(1));
                inter.setFROM_DIST(rs.getInt(2));
                
                inter.setTO_DIST(rs.getInt(3));
                inter.setEXPENSE(rs.getInt(4));
                inter.setMODE_OF_TRANSPORT(rs.getString(5));
                inter.setVEHICLE_NAME(rs.getString(6));
                inter.setPOPULARITY(rs.getInt(7));
                inter.setPOPULARITY_COUNT(rs.getInt(8));
                
                
                
                //stmt2 = con.createStatement();
                rs1 = stmt2.executeQuery("SELECT REGION_NAME FROM REGION WHERE REGION_ID ="+inter.getFROM_DIST());
                if(rs1.next())
                {
                    inter.setFROM_NAME(rs1.getString(1));
                    rs2 = stmt2.executeQuery("SELECT REGION_NAME FROM REGION WHERE REGION_ID =" + inter.getTO_DIST());
                    if(rs2.next())
                        inter.setTO_NAME(rs2.getString(1));
                }
                
                
                list.add(inter);
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