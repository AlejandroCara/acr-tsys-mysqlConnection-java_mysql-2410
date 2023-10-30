package Ej8;

import java.sql.SQLException;

import MyConnectionManager.MyConnectionManager;

public class Ej8App {

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
		

		query = "create table cajeros("
				+ "codigo int,"
				+ "nom_apels VARCHAR(255),"
				+ "primary key(codigo)"
				+ ");";
		cm.exequteQuery(query);
		
		

		query = "create table productos("
				+ "codigo_producto int,"
				+ "nombre VARCHAR(100),"
				+ "precio int,"
				+ "primary key (codigo_producto)"
				+ ");";
		cm.exequteQuery(query);
		
		
		
		query = "create table maquinas_registradoras("
				+ "codigo_maquina int,"
				+ "piso int,"
				+ "primary key (codigo_maquina)"
				+ ");";
		cm.exequteQuery(query);
		
		
		
		query = "create table ventas("
				+ "codigo_cajero int,"
				+ "codigo_producto int,"
				+ "codigo_maquina int,"
				+ "foreign key (codigo_cajero) REFERENCES cajeros (codigo)"
				+ "ON DELETE CASCADE ON UPDATE CASCADE,"
				+ "foreign key (codigo_producto) REFERENCES productos (codigo_producto)"
				+ "ON DELETE CASCADE ON UPDATE CASCADE,"
				+ "foreign key (codigo_maquina) REFERENCES maquinas_registradoras (codigo_maquina)"
				+ "ON DELETE CASCADE ON UPDATE CASCADE,"
				+ "primary key (codigo_cajero, codigo_producto, codigo_maquina)"
				+ ");";
		cm.exequteQuery(query);
		
		

		query = "insert into cajeros(codigo, nom_apels) values   (111, 'Jaime Hodges'),"
															   +"(222, 'Sissie O''Glassane'),"
															   +"(333, 'Jillian Thies'),"
															   +"(444, 'Orelle Gatheral'),"
															   +"(555, 'Lester Szymoni'),"
															   +"(666, 'Dick Sans'),"
															   +"(777, 'Brandea Stonary'),"
															   +"(888, 'Kaye Gore'),"
															   +"(999, 'Tobin Benbow'),"
															   + "(101, 'Brade Cammidge')";
		cm.exequteQuery(query);
		

		query = "insert into productos values  (1, 'Crichton Mathelon', 7),"
											+ "(2, 'Annamaria Erley', 18),"
											+ "(3, 'Cathrine Meneur', 9),"
											+ "(4, 'Farand Syms', 54),"
											+ "(5, 'Ichabod Mallinar', 47),"
											+ "(6, 'Allys Houten', 36),"
											+ "(7, 'Thatch Brixey', 56),"
											+ "(8, 'Honor Asker', 20),"
											+ "(9, 'Culley Nolder', 5),"
											+ "(10, 'Kelcy Flockhart', 38),"
											+ "(11, 'Calcio', 3)";
		cm.exequteQuery(query);
		
		
		query = "insert into maquinas_registradoras(codigo_maquina, piso) values   ('0090684516', 1),"
																				+ "('0044901909', 2),"
																				+ "('0027726511', 3),"
																				+ "('0066415048', 4),"
																				+ "('0028962728', 5),"
																				+ "('0037308275', 6),"
																				+ "('0046530187', 7),"
																				+ "('0069193317', 8),"
																				+ "('0052728860', 9),"
																				+ "('0082331067', 0)";
		cm.exequteQuery(query);
		
		
		query = "insert into ventas(codigo_cajero, codigo_producto, codigo_maquina) values     (111, 1, '0082331067'),"
																							+ "(222, 2, '0069193317'),"
																							+ "(333, 3, '0052728860'),"
																							+ "(444, 4, '0046530187'),"
																							+ "(555, 5, '0037308275'),"
																							+ "(666, 6, '0066415048'),"
																							+ "(777, 7, '0027726511'),"
																							+ "(888, 8, '0028962728'),"
																							+ "(999, 9, '0044901909')";
		cm.exequteQuery(query);

		System.out.println("\n\n");
		

		query = "select *"
			  + "from cajeros;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("DNI: " + results.getString("codigo"));
				System.out.println("Nombre: " + results.getString("nom_apels"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		
		

		query = "select *"
			  + "from productos;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("Codigo: " + results.getString("codigo_producto"));
				System.out.println("Nombre: " + results.getString("nombre"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}

	}

}
