package modelo;

import exepciones.ErrorEnCargaDeDatos;
import lombok.Data;

@Data

//existiran dos tipos de objeto Partido, uno con datos del archivo pronostico y otro del archivo resultado, 
//estos no se mezclaran y ambos se usaran para comparacion y obtencion de puntaje

public class Partido {
	private Equipo equipo1; // recibe un equipo
	private Equipo equipo2; // recibe otro equipo
	private String ronda = ""; // recibe a que ronda pertenece en caso que sea de tipo resultado
	private String participante = ""; // recibe un participante en caso que sea de tipo pronostico
	private int idPartido; // recibe un id de partido que es unico
	private int idParticipante; // recibe un id de participante que es unico por participante

	public Partido() {
	}

	public Partido(Equipo equipo1, Equipo equipo2, String ronda, int id) throws ErrorEnCargaDeDatos {
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.ronda = ronda;
		this.idPartido = id;

	}

	public int getGanador() throws ErrorEnCargaDeDatos {
		String aciertosEquipo1 = this.getEquipo1().getAciertos(); // obtengo los aciertos del equipo 1 (si hay X o no)
																	// en el archivo pronostico
		String aciertosEquipo2 = this.getEquipo2().getAciertos(); // obtengo los aciertos del equipo 1 (si hay X o no)
																	// en el archivo pronostico

		int golesEquipo1 = this.getEquipo1().getGoles(); // obtengo los goles del equipo 1 del archivo resultado
		int golesEquipo2 = this.getEquipo2().getGoles(); // obtengo los goles del equipo 1 del archivo resultado

		int ganador = 0; // variable para obtener el valor del ganador y posteriormente retornarla

		if (aciertosEquipo1 != null && aciertosEquipo2 != null) { // condicional por si se llegaran a mesclar los datos

			if (aciertosEquipo1.equals("X")) { // si se aposto por el equipo1 se retorna 1
				ganador = 1;
			} else if (aciertosEquipo2.equals("X")) { // si se aposto por el equipo2 se retorna 3
				ganador = 3;
			} else if (aciertosEquipo1.equals(" ") && aciertosEquipo2.equals(" ")) { // si se aposto por empate retorno
																						// 3
				ganador = 2;
			} else {
				throw new ErrorEnCargaDeDatos("Error en la carga de datos de pronostico \nse esperaba una X"); // en
																												// caso
																												// que
																												// no se
																												// apostara
																												// lanzo
																												// error
			}

		}

		if (aciertosEquipo1 == null && aciertosEquipo2 == null) { // condicional por si se llegaran a mesclar los datos

			if (golesEquipo1 > golesEquipo2) { // si el equipo 1 gano retorno 1
				ganador = 1;
			} else if (golesEquipo2 > golesEquipo1) { // si el equipo 2 gano retorno 3
				ganador = 3;
			} else if (golesEquipo2 == golesEquipo1) { // si hay empate retorno 2
				ganador = 2;
			} else {
				throw new ErrorEnCargaDeDatos(
						"Error en la carga de datos de resultado \nse esperaban valores numericos"); // en caso que haya
																										// error con
																										// valores en
																										// goles
			}

		}

		return ganador;
	}

}
