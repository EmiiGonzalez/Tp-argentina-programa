package modelo;

import lombok.Data;

@Data
public class Equipo {
	private String nombre;		//recibe el nombre
	private int goles;			//recibe la cantidad de goles
	private String aciertos;	//recibe una X o un espacio
	
	public Equipo() {
	}
	
	public Equipo(String nombre, int goles) {
		this.nombre = nombre;
		this.goles = goles;
	}
	public Equipo(String nombre, String aciertos) {
		this.nombre = nombre;
		this.aciertos = aciertos;
	}
	
	
}
