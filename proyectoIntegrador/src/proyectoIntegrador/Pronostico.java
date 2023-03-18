package proyectoIntegrador;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Pronostico {
	private List<String> pronosticos;
	
	public Pronostico(Path pronosticos) {
		try {
			this.pronosticos = Files.readAllLines(pronosticos,StandardCharsets.UTF_8);
			this.pronosticos.remove(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getPronosticos(){
		return pronosticos;
	}
	
	//Esta funcion devuelve 1 si se pronostico que gane el partido el equipo 1, 0.5 si empatan y 0 si gano el equipo 2
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