package proyectoIntegrador;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

//este bloque define la clase Pronostico, lee y procesa los datos del archivo pronosticos
public class Pronostico {
	
	//variable de instancia
	private List<String> pronosticos;
	
	public Pronostico(Path pronosticos) {
		//Pronostico(pronosticos) es el constructor de la clase Pronostico
		//dentro del bloque try se leen las lineas del archivo pronostico.txt y se remueve la primer linea o cabecera, en caso que no se pueda leer el archivo se imprime el error
		try {
			this.pronosticos = Files.readAllLines(pronosticos,StandardCharsets.UTF_8);
			this.pronosticos.remove(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getPronosticos(){
		//metodo para retornar el valor de la lista pronosticos
		return pronosticos;
	}
	
	//Esta funcion devuelve 1 si se pronostico que gane el partido el equipo 1, 0.5 si empatan y 0 si gano el equipo 2
	//es igual al metodo usado para Resultado
	public float getPronostico(String partido) {
		String[] partido_split = partido.split(",");
		if (partido_split[1].equals("X")) {
			return 1;
		}else if(partido_split[2].equals("X")){
			return (float) 0.5;
		}else {
			return 0;
		}
	}
	
	
}