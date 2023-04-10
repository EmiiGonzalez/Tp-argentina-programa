package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import exepciones.ErrorEnCargaDeDatos;
import lombok.Data;

@Data
public class Resultado {
	private Ronda rondaPronostico;
	private Ronda rondaResultado;
	private List<Persona> apuestasResultado = new ArrayList<>();
	private Map <Integer, Persona> puntajeFinalLista = new HashMap<>();
	
	
	
	
	public Resultado(Ronda rondaPronostico, Ronda rondaResultado) {
		this.rondaPronostico = rondaPronostico;
		this.rondaResultado = rondaResultado;


	}
	public Resultado() {
		
	}



	public void setPuntajeApostador(int gana, int pierde) throws ErrorEnCargaDeDatos {

		
		for (Partido partidoResultado : this.getRondaResultado().getPartidos()) {

			int idPartidoR = partidoResultado.getIdPartido();		//obtengo el id del partido resultado
			String rondaPartido = partidoResultado.getRonda();		//obtengo a que ronda pertenece el partido
			int ganadorR = partidoResultado.getGanador();			//obtengo que equipo gano 1=Equipo1, 2=Empate, 3=Equipo2
			
			Iterator<Partido> iterador = this.getRondaPronostico().getPartidos().iterator();
			
			while(iterador.hasNext()) {
			
				Partido partidoPronostico = iterador.next();
				
				String nombreParticipante = partidoPronostico.getParticipante();	//obtengo el nombre del participante
				int idPartidoP = partidoPronostico.getIdPartido();					//obtengo el id del partido
				int idPersonaP = partidoPronostico.getIdParticipante();				//obtengo el id del participante
				int ganadorP = partidoPronostico.getGanador();						//obtengo a que equipo se aposto  1=Equipo1, 2=Empate, 3=Equipo2
								
				boolean controlId = idPartidoP == idPartidoR;						//evaluo si el valor del id del partido coincide en ambos
				boolean resultadoApuesta = ganadorR == ganadorP;					//evaluo si el resultado del ganador coincide con el resultado apostado
				
				if (controlId) {													//si los id de los partidos son iguales
					Persona persona = new Persona();								//instancio un nuevo objeto persona
					persona.setId(idPersonaP);											//al objeto persona creado le asigno el id obtenido en pronostico
					persona.setNombre(nombreParticipante);								//al objeto persona creado le asigno el nombre obtenido en pronostico
					persona.setRonda(rondaPartido);										//al objeto persona creado le asigno la ronda obtenido en resultado
					
					if (resultadoApuesta) {											//si el resultado de la apuesta coincide, sumo los puntos enviados por parametros
						
						persona.setPuntaje(1);
						/*
						System.out.println("*******");
						System.out.println(nombreParticipante);
						System.out.println("Resultado: " + ganadorR);
						System.out.println("Pronostico: "+ ganadorP);
						System.out.println("idPartidoP: " + idPartidoP);
						System.out.println("idPartidoR: " + idPartidoR);
						System.out.println("Gano");
						*/
						iterador.remove();
					}
					this.getApuestasResultado().add(persona);
					
				}
				
				
			}
			
			
		}	
				
		setPuntajeFinalLista();
	}
	
	
	public void setPuntajeFinalLista() {
		
		for (Persona p : this.getApuestasResultado()) {
			int id = p.getId();
			
			if(getPuntajeFinalLista().containsKey(id)) {
				
				Persona personaMap = getPuntajeFinalLista().get(id);
				
				int puntosPersonaLista = p.getPuntaje();
				int puntosPersonaMap = personaMap.getPuntaje();
				
				personaMap.setPuntaje(puntosPersonaMap + puntosPersonaLista);
			}	else {
				getPuntajeFinalLista().put(id, p);
			}
			
		}
		
	}
				
}
				
		
	
