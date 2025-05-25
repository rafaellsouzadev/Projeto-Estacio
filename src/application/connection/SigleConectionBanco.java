package application.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SigleConectionBanco {
	
	private static final String url = "jdbc:postgresql://localhost:5432/trabalho_estacio?autoReconect=true";
	private static final String user = "postgres";
	private static final String senha = "?Kill4r3e2w1q#";
	private static Connection connection = null;
	
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	public SigleConectionBanco() {
		conectar();
	}
	
	private static void conectar() {
		
		try {
			
			if (connection == null) {
				Class.forName("org.postgresql.Driver"); 
				connection = DriverManager.getConnection(url, user, senha);
				connection.setAutoCommit(false); 
				
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

}
