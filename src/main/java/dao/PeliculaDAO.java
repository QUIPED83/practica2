package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dto.Pelicula;
import general.ConexionBD;
import general.DAO;
import general.DAOException;

public class PeliculaDAO  implements DAO<Pelicula, String>{
	private final String INSERT = "INSERT INTO pelicula(nombre, director, genero, protagonista) VALUES (?,?,?,?)";
    private final String UPDATE = "UPDATE pelicula SET nombre = ? WHERE id_pelicula = ?";
    private final String DELETE = "DELETE FROM pelicula WHERE id_pelicula = ?";
    //private final String GETALL = "SELECT * FROM Alumno";
    private final String GETALL = "SELECT * FROM pelicula";
    private final String GETONE = "SELECT * FROM pelicula WHERE nombre=?";
	
	@Override
	public Object insertar(Pelicula obj) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
      //  String clave = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNombre());
            stmt.setString(2, obj.getDirector());
            stmt.setString(3, obj.getGenero());
            stmt.setString(4, obj.getProtagonista());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();            
           /* if (rs.next()) {
                clave = rs.getString(1);
                obj.setMatricula(clave);                
            }*/
            return true;
        } catch (SQLException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
	}
	@Override
	public boolean modificar(Pelicula obj) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, obj.getNombre());
            stmt.setInt(2, obj.getId_pelicula());
            return stmt.executeUpdate() == 0;
        } catch (SQLException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return false;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
	}
	@Override
	public boolean eliminar(Pelicula obj) throws DAOException {
		 Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        try {
	            conn = ConexionBD.obtenerConexion();
	            stmt = conn.prepareStatement(DELETE);
	            stmt.setInt(1, obj.getId_pelicula());
	            return stmt.executeUpdate() == 0;
	        } catch (SQLException ex) {
	            System.out.println("Error causado por: " + ex.getMessage());
	            return false;
	        } finally {
	            cerrarConnection(conn);
	            cerrarResultSet(rs);
	            cerrarStatement(stmt);
	        }
	}
	@Override
	public List<Pelicula> obtenerTodos() throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
               peliculas.add((Pelicula) convertir(rs, new Pelicula()));
            }
            return peliculas;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        } finally {
        	//alumnos.stream().forEach((e)->{System.out.println(e.getId());});
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
	}
	@Override
	public List<Pelicula> obtenerTodos(String[] campos) throws DAOException {
		int numero_campos;
        String select;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        try {
            numero_campos = campos.length;
            select = prepararSelect(campos, numero_campos);
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(select);
            rs = stmt.executeQuery();
            while (rs.next()) {
                peliculas.add((Pelicula) convertir(rs, new Pelicula(), campos));
            }
            return peliculas;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
	}
	@Override
	public Pelicula obtener(String nombre) throws DAOException {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pelicula peli = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETONE);
            stmt.setString(1, nombre);
            rs = stmt.executeQuery();
            if (rs.next()) {
               peli = (Pelicula) convertir(rs, new Pelicula());
            } else {
                System.out.println("No se encontro la pelicula en la base de datos");
            }
            return peli;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
	}
	
	 public Pelicula obtenerModelo() {
	        return new Pelicula();
	    }
	private String prepararSelect(String campos[], int numero_campos){
        String select = "SELECT ";
        for (int i = 0; i < numero_campos - 1; i++) {
            select = select.concat(campos[i]) + ", ";
        }
        select = select.concat(campos[numero_campos - 1]);
        select = select.concat(" FROM pelicula;");
        return select;
    }
}
