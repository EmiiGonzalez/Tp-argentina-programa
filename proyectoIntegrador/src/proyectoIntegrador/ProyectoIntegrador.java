package proyectoIntegrador;

import java.nio.file.Path;
import java.nio.file.Paths;


public class ProyectoIntegrador {
	public static void main(String[] args) {
		Path resultado = Paths.get("src\\Archivos","resultados.txt");
		Path pronostico = Paths.get("src\\Archivos","pronosticos.txt");
		PronosticoDeportivo prd = new PronosticoDeportivo(resultado, pronostico);
		prd.Puntaje();
	}
}
