package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GradoImpl2 extends GradoImpl {

	public GradoImpl2(String nombre, Set<Asignatura> obligatorias,
			Set<Asignatura> optativas, Double creditosMinimos) {
		super(nombre, obligatorias, optativas, creditosMinimos);
	}

	public Double getNumeroTotalCreditos() {
		// 1. Inicializo la variable
		Double Nt = 0.;
		// 2. Recorro las asignaturas obligatorias, saco los creditos de cada
		// una, los sumo, y por ultimo le sumo los creditos minimos de las
		// optativas.
		Nt = getAsignaturasObligatorias().stream()
				.mapToDouble(x -> x.getCreditos()).sum()
				+ getNumeroMinimoCreditosOptativas();
		// 3. Devuelvo lo anterior
		return Nt;

	}

	public Set<Asignatura> getAsignaturas(Integer curso) {

		// 1. Inicializo el conjunto
		// Set<Asignatura> asig = new HashSet<>();

		// 2. Tomo las asignaturas del grado (OPTATIVAS Y OBLIGATORIAS)
		// Set<Asignatura> asigObligatorias = getAsignaturasObligatorias();
		// Set<Asignatura> asigOptativas = getAsignaturasOptativas();

		// 3. Inicializo otro conjunto que lo llamaremos total, con el proposito
		// de agregar los dos conjuntos anteriores a este, y tener todas las
		// asignaturas

		// Set<Asignatura> total = new HashSet<Asignatura>();
		// 4. Agregar asignaturas

		// total.addAll(asigOptativas);
		// total.addAll(asigObligatorias);

		// 5. Recorro el total de las asignaturas, con un filtro que obtenga las
		// que sean del mismo curso, y por ultimo las colecciono

		// asig = total.stream().filter(x->
		// x.getCurso().equals(curso)).collect(Collectors.toSet());

		return Stream
				.concat(getAsignaturasObligatorias().stream(),
						getAsignaturasOptativas().stream())
				.filter(x -> x.getCurso().equals(curso))
				.collect(Collectors.toSet());

	}

	public Asignatura getAsignatura(String codigo) {
		// 1. Inicializo la asignatura
		// Asignatura asig = null;

		// 2. Creo un conjunto vacio
		// Set<Asignatura> total = new HashSet<>();

		// 3. Añado las asignaturas (TODAS) al conjunto
		// total.addAll(getAsignaturasObligatorias());
		// total.addAll(getAsignaturasOptativas());

		// 4. Recorro el total de las asignaturas, con un filtro que mire por el
		// codigo, encuentro el PRIMERO QUE LO CUMPLA, y saco esa asignatura
		// asig = total.stream().filter(x ->
		// x.getCodigo().equals(codigo)).findFirst().get();

		return Stream
				.concat(getAsignaturasObligatorias().stream(),
						getAsignaturasOptativas().stream())
				.filter(x -> x.getCodigo().equals(codigo)).findFirst().get();

	}

	public Set<Departamento> getDepartamentos() {
		// 1º Inicializo el departamento
		// Set<Departamento> dpto = new HashSet<Departamento>();
		// 2. Creo un conjunto vacio
		// Set<Asignatura> total = new HashSet<>();
		//
		// 3. Añado las asignaturas (TODAS) al conjunto
		// total.addAll(getAsignaturasObligatorias());
		// total.addAll(getAsignaturasOptativas());
		//
		// 4. Recorro las asignaturas
		//
		// dpto = total.stream().map(x ->
		// x.getDepartamento()).collect(Collectors.toSet());

		return Stream
				.concat(getAsignaturasObligatorias().stream(),
						getAsignaturasOptativas().stream())
				.map(x -> x.getDepartamento()).collect(Collectors.toSet());

	}

	public Set<Profesor> getProfesores() {
		// //
		//
		// Set<Profesor> prof = new HashSet<Profesor>();
		//
		// Set<Departamento> dpto = getDepartamentos();
		//
		// prof = dpto.stream().flatMap(x ->
		// x.getProfesores().stream()).collect(Collectors.toSet());

		return getDepartamentos().stream()
				.flatMap(x -> x.getProfesores().stream())
				.collect(Collectors.toSet());

	}

	public SortedMap<Asignatura, Double> getCreditosPorAsignatura() {
		return Stream
				.concat(getAsignaturasObligatorias().stream(),
						getAsignaturasOptativas().stream())
				.collect(Collectors.toMap(x -> x, x -> x.getCreditos(),
						(x1, x2) -> x1, TreeMap::new));
	}

}