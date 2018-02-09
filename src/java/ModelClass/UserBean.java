package ModelClass;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.Calendar;
import java.util.GregorianCalendar;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean  implements Serializable{

public List<User> getUserList() throws ClassNotFoundException
{
List<User> list = new ArrayList<>();
/*try
{
for(int i=0;i<100;i++)
{
User usr = new User();
usr.setUserID(i);
usr.setName("name");
usr.setAddress("address");
usr.setCreated_date(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
list.add(usr);
} 
}
catch(Exception e)
{
}
return list;
}*/
/*
PreparedStatement ps = null;
Connection con = null;
ResultSet rs = null;
try
{
Class.forName("com.mysql.jdbc.Driver");
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/record", "root", "root");
String sql = "select * from region";
ps= con.prepareStatement(sql); 
rs= ps.executeQuery(); 
while (rs.next())
{
User usr = new User();
usr.setUserID(rs.getLong("userId"));
usr.setName(rs.getString("name"));
usr.setAddress(rs.getString("address"));
usr.setCreated_date(rs.getDate("created_date"));
list.add(usr);
} 
}
catch(Exception e)
{
e.printStackTrace();
}
finally
{
try
{
con.close();
ps.close();
}
catch(Exception e)
{
e.printStackTrace();
}
}*/
return list;
}
}