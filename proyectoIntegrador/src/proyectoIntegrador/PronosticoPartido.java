package proyectoIntegrador;

import java.sql.*;
import java.util.Map;

public class PronosticoPartido extends Pronostico{
	


	
	public PronosticoPartido() {
		
	}
	
	//Esta funcion devuelve 1 si se pronostico que gane el partido el equipo 1, 0.5 si empatan y 0 si gano el equipo 2
		//es igual al metodo usado para Resultado
	public void getPronostico(Map<String, Integer> Puntaje, String Equipo1, String Equipo2, float resultado, Statement stmt) throws PronosticosError {
		try {
			// Pasa algo con la query que toma a equipo1 y a equipo2 como integer y no strings
			ResultSet rs = stmt.executeQuery(String.format("SELECT participante, gana1, empate, gana2 FROM pronosticos WHERE equipo1=%1$s AND equipo2=%2$s",Equipo1,Equipo2));
			while (rs.next()) {
				if (resultado == 1 && rs.getString("gana1").equals("X")) {
					Puntaje.put(rs.getString("participante"), Puntaje.get(rs.getString("participante"))+1);
				}else if(resultado == 0.5 && rs.getString("empate").equals("X")) {
					Puntaje.put(rs.getString("participante"), Puntaje.get(rs.getString("participante"))+1);
				}else if(resultado == 0 && rs.getString("gana2").equals("X")) {
					Puntaje.put(rs.getString("participante"), Puntaje.get(rs.getString("participante"))+1);
				}
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

		

}
