package Ej9;

import java.sql.SQLException;

import MyConnectionManager.MyConnectionManager;

public class Ej9App {

	public static void main(String[] args) {

		MyConnectionManager cm = new MyConnectionManager();
		String dbName = "java_investigadores";
		String user = "root";
		String pwd = "root";
		String query = "";
		java.sql.ResultSet results;

		//Conectarse al servidor mysql y crear la base de datos
		cm.startConnection(user, pwd);
		cm.createDB(dbName);
		
		//Conectarse a la base de datos
		cm.startConnectionDB(user, pwd, dbName);
		

		query = "create table Facultad("
				+ "codigo int auto_increment primary key,"
				+ "nombre varchar(100)"
				+ ");";
		cm.exequteQuery(query);
		
		

		query = "create table Investigador("
				+ "dni varchar(8) primary key,"
				+ "nom_apels varchar(255),"
				+ "facultad int,"
				+ "foreign key(facultad) references Facultad(codigo)"
				+ ");";
		cm.exequteQuery(query);
		
		
		
		query = "create table Equipo("
				+ "num_serie char(4) primary key,"
				+ "nombre varchar(100),"
				+ "facultad int,"
				+ "foreign key (facultad) references Facultad(codigo)"
				+ ");";
		cm.exequteQuery(query);
		
		
		
		query = "create table Reserva("
				+ "dni varchar(8),"
				+ "num_serie char(4),"
				+ "comienzo datetime,"
				+ "fin datetime,"
				+ "primary key (dni, num_serie),"
				+ "foreign key (dni) references Investigador(dni),"
				+ "foreign key (num_serie) references Equipo(num_serie)"
				+ ");";
		cm.exequteQuery(query);
		
		

		query = "insert into Facultad(nombre) values ('Arquitectura'),"
												   +"('Artes y Diseño'),"
												   +"('Ciencias Políticas y Sociales'),"
												   +"('Contaduría y Administración'),"
												   +"('Derecho'),"
												   +"('Economía'),"
												   +"('Odontología'),"
												   +"('Ingeniería'),"
												   +"('Medicina')";
		cm.exequteQuery(query);
		

		query = "insert into Investigador(dni, nom_apels, facultad) values (43073824, 'Andy Beidebeke', 9),"
																		+ "(79476133, 'Camellia Jaynes', 7),"
																		+ "(99454073, 'Gardy Rose', 4),"
																		+ "(35092405, 'Carny Finlaison', 6),"
																		+ "(66083241, 'Farrel Straughan', 7),"
																		+ "(18353982, 'Delainey Trussler', 9),"
																		+ "(39168447, 'Vern Glaisner', 8),"
																		+ "(80517374, 'Jillie Bursnall', 1),"
																		+ "(25676797, 'Slade Jezzard', 7),"
																		+ "(52727990, 'Anya Bannester', 3)";
		cm.exequteQuery(query);
		
		
		query = "insert into Equipo(num_serie, nombre, facultad) values    ('ABCD', 'Hunfredo', 1),"
																		+ "('ABSD', 'Gianina', 1),"
																		+ "('GDAS', 'Stevie', 5),"
																		+ "('KSDA', 'Kessiah', 7),"
																		+ "('ASKD', 'Oralie', 9),"
																		+ "('QJDS', 'Kimball', 3),"
																		+ "('PLDS', 'Heath', 8),"
																		+ "('MBCS', 'Rolf', 3),"
																		+ "('URTD', 'Chip', 3),"
																		+ "('UJSD', 'Tobiah', 4)";
		cm.exequteQuery(query);
		
		
		query = "insert into Reserva(dni, num_serie, comienzo, fin) values (43073824, 'ABCD', '2023-04-03 11:41:21', '2022-12-21 19:27:31'),"
																		+ "(79476133, 'ABSD', '2023-10-09 03:21:10', '2023-06-14 18:51:57'),"
																		+ "(99454073, 'GDAS', '2022-12-18 15:13:26', '2023-08-25 16:49:25'),"
																		+ "(35092405, 'KSDA', '2023-01-06 13:04:08', '2023-02-21 16:15:21'),"
																		+ "(80517374, 'ASKD','2023-09-15 14:39:44', '2023-07-29 18:46:27'),"
																		+ "(80517374, 'QJDS','2023-09-29 21:08:10', '2023-06-25 14:16:12'),"
																		+ "(35092405, 'PLDS', '2023-05-20 09:52:38', '2023-08-08 06:55:11'),"
																		+ "(52727990, 'MBCS', '2023-06-08 23:22:02', '2023-07-03 05:41:01'),"
																		+ "(52727990, 'URTD', '2023-04-10 08:24:33', '2023-09-11 23:50:27'),"
																		+ "(80517374, 'UJSD', '2022-12-26 22:58:09', '2023-03-15 09:02:57')";
		cm.exequteQuery(query);

		System.out.println("\n\n");
		

		query = "select *"
			  + "from Facultad;";
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
			  + "from Investigador;";
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
		
		
		query = "select *"
			  + "from Equipo;";
		results = cm.getValues(query);
		
		try {
			while(results.next()){
				System.out.println("Num Serie: " + results.getString("num_serie"));
				System.out.println("Nombre: " + results.getString("nombre"));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No hay resultados");
		}
			
	}

}
