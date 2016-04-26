package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionAsignaturaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.TipoAsignatura;

// Este c�digo se les da como material adjunto al bolet�n T3
public class TestAsignatura {

	public static void main(String[] args) {
		testConstructorNormal();
		testConstructorNormal1();
		testConstructorExcepcion1();
		testConstructorExcepcion2();
		testConstructorExcepcion3();
		testConstructorExcepcion4();
		testConstructorExcepcion5();
		testConstructorExcepcion6();
		testConstructorExcepcion7();
	}

	/********************************
	 * CASOS DE PRUEBA
	 **************************/

	private static void testConstructorNormal() {
		System.out.println(
				"==================================Probando el constructor");
		testConstructor("Fundamentos de Programaci�n", "2050001", 12.0,
				TipoAsignatura.ANUAL, 1,
				new DepartamentoImpl("Matematicas Aplicadas"));
	}

	private static void testConstructorNormal1() {
		System.out.println(
				"==================================Probando el constructor");
		testConstructor("Introduccion a las Matematicas Discretas", "2050001",
				12.0, TipoAsignatura.ANUAL, 1,
				new DepartamentoImpl("Matematicas Aplicadas"));
	}

	private static void testConstructorExcepcion1() {
		System.out.println(
				"==================================Probando el constructor, c�digo de asignatura m�s largo");
		testConstructor("Fundamentos de Programaci�n", "20500010", 12.0,
				TipoAsignatura.ANUAL, 1,
				new DepartamentoImpl("Matematicas Aplicadas"));
	}

	private static void testConstructorExcepcion2() {
		System.out.println(
				"==================================Probando el constructor, c�digo de asignatura m�s corto");
		testConstructor("Fundamentos de Programaci�n", "205000", 12.0,
				TipoAsignatura.ANUAL, 1,
				new DepartamentoImpl("Matematicas Aplicadas"));
	}

	private static void testConstructorExcepcion3() {
		System.out.println(
				"==================================Probando el constructor, c�digo de asignatura no num�rico");
		testConstructor("Fundamentos de Programaci�n", "2A50001", 12.0,
				TipoAsignatura.ANUAL, 1,
				new DepartamentoImpl("Matematicas Aplicadas"));
	}

	private static void testConstructorExcepcion4() {
		System.out.println(
				"==================================Probando el constructor, cr�ditos incorrectos (0.0)");
		testConstructor("Fundamentos de Programaci�n", "2050001", 0.0,
				TipoAsignatura.ANUAL, 1,
				new DepartamentoImpl("Matematicas Aplicadas"));
	}

	private static void testConstructorExcepcion5() {
		System.out.println(
				"==================================Probando el constructor, cr�ditos incorrectos (-1.0)");
		testConstructor("Fundamentos de Programaci�n", "2050001", -1.0,
				TipoAsignatura.ANUAL, 1,
				new DepartamentoImpl("Matematicas Aplicadas"));
	}

	private static void testConstructorExcepcion6() {
		System.out.println(
				"==================================Probando el constructor, curso menor de 1");
		testConstructor("Fundamentos de Programaci�n", "2050001", 12.0,
				TipoAsignatura.ANUAL, -2,
				new DepartamentoImpl("Matematicas Aplicadas"));
	}

	private static void testConstructorExcepcion7() {
		System.out.println(
				"==================================Probando el constructor, curso mayor de 4");
		testConstructor("Fundamentos de Programaci�n", "2050001", 12.0,
				TipoAsignatura.ANUAL, 5,
				new DepartamentoImpl("Matematicas Aplicadas"));
	}

	/********************************
	 * METODOS AUXILIARES
	 **************************/

	private static void testConstructor(String nombre, String codigo,
			Double creditos, TipoAsignatura tipo, Integer curso,
			Departamento departament) {
		try {
			Asignatura a = new AsignaturaImpl(nombre, codigo, creditos, tipo,
					curso, departament);
			mostrarAsignatura(a);
		} catch (ExcepcionAsignaturaNoValida e) {
			System.out.println(
					"******************** Se ha capturado la excepci�n ExcepcionAsignaturaNoValida");
		} catch (Exception e) {
			System.out.println(
					"******************** ���Se ha capturado una EXCEPCI�N INESPERADA!!!");
		}
	}

	/**************************************************** MOSTRAR **********************************************/

	private static void mostrarAsignatura(Asignatura a) {
		System.out.println("Asignatura --> <" + a + ">");
		System.out.println("\tNombre: <" + a.getNombre() + ">");
		System.out.println("\tAcronimo: <" + a.getAcronimo() + ">");
		System.out.println("\tC�digo: <" + a.getCodigo() + ">");
		System.out.println("\tCr�ditos: <" + a.getCreditos() + ">");
		System.out.println("\tTipo: <" + a.getTipo() + ">");
		System.out.println("\tCurso: <" + a.getCurso() + "�>");
		System.out.println("\tDepartamento: <" + a.getDepartamento() + ">");
	}

}