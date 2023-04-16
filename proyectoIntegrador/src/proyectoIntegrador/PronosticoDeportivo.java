package proyectoIntegrador;

import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PronosticoDeportivo {
	//variables de iniciacion
	private Path resultado;
	private int extraRonda;
	private int extraFase;
	private int PuntoxPartido;
	private int PartidoxRonda;
	private Statement stmt;
	
	public PronosticoDeportivo(Path resultado, int extraRonda, int extraFase, int PuntoxPartido, int PartidoxRonda, Statement stmt) {
		//constructor de la clase, usado al instanciar el objeto
		this.resultado = resultado;
		this.extraRonda = extraRonda;
		this.extraFase = extraFase;
		this.PartidoxRonda = PartidoxRonda;
		this.PuntoxPartido = PuntoxPartido;
		this.stmt = stmt;
	}
	
	public List<String> getParticipantes(){
		List<String> participantes = new ArrayList<String>();
		try {
		ResultSet rs=stmt.executeQuery("select distinct participante from pronosticos");
		while (rs.next()){
			participantes.add(rs.getString("participante"));
		}
		}catch(Exception e){ System.out.println(e);}
		return participantes;
		
	}
	
	
	//metodo para verificar si se acerto o no y que retorne un puntaje en base a esto
	public Map<String,Integer> Puntaje(){
		
		//se instancian los objetos pronostico(prn) y resultado (res) 
		PronosticoPartido prn = new PronosticoPartido();
		ResultadoPartido res = new ResultadoPartido(this.resultado);
		
		
		//se crean dos listas/vectores/arrays con los resultados y pronostico  
		List <String> resultados = res.getResultados();
		Map<String, Integer> puntaje = new HashMap<String, Integer>();
		List<String> participantes = prn.getParticipantes(stmt);
		float resultado;
		List<Boolean> PuntosExtra=new ArrayList<Boolean>(Arrays.asList(new Boolean[participantes.size()]));
		Collections.fill(PuntosExtra, Boolean.TRUE);
		List<Integer> Contador  = new ArrayList<Integer>(Collections.nCopies(participantes.size(), 0));
		
		for (int i=0; i<participantes.size();i++) {
			puntaje.put(participantes.get(i), 0);
		}
		
		//se iteran ambas listas con un ciclo for y se evalua si los valores de retorno son iguales, en caso de serlo se suma un punto
		for(int i=0;i<resultados.size();i++) {
			//Primero obtengo la informacion de la posicion del array especificada con i o .get y luego comparo ambos vectores 
			try {
				res.setPartido(resultados.get(i));
				resultado = res.getResultado();
				prn.getPronostico(puntaje, res.getEquipo1(),res.getEquipo2(), resultado, stmt);
				for (int j=0; j<participantes.size();j++) {
					Contador.set(j, Contador.get(j) + 1);
					if (Contador.get(j)!= puntaje.get(participantes.get(j))) {
						PuntosExtra.set(j, false);
					}
					
				}
				if (i%PartidoxRonda == 0) {
					for (int j=0;j<participantes.size();j++) {
						if(PuntosExtra.get(j)) {
							puntaje.put(participantes.get(j), puntaje.get(participantes.get(j)+this.extraRonda));
						}
					}
				}
			} catch (ResultadosError | PronosticosError e) {
				e.printStackTrace();
			}
		}
		return puntaje;
		
	}
}
