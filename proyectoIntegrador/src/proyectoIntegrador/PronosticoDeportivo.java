package proyectoIntegrador;

import java.nio.file.Path;
import java.util.List;

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
	public void Puntaje() {
		
		//se instancian los objetos pronostico(prn) y resultado (res) 
		Pronostico prn = new Pronostico(pronostico);
		Resultado res = new Resultado(resultado);
		
		//inicializacion de variable puntaje para contar aciertos
		int puntaje = 0;
		
		//se crean dos listas/vectores/arrays con los resultados y pronostico  
		List <String> resultados = res.getResultados();
		List <String> pronosticos = prn.getPronosticos();
		
		//se iteran ambas listas con un ciclo for y se evalua si los valores de retorno son iguales, en caso de serlo se suma un punto
		for(int i=0;i<resultados.size();i++) {
			//Primero obtengo la informacion de la posicion del array especificada con i o .get y luego comparo ambos vectores 
			if(res.getResultado(resultados.get(i)) == prn.getPronostico(pronosticos.get(i))) {
				puntaje +=1;
			}
		}
		System.out.println(puntaje);
	}
}
