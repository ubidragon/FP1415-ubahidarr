package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;
import fp.grados.tipos.Centro;
import fp.grados.tipos.CentroImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.TipoEspacio;

public class TestCentro {

	public static void main(String[] args) {

		testConstructorNormal();
		testConstructor1Excepcion();
		testConstructor2Normal();
		testConstructor2Excepcion();
		testConstructor3Normal();
		testConstructor3Excepcion();

		testNuevoEspacio();
		testNuevoEspacioExcepcion();
		testNuevoEspacioExcepcion2();
		testEliminaEspacio();
		testEliminaEspacioFueraDeCentro();

	}

	private static void testConstructorNormal() {
		System.out.println(
				"\n==================================Probando el constructor");
		testConstructor("E.T.S.I.I.", "Avd. Reina Mercedes", 3, 4);
	}

	private static void testConstructor1Excepcion() {
		System.out.println(
				"\n==================================Probando excepcion de las plantas");
		testConstructor("Facultad de Matematicas", "Avd. Reina Mercedes", -1,
				1);
	}

	private static void testConstructor2Normal() {
		System.out.println(
				"\n==================================Probando el constructor");
		testConstructor("Facultad de Matematicas", "Avd. Reina Mercedes", 2, 5);
	}

	private static void testConstructor2Excepcion() {
		System.out.println(
				"\n==================================Probando la excepcion de los sotanos ");
		testConstructor("Facultad de Matematicas", "Avd. Reina Mercedes", 2,
				-1);
	}

	private static void testConstructor3Normal() {
		System.out.println(
				"\n==================================Probando el constructor");
		testConstructor("Facultad de Arquitectura", "Avd. Reina Mercedes", 5,
				3);
	}

	private static void testConstructor3Excepcion() {
		System.out.println(
				"\n==================================Probando ambas excepciones");
		testConstructor("Facultad de Matematicas", "Avd. Reina Mercedes", -8,
				-7);
	}

	private static void testNuevoEspacio() {

		System.out.println(
				"\n==================================Probando NuevoEspacio");

		Centro c = new CentroImpl("E.T.S.I.I.", "Avd. Reina Mercedes", 5, 4);
		Espacio es = new EspacioImpl(TipoEspacio.LABORATORIO, "A4.12", 40, 4);
		c.nuevoEspacio(es);
		testNuevoEspacio(c, es);
	}

	private static void testNuevoEspacioExcepcion() {

		System.out.println(
				"\n==================================Probando ExcepcionNuevoEspacio");

		Centro c = new CentroImpl("E.T.S.I.I.", "Avd. Reina Mercedes", 3, 4);
		Espacio es = new EspacioImpl(TipoEspacio.LABORATORIO, "A0.12", 40, -5);

		testNuevoEspacio(c, es);
	}

	private static void testNuevoEspacioExcepcion2() {
		System.out.println(
				"\n==================================Probando ExcepcionNuevoEspacio");

		Centro c = new CentroImpl("E.T.S.I.I.", "Avd. Reina Mercedes", 3, 4);
		Espacio es = new EspacioImpl(TipoEspacio.LABORATORIO, "A0.12", 40, 5);

		testNuevoEspacio(c, es);
	}

	private static void testEliminaEspacio() {

		System.out.println(
				"\n==================================Probando EliminaEspacio");

		Centro c = new CentroImpl("E.T.S.I.I.", "Avd. Reina Mercedes", 5, 4);
		Espacio es = new EspacioImpl(TipoEspacio.LABORATORIO, "A4.12", 40, 1);
		c.eliminaEspacio(es);
		testEliminaEspacio(c, es);
	}

	private static void testEliminaEspacioFueraDeCentro() {

		System.out.println(
				"\n==================================Probando EliminaEspacioFueraDeCentro");

		Centro c = new CentroImpl("E.T.S.I.I.", "Avd. Reina Mercedes", 5, 4);
		Espacio es = new EspacioImpl(TipoEspacio.LABORATORIO, "A4.12", 40, 9);
		c.eliminaEspacio(es);
		testEliminaEspacio(c, es);
	}

	/************************************************
	 * METODOS AUXILIARES
	 ***************************************/

	private static void testConstructor(String nombre, String direccion,
			Integer numeroPlantas, Integer numeroSotanos) {
		try {
			Centro c = new CentroImpl(nombre, direccion, numeroPlantas,
					numeroSotanos);
			mostrarCentro(c);
		} catch (ExcepcionCentroNoValido e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionCentroNoValido");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}

	}

	private static void testNuevoEspacio(Centro c, Espacio es) {
		try {
			System.out.println("Planta del nuevo espacio:" + es.getPlanta());
			System.out.println("Espacio a añadir: " + es);
			c.nuevoEspacio(es);

		} catch (ExcepcionCentroOperacionNoPermitida e) {
			System.out.println(
					"******************** Se ha capturado la excepción(nuevo espacio) ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}
	}

	private static void testEliminaEspacio(Centro c, Espacio es) {
		try {
			System.out
					.println("Planta del espacio a eliminar:" + es.getPlanta());
			System.out.println("Espacio a eliminar: " + es);
			c.eliminaEspacio(es);

		} catch (ExcepcionCentroOperacionNoPermitida e) {
			System.out.println(
					"******************** Se ha capturado la excepción(elimina espacio) ExcepcionCentroOperacionNoPermitida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}
	}

	private static void mostrarCentro(Centro c) {
		System.out.println("Centro --> <" + c + ">");
		System.out.println("\tNombre: <" + c.getNombre() + ">");
		System.out.println("\tDireccion: <" + c.getDireccion() + ">");
		System.out.println("\tNumero de Plantas del Centro: <"
				+ c.getNumeroPlantas() + ">");
		System.out.println("\tNumero de Sotanos del Centro: <"
				+ c.getNumeroSotanos() + ">");
	}
}