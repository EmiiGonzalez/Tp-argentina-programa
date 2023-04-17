package baseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import modelo.Persona;
import modelo.Resultado;

@Data
public class BaseDeDatos {

	private String driver;
	private String ip;
	private String usuario;
	private String pass;
	private String api;
	private String dbName;

	public BaseDeDatos(String api, String ip, String dbName, String usuario, String pass) {
		this.ip = ip;
		this.usuario = usuario;
		this.pass = pass;
		this.api = api;
		this.dbName = dbName;
	}

	public BaseDeDatos() {
	}

	public void setDatosEnBase(Resultado datos, String control) {

		try {
			Connection con = DriverManager.getConnection("jdbc:" + this.getApi() + "://" + this.getIp() + "/" + this.getDbName(), this.getUsuario(),this.getPass()); // conexion

			if (control.equals("P")) { // si el control es igual a P o T se prepara la sentencia sql para agregar o
										// actualizar la bd en un string

				// tengo que poner el campo puntajeTotal entre comillas porque si no me da
				// error, asi mismo se encuentra de forma identica en postgress
				String query = "INSERT INTO persona (id, nombre, \"puntajeTotal\") VALUES (?, ?, ?) ON CONFLICT (id) DO UPDATE SET nombre = EXCLUDED.nombre, \"puntajeTotal\" = EXCLUDED.\"puntajeTotal\"";
				PreparedStatement ps = con.prepareStatement(query); // para preparar la query que posteriormente se
																	// ejecutara

				for (Persona persona : datos.getPuntajeTotalPorPersona().values()) { // se recorre el objeto y se
																						// preparan los datos
					String nombre = persona.getNombre();
					int id = persona.getId();
					int puntaje = persona.getPuntaje();

					ps.setInt(1, id); // se agrega el primer dato a la sentencia sql
					ps.setString(2, nombre); // se agrega el segundo dato a la sentencia sql
					ps.setInt(3, puntaje); // se agrega el tercer dato a la sentencia sql
					ps.executeUpdate(); // se ejecuta la sentencia
				}

				ps.close();
			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	
	public List<String> getPronosticos(){
		List <String> partidosPonostico = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection("jdbc:" + this.getApi() + "://" + this.getIp() + "/" + this.getDbName(), this.getUsuario(),this.getPass());// conexion
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery("SELECT * FROM public.pronosticos");
			
			String cabecera = "participante,Equipo_1,Gana_1,Empate,Gana_2,Equipo_2,id_partido,id_persona";
			partidosPonostico.add(cabecera);
			
			while (rst.next()) {
					String linea = rst.getString("participante") + "," +
							rst.getString("Equipo_1") + "," +
							rst.getString("Gana_1") + "," +
							rst.getString("Empate") + "," +
							rst.getString("Gana_2") + "," +
							rst.getString("Equipo_2") + "," +
							rst.getLong("id_partido") + "," +
							rst.getLong("id_persona");
					partidosPonostico.add(linea);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return partidosPonostico;
	}

}

