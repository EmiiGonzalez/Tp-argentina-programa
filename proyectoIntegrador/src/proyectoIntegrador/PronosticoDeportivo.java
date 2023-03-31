package proyectoIntegrador;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PronosticoDeportivo {
	//variables de iniciacion
	private Path resultado;		
	private Path pronostico;
	
	public PronosticoDeportivo(Path resultado, Path pronostico) {
		//constructor de la clase, usado al instanciar el objeto
		this.resultado = resultado;
		this.pronostico = pronostico;
	}
	
	
	//metodo para verificar si se acerto o no y que retorne un puntaje en base a esto
	public void Puntaje(){
		
		//se instancian los objetos pronostico(prn) y resultado (res) 
		PronosticoPartido prn = new PronosticoPartido(this.pronostico);
		ResultadoPartido res = new ResultadoPartido(this.resultado);
		
		
		//se crean dos listas/vectores/arrays con los resultados y pronostico  
		List <String> resultados = res.getResultados();
		List <String> pronosticos = prn.getPronosticos();
		Map<String, Integer> puntaje = new HashMap<String, Integer>();
		List<String> participantes = prn.getParticipantes(); 
		
		for (int i=0; i<participantes.size();i++) {
			puntaje.put(participantes.get(i), 0);
		}
		
		//se iteran ambas listas con un ciclo for y se evalua si los valores de retorno son iguales, en caso de serlo se suma un punto
		for(int i=0;i<pronosticos.size();i++) {
			//Primero obtengo la informacion de la posicion del array especificada con i o .get y luego comparo ambos vectores 
			try {
				prn.setPartido(pronosticos.get(i));
				for(int j=0;j<resultados.size();j++) {
					res.setPartido(resultados.get(j));
					if((prn.getEquipo1().equals(res.getEquipo1())) && (prn.getEquipo2().equals(res.getEquipo2())) && (res.getResultado() == prn.getPronostico())) {
						puntaje.put(prn.getParticipante(), puntaje.get(prn.getParticipante())+1);
						break;
					}
				}
			} catch (ResultadosError | PronosticosError e) {
				e.printStackTrace();
			}
		}
		System.out.println(puntaje);
	}
}
