package bo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.PeliculaDAO;
import dto.Pelicula;
import general.DAOException;

@ManagedBean(name="peliculaBO")
@SessionScoped
public class PeliculaBO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pelicula peli;
	private PeliculaDAO peliDAO;
	
	public PeliculaBO() {
		peliDAO = new PeliculaDAO();
		peli = new Pelicula();
	}
	
	public List<Pelicula> getPeliculas(){
		try {
			return peliDAO.obtenerTodos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String edit(String id) {
		try {
			peli = peliDAO.obtener(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "addPeli";
	}
	
	public String save() {
		try {
			if(peli.getId_pelicula()==null || peli.getId_pelicula()==0) {
				peliDAO.insertar(peli);
			}else {
				peliDAO.modificar(peli);
			}
			peli = new Pelicula();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "peliculas";
	}
	
	public String eliminar(String nombre) {
		try {
			peli = peliDAO.obtener(nombre);
			peliDAO.eliminar(peli);
			peli = new Pelicula();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return "peliculas";
	}

	public Pelicula getPelicula() {
		return peli;
	}

	public void setPelicula(Pelicula peli) {
		this.peli = peli;
	}	
}