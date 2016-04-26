package fp.grados.tipos;

public interface Espacio extends Comparable<Espacio> {

	TipoEspacio getTipo();// Con esto determinamos el tipo de aula

	String getNombre();

	Integer getPlanta();

	Integer getCapacidad();

	void setNombre(String aula);

	void setCapacidad(Integer capacidad);

	void setTipo(TipoEspacio espacio);

	// getNumEspaciosCreados();
	// getEspaciosCreados();
	// getPlantaMayorEspacio();
	// getPlantaMenorEspacio();

}
