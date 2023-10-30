package Ej6;

import java.sql.SQLException;

import MyConnectionManager.MyConnectionManager;

public class Ej6App {

	public static void main(String[] args) {

		MyConnectionManager cm = new MyConnectionManager();
		String dbName = "java_pieza";
		String user = "root";
		String pwd = "root";
		String query = "";
		java.sql.ResultSet results;

		//Conectarse al servidor mysql y crear la base de datos
		cm.startConnection(user, pwd);
		cm.createDB(dbName);
		
		//Conectarse a la base de datos
		cm.startConnectionDB(user, pwd, dbName);
		

		query = "create table pieza("
				+ "codigo int auto_increment,"
				+ "nombre varchar(100),"
				+ "primary key(codigo)"
				+ ");";
		cm.exequteQuery(query);
		
		

		query = "create table proveedor("
				+ "id char(4),"
				+ "nombre varchar(100),"
				+ "primary key (id)"
				+ ");";
		cm.exequteQuery(query);
		
		
		
		query = "create table suministra("
				+ "codigo_pieza int,"
				+ "id_proveedor char(4),"
				+ "precio int,"
				+ "primary key (codigo_pieza, id_proveedor),"
				+ "foreign key (codigo_pieza) references pieza(codigo)"
				+ "on delete cascade on update cascade,"
				+ "foreign key (id_proveedor) references proveedor(id)"
				+ "on delete cascade on update cascade"
				+ ");";
		cm.exequteQuery(query);
		
		

		query = "insert into pieza(nombre) values ('RTX 2080'),"
											    +"('Intel Core i7-12700k'),"
											    +"('CPU Fan Hyper'),"
											    +"('Placa Base Gigabyte z200'),"
											    +"('Razer Blackwidow 2022'),"
											    +"('Razer Naga'),"
											    +"('SteelSeries Actis Pro 7')";
		cm.exequteQuery(query);
		

		query = "insert into proveedor values  	('as31', 'Razer'),"
											  +"('ds76', 'Nvidia'),"
											  +"('bg45', 'Gigabyte'),"
											  +"('sd43', 'SteelSeries'),"
											  +"('aw12', 'Intel');";
		cm.exequteQuery(query);
		
		
		query = "insert into suministra values (1, 'ds76', 1780),"
										     +"(2, 'aw12', 980),"
										     +"(3, 'bg45', 35),"
										     +"(4, 'bg45', 98),"
										     +"(5, 'as31', 130),"
										     +"(6, 'as31', 78),"
										     +"(7, 'sd43', 167)";
		cm.exequteQuery(query);

		System.out.println("\n\n");
		
		
		query = "select *"
			  + "from pieza;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("Codigo: " + results.getString("codigo"));
				System.out.println("Nombre: " + results.getString("nombre"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		
		

		query = "select *"
			  + "from proveedor;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("Codigo: " + results.getString("id"));
				System.out.println("Nombre: " + results.getString("nombre"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		
		
		query = "select p.nombre, pro.nombre as 'proveedor'"
			  + "from pieza as p join suministra as s on p.codigo = s.codigo_pieza join proveedor as pro on s.id_proveedor = pro.id;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("Producto: " + results.getString("nombre"));
				System.out.println("Proveedor: " + results.getString("proveedor"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		
	}

}
