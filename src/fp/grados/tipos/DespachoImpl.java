package fp.grados.tipos;

import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionDespachoNoValido;

public class DespachoImpl extends EspacioImpl implements Despacho {

	private Set<Profesor> profesores;

	/***************************************************
	 * CONSTRUCTOR 1
	 *************************************************/

	public DespachoImpl(String aula, Integer capacidad, Integer Planta,
			Set<Profesor> profesores) {
		super(TipoEspacio.OTRO, aula, capacidad, Planta);

		this.profesores = new HashSet<Profesor>();
		this.profesores.addAll(profesores);

		checkNumeroProfesores(capacidad, profesores);

	}

	/***************************************************
	 * CONSTRUCTOR 2
	 *************************************************/

	public DespachoImpl(String aula, Integer capacidad, Integer Planta,
			Profesor profesores) {
		super(TipoEspacio.OTRO, aula, capacidad, Planta);

		this.profesores = new HashSet<Profesor>();
		this.profesores.add(profesores);

		checkNumeroProfesores(capacidad, getProfesores());

	}

	/***************************************************
	 * CONSTRUCTOR 3
	 *************************************************/

	public DespachoImpl(String aula, Integer capacidad, Integer Planta) {
		super(TipoEspacio.OTRO, aula, capacidad, Planta);

		this.profesores = new HashSet<Profesor>();

	}

	/***************************************************
	 * CONSTRUCTOR 2
	 *************************************************/

	public DespachoImpl(String s) {

		super(s + "," + TipoEspacio.OTRO);

		this.profesores = new HashSet<Profesor>();

		checkNumeroProfesores(getCapacidad(), profesores);

	}

	/*************************************************** EXCEPCIONES ***************************************************/

	private void checkNumeroProfesores(Integer capacidad,
			Set<Profesor> profesores) {
		if (profesores.size() > capacidad) {
			throw new ExcepcionDespachoNoValido();
		}

	}

	public void setTipo() {
		throw new UnsupportedOperationException();

	}

	/***************************************************
	 * GETTERS AND SETTERS
	 *******************************************/

	public Set<Profesor> getProfesores() {

		return this.profesores;
	}

	public void setProfesores(Set<Profesor> profesores) {
		this.profesores = profesores;
		checkNumeroProfesores(getCapacidad(), profesores);
	}

	public void setTipo(TipoEspacio Espacio) {
		throw new UnsupportedOperationException();
	}

	/*************************************************** REPRESENTACION ************************************************/

	public String toString() {
		return super.toString() + profesores;
	}

}
