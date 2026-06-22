package service;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthService {

public static int currentUser=0;

public static String login(
String user,
String pass
){

try{

Connection con=
DBConnection.getConnection();

PreparedStatement ps=
con.prepareStatement(

"SELECT id,role FROM users WHERE username=? AND password=?"

);

ps.setString(
1,
user.trim()
);

ps.setString(
2,
pass.trim()
);

ResultSet rs=
ps.executeQuery();

if(rs.next()){

currentUser=
rs.getInt("id");

String role=
rs.getString("role");

con.close();

return role;

}

con.close();

}

catch(Exception e){

e.printStackTrace();

}

return "INVALID";

}



public static boolean register(
String user,
String pass
){

try{

Connection con=
DBConnection.getConnection();



PreparedStatement check=
con.prepareStatement(

"SELECT id FROM users WHERE username=?"

);

check.setString(
1,
user.trim()
);

ResultSet rs=
check.executeQuery();

if(rs.next()){

con.close();

return false;

}



PreparedStatement ps=
con.prepareStatement(

"INSERT INTO users(username,password,role) VALUES(?,?,?)"

);

ps.setString(
1,
user.trim()
);

ps.setString(
2,
pass.trim()
);

ps.setString(
3,
"USER"
);

ps.executeUpdate();

con.close();

return true;

}

catch(Exception e){

e.printStackTrace();

return false;

}

}



public static void logout(){

currentUser=0;

}

}