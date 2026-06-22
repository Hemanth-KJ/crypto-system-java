package service;

import crypto.AESUtil;

import database.DBConnection;

import java.sql.*;

import java.io.File;

public class CryptoService {

public static String encrypt(
String path,
String password
){

try{

File file=
new File(path);

String output=

AESUtil.encrypt(
path,
password
);

Connection con=
DBConnection.getConnection();

PreparedStatement ps=

con.prepareStatement(

"INSERT INTO files(filename,encrypted_path,secret_key,decrypted_path) VALUES(?,?,?,?)"

);

ps.setString(
1,
file.getName()
);

ps.setString(
2,
output
);

ps.setString(
3,
password
);

ps.setString(
4,
"Pending"
);

ps.executeUpdate();

return
"Encrypted";

}

catch(Exception e){

return e.getMessage();

}

}



public static String decryptByPath(
String path,
String password
){

try{

String output=

AESUtil.decrypt(
path,
password
);

return
"Decrypted → " + output;

}

catch(Exception e){

return
"Wrong Password";

}

}

}