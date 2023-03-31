package proyectoIntegrador;

import java.nio.file.Path;

public class ResultadoPartido extends Resultado{

	private String[] partido;
	
	public ResultadoPartido(Path resultados) {
		super(resultados);
	}

	
	
	public void setPartido(String partido) throws ResultadosError {
		this.partido = partido.split(",");
		if (this.partido.length != 5) {
			throw new ResultadosError(this.partido);
		}
	}
	
	public String getEquipo1() {
		return this.partido[Equipo1];
	}
	
	public String getEquipo2() {
		return this.partido[Equipo2];
	}
	
	public float getResultado() throws ResultadosError {
		
		//este metodo recibe una cadena de texto como parametro, la misma se divide en un vector utilizando las comas como divisor
		//se evaluan los goles por equipos y se retorna un valor dependiendo si es empate o que equipo fue el ganador
		//equipo1 = 1; equipo2 = 0; empate = 0.5
		
		try {
			int GolesEquipo1 = Integer.parseInt(this.partido[Goles1]);
			int GolesEquipo2 = Integer.parseInt(this.partido[Goles2]);
			if (GolesEquipo1 > GolesEquipo2) {
				return 1;
			}else if(GolesEquipo1 == GolesEquipo2){
				return (float) 0.5;
			}else {
				return 0;
			}
		}catch(NumberFormatException e) {
			throw new ResultadosError(this.partido);
		}
		
		
	}
}
