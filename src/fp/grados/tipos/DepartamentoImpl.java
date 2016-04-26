package fp.grados.tipos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;

public class DepartamentoImpl implements Departamento {

	private String nombre;
	private Set<Asignatura> asignaturas;
	private Set<Profesor> profesor;

	/*************************************************** CONSTRUCTOR ***************************************************/

	public DepartamentoImpl(String nombre) {
		this.nombre = nombre;
		this.asignaturas = new HashSet<Asignatura>();
		this.profesor = new HashSet<Profesor>();

	}

	// private void checkNuevaNota(Asignatura a) {
	// if (asignaturas.equals(-1)) {
	// a.setDepartamento(this);
	// asignaturas.add(a);
	// }
	//
	// }

	/***************************************************
	 * GETTERS AND SETTERS
	 *******************************************/

	public String getNombre() {
		return nombre;
	}

	public Set<Asignatura> getAsignaturas() {
		// una vez mas, hay que hacer copia de las asignaturas, wiiiii
		// no te preocupes que tambien me ha pasado jajaja

		return new HashSet<Asignatura>(asignaturas);
	}

	public void nuevaAsignatura(Asignatura a) {
		a.setDepartamento(this);
		asignaturas.add(a);
	}

	public void eliminaAsignatura(Asignatura a) {
		a.setDepartamento(null);
		asignaturas.remove(a);
	}

	public Set<Profesor> getProfesores() {
		// copia de los profesores
		return new HashSet<Profesor>(profesor);
	}

	public void borraTutorias() {
		for (Profesor p : getProfesores()) {
			p.borraTutorias();
		}
	}

	public Boolean existeProfesorAsignado(Asignatura a) {
		Boolean result = false;

		for (Profesor p : getProfesores()) {
			result = result || p.getAsignaturas().contains(a);

			if (result) {
				break;
			}
		}
		return result;
	}

	public void borraTutorias(Categoria c) {
		// Has recorrido los profesores, pero estas eliminando la categoria, no
		// la tutoria
		// tienes que hacer un if, comprobando que la categoria del profesor sea
		// la misma que la que te dan
		// y si se cumple, usar el metodo borra tutorias del profesor
		for (Profesor p : getProfesores()) {
			if (p.equals(c)) {
				p.borraTutorias();
			}
			profesor.remove(p);
		}

	}

	public void nuevoProfesor(Profesor p) {
		p.setDepartamento(this);
		profesor.add(p);
	}

	public void eliminaProfesor(Profesor pr) {
		pr.setDepartamento(null);
		profesor.remove(pr);
	}

	public Boolean estanTodasAsignaturasAsignadas() {
		Boolean asignada = false;
		List<Asignatura> asignatura = new ArrayList<Asignatura>();

		for (Profesor p : getProfesores()) {
			asignatura = p.getAsignaturas();// primero recorre y mete las
											// asignaturas de un profesor en la
											// lista asignatura
		}
		if (asignatura.containsAll(getAsignaturas())) {
			asignada = true; // y aqui comprueba que sean las mismas asignaturas
								// en ambos lados y va fuera del for porque es
								// para comprobar lo anterior si es cierto
								// devuelve true
		}
		return asignada;
	}

	public void eliminaAsignacionProfesorado(Asignatura a) {
		for (Profesor p : getProfesores()) {
			if (p.getAsignaturas().contains(a)) {
				p.eliminaAsignatura(a);
			}
		}

	}

	public SortedMap<Asignatura, SortedSet<Profesor>> getProfesoresPorAsignatura() {
		SortedMap<Asignatura, SortedSet<Profesor>> map = new TreeMap<Asignatura, SortedSet<Profesor>>();

		for (Profesor p : this.getProfesores()) {
			for (Asignatura asig : p.getAsignaturas()) {
				SortedSet<Profesor> pr = new TreeSet<Profesor>();

				pr.add(p);
				if (map.containsKey(asig)) {
					map.get(asig).addAll(pr);
				} else {
					map.put(asig, pr);
				}
			}
		}
		return map;
	}

	public SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor() {
		SortedMap<Profesor, SortedSet<Tutoria>> map = new TreeMap<Profesor, SortedSet<Tutoria>>();
		SortedSet<Tutoria> tut = new TreeSet<Tutoria>();
		for (Profesor p : this.getProfesores()) {
			tut.addAll(p.getTutorias());
			map.put(p, tut);
		}
		return map;
	}

	/***************************************************
	 * EQUALS HASHCODE COMPARETO
	 *************************************/

	public int hashCode() {
		return getNombre().hashCode();
	}

	public boolean equals(Object o) {
		boolean result = false;
		if (o instanceof Departamento) {
			Departamento dp = (Departamento) o;
			result = getNombre().equals(dp.getNombre());

		}
		return result;
	}

	public int compareTo(Departamento o) {
		return getNombre().compareTo(o.getNombre());
	}

	/*************************************************** REPRESENTACION ************************************************/

	public String toString() {
		return getNombre();
	}

	public Profesor getProfesorMaximaDedicacionMediaPorAsignatura() {

		Comparator<Profesor> cmp = Comparator.comparing(
				x -> x.getDedicacionTotal() / x.getAsignaturas().size());

		Predicate<Profesor> prend = (x -> !x.getAsignaturas().isEmpty());

		return getProfesores().stream().filter(prend).max(cmp).get();
	}

}
