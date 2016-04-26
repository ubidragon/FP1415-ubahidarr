package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionNotaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;

import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Convocatoria;
import fp.grados.tipos.Nota;
import fp.grados.tipos.NotaImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestNota {

	public static void main(String[] args) {

		testConstructorNormal();
		testConstructorNormal2();

		testConstructorExcepcion();
		testConstructorExcepcion2();
		testConstructorExcepcion3();
		testConstructor2Excepcion();
		testConstructor2Excepcion2();

	}

	/************************************************
	 * TEST CONSTRUCTOR(ES)
	 *************************************/

	private static void testConstructorNormal() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando el primer constructor");
		testConstructor1(
				new AsignaturaImpl("Fundamentos de Programación", "2050001",
						12.0, TipoAsignatura.ANUAL, 1,
						new DepartamentoImpl("L.S.I.")),
				2014, Convocatoria.SEGUNDA, 6.0, false);
	}

	private static void testConstructorNormal2() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando el segundo constructor");
		testConstructor2(
				new AsignaturaImpl("Fundamentos de Programación", "2050001",
						12.0, TipoAsignatura.ANUAL, 1,
						new DepartamentoImpl("L.S.I.")),
				2014, Convocatoria.SEGUNDA, 8.0);
	}

	private static void testConstructorExcepcion() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando excepcion valor negativo");
		testConstructor1(
				new AsignaturaImpl("Fundamentos de Programación", "2050001",
						12.0, TipoAsignatura.ANUAL, 1,
						new DepartamentoImpl("L.S.I.")),
				2014, Convocatoria.SEGUNDA, -6.0, false);
	}

	private static void testConstructorExcepcion2() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando excepcion valor superior a 10");
		testConstructor1(
				new AsignaturaImpl("Fundamentos de Programación", "2050001",
						12.0, TipoAsignatura.ANUAL, 1,
						new DepartamentoImpl("L.S.I.")),
				2014, Convocatoria.SEGUNDA, 12.0, false);
	}

	private static void testConstructorExcepcion3() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println(
				"Probando excepcion mencion de honor con nota inferior a 9");
		testConstructor1(
				new AsignaturaImpl("Fundamentos de Programación", "2050001",
						12.0, TipoAsignatura.ANUAL, 1,
						new DepartamentoImpl("L.S.I.")),
				2014, Convocatoria.SEGUNDA, 6.0, true);
	}

	private static void testConstructor2Excepcion() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando excepcion valor negativo");

		testConstructor2(
				new AsignaturaImpl("Fundamentos de Programación", "2050001",
						12.0, TipoAsignatura.ANUAL, 1,
						new DepartamentoImpl("L.S.I.")),
				2014, Convocatoria.SEGUNDA, -6.0);
	}

	private static void testConstructor2Excepcion2() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando excepcion valor superior a 10");

		testConstructor2(
				new AsignaturaImpl("Fundamentos de Programación", "2050001",
						12.0, TipoAsignatura.ANUAL, 1,
						new DepartamentoImpl("L.S.I.")),
				2014, Convocatoria.SEGUNDA, 12.0);
	}

	/************************************************
	 * TEST METODOS
	 *********************************************/

	//

	/************************************************
	 * METODOS AUXILIARES
	 ***************************************/

	private static void testConstructor1(Asignatura asignatura, Integer curso,
			Convocatoria convocatoria, Double nota, Boolean mencion) {

		try {

			Nota n = new NotaImpl(asignatura, curso, convocatoria, nota,
					mencion);

			mostrarNota(n);
		} catch (ExcepcionNotaNoValida e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}

	}

	private static void testConstructor2(Asignatura asignatura, Integer curso,
			Convocatoria convocatoria, Double nota) {

		try {

			Nota n = new NotaImpl(asignatura, curso, convocatoria, nota);

			mostrarNota(n);
		} catch (ExcepcionNotaNoValida e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}

	}

	/**************************************************** MOSTRAR **********************************************/

	private static void mostrarNota(Nota n) {
		System.out.println("Nota: <" + n + ">");
		System.out.println("\tAsignatura: <" + n.getAsignatura() + ">");
		System.out.println("\t Curso: <" + n.getCursoAcademico() + "º>");
		System.out.println("\tConvocatoria: <" + n.getConvocatoria() + ">");
		System.out.println("\tNota obtenida:  <" + n.getValor() + " - "
				+ n.getCalificacion() + ">");
		System.out.println(
				"\tMatricula de Honor:  <" + n.getMencionHonor() + ">");
	}
}