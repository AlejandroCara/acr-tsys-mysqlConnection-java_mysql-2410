package Ej3;

import java.sql.SQLException;

import MyConnectionManager.MyConnectionManager;

public class Ej3App {

	public static void main(String[] args) {
		
		MyConnectionManager cm = new MyConnectionManager();
		String dbName = "java_almacenes";
		String user = "root";
		String pwd = "root";
		String query = "";
		java.sql.ResultSet results;

		//Conectarse al servidor mysql y crear la base de datos
		cm.startConnection(user, pwd);
		cm.createDB(dbName);
		
		//Conectarse a la base de datos
		cm.startConnectionDB(user, pwd, dbName);
		
		//Crear la tabla almacen
		query = "create table almacen("
				+ "codigo int auto_increment,"
				+ "lugar varchar(50),"
				+ "capacidad int,"
				+ "primary key(codigo)"
				+ ");";
		cm.exequteQuery(query);
		
		
		//Crear la tabla caja
		query = "create table caja("
				+ "num_referencia char(5),"
				+ "contenido varchar(100),"
				+ "valor int,"
				+ "almacen int,"
				+ "primary key (num_referencia),"
				+ "foreign key (almacen) references almacen(codigo)"
				+ "on delete cascade on update cascade"
				+ ");";
		cm.exequteQuery(query);
		
		
		//Introducir almacenes
		query = "insert into almacen(lugar, capacidad) values ('Reus', 30),"
															+"('Tarragona', 50),"
															+"('Barcelona', 120),"
															+"('Lleida', 75),"
															+"('Salou', 40)";
		cm.exequteQuery(query);
		
		//Introducir cajas
		query = "insert into caja values('6543', 'Arroz', 60, 1),"
									  +"('7654', 'Lentejas', 80, 1),"
									  +"('4321', 'Judías', 130, 2),"
									  +"('5432', 'Zanahorias', 200, 3),"
									  +"('4567', 'Patatas', 477, 4),"
									  +"('6789', 'Arroz', 60, 5)";
		cm.exequteQuery(query);
		

		System.out.println("\n\n");
		
		//Consultar todos los cajas
		query = "select *"
			  + "from caja;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("Numero Referencia: " + results.getString("num_referencia"));
				System.out.println("Contenido: " + results.getString("contenido"));
				System.out.println("Valor: " + results.getString("valor"));
				System.out.println("Almacen: " + results.getString("almacen"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		
		System.out.println("\n\n");
		System.out.println("\n\n");
		
		//Consultar todos los almacenes
		query = "select *"
			  + "from almacen;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("Codigo: " + results.getString("codigo"));
				System.out.println("Lugar: " + results.getString("lugar"));
				System.out.println("Capacidad: " + results.getString("capacidad"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		
		
		//Consultar las cajas de los almacenes que tengan una capacidad mayoy de 60
		query = "select * "
			  + "from caja "
			  + "where almacen in ( select codigo "
			  + "					from almacen "
			  + "					where capacidad > 60);";
		results = cm.getValues(query);
		
		System.out.println("\n\n\n");
		System.out.println("Las cajas de los almacenes que tengan una capacidad mayoy de 60\n");
		try {
			while(results.next()){
				System.out.println("Numero Referencia: " + results.getString("num_referencia"));
				System.out.println("Contenido: " + results.getString("contenido"));
				System.out.println("Valor: " + results.getString("valor"));
				System.out.println("Almacen: " + results.getString("almacen"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		cm.closeConnection();
	}

}
