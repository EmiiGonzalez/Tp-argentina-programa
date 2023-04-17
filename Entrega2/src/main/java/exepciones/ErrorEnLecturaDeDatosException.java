package exepciones;

import java.io.IOException;

public class ErrorEnLecturaDeDatosException extends Exception {

	public ErrorEnLecturaDeDatosException(String mensaje, IOException causa) {
		super(mensaje, causa);
	}

	public ErrorEnLecturaDeDatosException(String mensaje) {
		super(mensaje);
	}
}
