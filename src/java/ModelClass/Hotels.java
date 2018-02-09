package ModelClass;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

@ManagedBean(name = "Hotels")
@RequestScoped
public class Hotels implements Serializable {

    private int PLACE_ID;
    private int REGION_ID;
    private int MAXIMUM_COST;
    private int MINIMUM_COST;
    private int RATING_COUNT;
    private int RATING;
    private String KIND;
    private String HOUSE_NO;
    private String ROAD_NO;
    private String AREA_NAME;
    private String DISTRICT;
    private String REGION_NAME;
    private String NAME;
    private final DBConnection dbconnection = new DBConnection();
    private Map<String, String> kinds;

    @PostConstruct
    public void init() {
        kinds = new HashMap<String, String>();
        kinds.put("Hotel", "Hotel");
        kinds.put("Resort", "Resort");
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

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public int getPLACE_ID() {
        return PLACE_ID;
    }

    public void setPLACE_ID(int PLACE_ID) {
        this.PLACE_ID = PLACE_ID;
    }

    public int getREGION_ID() {
        return REGION_ID;
    }

    public void setREGION_ID(int REGION_ID) {
        this.REGION_ID = REGION_ID;
    }

    public int getMAXIMUM_COST() {
        return MAXIMUM_COST;
    }

    public void setMAXIMUM_COST(int MAXIMUM_COST) {
        this.MAXIMUM_COST = MAXIMUM_COST;
    }

    public int getMINIMUM_COST() {
        return MINIMUM_COST;
    }

    public void setMINIMUM_COST(int MINIMUM_COST) {
        this.MINIMUM_COST = MINIMUM_COST;
    }

    public int getRATING_COUNT() {
        return RATING_COUNT;
    }

    public void setRATING_COUNT(int RATING_COUNT) {
        this.RATING_COUNT = RATING_COUNT;
    }

    public int getRATING() {
        return RATING;
    }

    public void setRATING(int RATING) {
        this.RATING = RATING;
        System.out.println(RATING);
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

    public void insert() throws SQLException {
        Statement stmt = null;
        Statement stmt1 = null;
        Connection con = null;
        ResultSet rs = null, rs1 = null;
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
        con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
        if (con != null) {
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            try {
                rs1 = stmt1.executeQuery("SELECT REGION_ID FROM REGION WHERE REGION_NAME = '" + getREGION_NAME() + "'");
                if (rs1.next()) {

                    rs = stmt.executeQuery("SELECT REGION_ID FROM HOTELS_AND_RESORTS WHERE HOTEL_NAME = '" + getNAME() + "' AND DISTRICT = '" + getDISTRICT()
                            + "' AND AREA_NAME = '" + getAREA_NAME() + "' AND ROAD_NO = '" + getROAD_NO() + "'");

                    if (!rs.next()) {
                        int s = rs1.getInt(1);
                        setREGION_ID(s);
                        String query = "INSERT INTO HOTELS_AND_RESORTS(PLACE_ID, REGION_ID, KIND, HOUSE_NO, ROAD_NO, AREA_NAME, DISTRICT,"
                                + " MAXIMUM_COST, MINIMUM_COST, HOTEL_NAME) VALUES(HOTEL_ID_VAL.NEXTVAL"
                                + "," + getREGION_ID() + ",'" + getKIND() + "','" + getHOUSE_NO() + "','" + getROAD_NO() + "','"
                                + getAREA_NAME() + "','" + getDISTRICT() + "'," + getMAXIMUM_COST() + "," + getMINIMUM_COST() + ",'" + getNAME() + "')";
                        System.out.println(query);
                        stmt.executeUpdate(query);
                    }
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
    
    public void demo() throws SQLException {
        System.out.println(getNAME());
        Statement stmt = null;
        Connection con = null;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(Hotels.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
        con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
        if (con != null) {
            stmt = con.createStatement();
            try {
                stmt.executeUpdate("DELETE FROM HOTELS_AND_RESORTS WHERE HOTEL_NAME = '" + getNAME()
                        + "' AND AREA_NAME = '" + getAREA_NAME() + "' AND ROAD_NO = '" + getROAD_NO() + "'");
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

    public void onKindChange() {
        System.out.println(KIND);
    }

}