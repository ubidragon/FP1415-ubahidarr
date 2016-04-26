package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionBecaNoValida;

public class BecaImpl implements Beca {
	private static final Integer DURACION_MINIMA = 1;
	private static final Double CUANTIA_MINIMA = 1500.0;
	private String codigo;
	private Double cuantiaTotal;
	private Integer duracion;
	private TipoBeca tipo;

	/***************************************************
	 * CONSTRUCTOR 1
	 **************************************************/

	public BecaImpl(String codigo, Double cuantiaTotal, Integer duracion,
			TipoBeca tipo) {
		// this(codigo, CUANTIA_MINIMA, DURACION_MINIMA ,tipo); se supone que
		// seria lo mismo.

		checkCuantiaTotal(cuantiaTotal);
		checkDuracion(duracion);
		checkCodigo(codigo);

		this.codigo = codigo;
		this.cuantiaTotal = cuantiaTotal;
		this.duracion = duracion;
		this.tipo = tipo;
	}

	/***************************************************
	 * CONSTRUCTOR 2
	 **************************************************/

	public BecaImpl(String codigo, TipoBeca tipo) {

		checkCodigo(codigo);

		this.codigo = codigo;
		this.cuantiaTotal = CUANTIA_MINIMA;
		this.duracion = DURACION_MINIMA;
		this.tipo = tipo;
	}

	/***************************************************
	 * CONSTRUCTOR 3
	 **************************************************/

	public BecaImpl(String s) {
		// 1º Trozear la cadena
		String[] trozos = s.split(",");

		// 2º chequear que el numero de valores es correcto
		if (trozos.length != 4) {
			throw new IllegalArgumentException();
		}

		// 3º Copiar y transformar cada subcadena al atributo correspondiente
		String codigo = trozos[0].trim();
		Double cuantiaTotal = new Double(trozos[1].trim());// para transformar
															// el String en
															// Double
		Integer duracion = new Integer(trozos[2].trim()); // para transformar el
															// String en Integer
		TipoBeca tipo = TipoBeca.valueOf(trozos[3].trim()); // para transformar
															// el String en el
															// tipo Beca

		// 4º chequear las restricciones del tipo
		checkCuantiaTotal(cuantiaTotal);
		checkDuracion(duracion);
		checkCodigo(codigo);

		// 5º copiar los atributos
		this.codigo = codigo;
		this.cuantiaTotal = cuantiaTotal;
		this.duracion = duracion;
		this.tipo = tipo;

	}

	/*************************************************** EXCEPCIONES ***************************************************/

	private void checkDuracion(Integer duracion2) {
		if (duracion2 < DURACION_MINIMA) {
			throw new ExcepcionBecaNoValida(
					"Se a producido un error al establecer la duracion, intentelo de nuevo");
		}
	}

	private void checkCuantiaTotal(Double cuantiaTotal) {
		if (cuantiaTotal < CUANTIA_MINIMA) {
			throw new ExcepcionBecaNoValida(
					"Error en la cuantia, revise los datos por favor");

		}
	}

	private void checkCodigo(String codigo) {
		Boolean esCorrecto = codigo.length() == 7
				&& Character.isLetter(codigo.charAt(0))
				&& Character.isLetter(codigo.charAt(1))
				&& Character.isLetter(codigo.charAt(2))
				&& Character.isDigit(codigo.charAt(3))
				&& Character.isDigit(codigo.charAt(4))
				&& Character.isDigit(codigo.charAt(5))
				&& Character.isDigit(codigo.charAt(6));

		if (!esCorrecto) {
			throw new ExcepcionBecaNoValida(
					"codigo incorrecto, inserte de nuevo el codigo");
		}
	}

	/***************************************************
	 * GETTERS AND SETTERS
	 *******************************************/

	public String getCodigo() {
		return codigo;
	}

	public Double getCuantiaTotal() {
		return cuantiaTotal;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public TipoBeca getTipo() {
		return tipo;
	}

	public Double getCuantiaMensual() {
		return getCuantiaTotal() / getDuracion();
	}

	public void setCuantiaTotal(Double cuantiaTotal) {
		checkCuantiaTotal(cuantiaTotal);
		this.cuantiaTotal = cuantiaTotal;

	}

	public void setDuracion(Integer duracion) {
		checkDuracion(duracion);
		this.duracion = duracion;

	}

	/*********************************************
	 * EQUALS HASHCODE COMPARETO
	 *******************************************/

	public boolean equals(Object b) {
		boolean bec = false;
		if (b instanceof Beca) {
			Beca a = (Beca) b;
			bec = this.getCodigo().equals(a.getCodigo())
					&& this.getTipo().equals(a.getTipo());
		}
		return bec;
	}

	public int hashCode() {
		return this.getCodigo().hashCode() * 59 + this.getTipo().hashCode();
	}

	public int compareTo(Beca be) {
		int bec = this.getCodigo().compareTo(be.getCodigo());
		if (bec == 0)
			bec = this.getTipo().compareTo(be.getTipo());
		return bec;
	}

	/*************************************************** REPRESENTACION ************************************************/

	public String toString() {
		return "[" + getCodigo() + "," + getTipo().toString().toLowerCase()
				+ "]";
	}

}
