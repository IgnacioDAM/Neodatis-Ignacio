package aed.neodatis;

public class Jugadores {
	private String nombre;
	private String deporte;
	private String ciudad;
	private int edad;
	private Pais pais;

	public Jugadores() {
	}

	public Jugadores(String nombre, String deporte, String ciudad, int edad, Pais pais) {
		this.nombre = nombre;
		this.deporte = deporte;
		this.ciudad = ciudad;
		this.edad = edad;
		this.pais = pais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		if (pais == null) {
			return "Nombre: " + nombre + "\nEdad: " + edad + "\nDeporte: " + deporte + "\nCiudad: " + ciudad
					+ "\nPais: desconocido";
		} else {
			return "Nombre: " + nombre + "\nEdad: " + edad + "\nDeporte: " + deporte + "\nCiudad: " + ciudad
					+ "\nPais: " + pais.getNombrePais();
		}

	}

}
