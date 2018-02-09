package ModelClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RateEvent;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "PlanBean")
@RequestScoped
public class PlanBean {

    private final DBConnection dbconnection = new DBConnection();
    private static int id=0;
    static private Plan plan;
    private static Hotels hotel = new Hotels();
    private static Restaurant restaurant;
    private static InterRegionTransport inter;
    private Date date;
    private static int rating3;
    private static int ratingres ;
    private static int ratingtrans;
    static ArrayList<LandMark> lands ;
    static ArrayList<Emergency> emergency ;
    static ArrayList<Market> market ;
    static ArrayList<IntraRegionTransport> intra ;
    
    public void onRowSelect(SelectEvent event) {
        setLands((ArrayList<LandMark>) ((Plan) event.getObject()).getSelectedlandmarks());
        setEmergency((ArrayList<Emergency>) ((Plan) event.getObject()).getEmergency());
        setMarket((ArrayList<Market>) ((Plan) event.getObject()).getMarket());
        setIntra((ArrayList<IntraRegionTransport>) ((Plan) event.getObject()).getSelectedintratransport());
        String str = ((Plan) event.getObject()).getINTER_REGION_NAME();
        InterRegionTransport inter = new InterRegionTransport();
        inter.setVEHICLE_NAME(str);
        setInter(inter);
        
        Hotels hotel = new Hotels();
        str = ((Plan) event.getObject()).getHOTEL_NAME();
        hotel.setNAME(str);
        setHotel(hotel);
        System.out.print(getHotel().getNAME());
       // setHotel((Hotels)((Plan) event.getObject()).getSelectedhotel());
       
        Restaurant rst = new Restaurant();
        str = ((Plan) event.getObject()).getRESTAURANT_NAME();
        rst.setRESTAURANT_NAME(str);
        setRestaurant(rst);
        //setRestaurant(((Plan) event.getObject()).getSelectedrestaurant());
    }

    public int getRatingres() {
        return ratingres;
    }

    public void setRatingres(int ratingres) {
        PlanBean.ratingres = ratingres;
    }

    public int getRatingtrans() {
        return ratingtrans;
    }

    public void setRatingtrans(int ratingtrans) {
        PlanBean.ratingtrans = ratingtrans;
    }

    public ArrayList<LandMark> getLands() {
        return lands;
    }

    public void setLands(ArrayList<LandMark> lands) {
        this.lands = lands;
    }

    public ArrayList<Emergency> getEmergency() {
        return emergency;
    }

    public void setEmergency(ArrayList<Emergency> emergency) {
        this.emergency = emergency;
    }

    public ArrayList<Market> getMarket() {
        return market;
    }

