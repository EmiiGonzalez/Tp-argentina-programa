package modelo;

import exepciones.ErrorEnCargaDeDatos;
import lombok.Data;

@Data
public class Torneo {
	
	private Partido partido;
	private String linea;
	private String rondaPartido;
	private String nombreParticipante;
	private Equipo equipo1; 
	private Equipo equipo2; 


	
	public Torneo() {
	}
	
	public Torneo(String linea) {
		this.linea = linea;
		
	}
	
	public void generarEquiposResultado() {
		 String numeroDeRonda = getLinea().split(",")[0];
		 String nombreEquipo1 = getLinea().split(",")[1];
		 int golesEquipo1 = Integer.parseInt(getLinea().split(",")[2]);
		 String nombreEquipo2 = getLinea().split(",")[4];
		 int golesEquipo2 = Integer.parseInt(getLinea().split(",")[3]);
		 
		 this.equipo1 = new Equipo(nombreEquipo1, golesEquipo1);
		 this.equipo2 = new Equipo(nombreEquipo2, golesEquipo2);
		 
		 this.setRondaPartido(numeroDeRonda);
		 
	}
	
	public void generarPartidoResultado() throws ErrorEnCargaDeDatos {
		this.generarEquiposResultado();
		int idPartido = Integer.parseInt(this.getLinea().split(",")[5]);
		this.partido = new Partido(this.getEquipo1(), this.getEquipo2(), this.getRondaPartido(), idPartido);
	}
	
	public void generarEquiposPronostico() {
		 String nombreEquipo1 = this.getLinea().split(",")[1];
		 String golesEquipo1 = this.getLinea().split(",")[2];
		 String nombreEquipo2 = this.getLinea().split(",")[5];
		 String golesEquipo2 = this.getLinea().split(",")[4];
		 String ronda = this.getLinea().split(",")[0];

		 
		 this.equipo1 = new Equipo(nombreEquipo1, golesEquipo1);
		 this.equipo2 = new Equipo(nombreEquipo2, golesEquipo2);
		 
		 this.setNombreParticipante(ronda);
		 
		 
	}
	
	public void generarPartidoPronostico() throws ErrorEnCargaDeDatos {
		this.generarEquiposPronostico();
		int idPartido = Integer.parseInt(this.getLinea().split(",")[6]);
		int idPersona = Integer.parseInt(this.getLinea().split(",")[7]);
		this.partido = new Partido(this.getEquipo1(), this.getEquipo2(), this.getRondaPartido(), idPartido);
		this.partido.setParticipante(nombreParticipante);
		this.partido.setIdParticipante(idPersona);
	}
	
}
	

