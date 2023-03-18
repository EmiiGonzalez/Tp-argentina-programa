package proyectoIntegrador;

import java.nio.file.Path;
import java.nio.file.Paths;


public class ProyectoIntegrador {
	public static void main(String[] args) {
		// se generan variables con los archivos
		Path resultado = Paths.get("src\\Archivos","resultados.txt");
		Path pronostico = Paths.get("src\\Archivos","pronosticos.txt");
		
		//se instancia un objeto de la clase PronosticoDeportivo en la variable llamada prd
		PronosticoDeportivo prd = new PronosticoDeportivo(resultado, pronostico);
		
		//se accede al metodo Puntaje de la clase
		prd.Puntaje();
	}
}
