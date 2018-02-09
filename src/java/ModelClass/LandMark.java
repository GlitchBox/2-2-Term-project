package ModelClass;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name="LandMark")
@RequestScoped
public class LandMark implements Serializable{
 
private DBConnection dbconnection = new DBConnection();  
private int LANDMARK_ID;
private String OPENING_DAYS;
private String GENRE;
private String DESCRIPTION;
private int ADDITIONAL_COST;
private int REGION_ID;
private String ROAD_NO;
private String AREA_NAME;
private String DISTRICT;
private String OPENING_TIME;
private String CLOSING_TIME;
private int RATING_COUNT;
private int RATING ;
private String LANDMARK_NAME;
private String REGION_NAME;
private Map<String, String> DAYS;
private Map<String, String> GENRES;


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
        
        GENRES = new HashMap<String, String>();
        GENRES.put("HISTORIC", "HISTORIC");
        GENRES.put("NATURAL", "NATURAL");
    }

    public Map<String, String> getGENRES() {
        return GENRES;
    }

    public void setGENRES(Map<String, String> GENRES) {
        this.GENRES = GENRES;
    }

    public void onKindChange() {
        System.out.println(OPENING_DAYS);
    }
    
    public void onDayChange() {
        System.out.println(OPENING_DAYS);
    }
     
    public Map<String, String> getDAYS() {
        return DAYS;
    }

    public void setDAYS(Map<String, String> DAYS) {
        this.DAYS = DAYS;
    }

    public String getLANDMARK_NAME() {
        return LANDMARK_NAME;
    }

    public void setLANDMARK_NAME(String LANDMARK_NAME) {
        this.LANDMARK_NAME = LANDMARK_NAME;
    }


    public int getLANDMARK_ID() {
        return LANDMARK_ID;
    }

    public void setLANDMARK_ID(int LANDMARK_ID) {
        this.LANDMARK_ID = LANDMARK_ID;
    }

    public String getOPENING_DAYS() {
        return OPENING_DAYS;
    }

    public void setOPENING_DAYS(String OPENING_DAYS) {
        this.OPENING_DAYS = OPENING_DAYS;
    }

    public String getGENRE() {
        return GENRE;
    }

    public void setGENRE(String GENRE) {
        this.GENRE = GENRE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public int getADDITIONAL_COST() {
        return ADDITIONAL_COST;
    }

    public void setADDITIONAL_COST(int ADDITIONAL_COST) {
        this.ADDITIONAL_COST = ADDITIONAL_COST;
    }

    public int getREGION_ID() {
        return REGION_ID;
    }

    public void setREGION_ID(int REGION_ID) {
        this.REGION_ID = REGION_ID;
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

    public String getOPENING_TIME() {
        return OPENING_TIME;
    }

    public void setOPENING_TIME(String OPENING_TIME) {
        this.OPENING_TIME = OPENING_TIME;
    }

    public String getCLOSING_TIME() {
        return CLOSING_TIME;
    }

    public void setCLOSING_TIME(String CLOSING_TIME) {
        this.CLOSING_TIME = CLOSING_TIME;
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
    }

    public String getREGION_NAME() {
        return REGION_NAME;
    }

    public void setREGION_NAME(String regionName) {
        this.REGION_NAME= regionName;
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
            rs = stmt.executeQuery("SELECT * FROM LANDMARKS WHERE LANDMARK_NAME = '"+getLANDMARK_NAME()+"'");
                if(!rs.next())
                {
                    
                   rs1 = stmt2.executeQuery("SELECT REGION_ID FROM REGION WHERE REGION_NAME ='"+getREGION_NAME()+"'");
                   if(rs1.next()){
                       
                   setREGION_ID(rs1.getInt(1));
                   System.out.print(getREGION_NAME()+" "+getREGION_ID());
                   
                   //
                   //rs1 = stmt2.executeQuery("SELECT REGION_ID FROM REGION WHERE REGION_NAME ='"+getREGION_NAME()+"'");
                   
                   String query = "INSERT INTO LANDMARKS(LANDMARK_ID,GENRE,ADDITIONAL_COST,REGION_ID,"
                           + "ROAD_NO,AREA_NAME,DISTRICT, OPENING_TIME,CLOSING_TIME,RATING_COUNT,RATING,"
                           + "LANDMARK_NAME,OPENING_DAYS,DESCRIPTION) VALUES(LANDMARK_ID_VAL.NEXTVAL"
                           +",'"+getGENRE()+"',"+getADDITIONAL_COST()+","+getREGION_ID()+",'"+getROAD_NO()+"','"
                           +getAREA_NAME()+"','"+getDISTRICT()+"','"+getOPENING_TIME()+"','"+getCLOSING_TIME()
                           +"',"+getRATING_COUNT()+","+getRATING()+",'"+getLANDMARK_NAME()+"','"+getOPENING_DAYS()
                           +"','"+getDESCRIPTION()+"')";
                   System.out.print(query);
                   stmt.executeUpdate(query);
                   }
                 
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
    System.out.println(getLANDMARK_NAME()); 
    Statement stmt = null;
    Connection con = null;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
    String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
    con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
    if (con!= null) {
            stmt = con.createStatement();
            try {
            stmt.executeUpdate("DELETE FROM LANDMARKS WHERE LANDMARK_NAME = '"+getLANDMARK_NAME()+"'");
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