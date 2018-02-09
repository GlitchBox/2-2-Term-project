package ModelClass;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean(name = "roleselection")
@SessionScoped
public class PageController implements Serializable {

    private String role;
    static  String user;
    private String password;
    private String newuser;
    private String newpassword;
    private String cell;
    private String email;
    private String newcell;
    private String newemail;

    public String getNewcell() {
        return newcell;
    }

    public void setNewcell(String newcell) {
        this.newcell = newcell;
    }

    public String getNewemail() {
        return newemail;
    }

    public void setNewemail(String newemail) {
        this.newemail = newemail;
    }
    private DBConnection dbconnection = new DBConnection();

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    
    public String getNewuser() {
        return newuser;
    }

    public void setNewuser(String newuser) {
        this.newuser = newuser;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public DBConnection getDbconnection() {
        return dbconnection;
    }

    public void setDbconnection(DBConnection dbconnection) {
        this.dbconnection = dbconnection;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        System.out.println(role);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String validate() throws SQLException {
        String flag = "none";
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
        con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
        if (con != null) {
            stmt = con.createStatement();
            try {
                rs = stmt.executeQuery("SELECT ROLE FROM USER_INFO WHERE USER_NAME = '" + getUser() + "' AND PWD = '" + getPassword() + "'");
                if (rs.next() && rs.getString(1).equals(getRole())) {
                    flag = rs.getString(1);
                    System.out.println("found");
                } else {
                    flag = "none";
                    System.out.println("not found");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "No Data Found!"));
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
        return flag;

    }
    
    public String newentry() throws SQLException
    {
         String flag = "found";
         Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        if(getRole().equals("Admin")){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "You Can not be an admin!"));
                        
            return flag;
        }
        
        String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
        con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
        if (con != null) {
            stmt = con.createStatement();
            try {
                rs = stmt.executeQuery("SELECT ROLE FROM USER_INFO WHERE USER_NAME = '" + getNewuser() + "' AND PWD = '" + getNewpassword() + "'");
                if (rs.next() && rs.getString(1).equals(getRole())) {
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "Existing!"));
                } else {
                    String sql = "INSERT INTO USER_INFO"+" VALUES(USER_INFO_VAL.NEXTVAL"+", "+"'"+getNewuser()+"','"+getNewcell()+"','"+getNewemail()+"','"+getNewpassword() +"','User')";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succesful!", "Account Created!"));
                    flag = "success";
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
        return flag;
    }

}
