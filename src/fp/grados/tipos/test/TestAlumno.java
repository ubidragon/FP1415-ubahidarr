package fp.grados.tipos.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;
import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Convocatoria;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Nota;
import fp.grados.tipos.NotaImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestAlumno {

	public static void main(String[] args) {

		testConstructorNormal();
		testConstructorExcepcion();

		testSetEmailNormal();
		testSetEmailExcepcion();

		testMatriculaAsignatura();
		testMatriculaAsignaturaExcepcion();

		testEliminaAsignatura();
		testEliminaAsignaturaExcepcion();

		testEvaluaAlumno();
		testEvaluaAlumno2();
		testEvaluaAlumnoExcepcion();
	}

	/************************************************
	 * TEST CONSTRUCTOR(ES)
	 *************************************/

	private static void testConstructorNormal() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando el primer constructor");
		testConstructor("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
	}

	private static void testConstructorExcepcion() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando el primer constructor, email incorrecto");
		testConstructor("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	/************************************************
	 * TEST METODOS
	 *********************************************/

	private static void testSetEmailNormal() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando setEmail");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		testSetEmail(a, "juan@alum.us.es");
	}

	private static void testSetEmailExcepcion() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("setEmail, email incorrecto");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		testSetEmail(a, "juan@gmail.com");
	}

	private static void testMatriculaAsignatura() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando matriculaAsignatura");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1,
				new DepartamentoImpl("L.S.I."));
		testMatriculaAsignatura(a, asig);
	}

	private static void testMatriculaAsignaturaExcepcion() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println(
				"Probando matriculaAsignatura, matricula doble en una asignatura");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1,
				new DepartamentoImpl("L.S.I."));
		a.matriculaAsignatura(asig);
		testMatriculaAsignatura(a, asig);
	}

	private static void testEliminaAsignatura() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando eliminaAsignatura");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1,
				new DepartamentoImpl("L.S.I."));
		a.matriculaAsignatura(asig);
		testEliminaAsignatura(a, asig);
	}

	private static void testEliminaAsignaturaExcepcion() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println(
				"Probando eliminaAsignatura, asignatura no matriculada");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1,
				new DepartamentoImpl("L.S.I."));
		testEliminaAsignatura(a, asig);
	}

	private static void testEvaluaAlumno() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando EvaluaAlumno");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Circuitos Electronicos Digitales",
				"2050546", 6.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1,
				new DepartamentoImpl("L.S.I."));
		Nota n = new NotaImpl(asig, 1, Convocatoria.PRIMERA, 7.5, false);
		a.matriculaAsignatura(asig);

		testEvaluaAlumno(a, asig, n);
	}

	private static void testEvaluaAlumno2() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando EvaluaAlumno");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Circuitos Electronicos Digitales",
				"2050546", 6.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1,
				new DepartamentoImpl("L.S.I."));
		Nota n = new NotaImpl(asig, 1, Convocatoria.PRIMERA, 7.5, false);
		a.matriculaAsignatura(asig);

		testEvaluaAlumno(a, asig, n);
	}

	private static void testEvaluaAlumnoExcepcion() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando EvaluaAlumnoExcepcion");
		Asignatura asig = null;
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Nota n = new NotaImpl(asig, 1, Convocatoria.PRIMERA, 7.5, false);
		testEvaluaAlumno(a, asig, n);

	}

	/************************************************
	 * METODOS AUXILIARES
	 ***************************************/

	private static void testConstructor(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email) {

		try {
			Asignatura asig = new AsignaturaImpl(
					"Circuitos Electronicos Digitales", "2050546", 6.0,
					TipoAsignatura.PRIMER_CUATRIMESTRE, 1,
					new DepartamentoImpl("L.S.I."));
			Alumno a = new AlumnoImpl(dni, nombre, apellidos, fechaNacimiento,
					email);
			Nota n = new NotaImpl(asig, 1, Convocatoria.PRIMERA, 7.5, false);

			mostrarAlumno(a, n);
		} catch (ExcepcionAlumnoNoValido e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionAlumnoNoValido");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}

	}

	private static void testSetEmail(Alumno a, String nuevoEmail) {

		try {
			System.out.println(
					"El email antes de la operación es: " + a.getEmail());
			System.out.println("El nuevo email es: " + nuevoEmail);
			a.setEmail(nuevoEmail);
			System.out.println(
					"El email después de la operación es: " + a.getEmail());
		} catch (ExcepcionAlumnoNoValido e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionAlumnoNoValido");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}
	}

	private static void testMatriculaAsignatura(Alumno a, Asignatura asig) {

		try {
			System.out.println("Las asignaturas antes de la operación son: "
					+ a.getAsignaturas());
			System.out.println("Nueva asignatura a matricular: " + asig);
			a.matriculaAsignatura(asig);
			System.out.println("Las asignaturas después de la operación son: "
					+ a.getAsignaturas());
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}
	}

	private static void testEliminaAsignatura(Alumno a, Asignatura asig) {

		try {
			System.out.println("Las asignaturas antes de la operación son: "
					+ a.getAsignaturas());
			System.out.println("Asignatura a eliminar: " + asig);
			a.eliminaAsignatura(asig);
			System.out.println("Las asignaturas después de la operación son: "
					+ a.getAsignaturas());
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}
	}

	private static void testEvaluaAlumno(Alumno a, Asignatura asig, Nota n) {
		try {

			System.out.println("Asignatura: " + n.getAsignatura());
			a.eliminaAsignatura(asig);
			System.out.println("Curso del alumno: " + n.getCursoAcademico());

			System.out.println("Tipo de Convocatoria: " + n.getConvocatoria());
			System.out.println("Nota: " + n.getValor());
			System.out.println("Mencion de Honor: " + n.getMencionHonor());
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}

	}

	/**************************************************** MOSTRAR **********************************************/

	private static void mostrarAlumno(Alumno a, Nota n) {
		System.out.println("Alumno --> <" + a + ">");
		System.out.println("\tDNI: <" + a.getDNI() + ">");
		System.out.println("\tNombre: <" + a.getNombre() + ">");
		System.out.println("\tApellidos: <" + a.getApellidos() + ">");
		System.out
				.println(
						"\tFecha Nacimiento: <"
								+ a.getFechaNacimiento()
										.format(DateTimeFormatter
												.ofPattern("dd/MM/yyyy"))
								+ ">");
		System.out.println("\tEdad: <" + a.getEdad() + ">");
		System.out.println("\tEmail:  <" + a.getEmail() + ">");
		System.out.println("\tCurso: <" + n.getCursoAcademico() + "º>");
		System.out.println("\tAsignaturas:  <" + a.getAsignaturas() + ">");
	}
}