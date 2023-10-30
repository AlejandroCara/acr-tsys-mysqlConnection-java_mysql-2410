package Ej4;

import java.sql.SQLException;

import MyConnectionManager.MyConnectionManager;

public class Ej4App {

	public static void main(String[] args) {

		MyConnectionManager cm = new MyConnectionManager();
		String dbName = "java_peliculas";
		String user = "root";
		String pwd = "root";
		String query = "";
		java.sql.ResultSet results;

		//Conectarse al servidor mysql y crear la base de datos
		cm.startConnection(user, pwd);
		cm.createDB(dbName);
		
		//Conectarse a la base de datos
		cm.startConnectionDB(user, pwd, dbName);
		
		//Crear la tabla pelicula
		query = "create table pelicula("
				+ "codigo int auto_increment,"
				+ "nombre varchar(100),"
				+ "calificacion_edad int,"
				+ "primary key(codigo)"
				+ ");";
		cm.exequteQuery(query);
		
		
		//Crear la tabla sala
		query = "create table sala("
				+ "codigo int auto_increment,"
				+ "nombre varchar(100),"
				+ "pelicula int,"
				+ "primary key (codigo),"
				+ "foreign key (pelicula) references pelicula(codigo)"
				+ "on delete set null on update cascade"
				+ ");";
		cm.exequteQuery(query);
		
		
		//Introducir peliculas
		query = "insert into pelicula(nombre, calificacion_edad) values ('El Señor de los anillos: El retorno del Rey', 18),"
																 +"('La Llegada', 16),"
																 +"('Como Entrnar a tu dragon', 13),"
																 +"('Warcraft', 13),"
																 +"('One Piece Red', 13)";
		cm.exequteQuery(query);
		
		//Introducir salas
		query = "insert into sala(nombre, pelicula) values  ('Sala 1', 1),"
														  +"('Sala 2', 1),"
														  +"('Sala 3', 3),"
														  +"('Sala 4', 2),"
														  +"('Sala 5', 4),"
														  +"('Sala 6', 5)";
		cm.exequteQuery(query);
		

		System.out.println("\n\n");
		
		//Consultar todas las salas
		query = "select *"
			  + "from sala;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("Codigo: " + results.getString("codigo"));
				System.out.println("Nombre: " + results.getString("nombre"));
				System.out.println("Pelicula: " + results.getString("pelicula"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		
		System.out.println("\n\n");
		System.out.println("\n\n");
		
		//Consultar todas las peliculas
		query = "select *"
			  + "from pelicula;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("Codigo: " + results.getString("codigo"));
				System.out.println("Nombre: " + results.getString("nombre"));
				System.out.println("Calificación Edad: " + results.getString("calificacion_edad"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}

	}

}
