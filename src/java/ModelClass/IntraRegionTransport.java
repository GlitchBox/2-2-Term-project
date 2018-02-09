    package ModelClass;

import java.io.Serializable;
import java.sql.CallableStatement;
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

@ManagedBean(name="IntraRT")
@RequestScoped
public class IntraRegionTransport implements Serializable{
private int INTRA_REGION_TRANSPORT_ID;
private int EXPENSE;
private long POPULARITY_COUNT;
private long POPULARITY;
private String FROM_DEST;
private String TO_DEST;
private String MODE_OF_TRANSPORT;
private String VEHICLE_NAME;
private String DEL_VEHICLE_NAME;
private String DEL_MODE_OF_TRANSPORT;
private String DEL_FROM_DEST;
private String DEL_TO_DEST;
private Map<String, String> MODES;

@PostConstruct
    public void init() {
        MODES = new HashMap<String, String>();
        MODES.put("BUS", "BUS");
        MODES.put("TRAIN", "TRAIN");
        MODES.put("BOAT", "BOAT");
        MODES.put("PLANE", "PLANE");
        MODES.put("MICRO BUS", "MICRO BUS");
        MODES.put("CNG", "CNG");
        
    }

    public void onModeChange() {
        System.out.println(MODE_OF_TRANSPORT);
    }
    
    public Map<String, String> getMODES() {
        return MODES;
    }

    public void setMODES(Map<String, String> MODES) {
        this.MODES = MODES;
    }

private DBConnection dbconnection = new DBConnection();  

    public String getDEL_VEHICLE_NAME() {
        return DEL_VEHICLE_NAME;
    }

    public void setDEL_VEHICLE_NAME(String DEL_VEHICLE_NAME) {
        this.DEL_VEHICLE_NAME = DEL_VEHICLE_NAME;
    }
    public String getDEL_MODE_OF_TRANSPORT() {
        return DEL_MODE_OF_TRANSPORT;
    }

    public void setDEL_MODE_OF_TRANSPORT(String DEL_MODE_OF_TRANSPORT) {
        this.DEL_MODE_OF_TRANSPORT = DEL_MODE_OF_TRANSPORT;
    }
    public String getDEL_FROM_DEST() {
        return DEL_FROM_DEST;
    }

    public void setDEL_FROM_DEST(String DEL_FROM_DEST) {
        this.DEL_FROM_DEST = DEL_FROM_DEST;
    }
    public String getDEL_TO_DEST() {
        return DEL_TO_DEST;
    }

    public void setDEL_TO_DEST(String DEL_TO_DEST) {
        this.DEL_TO_DEST = DEL_TO_DEST;
    }

    public int getINTRA_REGION_TRANSPORT_ID() {
        return INTRA_REGION_TRANSPORT_ID;
    }

    public void setINTRA_REGION_TRANSPORT_ID(int INTRA_REGION_TRANSPORT_ID) {
        this.INTRA_REGION_TRANSPORT_ID = INTRA_REGION_TRANSPORT_ID;
    }

    public int getEXPENSE() {
        return EXPENSE;
    }

    public void setEXPENSE(int EXPENSE) {
        this.EXPENSE = EXPENSE;
    }

    public long getPOPULARITY_COUNT() {
        return POPULARITY_COUNT;
    }

    public void setPOPULARITY_COUNT(long POPULARITY_COUNT) {
        this.POPULARITY_COUNT = POPULARITY_COUNT;
    }

    public long getPOPULARITY() {
        return POPULARITY;
    }

    public void setPOPULARITY(long POPULARITY) {
        this.POPULARITY = POPULARITY;
    }

    public String getFROM_DEST() {
        return FROM_DEST;
    }

    public void setFROM_DEST(String FROM_DEST) {
        this.FROM_DEST = FROM_DEST;
    }

    public String getTO_DEST() {
        return TO_DEST;
    }

    public void setTO_DEST(String TO_DEST) {
        this.TO_DEST = TO_DEST;
    }

    public String getMODE_OF_TRANSPORT() {
        return MODE_OF_TRANSPORT;
    }

    public void setMODE_OF_TRANSPORT(String MODE_OF_TRANSPORT) {
        this.MODE_OF_TRANSPORT = MODE_OF_TRANSPORT;
    }

    public String getVEHICLE_NAME() {
        return VEHICLE_NAME;
    }

    public void setVEHICLE_NAME(String VEHICLE_NAME) {
        this.VEHICLE_NAME = VEHICLE_NAME;
    }

public void insert() throws SQLException
{
    
        Statement stmt = null,stmt2 = null,stmt3=null;
        Connection con = null;
        ResultSet rs = null, rs1 = null,rs2=null;
        CallableStatement cstmt = null, cstmt2 = null;
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
        con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
        System.out.print("In interRegion insert function ");
        if (con != null) {
            System.out.println("Connected with connection #2");
            stmt = con.createStatement();
            stmt2 = con.createStatement();
            stmt3 = con.createStatement();
            try {
                rs = stmt.executeQuery("SELECT * FROM INTRA_REGION_TRANSPORT WHERE VEHICLE_NAME = '" + getVEHICLE_NAME() + "' AND "
                        + "MODE_OF_TRANSPORT='"+getMODE_OF_TRANSPORT()+"' AND FROM_DEST='"+getFROM_DEST()+"' AND TO_DEST='"+getTO_DEST()+"'");
                System.out.print("Query executed.");
                if (!rs.next()) {
                  
                    
                    //System.out.print(cstmt.getInt(1));
                String sql = "INSERT INTO INTRA_REGION_TRANSPORT VALUES(INTRA_ID_VAL.NEXTVAL"+
                        ",'"+getFROM_DEST()+"','"+getTO_DEST()+"',"+getEXPENSE()+",'"+getMODE_OF_TRANSPORT()
                        +"','"+getVEHICLE_NAME()+"',"+getPOPULARITY()+","+getPOPULARITY_COUNT()+")";
                System.out.print(sql);
                stmt2.executeUpdate(sql);
                }
            } catch (SQLException e) {
                System.out.println("No data found while inserting");
            } finally {
                try {
                    con.close();
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }

}

public void delete() throws SQLException
{
    Statement stmt = null,stmt2=null;
    ResultSet rs1=null,rs2 = null;
    Connection con = null;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
    String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
    con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
    System.out.print("In delete function INTRA");
    if (con!= null) {
            System.out.println("Connected with connection #2");
            stmt = con.createStatement();
            stmt2 = con.createStatement();
            try {
            /*int id1=0, id2=0;
            rs1 = stmt2.executeQuery("SELECT REGION_ID FROM REGION WHERE REGION_NAME='"+getDEL_FROM_DIST()+"'");
            if(rs1.next())
            {
                id1 = rs1.getInt(1);
                rs2 = stmt2.executeQuery("SELECT REGION_ID FROM REGION WHERE REGION_NAME='"+getDEL_TO_DIST()+"'");
                if(rs2.next())
                    id2 = rs2.getInt(1);
            }
            System.out.print(id1+" "+id2);*/
            String sql = "DELETE FROM INTRA_REGION_TRANSPORT WHERE FROM_DEST='"+getFROM_DEST()+"' AND TO_DEST= '"+getTO_DEST()+ "' AND VEHICLE_NAME = '" + getVEHICLE_NAME() + "' AND "
                        + "MODE_OF_TRANSPORT='"+getMODE_OF_TRANSPORT()+"'";
            System.out.print(sql);
            stmt.executeUpdate(sql);
            System.out.print("After deletion function INTRA");
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