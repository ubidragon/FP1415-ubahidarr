package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public interface Alumno extends Persona {
	Set<Asignatura> getAsignaturas();

	Boolean estaMatriculadoEn(Asignatura asig);

	Integer getCurso();

	void matriculaAsignatura(Asignatura asig);

	void eliminaAsignatura(Asignatura asig);

	void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria,
			Double nota, Boolean mencionHonor);

	void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria,
			Double nota);

	Expediente getExpediente();

	SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura();

	SortedSet<Asignatura> getAsignaturasOrdenadasPorCurso();

}