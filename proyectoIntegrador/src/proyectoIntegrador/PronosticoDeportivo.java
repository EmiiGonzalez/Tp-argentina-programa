package proyectoIntegrador;

import java.nio.file.Path;
import java.util.List;

public class PronosticoDeportivo {
	private Path resultado;
	private Path pronostico;
	
	public PronosticoDeportivo(Path resultado, Path pronostico) {
		this.resultado = resultado;
		this.pronostico = pronostico;
	}
	
	public void Puntaje() {
		Pronostico prn = new Pronostico(pronostico);
		Resultado res = new Resultado(resultado);
		int puntaje = 0;
		List <String> resultados = res.getResultados();
		List <String> pronosticos = prn.getPronosticos();
		for(int i=0;i<resultados.size();i++) {
			if(res.getResultado(resultados.get(i)) == prn.getPronostico(pronosticos.get(i))) {
				puntaje +=1;
			}
		}
		System.out.println(puntaje);
	}
}
