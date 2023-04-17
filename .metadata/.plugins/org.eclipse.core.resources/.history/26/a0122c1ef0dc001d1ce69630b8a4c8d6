package modelo;

import exepciones.ErrorEnCargaDeDatos;
import lombok.Data;

@Data
public class Torneo {
	
	
	//todos los atributos se generan a partir del atributo linea
	private Partido partido;
	private String linea;		//recibo este atributo desde el main, el mismo corresponde a una linea del archivo
	private String rondaPartido;		
	private String nombreParticipante;
	private Equipo equipo1; 
	private Equipo equipo2; 


	
	public Torneo() {
	}
	
	public Torneo(String linea, char tipoArchivo) throws ErrorEnCargaDeDatos {
		this.linea = linea;
		
		if (Character.toLowerCase(tipoArchivo) == 'p') {		//dependiendo el tipo de iteracion enviada como parametro se decide cual partido crear
            this.generarPartidoPronostico();					//se genera el partido
        } else if(Character.toLowerCase(tipoArchivo) == 'r'){
            this.generarPartidoResultado();
        }
		
	}
	
	private void generarEquiposResultado() {									//este metodo crea ambos equipos con los datos del archivo resultado, se asigna privado para que se pueda acceder fuera de la clase
		 String numeroDeRonda = getLinea().split(",")[0];						//obtengo el numero de ronda
		 String nombreEquipo1 = getLinea().split(",")[1];						//obtengo el nombre del equipo 1
		 int golesEquipo1 = Integer.parseInt(getLinea().split(",")[2]);			//obtengo la cantidad de goles del equipo 1
		 String nombreEquipo2 = getLinea().split(",")[4];						//obtengo el nombre del equipo 2
		 int golesEquipo2 = Integer.parseInt(getLinea().split(",")[3]);			//obtengo la cantidad de goles del equipo 2
		 
		 this.equipo1 = new Equipo(nombreEquipo1, golesEquipo1);				//al atributo equipo1 le asigno un nuevo objeto de tipo equipo y paso los valores		
		 this.equipo2 = new Equipo(nombreEquipo2, golesEquipo2);				//lo mismo 
		 
		 this.setRondaPartido(numeroDeRonda);									//al atributo rondaPartido le asigno el valor de la ronda, este sera utilizado posteriormente
		 																		//para discriminar el puntaje por rondas
		 
	}
	
	public void generarPartidoResultado() throws ErrorEnCargaDeDatos {			//este metodo llama al metodo privado generarEquiposResultado, y crea un nuevo objeto de tipo partido
																				//se dividen los metodos para modularizar funcionamientos
		this.generarEquiposResultado();											//llamo al metodo privado que me genera los equipos correspondientes al archivo resultado
		int idPartido = Integer.parseInt(this.getLinea().split(",")[5]);		//obtengo el id unico del partido
		this.partido = new Partido(this.getEquipo1(), this.getEquipo2(), this.getRondaPartido(), idPartido);	// creo un nuevo objeto del tipo partido pasando los datos creados anteriormente
	}
	
	private void generarEquiposPronostico() {						//este metodo crea ambos equipos con los datos del archivo pronostico, se asigna privado para que se pueda acceder fuera de la clase
		 String nombreEquipo1 = this.getLinea().split(",")[1];		//obtengo el nombre del equipo 1
		 String golesEquipo1 = this.getLinea().split(",")[2];		//obtengo la cantidad de goles del equipo 1 (si hay X o espacio)
		 String nombreEquipo2 = this.getLinea().split(",")[5];		//obtengo el nombre del equipo 2
		 String golesEquipo2 = this.getLinea().split(",")[4];		//obtengo la cantidad de goles del equipo 2 (si hay X o espacio)
		 String ronda = this.getLinea().split(",")[0];				//obtengo el nombre del participante

		 
		 this.equipo1 = new Equipo(nombreEquipo1, golesEquipo1);	//al atributo equipo1 le asigno un nuevo objeto de tipo equipo y paso los valores
		 this.equipo2 = new Equipo(nombreEquipo2, golesEquipo2);	//lo mismo 
		 
		 this.setNombreParticipante(ronda);							//al atributo nombreParticipante le paso el string con el nombre
		 
		 
	}
	
	public void generarPartidoPronostico() throws ErrorEnCargaDeDatos {		//este metodo llama al metodo privado generarEquiposPronostico, y crea un nuevo objeto de tipo partido 
																			//se dividen los metodos para modularizar funcionamientos
		this.generarEquiposPronostico();									//llamo al metodo privado que me genera los equipos correspondientes del archivo pronostico 
		int idPartido = Integer.parseInt(this.getLinea().split(",")[6]);	//obtengo el id unico del partido
		int idPersona = Integer.parseInt(this.getLinea().split(",")[7]);	//obtengo el id unico de la persona participante
		this.partido = new Partido(this.getEquipo1(), this.getEquipo2(), this.getRondaPartido(), idPartido);	// creo un nuevo objeto del tipo partido pasando los datos creados anteriormente
		this.partido.setParticipante(nombreParticipante);					//al objeto partido le asigno el nombre del participante
		this.partido.setIdParticipante(idPersona);							//al objeto partido le asigno el id de la persona
	}
	
	
	
	
}
	

