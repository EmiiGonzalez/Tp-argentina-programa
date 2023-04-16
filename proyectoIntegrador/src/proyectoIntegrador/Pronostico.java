package proyectoIntegrador;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//este bloque define la clase Pronostico, lee y procesa los datos del archivo pronosticos
public class Pronostico {
	

	public Pronostico() {
		//Pronostico(pronosticos) es el constructor de la clase Pronostico
	
	}
	

	public List<String> getParticipantes(Statement stmt){
		List<String> participantes = new ArrayList<String>();
		try {
		ResultSet rs=stmt.executeQuery("select distinct participante from pronosticos");
		while (rs.next()){
			participantes.add(rs.getString("participante"));
		}
		}catch(Exception e){ System.out.println(e);}
		return participantes;
	}
	
	
}
	