package fp.grados.tipos;

import java.util.Arrays;
import java.util.List;

import fp.grados.excepciones.ExcepcionAsignaturaNoValida;

public class AsignaturaImpl implements Asignatura {

	private String nombre;
	private String codigo;
	private Double creditos;
	private TipoAsignatura tipo;
	private Integer curso;
	private Departamento departamento;

	/***************************************************
	 * CONSTRUCTOR 1
	 *************************************************/

	public AsignaturaImpl(String nombre, String codigo, Double creditos,
			TipoAsignatura tipo, Integer curso, Departamento departament) {

		checkCodigo(codigo);
		checkCreditos(creditos);
		checkCurso(curso);

		this.nombre = nombre;
		this.codigo = codigo;
		this.creditos = creditos;
		this.tipo = tipo;
		this.curso = curso;
		setDepartamento(departament);
	}

	/***************************************************
	 * CONSTRUCTOR 2
	 *************************************************/
	// “Fundamentos de Programación#1234567#12.0#ANUAL#1”
	public AsignaturaImpl(String s) {

		// 1º Trozear la cadena
		List<String> trozos = Arrays.asList(s.split("#"));

		// 2º chequear que el numero de valores es correcto
		if (trozos.size() != 5) {
			throw new IllegalArgumentException();
		}

		// 3º Copiar y transformar cada subcadena al atributo correspondiente
		String nombre = trozos.get(0).trim();
		String codigo = trozos.get(1).trim();
		Double creditos = new Double(trozos.get(2).trim()); // para transformar
															// el String en
															// Double
		TipoAsignatura tipo = TipoAsignatura.valueOf(trozos.get(3).trim());
		Integer curso = new Integer(trozos.get(4).trim()); // para transformar
															// el String en
															// Integer
		// No se pone el atributo departamento por que al ser un objeto trae de
		// defecto el ser un null

		// 4º chequear las restricciones del tipo
		checkCodigo(codigo);
		checkCreditos(creditos);
		checkCurso(curso);

		// 5º copiar los atributos
		this.nombre = nombre;
		this.codigo = codigo;
		this.creditos = creditos;
		this.tipo = tipo;
		this.curso = curso;

	}

	/*************************************************** EXCEPCIONES ***************************************************/

	private void checkCodigo(String codigo) {
		if (codigo.length() != 7) {
			throw new ExcepcionAsignaturaNoValida(
					"El codigo debe estar formado por 7 digitos.");
		}

		try {
			new Integer(codigo);
		} catch (NumberFormatException n) {
			throw new ExcepcionAsignaturaNoValida(
					"El codigo debe estar formado por 7 digitos.");
		}

	}

	private void checkCreditos(Double creditos) {
		if (creditos <= 0) {
			throw new ExcepcionAsignaturaNoValida(
					"Los créditos deben ser un numero distinto de 0.");
		}

	}

	private void checkCurso(Integer curso) {
		if (curso < 1 || curso > 4) {
			throw new ExcepcionAsignaturaNoValida(
					"El curso debe estar comprendido entre 1 y 4.");
		}
	}

	/***************************************************
	 * GETTERS AND SETTERS
	 *******************************************/

	public String getNombre() {
		return nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public Double getCreditos() {
		return creditos;
	}

	public TipoAsignatura getTipo() {
		return tipo;
	}

	public Integer getCurso() {
		return curso;
	}

	public String getAcronimo() {
		String acronimo = "";
		// char[] array =getNombre().toCharArray();
		String nombre = getNombre();

		for (Character c : nombre.toCharArray()) {
			if (Character.isLetter(c) && Character.isUpperCase(c)) {
				acronimo += c;
			}
		}
		return acronimo;

	}

	public Departamento getDepartamento() {
		return departamento;
	}

	// 2.- Tiene que modificar la informacion del elemento

	public void setDepartamento(Departamento newDpto) {

		Departamento oldDpto = this.departamento;
		// 2.1- tomar el elemento actual que se va a cambiar

		if (newDpto != oldDpto) {
			this.departamento = newDpto; //
			// 2.2.- Chequear identidad(lo nuevbo ser distinto d ela antiguo)
			if (oldDpto != null) {
				oldDpto.eliminaAsignatura(this); // Se desvincula del
													// departamento
			}

			this.departamento = newDpto;

			if (newDpto != null) {
				newDpto.nuevaAsignatura(this); // se vincula con el nuevo
												// departamento
			}
		}
	}

	/*********************************************
	 * EQUALS HASHCODE COMPARETO
	 *******************************************/

	public boolean equals(Object obj) {
		boolean sig = false;
		if (obj instanceof Asignatura) {
			Asignatura x = (Asignatura) obj;
			sig = this.getCodigo().equals(x.getCodigo());
		}

		return sig;
	}

	public int hashCode() {
		return this.getCodigo().hashCode();

	}

	public int compareTo(Asignatura x) {
		int sig = this.getCodigo().compareTo(x.getCodigo());
		return sig;

	}

	// generate source equals and hashcode
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
	// return result;
	// }
	//
	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj) {
	// return true;
	// }
	// if (obj == null) {
	// return false;
	// }
	// if (!(obj instanceof AsignaturaImpl)) {
	// return false;
	// }
	// AsignaturaImpl other = (AsignaturaImpl) obj;
	// if (codigo == null) {
	// if (other.codigo != null) {
	// return false;
	// }
	// } else if (!codigo.equals(other.codigo)) {
	// return false;
	// }
	// return true;
	// }

	/*************************************************** REPRESENTACION ************************************************/

	public String toString() {
		return "(" + getCodigo() + ")" + getNombre();
	}

}
