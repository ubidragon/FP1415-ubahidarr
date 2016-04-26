package fp.grados.tipos.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fp.grados.excepciones.ExcepcionPersonaNoValida;
import fp.grados.tipos.Persona;
import fp.grados.tipos.PersonaImpl;

public class TestPersona {

	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcional1();
		testConstructor1Excepcional2();
		testConstructor1Excepcional3();
		testConstructor1Excepcional4();

		testConstructor2Normal();

		testSetDNINormal();
		testSetDNIExcepcional1();
		testSetDNIExcepcional2();
		testSetDNIExcepcional3();

		testSetEmail();
		testSetEmailExcepcional();
		testSetEmailExcepcional2();

	}

	/*********************************************
	 * Constructor 1
	 ***********************************************/

	private static void testConstructor1Normal() {
		System.out.println(
				"==================================Probando el primer constructor");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional1() {
		System.out.println(
				"==================================Probando el primer constructor con dni sin letra");
		testConstructor1("123456789", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional2() {
		System.out.println(
				"==================================Probando el primer constructor con dni de longitud menor de la esperada");
		testConstructor1("1234567X", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional3() {
		System.out.println(
				"==================================Probando el primer constructor con letra en dni que no se corresponde a los dígitos");
		testConstructor1("12345678X", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional4() {
		System.out.println(
				"==================================Probando el primer constructor con email sin arroba");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadiegmail.com");
	}

	/*********************************************
	 * Constructor 2
	 ***********************************************/

	private static void testConstructor2Normal() {
		System.out.println(
				"==================================Probando el segundo constructor");
		testConstructor2("20091912D", "Ismael", "Garrido",
				LocalDate.of(1993, 3, 15));
	}

	/*********************************************
	 * Check DNI
	 ***********************************************/

	private static void testSetDNINormal() {
		System.out.println("==================================Probando setDNI");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677J");
	}

	private static void testSetDNIExcepcional1() {
		System.out.println(
				"==================================Probando setDNI con dni sin letra");

		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "123456779");
	}

	private static void testSetDNIExcepcional2() {
		System.out.println(
				"==================================Probando setDNI con dni de longitud menor de la esperada");

		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677");
	}

	private static void testSetDNIExcepcional3() {
		System.out.println(
				"==================================Probando setDNI con letra en dni que no se corresponde a los dígitos");

		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677X");
	}

	/*********************************************
	 * Check Email
	 ***********************************************/

	private static void testSetEmail() {
		System.out
				.println("==================================Probando setEmail");

		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetEmail(p, "ubaldo@hotmail.es");
	}

	private static void testSetEmailExcepcional() {
		System.out.println(
				"==================================Probando setEmail con cadena vacia");

		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetEmail(p, "josegmail.com ");
	}

	private static void testSetEmailExcepcional2() {
		System.out.println(
				"==================================Probando setEmail sin @");

		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetEmail(p, "ubaldogmail.com");
	}

	/*********************************************
	 * METODOS AUXILIARES
	 ***********************************************/

	private static void testConstructor1(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email) {

		try {
			Persona p = new PersonaImpl(dni, nombre, apellidos, fechaNacimiento,
					email);
			mostrarPersona(p);
		} catch (ExcepcionPersonaNoValida e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}

	private static void testConstructor2(String dni, String nombre,
			String apellidos, LocalDate fecha) {

		try {
			Persona p = new PersonaImpl(dni, nombre, apellidos, fecha);
			mostrarPersona(p);
		} catch (ExcepcionPersonaNoValida e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}

	private static void testSetDNI(Persona p, String nuevoDNI) {

		try {
			System.out.println(
					"\t El dni antes de la operación es: " + p.getDNI());
			System.out.println("\t El nuevo dni es: " + nuevoDNI);
			p.setDNI(nuevoDNI);
			System.out.println(
					"\t El dni después de la operación es: " + p.getDNI());
		} catch (ExcepcionPersonaNoValida e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada. setDNI no funciona correctamente");
		}
	}

	private static void testSetEmail(Persona p, String nuevoEmail) {

		try {
			System.out.println(
					"\t El email antes de la operación es: " + p.getEmail());
			System.out.println("\t El nuevo email es: " + nuevoEmail);
			p.setEmail(nuevoEmail);
			System.out.println(
					"\t El email después de la operación es: " + p.getEmail());
		} catch (ExcepcionPersonaNoValida e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada. setDNI no funciona correctamente");
		}
	}

	/********************************************* MOSTRAR ***********************************************/

	private static void mostrarPersona(Persona p) {
		System.out.println("Persona --> <" + p + ">");
		System.out.println("\tNombre: <" + p.getNombre() + ">");
		System.out.println("\tApellidos: <" + p.getApellidos() + ">");
		System.out.println("\tDNI: <" + p.getDNI() + ">");
		System.out
				.println(
						"\tFecha Nacimiento: <"
								+ p.getFechaNacimiento()
										.format(DateTimeFormatter
												.ofPattern("dd/MM/yyyy"))
								+ ">");
		System.out.println("\tEdad: <" + p.getEdad() + ">");
		System.out.println("\tEmail:  <" + p.getEmail() + ">");
	}

}