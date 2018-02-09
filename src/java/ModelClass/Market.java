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

@ManagedBean(name = "Market")
@RequestScoped
public class Market  implements Serializable{

    private int SHOPPING_ID;
    private int REGION_ID;
    private String HOUSE_NO;
    private String ROAD_NO;
    private String AREA_NAME;
    private String DISTRICT;
    private String OPEN_HOURS;
    private String OPEN_DAYS;
    private String MARKET_NAME;
    private String DEL_MARKET_NAME;
    private String REGION_NAME;
    private Date VISIT_DATE;

    public Date getVISIT_DATE() {
        return VISIT_DATE;
    }

    public void setVISIT_DATE(Date VISIT_DATE) {
        this.VISIT_DATE = VISIT_DATE;
    }

    
    private Map<String, String> DAYS;
    private final DBConnection dbconnection = new DBConnection();

    @PostConstruct
    public void init() {
        DAYS = new HashMap<String, String>();
        DAYS.put("SATURDAY", "SATURDAY");
        DAYS.put("SUNDAY", "SUNDAY");
        DAYS.put("MONDAY", "MONDAY");
        DAYS.put("TUESDAY", "TUESDAY");
        DAYS.put("WEDNESDAY", "WEDNESDAY");
        DAYS.put("THURSDAY", "THURSDAY");
        DAYS.put("FRIDAY", "FRIDAY");
    }

    public String getREGION_NAME() {
        return REGION_NAME;
    }

    public void setREGION_NAME(String REGION_NAME) {
        this.REGION_NAME = REGION_NAME;
    }

    
    
    public void onDayChange() {
        System.out.println(OPEN_DAYS);
    }

    public Map<String, String> getDAYS() {
        return DAYS;
    }

    public void setDAYS(Map<String, String> DAYS) {
        this.DAYS = DAYS;
    }

    public String getDEL_MARKET_NAME() {
        return DEL_MARKET_NAME;
    }

    public void setDEL_MARKET_NAME(String DEL_MARKET_NAME) {
        this.DEL_MARKET_NAME = DEL_MARKET_NAME;
    }

    public int getSHOPPING_ID() {
        return SHOPPING_ID;
    }

    public void setSHOPPING_ID(int SHOPPING_ID) {
        this.SHOPPING_ID = SHOPPING_ID;
    }

    public int getREGION_ID() {
        return REGION_ID;
    }

    public void setREGION_ID(int REGION_ID) {
        this.REGION_ID = REGION_ID;
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

    public String getOPEN_HOURS() {
        return OPEN_HOURS;
    }

    public void setOPEN_HOURS(String OPEN_HOURS) {
        this.OPEN_HOURS = OPEN_HOURS;
    }

    public String getOPEN_DAYS() {
        return OPEN_DAYS;
    }

    public void setOPEN_DAYS(String OPEN_DAYS) {
        this.OPEN_DAYS = OPEN_DAYS;
    }

    public String getMARKET_NAME() {
        return MARKET_NAME;
    }

    public void setMARKET_NAME(String MARKET_NAME) {
        this.MARKET_NAME = MARKET_NAME;
    }
    
    public void insert() throws SQLException {

        Statement stmt = null,stmt2 = null;
        Connection con = null;
        ResultSet rs = null, rs1 = null;
        CallableStatement cstmt = null, cstmt2 = null;
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
        con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
        System.out.print("In insert function Market");
        if (con != null) {
            System.out.println("Connected with connection #2");
            stmt = con.createStatement();
            stmt2 = con.createStatement();
            try {
                rs = stmt.executeQuery("SELECT * FROM SHOPPING WHERE SHOP_NAME = '" + getMARKET_NAME() + "'");
                if (!rs.next()) {
                    //stmt.executeUpdate("INSERT INTO REGION (REGION_ID, REGION_NAME)"+" VALUES(REGION_ID_VAL.NEXTVAL"+", "+"'"+getRegionName()+"')");
                    //System.out.print("In insert function "+getRegionID()+getRegionName());
                    /*cstmt2 = con.prepareCall("{?=call FIND_REGION_ID(?)}");
                    cstmt2.setString(2, getREGION_NAME());
                    cstmt2.registerOutParameter(1,Types.INTEGER); 
                    cstmt.execute();
                    setREGION_ID(cstmt2.getInt(1));*/
                    rs1 = stmt2.executeQuery("SELECT REGION_ID FROM REGION WHERE REGION_NAME='"+getREGION_NAME()+"'");
                    if(rs1.next())
                    {
                        setREGION_ID(rs1.getInt(1));
                        System.out.print(getREGION_NAME()+" "+getREGION_ID());
                    }
                    //rs1 = stmt2.executeQuery();
                    cstmt = con.prepareCall("{call MARKET_INSERT(SHOPPING_ID_VAL.NEXTVAL,?,?,?,?,?,?,?,?)}");
                    //cstmt.setInt(1, getSHOPPING_ID());
                    cstmt.setInt(1, getREGION_ID());
                    System.out.print(getREGION_ID());
                    cstmt.setString(2, getHOUSE_NO());
                    System.out.print(getHOUSE_NO());
                    cstmt.setString(3, getROAD_NO());
                    System.out.print(getROAD_NO());
                    cstmt.setString(4, getAREA_NAME());
                    System.out.print(getAREA_NAME());
                    cstmt.setString(5, getDISTRICT());
                    System.out.print(getDISTRICT());
                    cstmt.setString(6, getOPEN_HOURS());
                    System.out.print(getOPEN_HOURS());
                    cstmt.setString(7, getOPEN_DAYS());
                    System.out.print(getOPEN_DAYS());
                    cstmt.setString(8, getMARKET_NAME());
                    System.out.print(getMARKET_NAME());
                    //cstmt.registerOutParameter(1, Types.INTEGER);
                    cstmt.executeUpdate();
                    
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

public void delete() throws SQLException {
        Statement stmt = null;
        Connection con = null;
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
        con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
        //System.out.print("In delete function "+getRegionName());
        if (con != null) {
            System.out.println("Connected with connection #2");
            stmt = con.createStatement();
            try {
                String sql = "DELETE FROM SHOPPING WHERE SHOP_NAME = '" + getMARKET_NAME() + "' AND ROAD_NO='"
                        + getROAD_NO() + "' AND AREA_NAME='" + getAREA_NAME() + "' AND DISTRICT='" + getDISTRICT()
                        + "'";
                System.out.print(sql);
                stmt.executeUpdate(sql);
                //System.out.print("In delete function "+getRegionName());
            } catch (SQLException e) {
                System.out.println("No data found during deletion");
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

}