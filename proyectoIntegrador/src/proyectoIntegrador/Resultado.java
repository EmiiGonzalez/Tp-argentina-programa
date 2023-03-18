package proyectoIntegrador;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Resultado {
	
	//se crea la variable de instancia resultados
	private List<String> resultados;
	
	public Resultado(Path resultados) {
		//Resultado(resultados) es el constructor de la clase resultado
		//dentro del bloque try se leen las lineas del archivo resultados.txt y se remueve la primer linea o cabecera, en caso que no se pueda leer el archivo se imprime el error
		try {
			this.resultados = Files.readAllLines(resultados,StandardCharsets.UTF_8);
			this.resultados.remove(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//dentro de getResultados se retorna el valor de la variable de instancia resultados
	public List<String> getResultados(){
		return resultados;
	}
	
	
	public float getResultado(String partido) {
		
		//este metodo recibe una cadena de texto como parametro, la misma se divide en un vector utilizando las comas como divisor
		//se evaluan los goles por equipos y se retorna un valor dependiendo si es empate o que equipo fue el ganador
		//equipo1 = 1; equipo2 = 0; empate = 0.5
		String[] partido_split = partido.split(",");
		int GolesEquipo1 = Integer.parseInt(partido_split[1]);
		int GolesEquipo2 = Integer.parseInt(partido_split[2]);
		if (GolesEquipo1 > GolesEquipo2) {
			return 1;
		}else if(GolesEquipo1 == GolesEquipo2){
			return (float) 0.5;
		}else {
			return 0;
		}
	}
	
}
