 package ModelClass;

import com.sun.xml.ws.runtime.dev.Session;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.RateEvent;

@ManagedBean(name = "Plan")
@SessionScoped
public class Plan {

    private int EMERGENCY_ID;
    public Integer rt=3;
    private int SHOPPING_ID;
    private int INTRA_REGION_TRANSPORT_ID;
    private int INTER_REGION_TRANSPORT_ID;
    private int RESTAURANT_ID;
    private int HOTEL_ID;
    private int LANDMARK_ID;
    private int USER_ID;
    private int PLAN_ID;
    private int BUDGET;
    private int DAYS;
    private String EMERGENCY_NAME;
    private String SHOPPING_NAME;
    private String INTRA_REGION_NAME;
    private String INTER_REGION_NAME;
    private String RESTAURANT_NAME;
    private String HOTEL_NAME;
    private String LANDMARK_NAME;
    private String DESTINATION;
    private String LOCATION;
    private final DBConnection dbconnection = new DBConnection();
    private Map<String, String> regions;
    private List<String> landmarks = new ArrayList<>();
    private List<LandMark> selectedlandmarks = new ArrayList<>();
    private List<Hotels> hotels = new ArrayList<>();
    private Hotels selectedhotel ;
    private List<Restaurant> restaurants = new ArrayList<>();
    private Restaurant selectedrestaurant ;
    private List<IntraRegionTransport> intratransport = new ArrayList<>();
    private List<IntraRegionTransport> selectedintratransport = new ArrayList<>(); ;
    private List<InterRegionTransport> intertransport = new ArrayList<>();
    private InterRegionTransport selectedintertransport;
    private List<Emergency> emergency = new ArrayList<>();
    private List<Market> market = new ArrayList<>();

 

    
    PageController pc = new PageController();
    private Map<String, String> intratransportMode;
    private int totalSpent;

    public int getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(int totalSpent) {
        this.totalSpent = totalSpent;
    }

    public Map<String, String> getIntratransportMode() {
        return intratransportMode;
    }

    public void setIntratransportMode(Map<String, String> intratransportMode) {
        this.intratransportMode = intratransportMode;
    }
    
    public int getBUDGET() {
        return BUDGET;
    }

    public void setBUDGET(int BUDGET) {
        this.BUDGET = BUDGET;
    }
    public String Demo() {
       System.out.println(getBUDGET() + " working? " + getDAYS());
       findPlan();
       return "okay";
   }
    
    public List<InterRegionTransport> getIntertransport() {
        return intertransport;
    }

    public void setIntertransport(List<InterRegionTransport> intertransport) {
        this.intertransport = intertransport;
    }

    public InterRegionTransport getSelectedintertransport() {
        return selectedintertransport;
    }

    public void setSelectedintertransport(InterRegionTransport selectedintertransport) {
        this.selectedintertransport = selectedintertransport;
    }
    
