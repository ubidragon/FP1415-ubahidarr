package fp.grados.tipos.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl2;
import fp.grados.tipos.TipoAsignatura;

public class TestProfesor {

	public static void main(String[] args) {

		testConstructorNormal();
		testEdad();
		testAsignatura();
		testDedicacionTotal();
		testCreditos();
		testBorraTutoria();
		testBorraTutoria();
		testBorraTutorias();
		testDedicacionAsignatura();
		testDedicacionAsignaturaExcepcion();
		testSetDepartamento();
		testImparteAsignatura();
		testImparteAsignaturaExcepcion();
		testImparteAsignaturaExcepcion2();
		testEliminaAsignatura();
	}

	private static void testConstructorNormal() {
		System.out.println(
				"\n=====test by Ubidragon==============Probando el primer constructor");

		testConstructor("20091912D", "Ismael", "Garrido",
				LocalDate.of(1994, 9, 18), "jose@gmail.com",
				Categoria.COLABORADOR, new DepartamentoImpl("Fisica"));
	}

	private static void testEdad() {
		System.out.println(
				"\n=====test by Ubidragon==============Probando checkEdad");
		testConstructor("20091912D", "Ismael", "Garrido",
				LocalDate.of(1999, 9, 18), "jose@gmail.com",
				Categoria.COLABORADOR, new DepartamentoImpl("Fisica"));

	}

	private static void testDedicacionTotal() {
		//

	}

	private static void testAsignatura() {

		// ArrayList<?> asignaturas = new ArrayList<Asignatura>();
		// asignaturas.add("Circuitos Electronicos Digitales");
		System.out.println(
				"\n=====test by Ubidragon==============Probando checkAsignatura");
		Departamento d = new DepartamentoImpl("L.S.I");
		Asignatura asig = new AsignaturaImpl("Circuitos Electronicos Digitales",
				"2050546", 6.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1,
				new DepartamentoImpl("Circuitos"));

		asig.getDepartamento();
		testDepartamentoAsignatura(d, asig);
	}

	private static void testCreditos() {
		//

	}

	private static void testBorraTutoria() {
		//

	}

	private static void testBorraTutorias() {
		//

	}

	private static void testDedicacionAsignatura() {
		//

	}

	private static void testDedicacionAsignaturaExcepcion() {
		//

	}

	private static void testSetDepartamento() {
		//

	}

	private static void testImparteAsignatura() {

	}

	private static void testImparteAsignaturaExcepcion() {

	}

	private static void testImparteAsignaturaExcepcion2() {

	}

	private static void testEliminaAsignatura() {
		//

	}

	/************************************************
	 * METODOS AUXILIARES
	 ***************************************/

	private static void testConstructor(String dni, String nombre,
			String apellidos, LocalDate fecha, String email,
			Categoria categoria, Departamento departamento) {

		try {
			Departamento d = new DepartamentoImpl("Matematicas Aplicadas");
			Profesor p = new ProfesorImpl2(dni, nombre, apellidos, fecha, email,
					categoria, d);

			mostrarProfesor(p, d);
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}

	}

	private static void testDepartamentoAsignatura(Departamento d,
			Asignatura asig) {

		try {
			System.out.println("La asignatura que imparte es: " + asig);
			System.out.println("Su departamento es: " + asig.getDepartamento());
			d.getNombre();
			System.out.println("Las asignaturas del departamento son: "
					+ d.getAsignaturas());
		} catch (ExcepcionProfesorOperacionNoPermitida e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}
	}

	/**************************************************** MOSTRAR **********************************************/

	private static void mostrarProfesor(Profesor p, Departamento d) {
		System.out.println("Alumno --> <" + p + ">");
		System.out.println("\tDNI: <" + p.getDNI() + ">");
		System.out.println("\tNombre: <" + p.getNombre() + ">");
		System.out.println("\tApellidos: <" + p.getApellidos() + ">");
		System.out
				.println(
						"\tFecha Nacimiento: <"
								+ p.getFechaNacimiento()
										.format(DateTimeFormatter
												.ofPattern("dd/MM/yyyy"))
								+ ">");
		System.out.println("\tEdad: <" + p.getEdad() + ">");
		System.out.println("\tEmail:  <" + p.getEmail() + ">");
		System.out.println("\tCategoria: <" + p.getCategoria() + ">");
		System.out.println("\tDepartamento:  <" + d.getNombre() + ">");
	}

}
