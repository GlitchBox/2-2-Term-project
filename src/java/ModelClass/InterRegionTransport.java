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

@ManagedBean(name="InterRT")
@RequestScoped
public class InterRegionTransport implements Serializable{

private int INTER_REGION_TRANSPORT_ID;
private int FROM_DIST;
private int TO_DIST;
private int EXPENSE;
private int POPULARITY_COUNT;
private long POPULARITY;
private String MODE_OF_TRANSPORT;
private String VEHICLE_NAME;
private String DEL_VEHICLE_NAME;
private String DEL_MODE_OF_TRANSPORT;
private String DEL_FROM_DIST;
private String DEL_TO_DIST;

private String TO_NAME;
private String FROM_NAME;
private final DBConnection dbconnection = new DBConnection();
private Map<String, String> MODES;


@PostConstruct
    public void init() {
        MODES = new HashMap<String, String>();
        MODES.put("BUS", "BUS");
        MODES.put("TRAIN", "TRAIN");
        MODES.put("BOAT", "BOAT");
         MODES.put("PLANE", "PLANE");
        
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
    
    public String getDEL_VEHICLE_NAME() {
        return DEL_VEHICLE_NAME;
    }

    public void setDEL_VEHICLE_NAME(String DEL_VEHICLE_NAME) {
        this.DEL_VEHICLE_NAME = DEL_VEHICLE_NAME;
    }
    
    public String getDEL_MODE_OF_TRANSPORT() {
        return DEL_MODE_OF_TRANSPORT;
    }

    public void setDEL_MODE_OF_TRANSPORT(String DEL_VEHICLE_NAME) {
        this.DEL_MODE_OF_TRANSPORT = DEL_VEHICLE_NAME;
    }
    
    public String getDEL_FROM_DIST() {
        return DEL_FROM_DIST;
    }

    public void setDEL_FROM_DIST(String DEL_VEHICLE_NAME) {
        this.DEL_FROM_DIST = DEL_VEHICLE_NAME;
    }
    
    public String getDEL_TO_DIST() {
        return DEL_TO_DIST;
    }

    public void setDEL_TO_DIST(String DEL_VEHICLE_NAME) {
        this.DEL_TO_DIST = DEL_VEHICLE_NAME;
    }

    public String getTO_NAME() {
        return TO_NAME;
    }

    public void setTO_NAME(String TO_NAME) {
        this.TO_NAME = TO_NAME;
    }

    public String getFROM_NAME() {
        return FROM_NAME;
    }

    public void setFROM_NAME(String FROM_NAME) {
        this.FROM_NAME = FROM_NAME;
    }

    public int getINTER_REGION_TRANSPORT_ID() {
        return INTER_REGION_TRANSPORT_ID;
    }

    public void setINTER_REGION_TRANSPORT_ID(int INTER_REGION_TRANSPORT_ID) {
        this.INTER_REGION_TRANSPORT_ID = INTER_REGION_TRANSPORT_ID;
    }

    public int getFROM_DIST() {
        return FROM_DIST;
    }

    public void setFROM_DIST(int FROM_DIST) {
        this.FROM_DIST = FROM_DIST;
    }

    public int getTO_DIST() {
        return TO_DIST;
    }

    public void setTO_DIST(int TO_DIST) {
        this.TO_DIST = TO_DIST;
    }

    public int getEXPENSE() {
        return EXPENSE;
    }

    public void setEXPENSE(int EXPENSE) {
        this.EXPENSE = EXPENSE;
    }

    public int getPOPULARITY_COUNT() {
        return POPULARITY_COUNT;
    }

    public void setPOPULARITY_COUNT(int POPULARITY_COUNT) {
        this.POPULARITY_COUNT = POPULARITY_COUNT;
    }

    public long getPOPULARITY() {
        return POPULARITY;
    }

    public void setPOPULARITY(long POPULARITY) {
        this.POPULARITY = POPULARITY;
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
                rs = stmt.executeQuery("SELECT * FROM INTER_REGION_TRANSPORT WHERE VEHICLE_NAME = '" + getVEHICLE_NAME() + "'AND "
                        + "MODE_OF_TRANSPORT='"+getMODE_OF_TRANSPORT()+"'AND FROM_DIST="+getFROM_DIST()+"AND TO_DIST="+getTO_DIST());
                System.out.print("Query executed.");
                if (!rs.next()) {
                    String sql;
                    sql = "SELECT REGION_ID FROM REGION WHERE REGION_NAME='"+getTO_NAME()+"'";
                    System.out.print(sql);
                    rs1=stmt2.executeQuery(sql);
                    if(rs1.next())
                    {
                        setTO_DIST(rs1.getInt(1));
                        sql = "SELECT REGION_ID FROM REGION WHERE REGION_NAME='"+getFROM_NAME()+"'";
                        System.out.print(sql);
                        rs2=stmt3.executeQuery(sql);
                        if(rs2.next())
                            setFROM_DIST(rs2.getInt(1));
                    }
                    System.out.print(getFROM_NAME()+" "+getTO_NAME());
                    cstmt = con.prepareCall("{call INTER_INSERT(INTER_ID_VAL.NEXTVAL,?,?,?,?,?,?,?)}");
                    //cstmt.setInt(1, getSHOPPING_ID());
                    //cstmt.setInt(, getINTER_REGION_TRANSPORT_ID());
                    //setPOPULARITY(0);
                    //setPOPULARITY_COUNT(0);
                    cstmt.setInt(1, getFROM_DIST());
                    System.out.print(getFROM_DIST()+"done");
                    cstmt.setInt(2, getTO_DIST());
                    System.out.print(getTO_DIST()+"done");
                    cstmt.setInt(3, getEXPENSE());
                    System.out.print(getEXPENSE()+"done");
                    cstmt.setString(4, getMODE_OF_TRANSPORT());
                    System.out.print(getMODE_OF_TRANSPORT()+"done");
                    cstmt.setString(5, getVEHICLE_NAME());
                    System.out.print(getVEHICLE_NAME()+"done");
                    cstmt.setLong(6, getPOPULARITY());
                    System.out.print(getPOPULARITY()+"done");
                    cstmt.setLong(7, getPOPULARITY_COUNT());
                    System.out.print(getPOPULARITY_COUNT()+"done");
                    //cstmt.registerOutParameter(1, Types.INTEGER);
                    cstmt.executeUpdate();
                    System.out.print("done");
                    
                    //System.out.print(cstmt.getInt(1));
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
    System.out.print("In delete function INTER");
    if (con!= null) {
            System.out.println("Connected with connection #2");
            stmt = con.createStatement();
            stmt2 = con.createStatement();
            try {
            int id1=0, id2=0;
            System.out.print(getFROM_NAME() +" "+getTO_NAME());
            rs1 = stmt2.executeQuery("SELECT REGION_ID FROM REGION WHERE REGION_NAME='"+getFROM_NAME()+"'");
            if(rs1.next())
            {
                id1 = rs1.getInt(1);
                rs2 = stmt2.executeQuery("SELECT REGION_ID FROM REGION WHERE REGION_NAME='"+getTO_NAME()+"'");
                if(rs2.next())
                    id2 = rs2.getInt(1);
            }
            System.out.print(id1+" "+id2);
            String sql = "DELETE FROM INTER_REGION_TRANSPORT WHERE FROM_DIST="+id1+" AND TO_DIST= "+id2+ " AND VEHICLE_NAME = '" + getVEHICLE_NAME() + "' AND "
                        + "MODE_OF_TRANSPORT='"+getMODE_OF_TRANSPORT()+"'";
            System.out.print(sql);
            stmt.executeUpdate(sql);
            System.out.print("After deletion function INTER");
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