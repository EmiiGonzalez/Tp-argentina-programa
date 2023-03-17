package proyectoIntegrador;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Resultado {
	private List<String> resultados;
	
	public Resultado(Path resultados) {
		try {
			this.resultados = Files.readAllLines(resultados,StandardCharsets.UTF_8);
			this.resultados.remove(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getResultados(){
		return resultados;
	}
	
	public float getResultado(String partido) {
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
