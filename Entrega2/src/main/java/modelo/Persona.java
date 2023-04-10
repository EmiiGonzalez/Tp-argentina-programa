package modelo;

import lombok.Data;

@Data

public class Persona {
	private String nombre;
	private int id;
	private int puntaje = 0;
	private String ronda;
	
	
	public Persona() {
	}

	public Persona(String nombre, int id, int puntaje, String ronda) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.puntaje = puntaje;
		this.ronda = ronda;
	}
	

}
