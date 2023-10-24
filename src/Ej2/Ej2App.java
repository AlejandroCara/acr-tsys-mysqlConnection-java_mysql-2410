package Ej2;

import java.sql.SQLException;

import MyConnectionManager.MyConnectionManager;

public class Ej2App {

	public static void main(String[] args) {

		MyConnectionManager cm = new MyConnectionManager();
		String user = "root";
		String pwd = "root";
		String query = "";
		java.sql.ResultSet results;

		//Conectarse al servidor mysql y crear la base de datos
		cm.startConnection(user, pwd);
		cm.createDB("java_empleados");
		
		//Conectarse a la base de datos
		cm.startConnectionDB(user, pwd, "java_empleados");
		
		//Crear la tabla departamento
		query = "create table departamento("
				+ "codigo int auto_increment,"
				+ "nombre varchar(100),"
				+ "presupuesto int,"
				+ "primary key(codigo)"
				+ ");";
		cm.exequteQuery(query);
		
		
		//Crear la tabla empleado
		query = "create table empleado("
				+ "dni varchar(8),"
				+ "nombre varchar(100),"
				+ "apellidos varchar(255),"
				+ "departamento int,"
				+ "primary key (dni),"
				+ "foreign key (departamento) references departamento(codigo)"
				+ "on delete no action on update cascade"
				+ ");";
		cm.exequteQuery(query);
		
		
		//Introducir departamentos
		query = "insert into departamento(nombre, presupuesto) values ('Marketing', 13000),"
																	+"('IT', 39999),"
																	+"('QA', 3000),"
																	+"('HR', 999999),"
																	+"('Ventas', 1200)";
		cm.exequteQuery(query);
		
		//Introducir empleados
		query = "insert into empleado(dni, nombre, departamento) values('34609700', 'Alfredo', 1),"
																	 +"('34609432', 'Casandra', 1),"
																	 +"('34609175', 'Clara', 2),"
																	 +"('34609423', 'Carlos', 2),"
																	 +"('34609876', 'Maria', 3),"
																	 +"('34609872', 'Manolo', 4)";
		cm.exequteQuery(query);
		

		System.out.println("\n\n");
		
		//Consultar todos los empleados
		query = "select *"
			  + "from empleado;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("DNI: " + results.getString("dni"));
				System.out.println("Nombre: " + results.getString("nombre"));
				System.out.println("Departamento: " + results.getString("departamento"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		
		System.out.println("\n\n");
		System.out.println("\n\n");
		
		//Consultar todos los departamentos
		query = "select *"
			  + "from departamento;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("Codigo: " + results.getString("codigo"));
				System.out.println("Nombre: " + results.getString("nombre"));
				System.out.println("Departamento: " + results.getString("presupuesto"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		
		
		//Consultar los empleados cuyo departamento tenga un presupuesto mayor a 15000
		query = "select * "
			  + "from empleado "
			  + "where departamento in (select codigo "
			  + "						from departamento "
			  + "						where presupuesto > 15000);";
		results = cm.getValues(query);
		
		System.out.println("\n\n\n");
		System.out.println("Empleados cuyo departamento tenga un presupuesto mayor a 15000");
		try {
			while(results.next()){
				System.out.println("DNI: " + results.getString("dni"));
				System.out.println("Nombre: " + results.getString("nombre"));
				System.out.println("Departamento: " + results.getString("departamento"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		cm.closeConnection();

	}

}
