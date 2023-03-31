package proyectoIntegrador;

import java.nio.file.Path;

public class PronosticoPartido extends Pronostico{
	

	private String[] partidos;
	private String Eq1;
	private String Eq2;
	
	public PronosticoPartido(Path pronosticos) {
		super(pronosticos);
	}
	
	
	public void setPartido(String partido) throws PronosticosError {
		this.partidos = partido.split(",");
		if (this.partidos.length != 6) {
			throw new PronosticosError(this.partidos);
		}
		this.Eq1 = partidos[Equipo1];
		this.Eq2 = partidos[Equipo2];
	}
	
	public String getEquipo1() {
		return Eq1;
	}
	
	public String getEquipo2() {
		return Eq2;
	}
	
	//Esta funcion devuelve 1 si se pronostico que gane el partido el equipo 1, 0.5 si empatan y 0 si gano el equipo 2
		//es igual al metodo usado para Resultado
	public float getPronostico() throws PronosticosError {
		
		if (this.partidos[Gana1].equals("X")) {
			return 1;
		}else if(this.partidos[Empate].equals("X")){
			return (float) 0.5;
		}else {
			return 0;
		}
	}
	
	public String getParticipante() {
		return this.partidos[Participante];
	}
		

}
