package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;

public class ProfesorImpl extends PersonaImpl implements Profesor {

	private SortedSet<Tutoria> tutorias;
	private Categoria categoria;
	private Departamento departamento;
	private List<Asignatura> asignatura;
	private List<Double> creditos;
	private static final Integer DEDICACION = 24;

	/*************************************************** CONSTRUCTORES *************************************************/

	public ProfesorImpl(String dni, String nombre, String apellidos,
			LocalDate fecha, String email, Categoria categoria,
			Departamento departamento) {
		super(dni, nombre, apellidos, fecha, email);

		checkEdad(getEdad());

		asignatura = new ArrayList<Asignatura>();
		creditos = new ArrayList<Double>();
		tutorias = new TreeSet<Tutoria>();
		this.categoria = categoria;
		setDepartamento(departamento);
	}

	/*************************************************** EXCEPCIONES ***************************************************/

	private void checkEdad(Integer edad) {
		if (edad < 18) {
			throw new ExcepcionProfesorNoValido(
					"Se ha producido un error al calcular la edad del profesor");
		}
	}

	private void checkAsignatura(Asignatura asig) {
		if ((getDepartamento()) == null && asig.getDepartamento() != null
				|| (getDepartamento()) != null && !(getDepartamento()
						.equals(asig.getDepartamento()))) {
			throw new ExcepcionProfesorOperacionNoPermitida("Error!!");
		}
	}

	private void checkDedicacion(Double dedicacion,
			Double numeroCreditosAsignatura) {
		// si la dedicacion NO es mayor de 0 y la dedicacion NO es menor
		// o igual a los creditos de la asignatura nueva ERROR
		if (!(dedicacion > 0.0 && dedicacion <= numeroCreditosAsignatura)) {
			throw new ExcepcionProfesorOperacionNoPermitida("Error!!");
		}
	}

	private void checkCreditos(Double dedicacionTotal) {
		// el private static final se usa en este check
		if (getDedicacionTotal() > DEDICACION) {
			throw new ExcepcionProfesorOperacionNoPermitida(
					"Demasiados creditos para este profesor");
		}
	}

	/***************************************************
	 * GETTERS AND SETTERS
	 *******************************************/

	public Categoria getCategoria() {
		return categoria;
	}

	public SortedSet<Tutoria> getTutorias() {
		return tutorias;
	}

	public void setFechaNacimiento(LocalDate fecha) {
		super.setFechaNacimiento(fecha);
		checkEdad(getEdad());
	}

	public void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos,
			DayOfWeek dia) {
		Tutoria a = new TutoriaImpl(dia, horaComienzo, duracionMinutos);
		tutorias.add(a);
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void borraTutoria(LocalTime horaComienzo, DayOfWeek dia) {
		tutorias.remove(this);
	}

	public void borraTutorias() {

		tutorias.removeAll(tutorias);
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public List<Asignatura> getAsignaturas() {
		// hay que hacer una copia de las asignaturas
		return new ArrayList<Asignatura>(asignatura);
	}

	public List<Double> getCreditos() {
		// hay que hacer una copia de los creditos
		return new ArrayList<Double>(creditos);
	}

	public Double getDedicacionTotal() {
		// la dedicacion se hace a partir de los creditos que vienen dados
		// en la lista
		// por lo que no hay que sacarle los creditos a las asignaturas, los
		// cuales pueden ser diferentes
		// es decir, recorres los creditos, no las asignaturas, y haces la suma
		Double dedicacionProfesor = 0.0;

		for (Double cerda : getCreditos()) {
			dedicacionProfesor = dedicacionProfesor + cerda;
		}

		return dedicacionProfesor;
	}

	public Double dedicacionAsignatura(Asignatura asig) {
		// aqui no se mete el check, solo a la hora de añadir la
		// asignatura, es decir, el imparte asignatura
		// no necesitas un for, en este caso, si el indice de la
		// asignatura no es -1
		// es decir, pertenece a las asignaturas del profesor
		// los creditos se encontraran en la misma posicion que la asignatura
		// por lo que index(de la asignatura) = index(de los creditos)

		Double result = 0.0;
		Integer index = getAsignaturas().indexOf(asig);

		if (!index.equals(-1)) {
			result = creditos.get(index); // esto quiere decir que el indice es
											// el de las
			// asignaturas y al estar las dos cosas en
			// la misma posicion y al coger el index de
			// asignatura veremos que es el mismo indice
			// de los creditos
		}

		return result;

	}

	public void setDepartamento(Departamento newDpto) {

		Departamento oldDpto = this.departamento;
		// 2.1- tomar el elemento actual que se va a cambiar
		if (newDpto != oldDpto) {
			this.departamento = newDpto; //
			// 2.2.- Chequear identidad(lo nuevbo ser distinto d ela antiguo)
			if (oldDpto != null) {
				oldDpto.eliminaProfesor(this); // Se desvincula del
												// departamento
			}
			this.departamento = newDpto;
			if (newDpto != null) {
				newDpto.nuevoProfesor(this); // se vincula con el nuevo
												// departamento
			}
		}
	}

	public void imparteAsignatura(Asignatura asig, Double dedicacion) {
		// si las asignaturas contienen a la asignatura sacas su indice y
		// le cambias la dedicacion(set(index, dedicacion))
		// si no, añades la asignatuas y añades los creditos, el metodo del
		// equals(-1) no vale como lo estas usando

		checkAsignatura(asig);
		checkDedicacion(dedicacion, asig.getCreditos());

		if (getAsignaturas().contains(asig)) {

			Integer index = getAsignaturas().indexOf(asig);
			creditos.set(index, dedicacion);// esto sirve para cambiar los
											// creditos de la poscion indicada
											// de la lista
		} else {
			asignatura.add(asig);
			creditos.add(dedicacion);
		}
		checkCreditos(getDedicacionTotal());
	}

	public void eliminaAsignatura(Asignatura asig) {
		Integer index = getAsignaturas().indexOf(asig);
		if (!index.equals(-1)) {
			asignatura.remove(asig);
			creditos.remove(index);
		}
	}

	/*************************************************** REPRESENTACION ************************************************/

	public String toString() {
		return super.toString() + "(" + getCategoria() + ")";
	}

}
