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
			
			procesarLineas(lineasResultado, rondasResultado, 'r');		//procesamiento de lineas del archivo para generar una list de objetos
			procesarLineas(lineasPronostico, rondasPronostico, 'p');	//procesamiento de lineas del archivo para generar una list de objetos
			
			Resultado resultado = new Resultado(rondasPronostico, rondasResultado);		//creo un nuevo objeto resultado enviando como parametro las listas de objetos partidos creadas anteriormente
			resultado.setPuntajeApostador(1,0);											//a travez del metodo creo la lista final con los resultados de cada participante individual, le paso por 
																						//parametros los puntajes si gana o pierde , en caso de perder si se desea restar debe ir con el signo negativo
			
			
			Map<String, Fase> resultadosFinales = resultado.getPersonasPorFase();		//guardo en una variable el map con la matriz de objetos
			obtenerPuntajesFiltrados(resultadosFinales);								//llamo a la funcion encargada de que me muestre en pantalla los valores formateados
			
			

		} catch (IOException e) {
			throw new ErrorEnLecturaDeDatosException("Error en leer archivo\n", e);		//execion por si los archivos no existen
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
	
	private static void obtenerPuntajesFiltrados(Map<String, Fase> resultadosFinales) {
		
		
		for (String fase : resultadosFinales.keySet()) {											//para cada elemento del map, que me guarde la key en clave
			String nombre = "";																		//inicializacion de variables 
			int puntaje = 0;
						
			Map<Integer, Persona> faseIterada = resultadosFinales.get(fase).getPuntajeFinalLista();	 	//creo una variable de tipo map, la cual contiene una lista de tipo map con objetos persona
			
			for(Integer id : faseIterada.keySet()) {										//para cada elemento del map, que me guarde la key en id, el cual corresponde al id unico de cada persona
				nombre = faseIterada.get(id).getNombre();									//obtengo y guardo el valor del nombre
				puntaje = faseIterada.get(id).getPuntaje();									//obtengo y guardo el valor del puntaje
				System.out.println("El puntaje de: " +  nombre + " en la fase " + fase + " es de " + puntaje + " puntos");		//imprimo con formato los valores correspondientes
			}
			System.out.println("*********************************************************");
		}
	}

}
