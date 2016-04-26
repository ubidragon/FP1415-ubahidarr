package fp.grados.tipos;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl implements Centro {

	private String Nombre, Direccion;
	private Integer NumeroPlantas, NumeroSotanos;
	private Set<Espacio> Espacios;

	/*************************************************** CONSTRUCTOR ***************************************************/

	public CentroImpl(String nombre, String direccion, Integer numeroPlantas,
			Integer numeroSotanos) {

		checkNumeroPlantas(numeroPlantas);
		checkNumeroSotanos(numeroSotanos);

		Nombre = nombre;
		Direccion = direccion;
		NumeroPlantas = numeroPlantas;
		NumeroSotanos = numeroSotanos;
		Espacios = new HashSet<Espacio>();
	}

	/*************************************************** EXCEPCIONES ***************************************************/

	private void checkNumeroSotanos(Integer sotanos) {
		if (sotanos < 0) {
			throw new ExcepcionCentroNoValido();
		}
	}

	private void checkNumeroPlantas(Integer plantas) {
		if (plantas < 1) {
			throw new ExcepcionCentroNoValido();
		}
	}

	/***************************************************
	 * GETTERS AND SETTERS
	 *******************************************/

	public String getNombre() {
		return Nombre;
	}

	public String getDireccion() {
		return Direccion;
	}

	public Integer getNumeroPlantas() {
		return NumeroPlantas;
	}

	public Integer getNumeroSotanos() {
		return NumeroSotanos;
	}

	public Set<Espacio> getEspacios() {
		return new HashSet<Espacio>(Espacios);
	}

	public Integer[] getConteosEspacios() {
		Integer[] result = new Integer[TipoEspacio.values().length];
		Arrays.fill(result, 0);

		for (Espacio esp : getEspacios()) {
			result[esp.getTipo().ordinal()]++;
		}

		return result;
	}

	public Set<Despacho> getDespachos() {
		Set<Despacho> result = new HashSet<Despacho>();

		for (Espacio e : getEspacios()) {

			if (e instanceof Despacho) {
				result.add((Despacho) e);
			}
		}
		return result;
	}

	public Set<Despacho> getDespachos(Departamento d) {
		Set<Despacho> result = new HashSet<Despacho>();

		for (Despacho dp : getDespachos()) {
			Set<Profesor> profesDpto = d.getProfesores();
			Set<Profesor> profesDespacho = dp.getProfesores();
			profesDespacho.retainAll(profesDpto);

			if (!profesDespacho.isEmpty()) {
				result.add(dp);
			}
		}
		return result;
	}

	public void nuevoEspacio(Espacio e) {
		Integer pl = getNumeroPlantas();
		Integer sot = getNumeroSotanos();
		if (e.getPlanta() > pl - 1 || e.getPlanta() < -sot) {
			throw new ExcepcionCentroOperacionNoPermitida(
					"Numero erroneo de plantas");
		}
		Espacios.add(e);
	}

	public void eliminaEspacio(Espacio e) {
		Espacios.remove(e);
	}

	public Set<Profesor> getProfesores() {
		Set<Profesor> prof = new HashSet<Profesor>();

		for (Despacho d : getDespachos()) {
			Set<Profesor> p = d.getProfesores();
			prof.addAll(p);
		}

		return prof;
	}

	public Set<Profesor> getProfesores(Departamento d) {
		Set<Profesor> prof = new HashSet<Profesor>();

		for (Profesor p : this.getProfesores()) {
			if (p.getDepartamento().equals(d)) {
				prof.add(p);
			}
		}
		return prof;
	}

	public Espacio getEspacioMayorCapacidad() {
		Espacio capacidad = null;
		Integer capacidad1 = 0;
		for (Espacio cap : getEspacios()) {
			Integer capacidad2 = cap.getCapacidad();
			if (capacidad1 < capacidad2) {
				capacidad1 = capacidad2;
				capacidad = cap;
			}
		}
		return capacidad;
	}

	public SortedMap<Profesor, Despacho> getDespachosPorProfesor() {
		SortedMap<Profesor, Despacho> map = new TreeMap<Profesor, Despacho>();

		for (Despacho d : getDespachos()) {

			for (Profesor p : d.getProfesores()) {
				map.put(p, d);
			}
		}
		return map;
	}

	/*********************************************
	 * EQUALS HASHCODE COMPARETO
	 *******************************************/

	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Centro) {
			Centro c = (Centro) obj;
			result = getNombre().equals(c.getNombre());
		}
		return result;
	}

	public int hashCode() {
		return getNombre().hashCode();
	}

	public int compareTo(Centro o) {
		return getNombre().compareTo(o.getNombre());
	}

	/*************************************************** REPRESENTACION ************************************************/

	public String toString() {
		return getNombre();
	}

	public SortedSet<Espacio> getEspaciosOrdenadosPorCapacidad() {

		Comparator<Espacio> cmp = Comparator.comparing(Espacio::getCapacidad)
				.reversed().thenComparing(Comparator.naturalOrder());

		SortedSet<Espacio> result = new TreeSet<>(cmp);
		// no es necesario poner el tipo en TreeSet<Espacio> solo el <> debido a
		// Java8
		result.addAll(getEspacios());

		return result;
	}

}