package proyectoIntegrador;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


public class ProyectoIntegrador {
	public static void main(String[] args) {
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
