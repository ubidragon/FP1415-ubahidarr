package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionEspacioNoValido;

public class EspacioImpl implements Espacio {

	private TipoEspacio Espacio;
	private String aula;
	private Integer Planta;
	private Integer capacidad;

	/***************************************************
	 * CONSTRUCTOR 1
	 *************************************************/

	public EspacioImpl(TipoEspacio nombreAula, String aula, Integer capacidad,
			Integer Planta) {

		checkCapacidad(capacidad,
				"EspacioImpl.EspacioImpl: Se ha producido un error en la capacidad del aula");

		Espacio = nombreAula;
		this.aula = aula;
		this.Planta = Planta;
		this.capacidad = capacidad;
	}

	/***************************************************
	 * CONSTRUCTOR 2
	 *************************************************/
	public EspacioImpl(String s) {

		// 1º Trozear la cadena
		String[] trozos = s.split(",");

		// 2º chequear que el numero de valores es correcto
		if (trozos.length != 4) {
			throw new IllegalArgumentException();
		}

		// 3º Copiar y transformar cada subcadena al atributo correspondiente
		TipoEspacio Espacio = TipoEspacio.valueOf(trozos[3].trim());
		String aula = trozos[0].trim();
		Integer Planta = new Integer(trozos[1].trim());
		Integer capacidad = new Integer(trozos[2].trim());

		// 4º chequear las restricciones del tipo
		checkCapacidad(capacidad,
				"EspacioImpl.EspacioImpl: Se ha producido un error en la capacidad del aula");

		// 5º copiar los atributos
		this.Espacio = Espacio;
		this.aula = aula;
		this.Planta = Planta;
		this.capacidad = capacidad;

	}

	/*************************************************** EXCEPCIONES ***************************************************/

	private void checkCapacidad(Integer capacidad, String msg) {
		if (capacidad <= 0)
			throw new ExcepcionEspacioNoValido(msg);
	}

	/***************************************************
	 * GETTERS AND SETTERS
	 *******************************************/

	public TipoEspacio getTipo() {
		return Espacio;
	}

	public void setTipo(TipoEspacio nombreAula) {
		Espacio = nombreAula;

	}

	public String getNombre() {
		return aula;
	}

	public Integer getPlanta() {
		return Planta;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setNombre(String aula) {
		this.aula = aula;
	}

	public void setCapacidad(Integer capacidad) {
		checkCapacidad(capacidad,
				"EspacioImpl.EspacioImpl: Se ha producido un error en la capacidad del aula");
		this.capacidad = capacidad;
	}

	/*************************************************** REPRESENTACION ***************************************************/

	public String toString() {
		return aula + "( planta " + Planta + ")";
	}

	/***************************************************
	 * EQUALS HASHCODE Y COMPARETO
	 *************************************/

	public boolean equals(Object e) {
		boolean esp = false;
		if (e instanceof Espacio) {
			Espacio es = (Espacio) e;
			esp = this.getNombre().equals(es.getNombre())
					&& this.getPlanta().equals(es.getPlanta());
		}
		return esp;
	}

	public int hashCode() {
		return this.getNombre().hashCode() * 31
				+ this.getPlanta().hashCode() * 59;
	}

	public int compareTo(Espacio esp) {
		int espa = this.getPlanta().compareTo(esp.getPlanta());
		if (espa == 0)
			espa = this.getNombre().compareTo(esp.getNombre());

		return espa;
	}

}
