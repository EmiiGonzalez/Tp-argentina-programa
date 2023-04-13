package modelo;


import java.util.List;
import java.util.ArrayList;
import lombok.Data;

public class Ronda {
	private List<Partido> partidos;			//atributo de clase

	public Ronda() {
		this.partidos = new ArrayList<Partido>();		//inicializo el array
	}
	
	public List<Partido> getPartidos() {
		return partidos;
	}
	
	
	public void setRondas(Partido partido) {		//agrego un objeto Partido al array
	        this.partidos.add(partido);
	}
	
	
	
	
}


