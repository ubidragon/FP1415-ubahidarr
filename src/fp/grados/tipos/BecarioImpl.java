package fp.grados.tipos;

import java.time.LocalDate;

import fp.grados.excepciones.ExcepcionBecarioNoValido;

public class BecarioImpl extends AlumnoImpl implements Becario {

	private Beca beca;
	private LocalDate fechaComienzo;

	/***************************************************
	 * CONSTRUCTOR 1
	 *************************************************/

	public BecarioImpl(String dni, String nombre, String apellidos,
			LocalDate fecha, String email, Beca beca, LocalDate fechaComienzo) {

		super(dni, nombre, apellidos, fecha, email);

		this.beca = beca;
		this.fechaComienzo = fechaComienzo;

		checkFechaComienzo(fechaComienzo);
	}

	/***************************************************
	 * CONSTRUCTOR 2
	 *************************************************/

	public BecarioImpl(String dni, String nombre, String apellidos,
			LocalDate fecha, String email, String codigo, Double cuantiaTotal,
			Integer duracion, TipoBeca tipo, LocalDate fechaComienzo) {

		super(dni, nombre, apellidos, fecha, email);

		this.beca = new BecaImpl(codigo, cuantiaTotal, duracion, tipo);
		this.fechaComienzo = fechaComienzo;

		checkFechaComienzo(fechaComienzo);

	}

	/*************************************************** EXCEPCIONES ***************************************************/

	private void checkFechaComienzo(LocalDate comienzo) {
		if (!comienzo.isAfter(LocalDate.now())) {
			throw new ExcepcionBecarioNoValido();
		}

	}

	public void setEmail(String email) {
		throw new UnsupportedOperationException();
	}

	/***************************************************
	 * GETTERS AND SETTERS
	 *******************************************/

	public Beca getBeca() {
		return beca;
	}

	@Override
	public LocalDate getFechaComienzo() {
		return fechaComienzo;
	}

	@Override
	public LocalDate getFechaFin() {
		return getFechaComienzo().plusMonths(getBeca().getDuracion());
		// para calcular la fecha de finalizacion de la beca
	}

	@Override
	public void setFechaComienzo(LocalDate fechaComienzo) {
		checkFechaComienzo(fechaComienzo);
		this.fechaComienzo = fechaComienzo;
	}

	/*************************************************** REPRESENTACION ************************************************/

	public String toString() {

		return super.toString() + " " + getBeca();
	}

}
