package javaSql;
import java.sql.*;
import javax.swing.*;
public class SqlLiteConnection {

	
	Connection conn=null;
	
	public static Connection dbConnector()
	{
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:/home/benti/workspace/BankData.sqlite");
			JOptionPane.showMessageDialog(null, "Connection Sucessfull");
			
			return conn;
					
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}
	
}
