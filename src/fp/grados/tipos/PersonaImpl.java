package fp.grados.tipos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import fp.grados.excepciones.ExcepcionPersonaNoValida;

public class PersonaImpl implements Persona {
	private String dni;
	private String nombre;
	private String apellidos;
	private String email;
	private LocalDate fecha;

	// private Integer cdni;

	/***************************************************
	 * CONSTRUCTOR 1
	 *************************************************/

	public PersonaImpl(String dni, String nombre, String apellidos,
			LocalDate fecha, String email) {

		checkDNI(dni);
		checkEmail(email);

		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.fecha = fecha;

	}

	/***************************************************
	 * CONSTRUCTOR 2
	 *************************************************/

	public PersonaImpl(String dni, String nombre, String apellidos,
			LocalDate fecha) {
		checkDNI(dni);

		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = "";
		this.fecha = fecha;

	}

	/***************************************************
	 * CONSTRUCTOR 3
	 *************************************************/
	// â€œ12345678Z,Juan,LÃ³pez GarcÃ­a,20/01/1998,juan@acmemail.comâ€�
	public PersonaImpl(String s) {

		// 1Âº Trozear la cadena
		String[] trozos = s.split(",");

		// 2Âº chequear que el numero de valores es correcto
		if (trozos.length != 5) {
			throw new IllegalArgumentException();
		}

		// 3Âº Copiar y transformar cada subcadena al atributo correspondiente
		String dni = trozos[0].trim();
		String nombre = trozos[1].trim();
		String apellidos = trozos[2].trim();
		LocalDate fecha = LocalDate.parse(trozos[3].trim(),
				DateTimeFormatter.ofPattern("d/M/yyyy"));
		String email = trozos[4].trim();

		// 4Âº chequear las restricciones del tipo
		checkDNI(dni);
		checkEmail(email);

		// 5Âº copiar los atributos
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha = fecha;
		this.email = email;

	}

	/*************************************************** EXCEPCIONES ***************************************************/

	private void checkEmail(String email) {
		if (email != "" && !email.contains("@")) {
			throw new ExcepcionPersonaNoValida("Por favor ingrese su "
					+ "email de nuevo, se ha producido un error");
		}
	}

	private void checkDNI(String dni) {
		if (dni.length() != 9) {
			throw new ExcepcionPersonaNoValida("longitud del dni inesperada");
		}
		Integer Nif = 0;
		try {
			String numero = dni.substring(0, dni.length() - 1);
			Nif = new Integer(numero);
		} catch (NumberFormatException e) {
			throw new ExcepcionPersonaNoValida("Error al introducir el dni");
		}
		Character letter = dni.charAt(dni.length() - 1);
		if (!Character.isAlphabetic(letter)) {
			throw new ExcepcionPersonaNoValida("Error al introducir el dni");
		}

		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		Character letra = letras.charAt(Nif % letras.length());

		if (!letra.equals(letter)) {
			throw new ExcepcionPersonaNoValida(
					"Se ha producido un error al introducir el dni");
		}

	}

	/***************************************************
	 * GETTERS AND SETTERS
	 *******************************************/

	public String getDNI() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fecha;
	}

	public String getEmail() {
		return email;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setDNI(String dni) {
		checkDNI(dni);
		this.dni = dni;
	}

	public void setFechaNacimiento(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Integer getEdad() {
		LocalDate edad = LocalDate.now();
		return (int) fecha.until(edad, ChronoUnit.YEARS);
	}

	public void setEmail(String email) {
		checkEmail(email);
		this.email = email;
	}

	/***************************************************
	 * EQUALS HASHCODE Y COMPARETO
	 *************************************/

	public boolean equals(Object per) {

		boolean pers = false;
		if (per instanceof Persona) {
			Persona p = (Persona) per;
			pers = getApellidos().equals(p.getApellidos())
					&& getNombre().equals(p.getNombre())
					&& getDNI().equals(p.getDNI());
		}
		return pers;
	}

	public int hashCode() {
		return getApellidos().hashCode() + getNombre().hashCode() * 31
				+ getDNI().hashCode() * 31 * 31;

	}

	public int compareTo(Persona p) {
		int pers = getApellidos().compareTo(p.getApellidos());
		if (pers == 0) {
			pers = getNombre().compareTo(p.getNombre());

			if (pers == 0) {
				pers = getDNI().compareTo(p.getDNI());
			}
		}
		return pers;
	}

	/*************************************************** REPRESENTACION ************************************************/

	public String toString() {
		return getDNI() + " - " + getApellidos() + "," + getNombre() + " - "
				+ getFechaNacimiento()
						.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	}
}
