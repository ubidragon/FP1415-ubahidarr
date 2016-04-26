package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public interface Departamento extends Comparable<Departamento> {

	String getNombre();

	Set<Asignatura> getAsignaturas();

	Boolean existeProfesorAsignado(Asignatura a);

	Boolean estanTodasAsignaturasAsignadas();

	Set<Profesor> getProfesores();

	void nuevaAsignatura(Asignatura a);

	void eliminaAsignatura(Asignatura a);

	void nuevoProfesor(Profesor p);

	void eliminaProfesor(Profesor p);

	void borraTutorias();

	void borraTutorias(Categoria c);

	void eliminaAsignacionProfesorado(Asignatura a);

	SortedMap<Asignatura, SortedSet<Profesor>> getProfesoresPorAsignatura();

	SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor();

	Profesor getProfesorMaximaDedicacionMediaPorAsignatura();

}