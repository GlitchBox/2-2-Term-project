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

@ManagedBean(name="MarketBean")
@RequestScoped
public class MarketBean implements Serializable{
private DBConnection dbconnection = new DBConnection();

public List<Market> getMarketList() throws ClassNotFoundException, SQLException
{
List<Market> list = new ArrayList<>();
Statement stmt = null,stmt2=null;
PreparedStatement ps = null;
Connection con = null;
ResultSet rs = null,rs1= null;
System.out.print("In MarketBean");

try {
            
 
            //Class.forName("oracle.jdbc.driver.OracleDriver"); 
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2,dbconnection.user, dbconnection.password);
            if (con!= null) {
                System.out.println("Connected with connection #2 in MarketBean");
                stmt = con.createStatement();
                stmt2 = con.createStatement();
            try {
            rs = stmt.executeQuery("SELECT * FROM SHOPPING");
            System.out.print("Printing...");
            while(rs.next())
            {
                Market mar = new Market();
                mar.setSHOPPING_ID(rs.getInt(1));
                mar.setREGION_ID(rs.getInt(2));
                
                mar.setHOUSE_NO(rs.getString(3));
                mar.setROAD_NO(rs.getString(4));
                mar.setAREA_NAME(rs.getString(5));
                mar.setDISTRICT(rs.getString(6));
                mar.setOPEN_HOURS(rs.getString(7));
                mar.setOPEN_DAYS(rs.getString(8));
                mar.setMARKET_NAME(rs.getString(9));
                
                
                //stmt2 = con.createStatement();
                rs1 = stmt2.executeQuery("SELECT REGION_NAME FROM REGION WHERE REGION_ID ="+mar.getREGION_ID());
                if(rs1.next())
                {
                    mar.setREGION_NAME(rs1.getString(1));
                }
                
                
                list.add(mar);
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