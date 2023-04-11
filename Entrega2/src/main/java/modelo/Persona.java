package modelo;

import lombok.Data;

@Data

//atributos de clase persona
public class Persona {
	private String nombre;		//nombre
	private int id;				//id unico
	private int puntaje = 0;	//puntaje por apuesta
	private String fase;		
	
	
	public Persona() {
	}

	public Persona(String nombre, int id, int puntaje, String fase) {
		super();
		this.nombre = nombre;		
		this.id = id;				
		this.puntaje = puntaje;
		this.fase = fase;
	}
	

}

