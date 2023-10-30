package Ej7;

import java.sql.SQLException;

import MyConnectionManager.MyConnectionManager;

public class Ej7App {

	public static void main(String[] args) {

		MyConnectionManager cm = new MyConnectionManager();
		String dbName = "java_cientificos";
		String user = "root";
		String pwd = "root";
		String query = "";
		java.sql.ResultSet results;

		//Conectarse al servidor mysql y crear la base de datos
		cm.startConnection(user, pwd);
		cm.createDB(dbName);
		
		//Conectarse a la base de datos
		cm.startConnectionDB(user, pwd, dbName);
		

		query = "create table cientifico("
				+ "dni varchar(8),"
				+ "nomapels varchar(255),"
				+ "primary key(dni)"
				+ ");";
		cm.exequteQuery(query);
		
		

		query = "create table proyecto("
				+ "id char(4),"
				+ "nombre varchar(255),"
				+ "horas int,"
				+ "primary key (id)"
				+ ");";
		cm.exequteQuery(query);
		
		
		
		query = "create table asignado_a("
				+ "dni_cientifico varchar(8),"
				+ "id_proyecto char(4),"
				+ "primary key (dni_cientifico, id_proyecto),"
				+ "foreign key (dni_cientifico) references cientifico(dni)"
				+ "on delete cascade on update cascade,"
				+ "foreign key (id_proyecto) references proyecto(id)"
				+ "on delete cascade on update cascade"
				+ ");";
		cm.exequteQuery(query);
		
		

		query = "insert into cientifico(dni, nomapels) values('99149698', 'Kissee'),"
														   +"('94058792', 'Warden'),"
														   +"('95880396', 'Worthington'),"
														   +"('91486196', 'Rory'),"
														   +"('93827341', 'Barbaraanne'),"
														   +"('93538273', 'Ermin'),"
														   +"('99117579', 'Violante'),"
														   +"('97285086', 'Filia'),"
														   +"('93951813', 'Reagen')";
		cm.exequteQuery(query);
		

		query = "insert into proyecto values  ('RE32', 'Scorpio', 500),"
											+"('YK84', 'Skin Eruptions', 669),"
											+ "('RZ78', 'Aversion to Exertion', 523),"
											+ "('WK47', 'Lovenox', 585),"
											+ "('AS05', 'EROS LONG STAY SILICONE GLIDE', 355),"
											+ "('PX22', 'Antibiotic and Pain Relief', 508),"
											+ "('KZ62', 'VYTORIN', 337)";
		cm.exequteQuery(query);
		
		
		query = "insert into asignado_a values ('99149698', 'RE32'),"
											+ "('94058792', 'RE32'),"
											+ "('95880396', 'YK84'),"
											+ "('91486196', 'RZ78'),"
											+ "('99117579', 'WK47')";
		cm.exequteQuery(query);

		System.out.println("\n\n");
		

		query = "select *"
			  + "from cientifico;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("DNI: " + results.getString("dni"));
				System.out.println("Nombre: " + results.getString("nomapels"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		
		

		query = "select *"
			  + "from proyecto;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("Codigo: " + results.getString("id"));
				System.out.println("Nombre: " + results.getString("nombre"));
				System.out.println("Horas: " + results.getString("horas"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
		
		
		query = "select c.nomapels, p.nombre as 'proyecto'"
			  + "from cientifico as c join asignado_a as a on c.dni = a.dni_cientifico join proyecto as p on a.id_proyecto = p.id;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("Cientifico: " + results.getString("nomapels"));
				System.out.println("Proyecto: " + results.getString("proyecto"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}

	}

}
