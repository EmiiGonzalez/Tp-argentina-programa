package proyectoIntegrador;

import java.nio.file.Path;
import java.nio.file.Paths;


public class ProyectoIntegrador {
	public static void main(String[] args) {
		Path resultado = Paths.get(".\\resultados.csv");
		Path pronostico = Paths.get(".\\pronosticos.csv");
		PronosticoDeportivo prd = new PronosticoDeportivo(resultado, pronostico);
		prd.Puntaje();
	}
}
