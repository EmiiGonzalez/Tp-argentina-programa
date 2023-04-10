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



	public void setPuntajeApostador(int gana, int pierde ) throws ErrorEnCargaDeDatos {

		
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
					
					if (resultadoApuesta) {											//si el resultado de la apuesta coincide o no, sumo o resto los puntos enviados por parametros segun corresponda
						persona.setPuntaje(gana);	
						iterador.remove();											//se remueve la linea para liberar memoria y tiempo de ejecucion
					}	else {
						persona.setPuntaje(pierde);
						iterador.remove();
					}
					this.getApuestasResultado().add(persona);						//se agrega la persona a la nueva lista con los resultados 
					
				}
				
				
			}
			
			
		}	
				
		setPuntajeFinalLista();		//llamo al metodo privado setPuntajeFinalLista para que me cree una nueva lista de tipo hashmap filtrandome por participante unico 
	}
	
	
	private void setPuntajeFinalLista() {			//el metodo es privado para que no se pueda acceder desde afuera 
		
		for (Persona p : this.getApuestasResultado()) {						//itero la lista con el resultado de cada partido por persona
			int id = p.getId();												//obtengo el id de la persona
			
			if(getPuntajeFinalLista().containsKey(id)) {					//si el map contiene el id
				
				Persona personaMap = getPuntajeFinalLista().get(id);		// obtengo el objeto persona del map utilizando el id como referencia de indice llave:valor
				
				int puntosPersonaLista = p.getPuntaje();					// obtengo el puntaje de la persona iterada
				int puntosPersonaMap = personaMap.getPuntaje();				// obtengo el puntaje de la persona del map ya filtrada
				
				personaMap.setPuntaje(puntosPersonaMap + puntosPersonaLista);	//seteo el nuevo puntaje de la persona del map 
			}	else {														//si el id no existe en el map
				getPuntajeFinalLista().put(id, p);							//agrego al map a la nueva persona, enviandole como llave el id y el objeto Persona como valor(este contiene el puntaje inicial)
			}
			
		}
		
	}
				
}
				
		
	