    public List<Hotels> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotels> hotels) {
        this.hotels = hotels;
    }

    public Hotels getSelectedhotel() {
        return selectedhotel;
    }

    public void setSelectedhotel(Hotels selectedhotel) {
        this.selectedhotel = selectedhotel;
    }


    public int getDAYS() {
        return DAYS;
    }

    public void setDAYS(int DAYS) {
        this.DAYS = DAYS;
    }

    public List<String> getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(List<String> landmarks) {
        this.landmarks = landmarks;
    }

    public List<LandMark> getSelectedlandmarks() {
        return selectedlandmarks;
    }

    public void setSelectedlandmarks(List<LandMark> selectedlandmarks) {
        this.selectedlandmarks = selectedlandmarks;
    }

    public List<Emergency> getEmergency() {
        return emergency;
    }

    public void setEmergency(List<Emergency> emergency) {
        this.emergency = emergency;
    }

    public List<Market> getMarket() {
        return market;
    }

    public void setMarket(List<Market> market) {
        this.market = market;
    }


    public Map<String, String> getRegions() {
        return regions;
    }

    public void setRegions(Map<String, String> regions) {
        this.regions = regions;
    }

    public String getDESTINATION() {
        return DESTINATION;
    }

    public void setDESTINATION(String DESTINATION) {
        this.DESTINATION = DESTINATION;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getEMERGENCY_NAME() {
        return EMERGENCY_NAME;
    }

    public void setEMERGENCY_NAME(String EMERGENCY_NAME) {
        this.EMERGENCY_NAME = EMERGENCY_NAME;
    }

    public String getSHOPPING_NAME() {
        return SHOPPING_NAME;
    }

    public void setSHOPPING_NAME(String SHOPPING_NAME) {
        this.SHOPPING_NAME = SHOPPING_NAME;
    }

    public String getINTRA_REGION_NAME() {
        return INTRA_REGION_NAME;
    }

    public void setINTRA_REGION_NAME(String INTRA_REGION_NAME) {
        this.INTRA_REGION_NAME = INTRA_REGION_NAME;
    }

    public String getINTER_REGION_NAME() {
        return INTER_REGION_NAME;
    }

    public void setINTER_REGION_NAME(String INTER_REGION_NAME) {
        this.INTER_REGION_NAME = INTER_REGION_NAME;
    }

    public String getRESTAURANT_NAME() {
        return RESTAURANT_NAME;
    }

    public void setRESTAURANT_NAME(String RESTAURANT_NAME) {
        this.RESTAURANT_NAME = RESTAURANT_NAME;
    }

    public String getHOTEL_NAME() {
        return HOTEL_NAME;
    }

    public void setHOTEL_NAME(String HOTEL_NAME) {
        this.HOTEL_NAME = HOTEL_NAME;
    }

    public String getLANDMARK_NAME() {
        return LANDMARK_NAME;
    }

    public void setLANDMARK_NAME(String LANDMARK_NAME) {
        this.LANDMARK_NAME = LANDMARK_NAME;
    }

    public int getEMERGENCY_ID() {
        return EMERGENCY_ID;
    }

    public void setEMERGENCY_ID(int EMERGENCY_ID) {
        this.EMERGENCY_ID = EMERGENCY_ID;
    }

    public int getSHOPPING_ID() {
        return SHOPPING_ID;
    }

    public void setSHOPPING_ID(int SHOPPING_ID) {
        this.SHOPPING_ID = SHOPPING_ID;
    }

    public int getINTRA_REGION_TRANSPORT_ID() {
        return INTRA_REGION_TRANSPORT_ID;
    }

    public void setINTRA_REGION_TRANSPORT_ID(int INTRA_REGION_TRANSPORT_ID) {
        this.INTRA_REGION_TRANSPORT_ID = INTRA_REGION_TRANSPORT_ID;
    }

    public int getINTER_REGION_TRANSPORT_ID() {
        return INTER_REGION_TRANSPORT_ID;
    }

    public void setINTER_REGION_TRANSPORT_ID(int INTER_REGION_TRANSPORT_ID) {
        this.INTER_REGION_TRANSPORT_ID = INTER_REGION_TRANSPORT_ID;
    }

    public int getRESTAURANT_ID() {
        return RESTAURANT_ID;
    }

    public void setRESTAURANT_ID(int RESTAURANT_ID) {
        this.RESTAURANT_ID = RESTAURANT_ID;
    }

    public int getHOTEL_ID() {
        return HOTEL_ID;
    }

    public void setHOTEL_ID(int HOTEL_ID) {
        this.HOTEL_ID = HOTEL_ID;
    }

    public int getLANDMARK_ID() {
        return LANDMARK_ID;
    }

    public void setLANDMARK_ID(int LANDMARK_ID) {
        this.LANDMARK_ID = LANDMARK_ID;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public int getPLAN_ID() {
        return PLAN_ID;
    }

    public void setPLAN_ID(int PLAN_ID) {
        this.PLAN_ID = PLAN_ID;
    }

    
    public void onLocationChange() {
        System.out.println(LOCATION);
    }

    public void onDestinationChange() throws SQLException {
        landmarks.clear();
        getSelectedlandmarks().clear();
        Statement stmt2 = null;
        Connection con = null;
        ResultSet rs1 = null;
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
        con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
        if (con != null) {
            stmt2 = con.createStatement();
            try {
                String query = "SELECT REGION_ID FROM REGION WHERE REGION_NAME ='" + DESTINATION + "'";
                rs1 = stmt2.executeQuery(query);
                if (rs1.next()) {
                    int i = rs1.getInt(1);
                    rs1 = stmt2.executeQuery("SELECT LANDMARK_NAME FROM LANDMARKS WHERE REGION_ID = " + i);
                    while (rs1.next()) {
                        landmarks.add(rs1.getString(1));
                    }
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
    }

    @PostConstruct
    public void init() {
        regions = new HashMap<String, String>();
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;
        String value;

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
            if (con != null) {
                stmt = con.createStatement();
                try {
                    rs = stmt.executeQuery("SELECT REGION_NAME FROM REGION");
                    while (rs.next()) {
                        value = rs.getString(1);
                        regions.put(value, value);
                    }
                } catch (SQLException e) {
                    System.out.println("No data found");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
    }
    
    public void findPlan()
    {
        System.out.println(LOCATION);
        System.out.println("budget: "+BUDGET);
        //regions = new HashMap<String, String>();
        
        Hotels hot=new Hotels();
        Restaurant res = new Restaurant();
        InterRegionTransport inter = new InterRegionTransport();
        IntraRegionTransport intra = new IntraRegionTransport();
        Statement stmt = null,stmt1=null,stmt2=null;
        CallableStatement cstmt;
        Connection con = null;
        ResultSet rs = null,rs1=null,rs2=null;
        String sql;
        int currentBalance = BUDGET,temp = 0, id1=0, id2=0;
        intratransportMode = new HashMap<String, String>();

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
            if (con != null) {
                stmt = con.createStatement();
                stmt1 = con.createStatement();
                stmt2 = con.createStatement();
                try {
                    //suggest inter region transport
                    intertransport.clear();
                    rs = stmt.executeQuery("SELECT REGION_ID FROM REGION WHERE REGION_NAME='"+LOCATION+"'");
                    //System.out.print();
                    if(rs.next()) {
                        id1 = rs.getInt(1);
                        rs1 = stmt1.executeQuery("SELECT REGION_ID FROM REGION WHERE REGION_NAME='"+DESTINATION+"'");
                       if(rs1.next())
                        {
                            id2 = rs1.getInt(1);
                            String query = "SELECT VEHICLE_NAME,EXPENSE FROM INTER_REGION_TRANSPORT WHERE FROM_DIST="
                            +id1+" AND TO_DIST="+ id2 +" AND 2*EXPENSE<="+currentBalance;
                            System.out.print(query);
                            rs2 = stmt2.executeQuery(query);
                            while(rs2.next())
                            {
                                inter = new InterRegionTransport();
                                inter.setVEHICLE_NAME(rs2.getString(1));
                                inter.setEXPENSE(rs2.getInt(2));
                                intertransport.add(inter);
                                System.out.print("inserted");
                                
                            }
                            
                        }
                    }
                    
                        //sql = "{? = CALL MINIMUM_SPENT_INTER(?,?,?)}";
                        cstmt = con.prepareCall("{? = call MINIMUM_SPENT_INTER(?,?,?)}");
                        cstmt.registerOutParameter(1, Types.INTEGER);
                        cstmt.setInt(2, id1);
                        cstmt.setInt(3, id2);
                        cstmt.setInt(4, currentBalance);
                        cstmt.execute();
                        temp = cstmt.getInt(1);
                        currentBalance -= temp;
                        System.out.println(temp+" current:"+currentBalance);
                        
                        //suggest Hotel options
                        hotels.clear();
                        sql = "SELECT HOTEL_NAME,MINIMUM_COST FROM HOTELS_AND_RESORTS WHERE REGION_ID="
                                + id2 +" AND MINIMUM_COST*"+DAYS+"<="+currentBalance;
                        System.out.println(sql);
                        if(DAYS>0)
                        {
                                rs=stmt.executeQuery(sql);
                                while(rs.next())
                                {
                                    hot=new Hotels();
                                    hot.setNAME(rs.getString(1));
                                    hot.setMINIMUM_COST(rs.getInt(2));
                                    hotels.add(hot);
                                    System.out.println(hot+" inserted");
                                }

                            cstmt = con.prepareCall("{? = call MINIMUM_SPENT_HOTEL(?,?,?)}");
                            cstmt.registerOutParameter(1, Types.INTEGER);
                            cstmt.setInt(2, id2);
                            cstmt.setInt(3, currentBalance);
                            cstmt.setInt(4, DAYS);
                            cstmt.execute();
                            temp = cstmt.getInt(1);
                            currentBalance -= temp;
                            System.out.println(temp+" current:"+currentBalance);
                        //suggest intra region transport options
                        }
                        intratransport.clear();
                        for(int i=0;i<landmarks.size();i++)
                        {
                            sql = "SELECT MODE_OF_TRANSPORT,FROM_DEST,TO_DEST,EXPENSE FROM INTRA_REGION_TRANSPORT WHERE FROM_DEST ='"+DESTINATION+"' AND TO_DEST ='"+
                                    landmarks.get(i)+"' AND 2*EXPENSE<="+currentBalance;
                            System.out.println(sql);
                            rs = stmt.executeQuery(sql);
                            while(rs.next())
                            {
                                intra = new IntraRegionTransport();
                                intra.setMODE_OF_TRANSPORT(rs.getString(1));
                                intra.setFROM_DEST(rs.getString(2));
                                intra.setTO_DEST(rs.getString(3));
                                intra.setEXPENSE(rs.getInt(4));
                                intratransport.add(intra);
                                intratransportMode.put(intra.getMODE_OF_TRANSPORT(),landmarks.get(i));
                                System.out.println(intra +" inserted");
                            }
                                System.out.println(DESTINATION+" "+landmarks.get(i));
                                cstmt = con.prepareCall("{? = call MINIMUM_SPENT_INTRA(?,?,?)}");
                                cstmt.registerOutParameter(1, Types.INTEGER);
                                cstmt.setString(2, DESTINATION);
                                cstmt.setString(3, landmarks.get(i));
                                cstmt.setInt(4, currentBalance);
                                cstmt.execute();
                                temp = cstmt.getInt(1);
                                currentBalance -= temp;
                                System.out.println(temp+" current:"+currentBalance);
                        }
                        
                        //suggest Restaurants options
                        restaurants.clear();
                        sql = "SELECT RESTAURANT_NAME,AVERAGE_COST FROM RESTAURANTS WHERE "
                                + "REGION_ID="+id2+" AND AVERAGE_COST*"+DAYS+"<="+currentBalance;
                        System.out.println(sql);
                        if(DAYS>0)
                        {
                            rs = stmt.executeQuery(sql);
                            while(rs.next())
                            {
                                res = new Restaurant();
                                res.setRESTAURANT_NAME(rs.getString(1));
                                res.setAVERAGE_COST(rs.getInt(2));
                                restaurants.add(res);
                                System.out.println("inserted "+res);
                            }
                            cstmt = con.prepareCall("{? = call MINIMUM_SPENT_RESTAURANT(?,?,?)}");
                            cstmt.registerOutParameter(1, Types.INTEGER);
                            cstmt.setInt(2, id2);
                            cstmt.setInt(3, currentBalance);
                            cstmt.setInt(4, DAYS);
                            cstmt.execute();
                            temp = cstmt.getInt(1);
                            currentBalance -= temp;
                            System.out.println(temp+" current:"+currentBalance);
                        
                        }
                } catch (SQLException e) {
                    System.out.println("No data found");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public boolean storePln()
    {
        System.out.println("In storePlan");
        //selectedintratransport = new ArrayList<String>();
        Statement stmt = null,stmt2 = null;
        Connection con = null;
        ResultSet rs = null,rs1 = null;
        String value,sql;
        int id1=0, id2=0,uid=0,interid=0,intraid=0,hotelid=0,restaurantid=0,planid=0,lid=0;

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
            if (con != null) {
                stmt = con.createStatement();
                stmt2 = con.createStatement();
                try {
                    //GET PLAN ID
                    sql = "SELECT PLAN_ID_VAL.NEXTVAL FROM DUAL";
                    System.out.println(sql);
                    rs = stmt.executeQuery(sql);
                    if(rs.next())
                    {
                        planid = rs.getInt(1);
                        System.out.println("Got plan id: "+planid);
                    }
                    //GET LOCATION ID
                    sql="SELECT REGION_ID FROM REGION WHERE REGION_NAME='"
                    +LOCATION+"'";
                    System.out.println(sql);
                    rs = stmt.executeQuery(sql);
                    if(rs.next())
                    {
                        id1 = rs.getInt(1);
                        System.out.println("Got location id: "+id1);
                    }
                    //GET DESTINATION ID
                    sql="SELECT REGION_ID FROM REGION WHERE REGION_NAME='"
                    +DESTINATION+"'";
                    System.out.println(sql);
                    rs = stmt.executeQuery(sql);
                    if(rs.next())
                    {
                        id2 = rs.getInt(1);
                        System.out.println("Got DESTINATION id: "+id2);
                    }
                    
                    //get User id
                    sql = "SELECT USER_ID FROM USER_INFO WHERE USER_NAME ='"+pc.getUser()+"'";
                    System.out.println(sql);
                    rs = stmt.executeQuery(sql);
                    
                    if(rs.next())
                    {
                        uid = rs.getInt(1);
                        System.out.println("user id: "+uid);
                    }
                    //store used inter region
                    sql = "SELECT INTER_REGION_TRANSPORT_ID FROM INTER_REGION_TRANSPORT WHERE FROM_DIST="+id1+" AND TO_DIST="
                            +id2+" AND VEHICLE_NAME ='"+selectedintertransport.getVEHICLE_NAME()+"'";
                    System.out.println(sql);
                    rs = stmt.executeQuery(sql);
                    if(rs.next())
                    {
                        
                        interid = rs.getInt(1);
                        System.out.println("inter region id: "+interid);
                    }
                    sql = "INSERT INTO USED_INTER_REGION_TRANSPORT VALUES("+uid+","+interid+",0,'')";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    //store hotels
                    //find hotel id
                    sql = "SELECT PLACE_ID FROM HOTELS_AND_RESORTS WHERE REGION_ID="+id2+" AND HOTEL_NAME='"
                            +selectedhotel.getNAME()+ "'";
                    System.out.println(sql);
                    rs = stmt.executeQuery(sql);
                    if(rs.next())
                    {
                        hotelid = rs.getInt(1);
                        System.out.println("hotel id: "+hotelid);
                    }
                    //now store
                    sql ="INSERT INTO STAYED_AT VALUES("+uid+","+id2+","+hotelid+",'','',0)";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    
                    //storing restaurants
                    sql = "SELECT RESTAURANT_ID FROM RESTAURANTS WHERE REGION_ID="+id2+" AND RESTAURANT_NAME='"
                            +selectedrestaurant.getRESTAURANT_NAME()+ "'";
                    System.out.println(sql);
                    rs = stmt.executeQuery(sql);
                    if(rs.next())
                    {
                        restaurantid = rs.getInt(1);
                        System.out.println("restaurant id: "+restaurantid);
                    }
                    //now store
                    sql ="INSERT INTO VISITED_RESTAURANTS VALUES("+uid+","+restaurantid+","+0+",'',"+id2+")";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    
                    //store in plan
                    sql ="INSERT INTO PLANS VALUES("+planid+","+uid+","+hotelid+","+restaurantid+","+interid+")";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    
                    //storing used intra region transport
                    System.out.println(selectedintratransport.size());
                    for(int i=0;i<selectedintratransport.size();i++)
                    {
                        sql = "SELECT INTRA_REGION_TRANSPORT_ID FROM INTRA_REGION_TRANSPORT WHERE FROM_DEST='"+DESTINATION+"' AND MODE_OF_TRANSPORT='"
                            +selectedintratransport.get(i).getMODE_OF_TRANSPORT()+ "' AND TO_DEST='"+intratransportMode.get(selectedintratransport.get(i).getMODE_OF_TRANSPORT())+"'";
                        System.out.println(sql);
                        rs = stmt.executeQuery(sql);
                        if(rs.next())
                        {
                            intraid = rs.getInt(1);
                            System.out.println("intra id: "+intraid);
                        }
                        //now store
                        sql ="INSERT INTO USED_INTRA_REGION_TRANSPORT VALUES("+uid+","+intraid+","+0+",'',"+planid+")";
                        System.out.println(sql);
                        stmt.executeUpdate(sql);
  
                        
                    }
                    //store landmarks
                        System.out.println(selectedlandmarks.size());
                        for(int i=0;i<selectedlandmarks.size();i++)
                        {
                            sql = "SELECT LANDMARK_ID FROM LANDMARKS WHERE REGION_ID="+id2+" AND LANDMARK_NAME='"
                                     +selectedlandmarks.get(i)+"'";
                            System.out.println(sql);
                            rs = stmt.executeQuery(sql);
                            if(rs.next())
                            {
                                lid = rs.getInt(1);
                                System.out.println("landmark id: "+lid);
                            }
                            //now store
                            sql ="INSERT INTO VISITED_LANDMARKS VALUES("+uid+","+lid+","+0+",'',"+planid+")";
                            System.out.println(sql);
                            stmt.executeUpdate(sql);
                        }
                    
                } catch (SQLException e) {
                    System.out.println("No data found");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    
    public void onrate(NodeSelectEvent event){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succesful!", "Account Created!"));
                    
    }
    
    public void Preview()
    {
        //add interregion cost
        totalSpent = 0;
        totalSpent += selectedintertransport.getEXPENSE();
        //add intraregion cost
        for(int i=0;i<selectedintratransport.size();i++)
        {
            totalSpent += selectedintratransport.get(i).getEXPENSE();
        }
        //add restaurant cost
        totalSpent += selectedrestaurant.getAVERAGE_COST();
        //add hotel cost
        totalSpent += (selectedhotel.getMAXIMUM_COST() + selectedhotel.getMINIMUM_COST())/2;
    }
    
    public void insert() throws SQLException {

        /*  Statement stmt = null;
    Connection con = null;
    ResultSet rs = null;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
    String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
    con = DriverManager.getConnection(dbURL2, user, password);
    System.out.print("In insert function "+getRegionID()+getRegionName());
            if (con!= null) {
                System.out.println("Connected with connection #2");
                stmt = con.createStatement();
            try {
            rs = stmt.executeQuery("SELECT * FROM REGION WHERE REGION_NAME = '"+getRegionName()+"'");
                if(!rs.next())
                {
                stmt.executeUpdate("INSERT INTO REGION (REGION_ID, REGION_NAME)"+" VALUES(REGION_ID_VAL.NEXTVAL"+", "+"'"+getRegionName()+"')");
                System.out.print("In insert function "+getRegionID()+getRegionName());
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
            }  */
    }

    public void delete() throws SQLException {
        /*  Statement stmt = null;
    Connection con = null;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
    String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
    con = DriverManager.getConnection(dbURL2, user, password);
    System.out.print("In delete function "+getRegionName());
    if (con!= null) {
            System.out.println("Connected with connection #2");
            stmt = con.createStatement();
            try {
            stmt.executeUpdate("DELETE FROM REGION WHERE REGION_NAME = '"+getDelRegionName()+"'");
            System.out.print("In delete function "+getRegionName());
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
            }  */

    }

    /**
     * @return the restaurants
     */
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    /**
     * @param restaurants the restaurants to set
     */
    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    /**
     * @return the selectedrestaurant
     */
    public Restaurant getSelectedrestaurant() {
        return selectedrestaurant;
    }

    /**
     * @param selectedrestaurant the selectedrestaurant to set
     */
    public void setSelectedrestaurant(Restaurant selectedrestaurant) {
        this.selectedrestaurant = selectedrestaurant;
    }

    /**
     * @return the intratransport
     */
    public List<IntraRegionTransport> getIntratransport() {
        return intratransport;
    }

    /**
     * @param intratransport the intratransport to set
     */
    public void setIntratransport(List<IntraRegionTransport> intratransport) {
        this.intratransport = intratransport;
    }

    /**
     * @return the selectedintratransport
     */
    public List<IntraRegionTransport> getSelectedintratransport() {
        return selectedintratransport;
    }

    /**
     * @param selectedintratransport the selectedintratransport to set
     */
    public void setSelectedintratransport(List<IntraRegionTransport> selectedintratransport) {
        this.selectedintratransport = selectedintratransport;
    }

}