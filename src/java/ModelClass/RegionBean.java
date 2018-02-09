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


@ManagedBean(name = "RegionBean")
@SessionScoped
public class RegionBean  implements Serializable{
    
    private DBConnection dbconnection = new DBConnection();

public List<Region> getRegionList() throws ClassNotFoundException, SQLException
{
List<Region> list = new ArrayList<>();

Statement stmt = null;
Connection con = null;
ResultSet rs = null;

try {
         DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2,  dbconnection.user, dbconnection.password);
            if (con!= null) {
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
        return list;
}
}