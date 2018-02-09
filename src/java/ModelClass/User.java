    package ModelClass;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean(name="User")
@RequestScoped
public class User  implements Serializable{


private int User_ID;
private String username;
private String CellNo;
private String Email;
private String pwd;
private String role;
private DBConnection dbconnection = new DBConnection();

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }

    public String getUser_Name() {
        return username;
    }

    public void setUser_Name(String User_Name) {
        this.username = User_Name;
    }

    public String getCell_No() {
        return CellNo;
    }

    public void setCell_No(String Cell_No) {
        this.CellNo = Cell_No;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public DBConnection getDbconnection() {
        return dbconnection;
    }

    public void setDbconnection(DBConnection dbconnection) {
        this.dbconnection = dbconnection;
    }

    
    
    public void insert() throws SQLException
{
    
    Statement stmt = null;
    Connection con = null;
    ResultSet rs = null;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
    String dbURL2 = "jdbc:oracle:thin:@localhost:1521:orcl";
    con = DriverManager.getConnection(dbURL2, dbconnection.user, dbconnection.password);
         if (con!= null) {
                stmt = con.createStatement();
            try {
            rs = stmt.executeQuery("SELECT * FROM USER_INFO WHERE USER_NAME = '"+getUser_Name()+"' AND CELL_NO = '" + getCell_No()
                    +"' AND ROLE = '" + getRole()+"'");
                if(!rs.next())
                {
                stmt.executeUpdate("INSERT INTO USER_INFO (USER_ID, USER_NAME , CELL_NO , EMAIL , PWD , ROLE)"+" "
                        + "VALUES(USER_ID_VAL.NEXTVAL"+", '"+getUser_Name()+"' , '"+getCell_No()+"' , '"+getEmail()
                        +"' , '"+getPwd() + "' , '"+getRole()+ "')");
                }
                
                else
                {
                    FacesContext.getCurrentInstance().addMessage("regionList", new FacesMessage("Entry already present"));
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



}