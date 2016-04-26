package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;

public class AlumnoImpl extends PersonaImpl implements Alumno {

	private Set<Asignatura> asignaturas;
	private Expediente expediente;

	/***************************************************
	 * CONSTRUCTOR 1
	 *************************************************/

	public AlumnoImpl(String dni, String nombre, String apellidos,
			LocalDate fecha, String email) {
		super(dni, nombre, apellidos, fecha, email);
		// el expediente al ser una clase inicialmente vacia, tienes que
		// inicializarla como un clase vacio:
		expediente = new ExpedienteImpl();
		asignaturas = new HashSet<Asignatura>();
		checkEmail(email);

	}

	/***************************************************
	 * CONSTRUCTOR 2
	 *************************************************/
	// â€œ12345678Z,Juan,LÃ³pez GarcÃ­a,20/1/1998,juan@alum.us.esâ€�
	public AlumnoImpl(String s) {
		super(s);

		checkEmail(getEmail());

		asignaturas = new HashSet<Asignatura>();
		expediente = new ExpedienteImpl();
	}

	/*************************************************** EXCEPCIONES ***************************************************/

	private void checkEmail(String email) {
		if (!email.endsWith("@alum.us.es")) {
			throw new ExcepcionAlumnoNoValido(
					"Se ha producido un error al insertar el email");
		}
	}

	private void checkAsignatura(Asignatura asig, Boolean esperando) {

		if (!estaMatriculadoEn(asig).equals(esperando)) {
			throw new ExcepcionAlumnoOperacionNoPermitida();
		}
	}

	public void eliminaAsignatura(Asignatura asig) {
		if (!estaMatriculadoEn(asig)) {
			throw new ExcepcionAlumnoOperacionNoPermitida();
		}
		asignaturas.remove(asig);

	}

	/***************************************************
	 * GETTERS AND SETTERS
	 *******************************************/

	public Set<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void matriculaAsignatura(Asignatura asig) {
		checkAsignatura(asig, false);
		asignaturas.add(asig);

	}

	public Integer getCurso() {

		Integer result = 0;

		for (Asignatura asig : getAsignaturas()) {

			Integer curso = asig.getCurso();

			if (curso > result) {
				result = curso;
			}
			// result = curso > result ? curso : result;
		}
		return result;
	}

	public void setEmail(String email) {
		checkEmail(email);
		super.setEmail(email);
	}

	public Boolean estaMatriculadoEn(Asignatura asig) {
		return getAsignaturas().contains(asig);
	}

	public void evaluaAlumno(Asignatura a, Integer curso,
			Convocatoria convocatoria, Double nota, Boolean mencionHonor) {

		if (!asignaturas.contains(a)) {
			throw new ExcepcionAlumnoOperacionNoPermitida();
		}
		Nota n = new NotaImpl(a, curso, convocatoria, nota, mencionHonor);
		expediente.nuevaNota(n);

	}

	public void evaluaAlumno(Asignatura a, Integer curso,
			Convocatoria convocatoria, Double nota) {
		if (!asignaturas.contains(a)) {
			throw new ExcepcionAlumnoOperacionNoPermitida();
		}
		Nota n = new NotaImpl(a, curso, convocatoria, nota);
		expediente.nuevaNota(n);
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura() {

		SortedMap<Asignatura, Calificacion> map = new TreeMap<Asignatura, Calificacion>();
		for (Nota n : getExpediente().getNotas()) {
			if (map.containsKey(n.getAsignatura())) {
				if (map.get(n.getAsignatura()).ordinal() < n.getCalificacion()
						.ordinal()) {
					map.replace(n.getAsignatura(), n.getCalificacion());
				}
			} else {
				map.put(n.getAsignatura(), n.getCalificacion());
			}
		}
		return map;
	}

	public SortedSet<Asignatura> getAsignaturasOrdenadasPorCurso() {

		Comparator<Asignatura> cmp = Comparator.comparing(Asignatura::getCurso)
				.reversed().thenComparing(Comparator.naturalOrder());
		SortedSet<Asignatura> result = new TreeSet<>(cmp);
		result.addAll(getAsignaturas());
		return result;
	}

	/*************************************************** REPRESENTACION ************************************************/

	public String toString() {
		return "(" + getCurso() + "º)" + super.toString();
	}

}
