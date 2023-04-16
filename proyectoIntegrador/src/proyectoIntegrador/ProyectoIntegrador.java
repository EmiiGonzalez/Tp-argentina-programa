package proyectoIntegrador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.nio.file.Paths;
import java.util.Map;


public class ProyectoIntegrador {
	public static void main(String[] args) {
		String dbName = null;
		String pass = null;
		int extraRonda = 0;
		int extraFase = 0;
		int PuntoxPartido = 0;
		int PartidoxRonda = 0;
		try {
			Path configuracion = Paths.get("src\\Archivos","configuracion.txt");
			for (String linea : Files.readAllLines(configuracion)){
				String[] linea_split = linea.split(",");
				if(linea_split[0].equals("dbName")) {
					dbName = linea_split[1];
				}else if(linea_split[0].equals("pass")) {
					pass = linea_split[1];
				}else if(linea_split[0].equals("extraRonda")) {
					extraRonda = Integer.parseInt(linea_split[1]);
				}else if(linea_split[0].equals("extraFase")) {
					extraFase = Integer.parseInt(linea_split[1]);
				}else if(linea_split[0].equals("partido")) {
					PuntoxPartido = Integer.parseInt(linea_split[1]);
				}else if(linea_split[0].equals("partidoxronda")) {
					PartidoxRonda = Integer.parseInt(linea_split[1]);
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		try{  
			//Conexion a la base de datos
			Class.forName("org.postgresql.Driver");    
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost/ProyectoIntegradorJava",dbName,pass);  
			Statement stmt=con.createStatement();  
			
			// se generan variables con los archivos
			Path resultado = Paths.get("src\\Archivos","resultados.txt");
			
			//se instancia un objeto de la clase PronosticoDeportivo en la variable llamada prd pasando como argumento al constructor los archivos
			PronosticoDeportivo prd = new PronosticoDeportivo(resultado, extraRonda, extraFase, PuntoxPartido, PartidoxRonda, stmt);
			
			//se accede al metodo Puntaje de la clase PronosticoDeportivo
			Map<String,Integer> puntaje = prd.Puntaje();
			System.out.println(puntaje);
			

			con.close();  

			} catch(Exception e){ System.out.println(e);}  
	}
}
