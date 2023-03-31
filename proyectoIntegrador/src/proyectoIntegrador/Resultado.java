package proyectoIntegrador;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Resultado {
	
	//se crea la variable de instancia resultados
	private List<String> resultados;
	protected int Ronda;
	protected int Goles1;
	protected int Goles2;
	protected int Equipo1;
	protected int Equipo2;
	
	public Resultado(Path resultados) {
		//Resultado(resultados) es el constructor de la clase resultado
		//dentro del bloque try se leen las lineas del archivo resultados.txt y se remueve la primer linea o cabecera, en caso que no se pueda leer el archivo se imprime el error
		try {
			this.resultados = Files.readAllLines(resultados,StandardCharsets.UTF_8);
			String[] Encabezado = this.resultados.get(0).split(",");
			for (int i=0; i<Encabezado.length;i++) {
				if(Encabezado[i].equals("Ronda")) {
					Ronda = i;
				}else if (Encabezado[i].equals("Cant. Goles Equipo 1")){
					Goles1 = i;
				}else if (Encabezado[i].equals("Cant. Goles Equipo 2")){
					Goles2 = i;
				}else if (Encabezado[i].equals("Equipo 1")){
					Equipo1 = i;
				}else if (Encabezado[i].equals("Equipo 2")){
					Equipo2 = i;
				}
			}
			this.resultados.remove(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//dentro de getResultados se retorna el valor de la variable de instancia resultados
	public List<String> getResultados(){
		return this.resultados;
	}
	
	

	
}
