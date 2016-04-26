package fp.grados.tipos;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DepartamentoImpl2 extends DepartamentoImpl {

	public DepartamentoImpl2(String nombre) {
		super(nombre);
	}

	public void borraTutorias() {
		getProfesores().stream().forEach(Profesor::borraTutorias);
		// forEach es para usar un consumidor para cada uno de ellos hazme X
	}

	public void borraTutorias(Categoria categoria) {
		// te convierte la lista de profesores en un stream con el filtro te
		// saca los profesores que tiene esa categoria y para cada uno de ellos
		// con el foreach le borra las tutorias
		getProfesores().stream().filter(p -> p.getCategoria().equals(categoria))
				.forEach(Profesor::borraTutorias);

	}

	public Boolean existeProfesorAsignado(Asignatura a) {
		// Recorre los profesores con el stream y si para alguno de ellos
		// (anyMatch) con que solo uno de ellos cumpla las condiciones entonces
		// te da true

		return getProfesores().stream()
				.anyMatch(x -> x.getAsignaturas().contains(a));

	}

	public Boolean estanTodasAsignaturasAsignadas() {
		// 1. Recorrer los profesores
		// 2. Le pasa un flatMap, obteniendo las asignaturas de los profesores y
		// recorriendolas a su vez.
		// 3. Las colecciono a un set, ya que es el tipo del anterior
		// (getAsignaturas())
		// 4. Compruebo el metodo booleano empleando el "containsAll()" y le
		// paso las asignaturas del departamento como parametro.
		return getProfesores().stream()
				.flatMap(x -> x.getAsignaturas().stream())
				.collect(Collectors.toSet()).containsAll(getAsignaturas());
	}

	public void eliminaAsignacionProfesorado(Asignatura a) {
		// Recorre profesores y para cada uno de ellos(forEach) elimna la
		// asignatura A
		// (forEach) y luego recorre los profesores en el que cada uno de ellos
		// elimine el indice de la lista que se habia eliminado anteriormente
		// (con referencia al indice de la lista de asignatura)
		if (getAsignaturas().contains(a)) {
			getProfesores().stream().forEach(
					x -> x.getCreditos().remove(x.getAsignaturas().indexOf(a)));
			getProfesores().stream().forEach(x -> x.eliminaAsignatura(a));
		}

	}

	public SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor() {
		// te recorre los profesores (no colecciona directamente los profesores)
		// sino con un collect creas un Map en el que:
		// 1º metes los profesores uno a uno
		// 2º te mete las tutorias del profesores que estas metiendo en ese
		// momento
		// 3º te los ordena
		// 4º convierte ese map creado en un SortedMap usando la expresion dada
		// (vamos carajote lo ultimo)
		return getProfesores().stream().collect(Collectors.toMap(p -> p,
				p -> p.getTutorias(), (p1, p2) -> p1, TreeMap::new));

	}

}