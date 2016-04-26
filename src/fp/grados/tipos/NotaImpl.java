package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionNotaNoValida;

public class NotaImpl implements Nota {

	private Asignatura asignatura; // el "final" indica que una vez que se
									// le de un valor al atributo tendra
									// ese valor y no otro
	private Integer curso;
	private Convocatoria convocatoria;
	private Double nota;
	private Boolean mencion;

	/***************************************************
	 * CONSTRUCTOR 1
	 *************************************************/

	public NotaImpl(Asignatura asignatura, Integer curso,
			Convocatoria convocatoria, Double nota, Boolean mencion) {

		checkValor(nota);
		checkMencion(nota, mencion);

		this.asignatura = asignatura;
		this.curso = curso;
		this.convocatoria = convocatoria;
		this.nota = nota;
		this.mencion = mencion;

	}

	/***************************************************
	 * CONSTRUCTOR 2
	 *************************************************/

	public NotaImpl(Asignatura asignatura, Integer curso,
			Convocatoria convocatoria, Double nota) {

		checkValor(nota);

		this.asignatura = asignatura;
		this.curso = curso;
		this.convocatoria = convocatoria;
		this.nota = nota;
		this.mencion = false;
	}

	/***************************************************
	 * CONSTRUCTOR 3
	 *************************************************/
	// â€œFundamentos de
	// ProgramaciÃ³n#1234567#12.0#ANUAL#1,2014,PRIMERA,10.0,trueâ€�
	public NotaImpl(String s) {
		// 1Âº Trozear la cadena
		String[] trozos = s.split(";");

		// 2Âº chequear que el numero de valores es correcto
		if (trozos.length != 5) {
			throw new IllegalArgumentException();
		}

		// 3Âº Copiar y transformar cada subcadena al atributo correspondiente
		Asignatura asignatura = new AsignaturaImpl((trozos[0].trim()));
		Integer curso = new Integer(trozos[1].trim()); // para transformar el
														// String en Integer
		Convocatoria convocatoria = Convocatoria.valueOf(trozos[2].trim()); // para
																			// transformar
																			// el
																			// String
																			// en
																			// el
																			// tipo
																			// Convocatoria
		Double nota = new Double(trozos[3].trim());// para transformar el String
													// en Double
		Boolean mencion = new Boolean(trozos[4].trim());// para transformar el
														// String en Boolean

		// 4Âº chequear las restricciones del tipo
		checkValor(nota);
		checkMencion(nota, mencion);

		// 5Âº copiar los atributos
		this.asignatura = asignatura;
		this.curso = curso;
		this.convocatoria = convocatoria;
		this.nota = nota;
		this.mencion = mencion;

	}

	/*************************************************** EXCEPCIONES ***************************************************/

	private void checkValor(Double nota) {
		if (nota < 0 || nota > 10)
			throw new ExcepcionNotaNoValida(
					"Se ha producido un error al introducir las notas"
							+ ", intentelo de nuevo mas tarde");
	}

	private void checkMencion(Double nota, Boolean mencion) {
		if (nota < 9 && mencion == true)
			throw new ExcepcionNotaNoValida("Se ha producido un error");

	}

	/***************************************************
	 * GETTERS AND SETTERS
	 *******************************************/

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public Integer getCursoAcademico() {
		return curso;
	}

	public Convocatoria getConvocatoria() {
		return convocatoria;
	}

	public Double getValor() {
		return nota;
	}

	public Boolean getMencionHonor() {
		return mencion;
	}

	public Calificacion getCalificacion() {
		Calificacion c = null;
		if (nota < 5.0) {
			c = Calificacion.SUSPENSO;
		} else if (nota >= 5.0 && nota < 7.0) {
			c = Calificacion.APROBADO;
		} else if (nota >= 7.0 && nota < 9.0) {
			c = Calificacion.NOTABLE;
		} else if (nota >= 9.0 && mencion == false) {
			c = Calificacion.SOBRESALIENTE;
		} else if (nota >= 9.0 && mencion == true) {
			c = Calificacion.MATRICULA_DE_HONOR;
		}

		return c;
	}

	/*********************************************
	 * EQUALS HASHCODE COMPARETO
	 *******************************************/

	public boolean equals(Object n) {
		boolean note = false;
		if (n instanceof Nota) {
			Nota a = (Nota) n;
			note = this.getCursoAcademico().equals(a.getCursoAcademico())
					&& this.getAsignatura().equals(a.getAsignatura())
					&& this.getConvocatoria().equals(a.getConvocatoria());
		}
		return note;
	}

	public int hashCode() {
		return this.getCursoAcademico().hashCode()
				+ this.getAsignatura().hashCode() * 31
				+ this.getConvocatoria().hashCode() * 31 * 31;
	}

	public int compareTo(Nota no) {
		int not;

		Integer cursoacad = getCursoAcademico();
		Integer cursoacade = no.getCursoAcademico();
		not = cursoacad.compareTo(cursoacade);

		if (not == 0) {

			Asignatura asin = getAsignatura();
			Asignatura asint = no.getAsignatura();
			not = asin.compareTo(asint);
			if (not == 0) {
				Convocatoria conv = getConvocatoria();
				Convocatoria combo = no.getConvocatoria();
				not = conv.compareTo(combo);
			}
		}

		return not;
	}

	/*************************************************** REPRESENTACION ************************************************/

	public String toString() {
		Integer ano = curso + 1;
		String a = ano.toString();
		String a1 = a.substring(2, 4);
		return "" + getAsignatura() + "," + curso + "-" + a1 + ","
				+ convocatoria + "," + nota + "," + getCalificacion() + "";
	}

}
