package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;

public class TutoriaImpl implements Tutoria {

	private DayOfWeek dia;
	private LocalTime comienzo;
	private LocalTime fin;

	/***************************************************
	 * CONSTRUCTOR 1
	 *************************************************/

	public TutoriaImpl(DayOfWeek dia, LocalTime comienzo, LocalTime fin) {

		checkDia(dia);

		this.dia = dia;
		this.comienzo = comienzo;
		this.fin = fin;

		checkDuracion(getDuracion());
	}

	/***************************************************
	 * CONSTRUCTOR 2
	 *************************************************/

	public TutoriaImpl(DayOfWeek dia, LocalTime comienzo, Integer duracion) {
		checkDuracion(duracion);
		checkDia(dia);

		this.dia = dia;
		this.comienzo = comienzo;
		this.fin = comienzo.plusMinutes(duracion);

	}

	/***************************************************
	 * CONSTRUCTOR 3
	 *************************************************/

	public TutoriaImpl(String s) {
		String[] partes = s.split(",");

		if (partes.length != 3) {
			throw new IllegalArgumentException();
		}

		String dia = partes[0].trim();
		DayOfWeek diasemana = pasaDia(dia);
		LocalTime horaCom = LocalTime.parse(partes[1].trim());
		LocalTime horaFin = LocalTime.parse(partes[2].trim());

		checkDia(diasemana);
		checkDuracion((int) horaCom.until(horaFin, ChronoUnit.MINUTES));

		this.dia = diasemana;
		this.comienzo = horaCom;
		this.fin = horaFin;

	}

	public DayOfWeek pasaDia(String dia) {
		DayOfWeek diasemana;
		if (dia.equals("L")) {
			diasemana = DayOfWeek.MONDAY;
		} else if (dia.equals("M")) {
			diasemana = DayOfWeek.TUESDAY;

		} else if (dia.equals("X")) {
			diasemana = DayOfWeek.WEDNESDAY;
		} else if (dia.equals("J")) {
			diasemana = DayOfWeek.THURSDAY;
		} else if (dia.equals("V")) {
			diasemana = DayOfWeek.FRIDAY;
		} else {
			throw new ExcepcionTutoriaNoValida();

		}
		return diasemana;

	}

	/*************************************************** EXCEPCIONES ***************************************************/

	private void checkDia(DayOfWeek dia) {
		if (dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY) {

			throw new ExcepcionTutoriaNoValida(
					"Error a la hora de escoger el dia, por favor introduzca otro dia");
		}
	}

	private void checkDuracion(Integer duracion) {
		if (duracion < 15) {
			throw new ExcepcionTutoriaNoValida("Error introduzca de nuevo "
					+ "el tiempo que desea para la tutoria, el intervalo de tiempo"
					+ " ha sido demasiado pequeño");
		}
	}

	/***************************************************
	 * GETTERS AND SETTERS
	 *******************************************/

	public DayOfWeek getDiaSemana() {
		return dia;
	}

	public LocalTime getHoraComienzo() {
		return comienzo;
	}

	public LocalTime getHoraFin() {
		return fin;
	}

	public Integer getDuracion() {
		return (int) comienzo.until(fin, ChronoUnit.MINUTES);

	}

	public String getDia() {
		String c;
		if (this.dia == DayOfWeek.MONDAY) {
			c = "L";
		} else if (this.dia == DayOfWeek.TUESDAY) {
			c = "M";
		} else if (this.dia == DayOfWeek.WEDNESDAY) {
			c = "X";
		} else if (this.dia == DayOfWeek.THURSDAY) {
			c = "J";
		} else if (this.dia == DayOfWeek.FRIDAY) {
			c = "V";
		} else {

			c = null;
		}
		return c;
	}

	/***************************************************
	 * EQUALS HASHCODE Y COMPARETO
	 *************************************/

	public boolean equals(Object o) {
		boolean tut = false;
		if (o instanceof Tutoria) {
			Tutoria t = (Tutoria) o;
			tut = this.getDiaSemana().equals(t.getDiaSemana())
					&& this.getHoraComienzo().equals(t.getHoraComienzo());
		}
		return tut;
	}

	public int hashCode() {
		return this.getDiaSemana().hashCode()
				+ this.getHoraComienzo().hashCode() * 11;

	}

	public int compareTo(Tutoria t) {
		int tut = this.getDiaSemana().compareTo(t.getDiaSemana());
		if (tut == 0)
			tut = getHoraComienzo().compareTo(t.getHoraComienzo());

		return tut;
	}

	/*************************************************** REPRESENTACION ***************************************************/

	public String toString() {
		return getDia() + " " + comienzo + "-" + fin;
	}

}
