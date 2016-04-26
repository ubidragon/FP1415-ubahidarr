package fp.grados.tipos;

public interface BecaInmutable extends Comparable<BecaInmutable> {

	// TIENE QUE HACER QUE ESTO SEA INMUTABLE

	String getCodigo();

	Double getCuantiaTotal();

	Integer getDuracion();

	TipoBeca getTipo();

	Double getCuantiaMensual();

}
