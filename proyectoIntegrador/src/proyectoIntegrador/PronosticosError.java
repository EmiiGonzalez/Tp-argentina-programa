package proyectoIntegrador;

public class PronosticosError extends Exception {
	private final String[] partido;
	public PronosticosError (String[] partido) {
		this.partido = partido;
	}
}
