package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public interface Centro extends Comparable<Centro> {
	String getNombre();

	String getDireccion();

	Integer getNumeroPlantas();

	Integer getNumeroSotanos();

	Integer[] getConteosEspacios();

	Set<Despacho> getDespachos();

	Set<Despacho> getDespachos(Departamento d);

	Set<Profesor> getProfesores();

	Set<Profesor> getProfesores(Departamento d);

	Espacio getEspacioMayorCapacidad();

	// �CUIDADO! Aqu� no hay una relaci�n bidireccional
	// (no hay que hacer nada en el tipo Espacio)

	Set<Espacio> getEspacios();

	void nuevoEspacio(Espacio e);

	void eliminaEspacio(Espacio e);

	SortedMap<Profesor, Despacho> getDespachosPorProfesor();

	SortedSet<Espacio> getEspaciosOrdenadosPorCapacidad();

}