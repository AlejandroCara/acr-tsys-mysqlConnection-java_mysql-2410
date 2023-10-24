package Ej1;

import java.sql.SQLException;

import MyConnectionManager.MyConnectionManager;

public class Ej1App {

	public static void main(String[] args) {
		
		MyConnectionManager cm = new MyConnectionManager();
		String user = "root";
		String pwd = "root";
		String query = "";
		java.sql.ResultSet results;
		
		//Conectarse al servidor mysql y crear la base de datos
		cm.startConnection(user, pwd);
		cm.createDB("java_tienda");
		
		//Conectarse a la base de datos
		cm.startConnectionDB(user, pwd, "java_tienda");
		
		//Crear la tabla fabricante
		query = "create table fabricante("
				+ "codigo int auto_increment,"
				+ "nombre varchar(100),"
				+ "primary key(codigo)"
				+ ");";
		cm.exequteQuery(query);
		
		
		//Crear la tabla articulo
		query = "create table articulo("
				+ "codigo int auto_increment,"
				+ "nombre varchar(100),"
				+ "precio int,"
				+ "fabricante int,"
				+ "primary key (codigo),"
				+ "foreign key (fabricante) references fabricante(codigo)"
				+ "on delete cascade on update cascade"
				+ ");";
		cm.exequteQuery(query);
		
		
		//Introducir fabricantes
		query = "insert into fabricante(nombre) values('Sony'),"
													+"('Canon'),"
													+"('Nikkon'),"
													+"('Nintendo'),"
													+"('Microsoft')";
		cm.exequteQuery(query);
		
		//Introducir articulos
		query = "insert into articulo(nombre, precio, fabricante) values('Televisor 45 puldagas', 400, 1),"
																	  +"('Camara 200x', 1200, 2),"
																	  +"('Evil Nikon', 700, 3),"
																	  +"('PlayStation 5', 600, 1),"
																	  +"('Nintendo Switch', 300, 4),"
																	  +"('Xbox Series X', 570, 5)";
		cm.exequteQuery(query);
		
		
		//Consultar todos los articulos
		query = "select *"
			  + "from articulo";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("Codigo: " + results.getString("codigo"));
				System.out.println("Nombre: " + results.getString("nombre"));
				System.out.println("Codigo Fabricante: " + results.getString("fabricante"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		
		
		//Consultar articulos del fabricante 1 (Sony)
		query = "select * "
			  + "from articulo "
			  + "where fabricante = 1;";
		results = cm.getValues(query);
		
		System.out.println("\n\n\n");
		System.out.println("Articulos del fabricante 1");
		try {
			while(results.next()){
				System.out.println("Codigo: " + results.getString("codigo"));
				System.out.println("Nombre: " + results.getString("nombre"));
				System.out.println("Codigo Fabricante: " + results.getString("fabricante"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		cm.closeConnection();
	}


}
