package modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import exepciones.ErrorEnCargaDeDatos;

public class TpGrupo6 {

	public static void main(String[] args) throws ErrorEnCargaDeDatos {
		
		try {
			List<String> lineasResultado = Files.readAllLines(Paths.get(".\\src\\main\\java\\Archivos\\resultados.txt"), StandardCharsets.ISO_8859_1);
			List<String> lineasPronostico = Files.readAllLines(Paths.get(".\\src\\main\\java\\Archivos\\pronostico.txt"), StandardCharsets.ISO_8859_1);
			
			Ronda rondasResultado = new Ronda();	//instancia de objeto ronda
			Ronda rondasPronostico = new Ronda();	//instancia de objeto ronda
			
			procesarLineas(lineasResultado, rondasResultado, 'r');		//procesamiento de lineas del archivo para generar una list de objetos
			procesarLineas(lineasPronostico, rondasPronostico, 'p');	//procesamiento de lineas del archivo para generar una list de objetos
			
			Resultado resultado = new Resultado(rondasPronostico, rondasResultado);
			resultado.setPuntajeApostador(1,0);
			
			//System.out.println(resultado.getApuestasResultado());
			System.out.println(resultado.getPuntajeFinalLista());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//funcion para reciclar codigo y no escribir dos veces el ciclo for dentro del cuerpo main
	private static void procesarLineas(List<String> lineas, Ronda ronda, char tipoArchivo) throws ErrorEnCargaDeDatos {	
	    for (int i = 1; i < lineas.size(); i++) {					//se empieza desde 1 para omitir la cabecera
	        String linea = lineas.get(i); 							//instancio en variable por indice        
	        Torneo resultado = new Torneo(linea);				//creo un nuevo objeto resultado pasando como parametro la linea o string creado anteriormente  
	        if (Character.toLowerCase(tipoArchivo) == 'p') {		//dependiendo el tipo de iteracion enviada como parametro se decide cual partido crear
	            resultado.generarPartidoPronostico();				//se genera el partido 
	            ronda.setRondas(resultado.getPartido());
	        } else if(Character.toLowerCase(tipoArchivo) == 'r'){
	            resultado.generarPartidoResultado();
	            ronda.setRondas(resultado.getPartido());
	        }
	        				//al objeto ronda pasado por parametro, utilizando el paso por referencia de objetos, se le agregan los partidos
	    }															//paso por referencia: todos los cambios que se hacen al objeto pasado por parametro se reflejan en el objeto original
	    															//ya que apuntan a la misma direccion de memoria
	}

}
