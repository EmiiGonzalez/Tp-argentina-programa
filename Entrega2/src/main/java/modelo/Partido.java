package modelo;

import exepciones.ErrorEnCargaDeDatos;
import lombok.Data;

@Data
public class Partido{
	private Equipo equipo1;
	private Equipo equipo2;
	private String ronda = "";
	private String participante = "";
	private int idPartido;
	private int idParticipante;
	
	
	public Partido() {
	}
	public Partido(Equipo equipo1, Equipo equipo2, String ronda, int id) throws ErrorEnCargaDeDatos  {
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.ronda = ronda;
		this.idPartido = id;
		
	}
	
	
	public int getGanador() throws ErrorEnCargaDeDatos {
	    String aciertosEquipo1 = this.getEquipo1().getAciertos();
	    String aciertosEquipo2 = this.getEquipo2().getAciertos();
	    
	    int golesEquipo1 = this.getEquipo1().getGoles();
	    int golesEquipo2 = this.getEquipo2().getGoles();
	    
	    int ganador = 0;
	    

	    
	    if (aciertosEquipo1 != null && aciertosEquipo2 != null) {
	    
		    if (aciertosEquipo1.equals("X")) {
		    	ganador = 1;
		    } else if (aciertosEquipo2.equals("X")) {
		    	ganador = 3;
		    } else if (aciertosEquipo1.equals(" ") && aciertosEquipo2.equals(" ")) {
		    	ganador =  2;
		    } else {
		    	throw new ErrorEnCargaDeDatos("Error en la carga de datos de pronostico \nse esperaba una X");
		    }
		   
	    }
	    
	    if (aciertosEquipo1 == null && aciertosEquipo2 == null) {		//condicional por si se llegaran a mesclar los datos
	    	
	    	if(golesEquipo1>golesEquipo2) {
	    		ganador = 1;
	    	}	else if (golesEquipo2 > golesEquipo1){
	    		ganador = 3;
	    	}	else {
	    		ganador = 2;
	    	}
	    	
	    }
	    
	    return ganador;
	}
	
	
	
}
