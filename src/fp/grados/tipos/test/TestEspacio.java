package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionEspacioNoValido;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.TipoEspacio;

public class TestEspacio {

	public static void main(String[] args) {

		testConstructorNormal();
		testConstructorExcepcion();
	}

	/************************************************
	 * TEST CONSTRUCTOR(ES)
	 *************************************/

	private static void testConstructorNormal() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando el primer constructor");
		testConstructor(TipoEspacio.LABORATORIO, "G1.12", 35, 1);
	}

	private static void testConstructorExcepcion() {
		System.out.println("");
		System.out.println("\t=====test by Ubidragon========");
		System.out.println("Probando el constructor, Capacidad Incorrecta");
		testConstructor(TipoEspacio.EXAMEN, "G1.12", 0, 1);
	}

	/************************************************
	 * METODOS AUXILIARES
	 ***************************************/

	private static void testConstructor(TipoEspacio nombreAula, String aula,
			Integer capacidad, Integer Planta) {

		try {
			Espacio es = new EspacioImpl(nombreAula, aula, capacidad, Planta);
			mostrarEspacio(es);
		} catch (ExcepcionEspacioNoValido e) {
			System.out.println(
					"******************** Se ha capturado la excepción ExcepcionEspacioNoValido");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada.");
		}

	}

	/**************************************************** MOSTRAR **********************************************/

	private static void mostrarEspacio(Espacio es) {
		System.out.println("Espacio --> < " + es + " >");
		System.out.println("\t Tipo de Aula: < " + es.getTipo() + " >");
		System.out.println("\t Nombre del Aula: < " + es.getNombre() + " >");
		System.out.println("\t Capacidad del Aula: < " + es.getCapacidad()
				+ " personas >");
		System.out.println("\t Planta del aula: < " + es.getPlanta() + "ª >");

	}
}