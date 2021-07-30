package dto;

public class Pelicula {
private Integer id_pelicula = null;
private String nombre;
private String director;
private String genero;
private String protagonista;

public Pelicula(){}

public Integer getId_pelicula() {
	return id_pelicula;
}
public void setId_pelicula(Integer id_pelicula) {
	this.id_pelicula = id_pelicula;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getDirector() {
	return director;
}
public void setDirector(String director) {
	this.director = director;
}
public String getGenero() {
	return genero;
}
public void setGenero(String genero) {
	this.genero = genero;
}
public String getProtagonista() {
	return protagonista;
}
public void setProtagonista(String protagonista) {
	this.protagonista = protagonista;
}


}
