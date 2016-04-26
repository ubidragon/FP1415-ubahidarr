package fp.grados.tipos;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CentroImpl2 extends CentroImpl {

	public CentroImpl2(String nombre, String direccion, Integer numeroPlantas,
			Integer numeroSotanos) {
		super(nombre, direccion, numeroPlantas, numeroSotanos);

	}

	public Espacio getEspacioMayorCapacidad() {
		Set<Espacio> espacios = getEspacios();
		Comparator<Espacio> cmp = Comparator.comparing(Espacio::getCapacidad);

		//
		// if(!res.isPresent()){
		// throw new
		// ExcepcionCentroOperacionNoPermitida("el centro no tiene ese espacio")
		// }

		return espacios.stream().max(cmp).get();

	}

	public Integer[] getConteosEspacios() {
		Set<Espacio> espacios = getEspacios();

		return new Integer[] { cuentaEspaciosTipo(TipoEspacio.TEORIA, espacios),
				cuentaEspaciosTipo(TipoEspacio.LABORATORIO, espacios),
				cuentaEspaciosTipo(TipoEspacio.SEMINARIO, espacios),
				cuentaEspaciosTipo(TipoEspacio.EXAMEN, espacios),
				cuentaEspaciosTipo(TipoEspacio.OTRO, espacios) };

	}

	private Integer cuentaEspaciosTipo(TipoEspacio tipo,
			Set<Espacio> espacios) {
		/*
		 * pasamos de la coleccion a un stream lo hemos filtrado para que solo
		 * haya tipos de tipo TIPO y para contar usaremos count() que nos
		 * devuelve el numero de la coleccion inicial
		 */

		return (int) espacios.stream().filter(e -> e.getTipo().equals(tipo))
				.count();
	}

	public Set<Despacho> getDespachos() {

		return getEspacios().stream().filter(x -> x instanceof Despacho)
				.map(x -> (Despacho) x).collect(Collectors.toSet());
	}

	public Set<Despacho> getDespachos(Departamento d) {
		//

		return getDespachos().stream()
				.filter(x -> x.getProfesores().equals(d.getProfesores()))
				.collect(Collectors.toSet());
	}

	public Set<Profesor> getProfesores() {
		/*
		 * con (flatMap) esto aplanamos el stream
		 */
		return getDespachos().stream().flatMap(d -> d.getProfesores().stream())
				.collect(Collectors.toSet());

	}

	public Set<Profesor> getProfesores(Departamento d) {
		return getProfesores().stream()
				.filter(p -> p.getDepartamento().equals(d))
				.collect(Collectors.toSet());
	}

	public SortedMap<Profesor, Despacho> getDespachosPorProfesor() {
		return getProfesores().stream().filter(x -> findDespacho(x))
				.collect(Collectors.toMap(x -> x, x -> searchDespacho(x),
						(x1, x2) -> x1, TreeMap::new));
	}

	private Boolean findDespacho(Profesor x) {
		return getDespachos().stream()
				.anyMatch(d -> d.getProfesores().contains(x));
	}

	private Despacho searchDespacho(Profesor x) {
		return getDespachos().stream()
				.filter(d -> d.getProfesores().contains(x)).findFirst().get();
	}
}