package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AlumnoImpl2 extends AlumnoImpl {

	public AlumnoImpl2(String dni, String nombre, String apellidos,
			LocalDate fecha, String email) {
		super(dni, nombre, apellidos, fecha, email);

	}

	public AlumnoImpl2(String s) {
		super(s);
	}

	public Integer getCurso() {

		Integer result = 0;

		if (getAsignaturas().isEmpty()) {
			result = 0;
		} else {
			result = getAsignaturas().stream()
					.max(Comparator.comparing(x -> x.getCurso())).get()
					.getCurso();
		}
		return result;
	}

	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura() {

		return getExpediente().getNotas().stream()
				.collect(Collectors.toMap(x -> x.getAsignatura(),
						x -> x.getCalificacion(),
						(x1, x2) -> calificacion(x1, x2), TreeMap::new));

	}

	private Calificacion calificacion(Calificacion x1, Calificacion x2) {
		Integer calif1 = x1.ordinal();
		Integer calif2 = x2.ordinal();
		Calificacion c = null;
		if (calif2.compareTo(calif1) > 0) {
			c = x2;
		} else {
			c = x1;
		}
		// Equivcale al if else anterior se llama OPERADOR TERNARIO
		// c = calif2.compareTo(calif1) > 0? x2:x1;

		return c;
	}

}