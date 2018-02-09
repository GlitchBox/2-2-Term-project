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

@ManagedBean(name="VisMarBean")
@RequestScoped
public class VisitedMarketBean implements Serializable{

    private final String user = "Navid";
    private final String password = "1234";

public List<VisitedMarket> getVisitedMarketList() throws ClassNotFoundException, SQLException
{
List<VisitedMarket> list = new ArrayList<>();
/*
Statement stmt = null;
PreparedStatement ps = null;
Connection con = null;
ResultSet rs = null;
System.out.print("In RegionBean");

try {
            
 
            //Class.forName("oracle.jdbc.driver.OracleDriver"); 
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2, user, password);
            if (con!= null) {
                System.out.println("Connected with connection #2 in regionbean");
                stmt = con.createStatement();
            try {
            rs = stmt.executeQuery("SELECT * FROM REGION");
            while(rs.next())
            {
                Region region = new Region();
                region.setRegionID(rs.getInt(1));
                region.setRegionName(rs.getString(2));
                list.add(region);
            }
            }
            catch(SQLException e)
            {
                System.out.println("No data found");
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
*/
        return list;
}
}