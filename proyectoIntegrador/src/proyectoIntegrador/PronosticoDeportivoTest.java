package proyectoIntegrador;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PronosticoDeportivoTest {
	
	PronosticoDeportivo Test;
	
	@BeforeEach
	public void setup() {
		Path res = Paths.get("src\\Archivos","resultados.txt");
		try {
			Class.forName("org.postgresql.Driver");    
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost/ProyectoIntegradorJava","postgres","ArgentinaPrograma");  
			Statement stmt=con.createStatement();
			Test = new PronosticoDeportivo(res,5,20,1,10,stmt);
		}catch(Exception e){ System.out.println(e);}
		
		
	}

	
	//Deberian funcionar los tests pero no sabemos por qué si ponemos adecuadamente los strings Java no los toma como algo que existe en el mapa.
	@Test
	void testPuntaje1() {
		int Puntaje = Test.Puntaje().get(Test.getParticipantes().get(0));
		int PuntajeCorrecto = 6;
		assertTrue(Puntaje==PuntajeCorrecto);
	}
	
	@Test
	void testPuntaje2() {
		int Puntaje = Test.Puntaje().get(Test.getParticipantes().get(1));
		int PuntajeCorrecto = 9;
		assertTrue(Puntaje==PuntajeCorrecto);
	}

}
