/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Asus
 */
@ManagedBean(name = "Gallery")
@SessionScoped
public class Gallery {
    
    //static String user;
    private String entry;
    static String LOCATION;
    private List<String> images;
    
    
    private final DBConnection dbconnection = new DBConnection();
    
    /*@PostConstruct
    public void init()
    {
        images = new ArrayList<String>();
        images.add("BICHNAKADI.jpg");
        LOCATION = "SYLHET";
    }*/
    
    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }
    
    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
        System.out.print(entry);
    }

    public void onclick() throws SQLException
    {
        System.out.println(PageController.user);
        loadImages();
    }
    
     
    //@PostConstruct
    public void loadImages() throws SQLException {
        images = new ArrayList<String>();
        Statement stmt2 = null;
        Connection con = null;
        ResultSet rs1 = null;
        
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
        con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
        if (con != null) {
            stmt2 = con.createStatement();
            try {
                String query = "SELECT L.LANDMARK_NAME FROM LANDMARKS L, REGION R WHERE R.REGION_NAME ='" + LOCATION + "' AND R.REGION_ID = L.REGION_ID";
                rs1 = stmt2.executeQuery(query);
                while(rs1.next()) {
                    //int i = rs1.getInt(1);
                    //rs1 = stmt2.executeQuery("SELECT LANDMARK_NAME FROM LANDMARKS WHERE REGION_ID = " + i);
                    images.add(rs1.getString(1)+".jpg");
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    con.close();
                    stmt2.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        /*for (int i = 1; i <= 12; i++) {
            images.add(LOCATION+ + i + ".jpg");
        }*/
    }
 
    public List<String> getImages() {
        return images;
    }
    

}

