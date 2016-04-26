package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import fp.grados.excepciones.ExcepcionGradoNoValido;

public class GradoImpl implements Grado {
	// te matare, pero bueno xD los atributos van con private, nunca public, al
	// menos que yo sepa
	private String nombre;
	private Double creditosMinimos;
	private Set<Asignatura> obligatorias;
	private Set<Asignatura> optativas;

	/*************************************************** CONSTRUCTOR ***************************************************/

	public GradoImpl(String nombre, Set<Asignatura> obligatorias,
			Set<Asignatura> optativas, Double creditosMinimos) {
		// en este caso estan mal los checks, ahora te pondre porque si puedo,
		// si no te lo explico cuando sea
		// PD: nunca metas un check si sabes que esta mal, saltara toda la clase
		// y sera aun menos nota que si no lo pones
		// pon estos check al final del constructor, uno de los casos en los que
		// falla si se pone arriba es este
checkAsignaturas(optativas);
		this.nombre = nombre;
		this.obligatorias = obligatorias;
		this.optativas = optativas;
		this.creditosMinimos = creditosMinimos;

		checkMin(creditosMinimos);
		

	}

	/*************************************************** EXCEPCIONES ***************************************************/

	private void checkMin(Double creditosMinimos) {
		// en este check, el if hay que ponerlo fuera del for
		// si no lo que ocurre es que se hace el checkeo cada vez que sale una
		// nota
		// y no te hace el checkeo del total, y en este caso tiene que ser el
		// total, porque es una suma
		Double credit = 0.0;

		for (Asignatura asig : getAsignaturasOptativas()) {

			credit = credit + asig.getCreditos();
		}
		if (creditosMinimos < 0 || creditosMinimos > credit)
			throw new ExcepcionGradoNoValido(
					"Numero de creditos minimos globales erroneos");

	}

	private void checkAsignaturas(Set<Asignatura> optativas) {
		Double credOpt = null;
		// int p = 0;// integer de indice
		// // los set son inmutables
		// Asignatura opt = (Asignatura) optativas.toArray()[p];// esta
		// // transformandot el set en un array y coge
		// // el primer elemento del Array= que del Set
		// // empezamos a contar desde 0 hasta n-1
		//
		// for (Asignatura asig : this.getAsignaturasOptativas()) {
		// credOpt = asig.getCreditos();
		// if (!credOpt.equals(opt.getCreditos())) {
		// throw new ExcepcionGradoNoValido(
		// "Numero minimo de creditos erroneos");
		// }
		// }
		Boolean esprimero = true;
		for (Asignatura asig : optativas) {
			if (esprimero) {
				credOpt = asig.getCreditos();
				esprimero = false;
			} else {
				if (!credOpt.equals(asig.getCreditos())) {
					throw new ExcepcionGradoNoValido();
				}
			}
		}
	}

	/***************************************************
	 * GETTERS AND SETTERS
	 *******************************************/

	public String getNombre() {
		return nombre;
	}

	public Double getNumeroMinimoCreditosOptativas() {
		return creditosMinimos;
	}

	public Set<Asignatura> getAsignaturasObligatorias() {
		return obligatorias;
	}

	public Set<Asignatura> getAsignaturasOptativas() {
		return optativas;
	}

	public Double getNumeroTotalCreditos() {
		checkMin(creditosMinimos);
		Double notaTotal = 0.0;
		for (Asignatura asig : getAsignaturasObligatorias()) {
			Double oblig = asig.getCreditos();
			notaTotal = notaTotal + oblig;

		}

		return notaTotal + getNumeroMinimoCreditosOptativas();
	}

	public Set<Departamento> getDepartamentos() {

		Set<Departamento> departamento = new HashSet<Departamento>();

		// for (Departamento d : getDepartamentos()) {

		// Set<Asignatura> asigOb = d.getAsignaturas();
		// Set<Asignatura> asigOp = d.getAsignaturas();

		// if (asigOb.isEmpty() && asigOp.isEmpty()) {
		// departamento.add(d);
		// }
		// }
		for (Asignatura asig : getAsignaturasObligatorias()) {
			Departamento asigOb = asig.getDepartamento();
			departamento.add(asigOb);
			for (Asignatura a : getAsignaturasOptativas()) {
				Departamento asigOp = a.getDepartamento();
				departamento.add(asigOp);

			}
		}
		return departamento;
	}

	public Set<Profesor> getProfesores() {
		Set<Profesor> pr = new HashSet<Profesor>();
		for (Departamento d : getDepartamentos()) {
			Set<Profesor> prof = d.getProfesores();
			pr.addAll(prof);
		}

		return pr;
	}

	public Set<Asignatura> getAsignaturas(Integer curso) {
		Set<Asignatura> asigTotal = new HashSet<Asignatura>();

		for (Asignatura asig : getAsignaturasOptativas()) {
			if (asig.getCurso() == curso) {
				asigTotal.add(asig);

			}
			for (Asignatura as : getAsignaturasObligatorias()) {
				if (as.getCurso() == curso) {
					asigTotal.add(as);
				}
			}

		}

		return asigTotal;
	}

	public Asignatura getAsignatura(String codigo) {
		Asignatura a = null;
		for (Asignatura asig : getAsignaturasOptativas()) {
			if (asig.getCodigo() == codigo) {
				asig = a;
			}
			for (Asignatura asign : getAsignaturasObligatorias()) {
				if (asign.getCodigo() == codigo) {
					asign = a;
				}
			}
		}

		return a;
	}

	public SortedMap<Asignatura, Double> getCreditosPorAsignatura() {
		SortedMap<Asignatura, Double> map = new TreeMap<Asignatura, Double>();

		for (Asignatura asig : getAsignaturasObligatorias()) {
			map.put(asig, asig.getCreditos());
		}
		for (Asignatura as : getAsignaturasOptativas()) {
			map.put(as, as.getCreditos());
		}
		return map;
	}

	public SortedSet<Departamento> getDepartamentosOrdenadosPorAsignaturas() {

		Comparator<Departamento> cmp = Comparator
				.comparing(x -> x.getAsignaturas().size());
		cmp = cmp.reversed().thenComparing(Comparator.naturalOrder());
		SortedSet<Departamento> result = getDepartamentos().stream().sorted(cmp)
				.collect(Collectors
						.toCollection(() -> new TreeSet<Departamento>()));
		System.out.println(result);
		return result;
	}

	/*********************************************
	 * EQUALS HASHCODE COMPARETO
	 *******************************************/

	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Grado) {
			Grado g = (Grado) obj;
			result = getNombre().equals(g.getNombre());
		}
		return result;
	}

	public int hashCode() {
		return getNombre().hashCode();
	}

	public int compareTo(Grado o) {
		return getNombre().compareTo(o.getNombre());
	}

	/*************************************************** REPRESENTACION ************************************************/

	public String toString() {
		// en el to string solo entra el nombre, NADA MAS
		return getNombre();
	}

}
