package general;
import java.sql.*;
public class ConexionBD {
private static final String HOST="localhost";
private static final String PORT="3306";
private static final String USER="root";
private static final String PASSW="";
private static final String BD="peliculas";

public static Connection obtenerConexion(){
	Connection conn=null;
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
	}catch(ClassNotFoundException e){
		e.printStackTrace();
	}
	
	try {
		conn=DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORT+"/"+BD,USER,PASSW);	                      
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return conn;
}

}
