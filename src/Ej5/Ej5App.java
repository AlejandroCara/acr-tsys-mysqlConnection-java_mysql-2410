package Ej5;

import java.sql.SQLException;

import MyConnectionManager.MyConnectionManager;

public class Ej5App {

	public static void main(String[] args) {

		MyConnectionManager cm = new MyConnectionManager();
		String dbName = "java_directores";
		String user = "root";
		String pwd = "root";
		String query = "";
		java.sql.ResultSet results;

		//Conectarse al servidor mysql y crear la base de datos
		cm.startConnection(user, pwd);
		cm.createDB(dbName);
		
		//Conectarse a la base de datos
		cm.startConnectionDB(user, pwd, dbName);
		

		query = "create table despacho("
				+ "numero int auto_increment,"
				+ "capacidad int not null,"
				+ "primary key(numero)"
				+ ");";
		cm.exequteQuery(query);
		
		

		query = "create table director("
				+ "dni varchar(9),"
				+ "nom_apels varchar(255),"
				+ "dni_jefe varchar(9),"
				+ "despacho int,"
				+ "primary key (dni),"
				+ "foreign key (dni_jefe) references director(dni)"
				+ "on delete cascade on update cascade,"
				+ "foreign key (despacho) references despacho(numero)"
				+ "on delete cascade on update cascade"
				+ ");";
		cm.exequteQuery(query);
		
		

		query = "insert into despacho(numero, capacidad) values (1, 4),"
															  +"(2, 23),"
															  +"(3, 27),"
															  +"(4, 16),"
															  +"(5, 9)";
		cm.exequteQuery(query);
		

		query = "insert into director(dni, nom_apels, dni_jefe, despacho) values  		('7229241K', 'Emmy Flintoff', null, 1),"
																					  +"('1839787K', 'Alanah Kunisch', '7229241K', 2),"
																					  +"('3196684L', 'Emera Ather', '7229241K', 3),"
																					  +"('4530331U', 'Bab Andrus', null, 4),"
																					  +"('2682491T', 'Flori Hansed', '4530331U', 5)";
		cm.exequteQuery(query);
		

		System.out.println("\n\n");
		
		query = "select *"
			  + "from director;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("DNI: " + results.getString("dni"));
				System.out.println("Nombre: " + results.getString("nom_apels"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		
	}

}
