package fp.grados.tipos;

public interface Beca extends Comparable<Beca> {

	String getCodigo();

	Double getCuantiaTotal();

	Integer getDuracion();

	TipoBeca getTipo();

	Double getCuantiaMensual();

	void setCuantiaTotal(Double CuantiaTotal);

	void setDuracion(Integer duracion);

	// getNumBecasCreadas();
	// getNumBecasTipo();
	// getBecasCreadas();

}
