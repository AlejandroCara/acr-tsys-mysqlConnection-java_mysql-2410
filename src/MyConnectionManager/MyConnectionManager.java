package MyConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnectionManager {
	
	private Connection connection;
	
	public MyConnectionManager() {
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	//Inicia la conexión con el servidor mysql y la almacena en connection
	public void startConnection(String user, String pwd) {
		//Intenta cerrar una posible anterior conexión 
		this.closeConnection();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", user, pwd);
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}

	}
	
	//Inicia la conexión con la base de datos especifica y la almacena en connection
	public void startConnectionDB(String user, String pwd, String bbdd) {
		//Intenta cerrar una posible anterior conexión 
		this.closeConnection();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bbdd, "root", "root");
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}

	}
	
	//Crea la base de datos con el nombre introducido por parametro
	public void createDB(String dbName) {
		try {
			//Borrar la base de datos si existe
			String query = "drop database if exists " + dbName;
			Statement st = this.connection.createStatement();	
			st.executeUpdate(query);
			
			//Creara la base de datos
			query = "create database " + dbName;
			st = this.connection.createStatement();	
			st.executeUpdate(query);
			System.out.println("Base de datos " + dbName + " creada.");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	//Intenta cerrar la conexión en caso de que exista
	public void closeConnection() {
		
		if(this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		} 
	}
	
	/**
	 * Metodo para ejecutar querys que no devuelven resultado como CREATE y INSERT
	 * 
	 * @param  Query que se ejecutará sobre el servidor
	 * 
	 * @return ninguno
	 * */
	public void exequteQuery(String inQuery) {
		try {
			Statement st = this.connection.createStatement();
			st.executeUpdate(inQuery);
			System.out.println("Query ejecutada exitosamente.");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	//Devuelve un resultset de los valores leidos de la base de datos
	public java.sql.ResultSet getValues(String inQuery){
		java.sql.ResultSet results = null;
		try {
			Statement st = this.connection.createStatement();
			results = st.executeQuery(inQuery);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return results;
	}


}
