package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import fp.grados.excepciones.ExcepcionTutoriaNoValida;
import fp.grados.tipos.Tutoria;
import fp.grados.tipos.TutoriaImpl;

public class TestTutoria {

	public static void main(String[] args) {

		testConstructorNormal();
		testConstructorNormal2();
		testConstructorNormal3();
		testConstructorNormal4();
		testConstructorExcepcion();
		testConstructorExcepcion2();
		testConstructorExcepcion3();
		testConstructorExcepcion4();
		testConstructorExcepcion5();
		testConstructorExcepcion6();

	}

	/************************************************
	 * TEST CONSTRUCTOR(ES)
	 *************************************/

	private static void testConstructorNormal() {
		System.out.println(
				"\n=====test by Ubidragon==============Probando el primer constructor");
		testConstructor(DayOfWeek.FRIDAY, LocalTime.of(15, 15),
				LocalTime.of(15, 40));
	}

	private static void testConstructorNormal2() {
		System.out.println(
				"\n=====test by Ubidragon==============Probando el primer constructor");
		testConstructor(DayOfWeek.FRIDAY, LocalTime.of(06, 15),
				LocalTime.of(06, 50));
	}

	private static void testConstructorNormal3() {
		System.out.println(
				"\n=====test by Ubidragon==============Probando el segundo constructor");
		testConstructor2(DayOfWeek.FRIDAY, LocalTime.of(15, 15), 45);
	}

	private static void testConstructorNormal4() {
		System.out.println(
				"\n=====test by Ubidragon==============Probando el segundo constructor");
		testConstructor2(DayOfWeek.MONDAY, LocalTime.of(15, 15), 20);

	}

	private static void testConstructorExcepcion() {
		System.out.println(
				"\n=====test by Ubidragon=================Probando el primer constructor, Dia incorrecto");
		testConstructor(DayOfWeek.SATURDAY, LocalTime.of(15, 15),
				LocalTime.of(15, 40));
	}

	private static void testConstructorExcepcion2() {
		System.out.println(
				"\n=====test by Ubidragon=================Probando el primer constructor, Dia incorrecto 2");
		testConstructor(DayOfWeek.SUNDAY, LocalTime.of(15, 15),
				LocalTime.of(15, 40));
	}

	private static void testConstructorExcepcion3() {
		System.out.println(
				"\n=====test by Ubidragon=================Probando el primer constructor, Tiempo incorrecto");
		testConstructor(DayOfWeek.FRIDAY, LocalTime.of(15, 15),
				LocalTime.of(15, 20));
	}

	private static void testConstructorExcepcion4() {
		System.out.println(
				"\n=====test by Ubidragon=================Probando el segundo constructor, Dia incorrecto");
		testConstructor2(DayOfWeek.SUNDAY, LocalTime.of(15, 15), 20);
	}

	private static void testConstructorExcepcion5() {
		System.out.println(
				"\n=====test by Ubidragon=================Probando el segundo constructor, Dia incorrecto");
		testConstructor2(DayOfWeek.SATURDAY, LocalTime.of(15, 15), 20);
	}

	private static void testConstructorExcepcion6() {
		System.out.println(
				"\n=====test by Ubidragon=================Probando el segundo constructor, duracion inferior a 15");
		testConstructor2(DayOfWeek.FRIDAY, LocalTime.of(15, 15), 10);
	}

	/************************************************
	 * TEST METODOS
	 *********************************************/

	//

	/************************************************
	 * METODOS AUXILIARES
	 ***************************************/

	private static void testConstructor(DayOfWeek dia, LocalTime comienzo,
			LocalTime fin) {

		try {
			Tutoria t = new TutoriaImpl(dia, comienzo, fin);

			mostrarTutoria(t);
		} catch (ExcepcionTutoriaNoValida e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionTutoriaNoValida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}

	}

	private static void testConstructor2(DayOfWeek dia, LocalTime comienzo,
			Integer duracion) {

		try {
			Tutoria t = new TutoriaImpl(dia, comienzo, duracion);

			mostrarTutoria(t);
		} catch (ExcepcionTutoriaNoValida e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionTutoriaNoValida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}

	}

	/**************************************************** MOSTRAR **********************************************/

	private static void mostrarTutoria(Tutoria t) {
		System.out.println("Tutoria: <" + t + ">");
		System.out.println("\tDia de la Tutoria: <" + t.getDiaSemana() + ">");
		System.out.println("\tHora de Comienzo: <" + t.getHoraComienzo() + ">");
		System.out.println("\tHora de Fin: <" + t.getHoraFin() + ">");
		System.out.println("\tDuracion:  <" + t.getDuracion() + ">");
	}
}