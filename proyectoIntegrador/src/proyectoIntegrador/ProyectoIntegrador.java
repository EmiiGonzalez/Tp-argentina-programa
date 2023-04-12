package proyectoIntegrador;

import java.nio.file.Path;
import java.sql.*;
import java.nio.file.Paths;
import java.util.Map;


public class ProyectoIntegrador {
	public static void main(String[] args) {
		
		try{  

			Class.forName("org.postgresql.Driver");    
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost/ProyectoIntegradorJava","postgres","ArgentinaPrograma");  
			Statement stmt=con.createStatement();  
			

 

			//ResultSet rs=stmt.executeQuery("select codigo,denominacion,estado from color where denominacion like '%LILA%'");  

			//while(rs.next())  

			//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  

 

			///int result=stmt.executeUpdate("insert into tabla1 (id,nombre) values (3,'Leonel')");  

			//System.out.println(result + " records affected");  

			

			//ResultSet rs=stmt.executeQuery("select * from tabla1  ");  

			

			//ResultSetMetaData rsmd=rs.getMetaData();

 

			

			//while(rs.next())  

			//System.out.println(rs.getInt(1)+"  "+rs.getString(2));

			//	System.out.println(rsmd.getColumnCount());

 

 

			con.close();  

			} catch(Exception e){ System.out.println(e);}  
		
		// se generan variables con los archivos
		System.out.println(System.getProperty("user.dir"));
		Path resultado = Paths.get("src\\Archivos","resultados.txt");
		Path pronostico = Paths.get("src\\\\Archivos","pronosticos.txt");
		
		//se instancia un objeto de la clase PronosticoDeportivo en la variable llamada prd pasando como argumento al constructor los archivos
		PronosticoDeportivo prd = new PronosticoDeportivo(resultado, pronostico);
		
		//se accede al metodo Puntaje de la clase PronosticoDeportivo
		Map<String,Integer> puntaje = prd.Puntaje();
		System.out.println(puntaje);
	}
}
