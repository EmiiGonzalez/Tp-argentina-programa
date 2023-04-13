package pronosticoProyecto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import modelo.*;

import exepciones.*;

public class TpGrupo6 {

	public static void main(String[] args) throws ErrorEnCargaDeDatos, ErrorEnLecturaDeDatosException {
		
		try {
			List<String> lineasResultado = Files.readAllLines(Paths.get(".\\src\\main\\java\\Archivos\\resultados.txt"), StandardCharsets.ISO_8859_1);
			List<String> lineasPronostico = Files.readAllLines(Paths.get(".\\src\\main\\java\\Archivos\\pronostico.txt"), StandardCharsets.ISO_8859_1);
			
			Ronda rondasResultado = new Ronda();	//instancia de objeto ronda
			Ronda rondasPronostico = new Ronda();	//instancia de objeto ronda
			
			procesarLineas(lineasResultado, rondasResultado, 'r');		//procesamiento de lineas del archivo para generar una list de objetos, el mismo modifico el contenido del objeto rondasResultado 
			procesarLineas(lineasPronostico, rondasPronostico, 'p');	//procesamiento de lineas del archivo para generar una list de objetos, el mismo modifico el contenido del objeto rondasPronostico 
			
			double multiplicadorDePuntos = 1;
			int puntosPorAcertar = 1;
			int puntosPorErrar = 0;
			
			
			Resultado resultado = new Resultado(rondasPronostico, rondasResultado, multiplicadorDePuntos, puntosPorAcertar, puntosPorErrar);				//creo un nuevo objeto resultado enviando como parametro las listas de objetos partidos creadas anteriormente
			System.out.println(resultado.getPuntajeTotalPorPersona());
			System.out.println(resultado.getPersonasPorFase());
			
			

		} catch (IOException e) {
			throw new ErrorEnLecturaDeDatosException("Error en leer archivo\n", e);		//execion por si los archivos no existen
		}

	}
	

	//funcion para reciclar codigo y no escribir dos veces el ciclo for dentro del cuerpo main
	//dependiendo el char enviado como parametro, el constructor de la clase Torneo decide cual camino seguir
	
	private static void procesarLineas(List<String> lineas, Ronda ronda, char tipoArchivo) throws ErrorEnCargaDeDatos {	
	    for (int i = 1; i < lineas.size(); i++) {							//se empieza desde 1 para omitir la cabecera
	        String linea = lineas.get(i); 									//instancio en variable por indice        
	        Torneo resultado = new Torneo(linea, tipoArchivo);				//creo un nuevo objeto resultado pasando como parametro la linea o string creado anteriormente 
	        ronda.setRondas(resultado.getPartido());
	        														//al objeto ronda pasado por parametro, utilizando el paso por referencia de objetos, se le agregan los partidos
	    }															//paso por referencia: todos los cambios que se hacen al objeto pasado por parametro se reflejan en el objeto original
	    															//ya que apuntan a la misma direccion de memoria
	}
	
	
}
