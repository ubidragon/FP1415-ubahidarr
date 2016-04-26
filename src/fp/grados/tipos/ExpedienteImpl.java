package fp.grados.tipos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionExpedienteOperacionNoPermitida;

public class ExpedienteImpl implements Expediente {

	// MUY IMPORTANTE!!!: comienza por la clase Expediente, despues ve a
	// alumno o dejalo para el final
	// despues de a ver hecho expediente, o en su defecto alumno, ve a Profesor,
	// de profesor a departamento y de ahi a grado

	private List<Nota> notas;

	/*************************************************** CONSTRUCTOR ***************************************************/

	public ExpedienteImpl() {

		notas = new ArrayList<Nota>();
		// el check se lo tienes que hacer sobre una nota nueva, es decir
		// se lo introduces en nuevaNota
		// y que cuando intente añadirla priemro te haga un conteo, corrige el
		// check a partir de esto

	}

	/*************************************************** EXCEPCIONES ***************************************************/
	private void checkNota(Nota cocaina) {
		int not = 0;
		for (Nota heroina : getNotas()) {
			if (cocaina.getAsignatura().equals(heroina.getAsignatura())
					&& cocaina.getCursoAcademico()
							.equals(heroina.getCursoAcademico())) {
				not++;
			}
		}

		if (not == 2) {
			throw new ExcepcionExpedienteOperacionNoPermitida(
					"Error al realizar el conteo de notas");
		}
	}

	/***************************************************
	 * GETTERS AND SETTERS
	 *******************************************/

	public List<Nota> getNotas() {

		return new ArrayList<Nota>(notas); // tienes que hacer una copia de las
											// notas.
	}

	public Double getNotaMedia() {
		List<Nota> not = new ArrayList<Nota>();
		Double notaMedia = 0.0;
		for (Nota n : getNotas()) {
			Double nota = n.getValor();
			if (nota >= 5) {
				not.add(n);
			}
		}
		for (Nota n2 : not) {
			Double note = n2.getValor();
			notaMedia = notaMedia + (note / not.size());
		}

		return notaMedia;

		// Inicializa una nueva cadena vacia de notas, donde meteras las notas
		// mayores o iguales a 5
		// despues hay que recorrerla(esto es el for cojonudo) para hacer el
		// calculo
		// a partir del valor de la nota
		// si no se hace asi, cogera el total de las notas al hacer el size y no
		// el
		// numero de notas mayores o iguales a 5
	}

	public void nuevaNota(Nota n) {
		checkNota(n);
		// te falta checkeaar que no haya una nota igual, ya que si la hay, hay
		// que borrarla e introducir la nueva despues
		for (Nota NOT : getNotas()) {
			if (n.equals(NOT)) {
				notas.remove(NOT);
			}
		}

		notas.add(n);

	}

	public List<Nota> getNotasOrdenadasPorAsignatura() {
		//
		Comparator<Nota> cmp = Comparator.comparing(Nota::getAsignatura)
				.thenComparing(Comparator.naturalOrder());
		List<Nota> notas = new ArrayList<Nota>(getNotas());
		Collections.sort(notas, cmp);
		return notas;
	}

	public Nota getMejorNota() {

		Comparator<Nota> cmp = Comparator.comparing(Nota::getCalificacion)
				.thenComparing(Nota::getValor).reversed()
				.thenComparing(Nota::getConvocatoria)
				.thenComparing(Nota::getCursoAcademico);
		SortedSet<Nota> notas = new TreeSet<>(cmp);
		notas.addAll(getNotas());
		return notas.first();

	}

	/***************************************************
	 * EQUALS HASHCODE COMPARETO
	 *************************************/

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notas == null) ? 0 : notas.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Expediente) {
			Expediente e = (Expediente) obj;
			result = getNotas().equals(e.getNotas());
		}
		return result;
	}

	/*************************************************** REPRESENTACION ************************************************/

	public String toString() {
		return notas.toString();
		// tiene que devolver la representacion como cadena de las notas,
		// es decir: return getNotas().toString();
	}

}
