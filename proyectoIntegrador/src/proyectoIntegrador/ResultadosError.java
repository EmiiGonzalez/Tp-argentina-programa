package proyectoIntegrador;

public class ResultadosError extends Exception {
	private final String[] partido;
	public ResultadosError (String[] partido) {
		this.partido = partido;
	}
}
