package service;

import database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HistoryService {



public static String getHistory(){

StringBuilder history=
new StringBuilder();

try{

Connection con=
DBConnection.getConnection();

Statement st=
con.createStatement();

ResultSet rs=

st.executeQuery(

"SELECT h.id,u.username,h.action_type,h.file_name,h.created_at " +

"FROM history h " +

"LEFT JOIN users u ON h.user_id=u.id " +

"ORDER BY h.id DESC"

);



boolean found=
false;



while(rs.next()){

found=true;

history.append(

"ID : "

+

rs.getInt("id")

+

" | USER : "

+

rs.getString("username")

+

" | ACTION : "

+

rs.getString("action_type")

+

" | FILE : "

+

rs.getString("file_name")

+

" | DATE : "

+

rs.getString("created_at")

+

"\n"

);

}



if(!found){

history.append(

"No History Available"

);

}



con.close();

}

catch(Exception e){

history.append(

"History Error : "

+

e.getMessage()

);

}

return history.toString();

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

int total=

rs.getInt(
"total"
);

con.close();

return total;

}

}

catch(Exception e){}

return 0;

}

}