package proyectoIntegrador;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

//este bloque define la clase Pronostico, lee y procesa los datos del archivo pronosticos
public class Pronostico {
	
	//variable de instancia
	private List<String> pronosticos;
	//Los enteros se colocan para funcionar como índices de cada una de las columnas del archivo
	protected int Participante;
	protected int Gana1;
	protected int Gana2;
	protected int Empate;
	protected int Equipo1;
	protected int Equipo2;
	
	public Pronostico(Path pronosticos) {
		//Pronostico(pronosticos) es el constructor de la clase Pronostico
		//dentro del bloque try se leen las lineas del archivo pronostico.txt y se remueve la primer linea o cabecera, en caso que no se pueda leer el archivo se imprime el error
		try {
			this.pronosticos = Files.readAllLines(pronosticos,StandardCharsets.UTF_8);
			String[] Encabezado = this.pronosticos.get(0).split(",");
			for (int i=0; i<Encabezado.length;i++) {
				if(Encabezado[i].equals("Participante")) {
					Participante = i;
				}else if (Encabezado[i].equals("Gana 1")){
					Gana1 = i;
				}else if (Encabezado[i].equals("Gana 2")){
					Gana2 = i;
				}else if (Encabezado[i].equals("Empate")){
					Empate = i;
				}else if (Encabezado[i].equals("Equipo 1")){
					Equipo1 = i;
				}else if (Encabezado[i].equals("Equipo 2")){
					Equipo2 = i;
				}
			}
			this.pronosticos.remove(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getPronosticos(){
		//metodo para retornar el valor de la lista pronosticos
		return pronosticos;
	}
	
	public List<String> getParticipantes(){
		List<String> participantes = new ArrayList<String>();
		for(int i=0;i<this.pronosticos.size();i++) {
			String partido = this.pronosticos.get(i);
			String[] partido_split = partido.split(",");
			if (!participantes.contains(partido_split[this.Participante])) {
				participantes.add(partido_split[this.Participante]);
			}
		}
		return participantes;
	}
	
	
}
	