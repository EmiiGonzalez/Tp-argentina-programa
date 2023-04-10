package modelo;


import java.util.List;
import java.util.ArrayList;
import lombok.Data;

public class Ronda {
	private List<Partido> partidos;

	public Ronda() {
		this.partidos = new ArrayList<Partido>();
	}
	
	public List<Partido> getPartidos() {
		return partidos;
	}
	
	
	public void setRondas(Partido partido) {
	        this.partidos.add(partido);
	}
	
	
	
	
}

