package proyectoIntegrador;

import java.nio.file.Path;
import java.nio.file.Paths;


public class ProyectoIntegrador {
	public static void main(String[] args) {
		// se generan variables con los archivos
		System.out.println(System.getProperty("user.dir"));
		Path resultado = Paths.get("proyectoIntegrador\\src\\Archivos","resultados.txt");
		Path pronostico = Paths.get("proyectoIntegrador\\src\\\\Archivos","pronosticos.txt");
		
		//se instancia un objeto de la clase PronosticoDeportivo en la variable llamada prd pasando como argumento al constructor los archivos
		PronosticoDeportivo prd = new PronosticoDeportivo(resultado, pronostico);
		
		//se accede al metodo Puntaje de la clase PronosticoDeportivo
		prd.Puntaje();
	}
}