    public void setMarket(ArrayList<Market> market) {
        this.market = market;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    

    public InterRegionTransport getInter() {
        return inter;
    }

    public void setInter(InterRegionTransport inter) {
        this.inter = inter;
    } 
    
    public Hotels getHotel() {
        return hotel;
    }

    public void setHotel(Hotels hotel) {
        this.hotel = hotel;
    }

    public ArrayList<IntraRegionTransport> getIntra() {
        return intra;
    }

    public void setIntra(ArrayList<IntraRegionTransport> intra) {
        this.intra = intra;
    }

    public int getRating3() {
        return rating3;
    }

    public void setRating3(int rating3) {
        this.rating3 = rating3;
    }
    
    public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue() + getHotel().getNAME());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onrateHotel(RateEvent rateEvent) throws ClassNotFoundException,SQLException {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        String sql;
        Statement stmt = null;
        Statement stmt1 = null;
        Connection con = null;
        ResultSet rs1 = null;
        System.out.print("In RegionBean");
        
         DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
            if(con!=null){
            stmt = con.createStatement();
            sql = "SELECT USER_ID FROM USER_INFO WHERE USER_NAME = '" + PageController.user+ "'";
                    rs1 = stmt.executeQuery(sql);
                    int id=0;
                    if(rs1.next()){
                        id = rs1.getInt(1);
                        System.out.print(id);
                    }
            sql = "UPDATE STAYED_AT SET RATING = " + ((Integer) rateEvent.getRating()).intValue() + " WHERE USER_ID = " + id + " AND PLACE_ID = " + plan.getHOTEL_ID() ;
            System.out.print(sql);
            try{
            stmt.executeUpdate(sql);
            }
            catch(SQLException e)
            {
                System.out.print("not worked");
            }
            finally{
            try {
                con.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        }
            
    }
    
    public void onrateRestaurant(RateEvent rateEvent) throws ClassNotFoundException,SQLException {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        String sql;
        Statement stmt = null;
        Statement stmt1 = null;
        Connection con = null;
        ResultSet rs1 = null;
        System.out.print("In RegionBean");
        
         DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
            if(con!=null){
            stmt = con.createStatement();
            sql = "SELECT USER_ID FROM USER_INFO WHERE USER_NAME = '" + PageController.user+ "'";
                    rs1 = stmt.executeQuery(sql);
                    int id=0;
                    if(rs1.next()){
                        id = rs1.getInt(1);
                        System.out.print(id);
                    }
            sql = "UPDATE VISITED_RESTAURANTS SET RATING = " + ((Integer) rateEvent.getRating()).intValue() + " WHERE USER_ID = " + id + " AND RESTAURANT_ID = " + plan.getRESTAURANT_ID() ;
            System.out.print(sql);
            try{
            stmt.executeUpdate(sql);
            }
            catch(SQLException e)
            {
                System.out.print("not worked");
            }
            finally{
            try {
                con.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        }
            
    }
    
    public void onrateInter(RateEvent rateEvent) throws ClassNotFoundException,SQLException {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        String sql;
        Statement stmt = null;
        Statement stmt1 = null;
        Connection con = null;
        ResultSet rs1 = null;
        System.out.print("In RegionBean");
        
         DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
            if(con!=null){
            stmt = con.createStatement();
            sql = "SELECT USER_ID FROM USER_INFO WHERE USER_NAME = '" + PageController.user+ "'";
                    rs1 = stmt.executeQuery(sql);
                    int id=0;
                    if(rs1.next()){
                        id = rs1.getInt(1);
                        System.out.print(id);
                    }
            sql = "UPDATE USED_INTER_REGION_TRANSPORT SET RATING = " + ((Integer) rateEvent.getRating()).intValue() + " WHERE USER_ID = " + id + " AND INTER_REGION_TRANSPORT_ID = " + plan.getINTER_REGION_TRANSPORT_ID() ;
            System.out.print(sql);
            try{
            stmt.executeUpdate(sql);
            }
            catch(SQLException e)
            {
                System.out.print("not worked");
            }
            finally{
            try {
                con.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        }
            
    }
    
    
    public void demo(int i)
    {
        System.out.print(getHotel().getNAME()+ "  " + i);
    }

    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
    
    public List<Plan> getPlanList() throws ClassNotFoundException, SQLException {
        List<Plan> list = new ArrayList<>();
        List<LandMark> lands = new ArrayList<>();
        List<Emergency> emergency = new ArrayList<>();
        List<Market> market = new ArrayList<>();
        List<IntraRegionTransport> intra = new ArrayList<>();
        
        String sql;
        Statement stmt = null;
        Statement stmt1 = null;
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        System.out.print("In RegionBean");

        try {

            //Class.forName("oracle.jdbc.driver.OracleDriver"); 
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
            if (con != null) {
                System.out.println("Connected with connection #2 in regionbean");
                stmt = con.createStatement();
                stmt1 = con.createStatement();
                try {
                    sql = "SELECT USER_ID FROM USER_INFO WHERE USER_NAME = '" + PageController.user+ "'";
                    System.out.print(sql);
                    rs1 = stmt1.executeQuery(sql);
                    if(rs1.next())
                        id = rs1.getInt(1);
                    sql = "SELECT * FROM PLANS WHERE USER_ID ="+id;
                    System.out.print(sql);
                    rs = stmt.executeQuery("SELECT * FROM PLANS WHERE USER_ID ="+id);
                    while (rs.next()) {
                        lands.clear();
                        emergency.clear();
                        market.clear();
                        intra.clear();
                        Plan plan = new Plan();
                        plan.setPLAN_ID(rs.getInt(1));
                        plan.setUSER_ID(rs.getInt(2));
                        plan.setHOTEL_ID(rs.getInt(3));
                        plan.setRESTAURANT_ID(rs.getInt(4));
                        plan.setINTER_REGION_TRANSPORT_ID(rs.getInt(5));
                        sql = "SELECT H.HOTEL_NAME , S.RATING FROM HOTELS_AND_RESORTS H JOIN STAYED_AT S ON (S.PLACE_ID = H.PLACE_ID AND S.PLACE_ID = " + plan.getHOTEL_ID() + " AND S.USER_ID= "  +id + " ) ";
                        System.out.print(sql);
                        rs1 = stmt1.executeQuery(sql);
                        if(rs1.next()){
                        plan.setHOTEL_NAME(rs1.getString(1));
                        setRating3(rs1.getInt(2));
                        }
                        
                        
                        sql =  " SELECT H.RESTAURANT_NAME , S.RATING FROM RESTAURANTS H JOIN VISITED_RESTAURANTS S ON (S.RESTAURANT_ID = H.RESTAURANT_ID AND S.RESTAURANT_ID = " + plan.getRESTAURANT_ID() + " AND S.USER_ID=" +id + " ) ";
                        System.out.print(sql);
                        rs1 = stmt1.executeQuery(sql);
                        if(rs1.next()){
                        plan.setRESTAURANT_NAME(rs1.getString(1));
                        setRatingres(rs1.getInt(2));
                        }
                        sql ="SELECT I.VEHICLE_NAME , U.RATING FROM INTER_REGION_TRANSPORT I JOIN USED_INTER_REGION_TRANSPORT U ON ( I.INTER_REGION_TRANSPORT_ID = U.INTER_REGION_TRANSPORT_ID AND " +
                         "U.INTER_REGION_TRANSPORT_ID=" + plan.getINTER_REGION_TRANSPORT_ID() + " AND U.USER_ID= " +id + " ) ";
                        System.out.print(sql);
                        rs1 = stmt1.executeQuery(sql);
                        if(rs1.next()){
                        plan.setINTER_REGION_NAME(rs1.getString(1));
                        setRatingtrans(rs1.getInt(2));
                        }
                        //add intra to the list
                        sql = "SELECT I.MODE_OF_TRANSPORT,I.FROM_DEST,I.TO_DEST FROM USED_INTRA_REGION_TRANSPORT U,INTRA_REGION_TRANSPORT I WHERE U.PLAN_ID ="+plan.getPLAN_ID()
                                +" AND USER_ID="+id+" AND I.INTRA_REGION_TRANSPORT_ID = U.INTRA_REGION_TRANSPORT_ID";
                        System.out.print(sql);
                        rs1 = stmt1.executeQuery(sql);
                        while(rs1.next())
                        {
                            IntraRegionTransport temp1 = new IntraRegionTransport();
                            temp1.setMODE_OF_TRANSPORT(rs1.getString(1));
                            temp1.setFROM_DEST(rs1.getString(2));
                            temp1.setTO_DEST(rs1.getString(3));
                            
                            intra.add(temp1);
                        }
                        plan.setSelectedintratransport(intra);
                        //add landmarks
                        sql = "SELECT L.LANDMARK_NAME,V.RATING FROM LANDMARKS L, VISITED_LANDMARKS V WHERE V.PLAN_ID="
                                +plan.getPLAN_ID()+" AND V.USER_ID="+id+" AND V.LANDMARK_ID = L.LANDMARK_ID";
                        System.out.print(sql);
                        rs1 = stmt1.executeQuery(sql);
                        while(rs1.next())
                        {
                            LandMark temp = new LandMark();
                            temp.setLANDMARK_NAME(rs1.getString(1));
                            lands.add(temp);
                        }
                        plan.setSelectedlandmarks(lands);
                        
                        //add visited emergency
                        sql = "SELECT E.EMERGENCY_NAME FROM EMERGENCY_SERVICES E, AVAILED_EMERGENCY A WHERE A.PLAN_ID="
                                +plan.getPLAN_ID()+" AND A.USER_ID="+id+" AND A.EMERGENCY_ID = E.EMERGENCY_ID";
                        System.out.print(sql);
                        rs1 = stmt1.executeQuery(sql);
                        while(rs1.next())
                        {
                            Emergency temp = new Emergency();
                            temp.setEMERGENCY_NAME(rs1.getString(1));
                            emergency.add(temp);
                        }
                        plan.setEmergency(emergency);
                        //add visited shopping malls
                        sql = "SELECT S.SHOP_NAME,V.VISIT_DATE FROM SHOPPING S, VISITED_MARKETS V WHERE V.PLAN_ID="
                                +plan.getPLAN_ID()+" AND V.USER_ID="+id+" AND V.SHOPPING_ID = S.SHOPPING_ID";
                        System.out.print(sql);
                        rs1 = stmt1.executeQuery(sql);
                        while(rs1.next())
                        {
                            Market temp = new Market();
                            temp.setMARKET_NAME(rs1.getString(1));
                            temp.setVISIT_DATE(rs1.getDate(2));
                            market.add(temp);
                        }
                        plan.setMarket(market);
                        /*region.setRegionID(rs.getInt(1));
                        region.setRegionName(rs.getString(2));*/
                        list.add(plan);
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

        return list;
    }
    
    public void demo() throws ParseException {

        String dim = null;
        
        //date to string
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
        try {
            dim = sdfr.format(date);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        setDate(sdfr.parse(dim));

        FacesMessage msg = new FacesMessage("Car Selected", dim + "  p  " + "  p  " + getDate());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}