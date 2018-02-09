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
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

@ManagedBean(name = "Emergency")
@RequestScoped
public class Emergency implements Serializable {

    private int EMERGENCY_ID;
    private int REGION_ID;
    private String EMERGENCY_NAME;
    private String KIND;
    private String HOUSE_NO;
    private String ROAD_NO;
    private String AREA_NAME;
    private String DISTRICT;
    private String CONTACT_INFO;
    private String REGION_NAME;
    private Map<String, String> kinds;
    private final DBConnection dbconnection = new DBConnection();

    @PostConstruct
    public void init() {
        kinds = new HashMap<String, String>();
        kinds.put("FIRE SERVICE", "FIRE SERVICE");
        kinds.put("POLICE", "POLICE");
        kinds.put("HOSPITAL", "HOSPITAL");
        kinds.put("AMBULANCE", "AMBULANCE");
    }
    
    public void onKindChange() {
        System.out.println(KIND);
    }

    public Map<String, String> getKinds() {
        return kinds;
    }

    public void setKinds(Map<String, String> kinds) {
        this.kinds = kinds;
    }

    public String getREGION_NAME() {
        return REGION_NAME;
    }

    public void setREGION_NAME(String REGION_NAME) {
        this.REGION_NAME = REGION_NAME;
    }

    public String getEMERGENCY_NAME() {
        return EMERGENCY_NAME;
    }

    public void setEMERGENCY_NAME(String NAME) {
        this.EMERGENCY_NAME = NAME;
    }

    public int getEMERGENCY_ID() {
        return EMERGENCY_ID;
    }

    public void setEMERGENCY_ID(int EMERGENCY_ID) {
        this.EMERGENCY_ID = EMERGENCY_ID;
    }

    public int getREGION_ID() {
        return REGION_ID;
    }

    public void setREGION_ID(int REGION_ID) {
        this.REGION_ID = REGION_ID;
    }

    public String getKIND() {
        return KIND;
    }

    public void setKIND(String KIND) {
        this.KIND = KIND;
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



public void insert() throws SQLException
{
    
    Statement stmt = null,stmt2 = null;
    Connection con = null;
    ResultSet rs = null,rs1 = null;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
    String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
    con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
            if (con!= null) {
                stmt = con.createStatement();
                stmt2 = con.createStatement();
            try {
            rs = stmt.executeQuery("SELECT * FROM EMERGENCY_SERVICES WHERE EMERGENCY_NAME = '"+getEMERGENCY_NAME()+"'");
                if(!rs.next())
                {
                    
                   rs1 = stmt2.executeQuery("SELECT REGION_ID FROM REGION WHERE REGION_NAME ='"+getREGION_NAME()+"'");
                   if(rs1.next()){
                       
                   setREGION_ID(rs1.getInt(1));
                   System.out.print(getREGION_NAME()+" "+getREGION_ID());
                   
                   //
                   //rs1 = stmt2.executeQuery("SELECT REGION_ID FROM REGION WHERE REGION_NAME ='"+getREGION_NAME()+"'");
                   
                   String query = "INSERT INTO EMERGENCY_SERVICES(EMERGENCY_ID,REGION_ID,KIND,"
                           + "HOUSE_NO,ROAD_NO,AREA_NAME,DISTRICT,EMERGENCY_NAME,CONTACT_INFO)"
                           + "VALUES(EMERGENCY_ID_VAL.NEXTVAL"
                           +","+getREGION_ID()+",'"+getKIND()+"','"+getHOUSE_NO()+"','"+getROAD_NO()+"','"
                           +getAREA_NAME()+"','"+getDISTRICT()+"','"+getEMERGENCY_NAME()+"','"+getCONTACT_INFO()
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
    Statement stmt = null;
    Connection con = null;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
    String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
    con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
    System.out.print("In delete function EMERGENCY");
    if (con!= null) {
            System.out.println("Connected with connection #2");
            stmt = con.createStatement();
            try {
                String sql = "DELETE FROM EMERGENCY_SERVICES WHERE EMERGENCY_NAME = '"+getEMERGENCY_NAME()
                        +"' AND ROAD_NO='"+getROAD_NO()+"' AND AREA_NAME ='"+getAREA_NAME()+"' AND DISTRICT='"
                        +getDISTRICT()+"'";
            System.out.print(sql);
                stmt.executeUpdate(sql);
            System.out.print("After deletion function EMERGENCY");
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