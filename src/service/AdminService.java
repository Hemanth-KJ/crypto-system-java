package service;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminService {



public static String getUsers(){

StringBuilder data=
new StringBuilder();

try{

Connection con=
DBConnection.getConnection();

Statement st=
con.createStatement();

ResultSet rs=

st.executeQuery(

"SELECT id,username,role FROM users ORDER BY id"

);



while(rs.next()){

data.append(

"ID : "

+

rs.getInt("id")

+

" | USER : "

+

rs.getString("username")

+

" | ROLE : "

+

rs.getString("role")

+

"\n"

);

}

con.close();

}

catch(Exception e){

return e.getMessage();

}

return data.toString();

}



public static boolean deleteUser(
int id
){

try{

if(
id==
AuthService.currentUser
){

return false;

}

Connection con=
DBConnection.getConnection();

PreparedStatement ps=

con.prepareStatement(

"DELETE FROM users WHERE id=?"

);

ps.setInt(
1,
id
);

int rows=

ps.executeUpdate();

con.close();

return rows>0;

}

catch(Exception e){

return false;

}

}



public static int totalUsers(){

try{

Connection con=
DBConnection.getConnection();

Statement st=
con.createStatement();

ResultSet rs=

st.executeQuery(

"SELECT COUNT(*) total FROM users"

);

if(rs.next()){

int count=

rs.getInt(
"total"
);

con.close();

return count;

}

}

catch(Exception e){}

return 0;

}



public static int totalFiles(){

try{

Connection con=
DBConnection.getConnection();

Statement st=
con.createStatement();

ResultSet rs=

st.executeQuery(

"SELECT COUNT(*) total FROM files"

);

if(rs.next()){

int count=

rs.getInt(
"total"
);

con.close();

return count;

}

}

catch(Exception e){}

return 0;

}



public static int totalHistory(){

try{

Connection con=
DBConnection.getConnection();

Statement st=
con.createStatement();

ResultSet rs=

st.executeQuery(

"SELECT COUNT(*) total FROM history"

);

if(rs.next()){

int count=

rs.getInt(
"total"
);

con.close();

return count;

}

}

catch(Exception e){}

return 0;

}



public static void refresh(){

getUsers();

totalUsers();

totalFiles();

totalHistory();

}

}