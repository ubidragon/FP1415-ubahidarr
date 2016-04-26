package fp.grados.tipos;

import java.time.LocalDate;

public interface Persona extends Comparable<Persona> {

	String getDNI();

	String getNombre();

	String getApellidos();

	LocalDate getFechaNacimiento();

	String getEmail();

	Integer getEdad();

	// sets
	void setNombre(String nombre);

	void setApellidos(String apellidos);

	void setDNI(String DNI);

	void setFechaNacimiento(LocalDate fecha);

	void setEmail(String email);

}
