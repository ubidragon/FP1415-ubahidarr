package fp.grados.utiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.AlumnoImpl2;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Beca;
import fp.grados.tipos.BecaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Centro;
import fp.grados.tipos.CentroImpl;
import fp.grados.tipos.CentroImpl2;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.DepartamentoImpl2;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.Grado;
import fp.grados.tipos.GradoImpl;
import fp.grados.tipos.GradoImpl2;
import fp.grados.tipos.Nota;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.ProfesorImpl2;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.TipoBeca;
import fp.grados.tipos.TipoEspacio;
import fp.grados.tipos.Tutoria;

public class Grados {

	// METODO GENERICO PARA PODER HACER EL METODO CREACIONAL APARTIR DE UN
	// FICHERO
	public static <T> List<T> leeFichero(String nombreFichero,
			Function<String, T> funcion_deString_aT) {
		List<T> res = null;
		try {
			res = Files.lines(Paths.get(nombreFichero)).map(funcion_deString_aT)
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.out
					.println("Error en lectura del fichero: " + nombreFichero);
		}

		return res;
	}

	/****************************** TipoAlumno ******************************/

	private static Set<Alumno> alumnosCreados = new HashSet<Alumno>();

	// METODO CREACIONAL POR PARAMETROS
	public static Alumno createAlumno(String dni, String nombre,
			String apellidos, LocalDate fecha, String email) {

		// crear una variable de tipo retorno
		Alumno result;
		// usar el constructor correspondiente
		if (usarJava8) {
			result = new AlumnoImpl2(dni, nombre, apellidos, fecha, email);
		} else {
			result = new AlumnoImpl(dni, nombre, apellidos, fecha, email);
		}

		// 3.- Actualizar las propiedades poblacionales
		actualizaPobsAlumno(result);
		// 4.-Devolver el objeto
		return result;
	}

	// METODO CREACIONAL COPIA
	public static Alumno createAlumno(Alumno original) {
		/**
		 * Usamos el constructor de arriba para generar una copia apartir del
		 * original mencionado anteriormente
		 **/
		Alumno res = createAlumno(original.getDNI(), original.getNombre(),
				original.getApellidos(), original.getFechaNacimiento(),
				original.getEmail());
		for (Asignatura a : original.getAsignaturas()) {
			res.matriculaAsignatura(a);
		}
		for (Nota n : original.getExpediente().getNotas()) {
			res.evaluaAlumno(n.getAsignatura(), n.getCursoAcademico(),
					n.getConvocatoria(), n.getValor());
		}
		return res;
	}

	// METODO CREACIONAL APARTIR DE UN STRING
	public static Alumno createAlumno(String s) {
		/** genera un nuevo alumno en base al String **/
		Alumno result = new AlumnoImpl(s);
		/** Añade el alumno al Set para eso es el actualiza **/
		actualizaPobsAlumno(result);
		return result;
	}

	// METODO CREACIONAL APARTIR DE UN FICHERO
	public static List<Alumno> createAlumnos(String file) {

		/**
		 * lee el fichero donde se encuentra las propiedades y crea una lista de
		 * alumnos apartir de este fichero
		 **/
		List<Alumno> result = leeFichero(file, s -> new AlumnoImpl(s));
		return result;
	}

	private static void actualizaPobsAlumno(Alumno result) {
		// añade un alumno al conjunto de los alumnos creados
		alumnosCreados.add(result);
	}

	public static Integer getNumAlumnosCreados() {
		// nos devuelve el tamaño del conjunto de los alumnos creados
		return alumnosCreados.size();
	}

	public static Set<Alumno> getAlumnosCreados() {
		return new HashSet<Alumno>(alumnosCreados);
	}

	/****************************** TipoAsignatura ******************************/

	/*
	 * Asignatura:códigos de todas las asignaturas creadas. La implementación de
	 * esta propiedad debe posibilitar el acceso a cualquier asignatura creada a
	 * partir de su código.
	 */

	private static Map<String, Asignatura> asignaturasCreadas = new HashMap<String, Asignatura>();

	public static Set<Asignatura> getAsignaturasCreadas() {
		return new HashSet<Asignatura>(asignaturasCreadas.values());
	}

	public static Integer getNumAsignaturasCreadas() {
		return asignaturasCreadas.size();
	}

	public static Set<String> getCodigosAsignaturasCreadas() {
		return asignaturasCreadas.keySet();
	}

	// METODO CREACIOAL APARTIR DE UN FICHERO
	public static List<Asignatura> createAsignaturas(String file) {
		// Creacion de una lista apartir de un fichero
		/**
		 * lee el fichero donde se encuentra las propiedades y crea una lista de
		 * alumnos apartir de este fichero
		 **/
		List<Asignatura> result = leeFichero(file, s -> createAsignatura(s));
		return result;
	}

	public static Asignatura getAsignaturaCreada(String codigo) {
		return asignaturasCreadas.get(codigo);
	}

	// METODO CREACIONAL APARTIR DE UN STRING
	public static Asignatura createAsignatura(String s) {
		// 1..- Crear una variable del tipo retorno
		Asignatura result;
		// 2.-Usar el constructor que toque
		result = new AsignaturaImpl(s);
		// 3.- Actualizar las propiedades poblacionales
		actualizaPobsAsignatura(result);
		// 4.-
		return result;
	}

	// METODO CREACIONAL POR PARAMETROS
	public static Asignatura createAsignatura(String nombre, String codigo,
			Double credito, TipoAsignatura tipo, Integer curso,
			Departamento dpto) {
		// 1..- Crear una variable del tipo retorno
		Asignatura result;
		// 2.-Usar el constructor que toque
		result = new AsignaturaImpl(nombre, codigo, credito, tipo, curso, dpto);
		// 3.- Actualizar las propiedades poblacionales
		actualizaPobsAsignatura(result);
		// 4.-Devolver el objeto
		return result;
	}

	private static void actualizaPobsAsignatura(Asignatura result) {
		asignaturasCreadas.put(result.getCodigo(), result);
		/**
		 * Para codigo devuelve una asignatura, para que cuando se le meta X
		 * codigo te devuelva la asignatura a la que corresponde
		 **/
	}

	/******************************** TipoBeca ********************************/
	private static void actualizaPobsBeca(Beca result) {
		becasCreadas.add(result);
	}

	private static Set<Beca> becasCreadas = new HashSet<Beca>();

	public static Set<Beca> getBecasCreadas() {
		return new HashSet<Beca>(becasCreadas);
	}

	public static Integer getNumBecasCreadas() {
		return becasCreadas.size();
	}

	public static Integer getNumBecasTipo(TipoBeca tipo) {

		return (int) becasCreadas.stream().filter(x -> x.getTipo().equals(tipo))
				.count();
	}

	// METODO CREACIONAL APARTIR DE UN STRING
	public static Beca createBeca(String s) {
		Beca result;
		result = new BecaImpl(s);
		actualizaPobsBeca(result);
		return result;
	}

	// METODO CREACIONAL APARTIR DE UN FICHERO
	public static List<Beca> createBecas(String file) {
		List<Beca> result = leeFichero(file, s -> createBeca(s));
		return result;
	}

	// METODO CREACIONAL POR PARAMETROS
	public static Beca createBeca(String codigo, Double cuantiaTotal,
			Integer duracion, TipoBeca tipo) {

		Beca result = new BecaImpl(codigo, cuantiaTotal, duracion, tipo);
		actualizaPobsBeca(result);
		return result;
	}

	public static Beca createBeca(String codigo, TipoBeca tipo) {
		Beca b = new BecaImpl(codigo, tipo);
		actualizaPobsBeca(b);
		return b;
	}

	// METODO CREACIONAL COPIA
	public static Beca createBeca(Beca original) {
		return createBeca(original.getCodigo(), original.getCuantiaTotal(),
				original.getDuracion(), original.getTipo());
	}

	/****************************** TipoCentro ************************************/

	private static Set<Centro> centrosCreados = new HashSet<Centro>();

	// METODO CREACIONAL POR PARAMETROS
	public static Centro createCentro(String nombre, String direccion,
			Integer numeroPlantas, Integer numeroSotanos) {

		Centro result;
		if (usarJava8) {
			result = new CentroImpl2(nombre, direccion, numeroPlantas,
					numeroSotanos);
		} else {
			result = new CentroImpl(nombre, direccion, numeroPlantas,
					numeroSotanos);
		}
		actualizaPobsCentro(result);
		return result;

	}

	// METODO CREACIONAL COPIA
	public static Centro createCentro(Centro original) {
		Centro res = createCentro(original.getNombre(), original.getDireccion(),
				original.getNumeroPlantas(), original.getNumeroSotanos());
		for (Espacio e : original.getEspacios()) {
			res.nuevoEspacio(e);
		}
		return res;
	}

	private static void actualizaPobsCentro(Centro result) {
		centrosCreados.add(result);
	}

	public static Integer getNumCentrosCreados() {
		return centrosCreados.size();
	}

	public static Set<Centro> getCentrosCreados() {
		return new HashSet<Centro>(centrosCreados);
	}

	public static Integer getMaxPlantas() {
		Integer maxPlanta = 0;
		for (Centro esp : centrosCreados) {
			Integer maxPlanta1 = esp.getNumeroPlantas();
			if (maxPlanta.compareTo(maxPlanta1) < 0) {
				maxPlanta = maxPlanta1;
			}
		}
		if (centrosCreados.isEmpty()) {
			return null;
		} else {
			return maxPlanta;
		}
	}

	public static Integer getMaxSotanos() {
		Integer maxSotano = 0;
		for (Centro esp : centrosCreados) {
			Integer maxSotano1 = esp.getNumeroSotanos();
			if (maxSotano.compareTo(maxSotano1) < 0) {
				maxSotano = maxSotano1;
			}
		}
		if (centrosCreados.isEmpty()) {
			return null;
		} else {
			return maxSotano;
		}
	}

	public static Double getMediaPlantas() {
		Double p = 0.;
		// recorro los centros
		for (Centro c : centrosCreados) {
			// A cada uno de ellos le saco el numero de plantas
			Integer plantas = c.getNumeroPlantas();
			// numero de plantas totales entre todos los centros
			p = p + plantas;
		}
		return p / centrosCreados.size();
		// el casting es para que nos lo devuelva como double

	}

	public static Double getMediaSotanos() {
		Double s = 0.;
		// recorro los centros
		for (Centro c : centrosCreados) {
			// A cada uno de ellos le saco el numero de sotanos
			Integer sotanos = c.getNumeroSotanos();
			// numero de sotanos totales entre todos los centros
			s = s + sotanos;
		}
		return s / centrosCreados.size();

		// el casting es para que nos lo devuelva como double

	}

	/****************************** TipoDepartamento ******************************/
	private static Set<Departamento> departamentosCreados = new HashSet<Departamento>();

	private static void actualizaPobsDepartamento(Departamento res) {
		departamentosCreados.add(res);
	}

	public static Integer getNumDepartamentosCreados() {
		return departamentosCreados.size();
	}

	public static Set<Departamento> getDepartamentosCreados() {
		return new HashSet<Departamento>(departamentosCreados);
	}

	// METODO CREACIONAL POR PARAMETROS
	public static Departamento createDepartamento(String nombre) {
		Departamento result;
		if (usarJava8) {
			result = new DepartamentoImpl2(nombre);
		} else {
			result = new DepartamentoImpl(nombre);
		}
		actualizaPobsDepartamento(result);
		return result;
	}

	/****************************** TipoDespacho ******************************/

	private static SortedSet<Despacho> despachosCreados = new TreeSet<Despacho>();

	private static void actualizaPobsDespacho(Despacho res) {
		despachosCreados.add(res);
	}

	// METODO CREACIONAL APARTIR DE UN STRING
	public static Despacho createDespacho(String s) {
		Despacho result;
		result = new DespachoImpl(s);
		actualizaPobsDespacho(result);
		actualizaPobsEspacio(result);
		return result;
	}

	// METODO CREACIONAL APARTIR DE UN FICHERO
	public static List<Despacho> createDespachos(String file) {
		List<Despacho> result = leeFichero(file, s -> new DespachoImpl(s));
		return result;
	}

	// METODO CREACIONAL POR PARAMETROS
	public static Despacho createDespacho(String aula, Integer capacidad,
			Integer Planta) {

		Despacho result = new DespachoImpl(aula, capacidad, Planta);
		actualizaPobsDespacho(result);
		actualizaPobsEspacio(result);
		return result;
	}

	// METODO CREACIONAL COPIA
	public static Despacho createDespacho(Despacho original) {
		return createDespacho(original.getNombre(), original.getCapacidad(),
				original.getPlanta());
	}

	/****************************** TipoEspacio ******************************/
	private static SortedSet<Espacio> espaciosCreados = new TreeSet<Espacio>();

	private static void actualizaPobsEspacio(Espacio res) {
		espaciosCreados.add(res);
	}

	public static SortedSet<Espacio> getEspaciosCreados() {
		return new TreeSet<Espacio>(espaciosCreados);
	}

	public static Integer getNumEspaciosCreados() {
		return espaciosCreados.size();
	}

	// METODO CREACIONAL APARTIR DE UN STRING
	public static Espacio createEspacio(String s) {
		Espacio result;
		result = new EspacioImpl(s);
		actualizaPobsEspacio(result);
		return result;
	}

	// METODO CREACIONAL APARTIR DE UN FICHERO
	public static List<Espacio> createEspacios(String file) {
		List<Espacio> result = leeFichero(file, s -> new EspacioImpl(s));
		return result;
	}

	// METODO CREACIONAL POR PARAMETROS
	public static Espacio createEspacio(TipoEspacio nombreAula, String aula,
			Integer capacidad, Integer Planta) {

		Espacio result = new EspacioImpl(nombreAula, aula, capacidad, Planta);
		actualizaPobsEspacio(result);
		return result;
	}

	// METODO CREACIONAL COPIA
	public static Espacio createEspacio(Espacio original) {
		return createEspacio(original.getTipo(), original.getNombre(),
				original.getCapacidad(), original.getPlanta());
	}

	public static Integer[] getNumEspaciosCreados2() {

		Integer[] result = new Integer[TipoEspacio.values().length];
		// Siempre hay que incializar el Array sino da un NullPointer
		Arrays.fill(result, 0);

		for (Espacio esp : espaciosCreados) {
			result[esp.getTipo().ordinal()]++;
		}
		return result;
	}

	public static Integer getPlantaMayorEspacio() {
		// Tenemos que crear un integer vacio(null) que te devuelve el que este
		// en la planta mas alta, aparte de eso hay que recorre los espacios con
		// un for sacarle la planta l que se este recorriendo en ese momento y
		// comparar la primera planta(null) que es la que estas sacando y si la
		// segunda es mayor que la primera entonces se igual
		if (espaciosCreados.isEmpty()) {
			return null;
		} else {
			return espaciosCreados.stream()
					.max(Comparator.comparing(Espacio::getPlanta)).get()
					.getPlanta();
		}
	}

	public static Integer getPlantaMenorEspacio() {
		// poseemos un conjunto de espacios que tenemos ordenados(sortedset)
		// necesitamos uno para coger su planta
		// 1º si estan vacio y no hay ningun espacio creado, la plantya tiene
		// que ser null no hay ningun espacio y si hay espacios, cogemos la
		// planta del rpimero que habias cogido redccorremos el espacio le vamos
		// sacando la planta y lo comparamos la planta del primero con las demas
		// 2º si la primera es mas grande que la segunda te tiene que cambiar el
		// ps por la mas pequeña
		if (espaciosCreados.isEmpty()) {
			return null;
		} else {
			return espaciosCreados.stream()
					.min(Comparator.comparing(Espacio::getPlanta)).get()
					.getPlanta();
		}
	}

	/****************************** TipoGrado ******************************/

	private static Set<Grado> gradosCreados = new HashSet<Grado>();

	private static void actualizaPobsGrado(Grado res) {
		gradosCreados.add(res);
	}

	public static Set<Grado> getGradosCreados() {
		return new HashSet<Grado>(gradosCreados);
	}

	public static Grado createGrado(String nombre, Set<Asignatura> obligatorias,
			Set<Asignatura> optativas, Double creditosMinimos) {
		Grado result;
		if (usarJava8) {
			result = new GradoImpl2(nombre, obligatorias, optativas,
					creditosMinimos);
		} else {
			result = new GradoImpl(nombre, obligatorias, optativas,
					creditosMinimos);
		}

		actualizaPobsGrado(result);
		return result;
	}

	public static Integer getNumGradosCreados() {
		return gradosCreados.size();
	}

	public static Double getMediaAsignaturasGrados() {
		return getMediaAsignaturasObligatoriasGrados()
				+ getMediaAsignaturasOptativasGrados();
	}

	public static Double getMediaAsignaturasObligatoriasGrados() {
		//
		Double a = 0.;
		for (Grado gr : gradosCreados) {
			Integer m = gr.getAsignaturasObligatorias().size();
			a = a + m;
		}
		if (gradosCreados.isEmpty()) {
			return 0.;
		}
		return a / gradosCreados.size();
	}

	public static Double getMediaAsignaturasOptativasGrados() {
		Double a = 0.;
		for (Grado gr : gradosCreados) {
			Integer m = gr.getAsignaturasOptativas().size();
			a = a + m;
		}
		if (gradosCreados.isEmpty()) {
			return 0.;
		}
		return a / gradosCreados.size();
	}

	/****************************** TipoProfesor ******************************/

	private static Set<Profesor> profesoresCreados = new HashSet<Profesor>();

	private static Boolean usarImplementacionMapProfesor = false;

	// METODO CREACIONAL POR PARAMETROS
	public static Profesor createProfesor(String dni, String nombre,
			String apellidos, LocalDate fecha, String email,
			Categoria categoria, Departamento departamento) {

		Profesor result;

		if (usarImplementacionMapProfesor) {
			result = new ProfesorImpl2(dni, nombre, apellidos, fecha, email,
					categoria, departamento);
		} else {
			result = new ProfesorImpl(dni, nombre, apellidos, fecha, email,
					categoria, departamento);
		}

		actualizaPobsProfesor(result);
		return result;
	}

	// METODO CREACIONAL COPIA
	public static Profesor createProfesor(Profesor p) {
		Profesor res = createProfesor(p.getDNI(), p.getNombre(),
				p.getApellidos(), p.getFechaNacimiento(), p.getEmail(),
				p.getCategoria(), p.getDepartamento());
		for (Tutoria t : p.getTutorias()) {
			res.nuevaTutoria(t.getHoraComienzo(), t.getDuracion(),
					t.getDiaSemana());
		}
		for (Asignatura a : p.getAsignaturas()) {
			res.imparteAsignatura(a, p.dedicacionAsignatura(a));
		}
		return res;
	}

	private static void actualizaPobsProfesor(Profesor prof) {
		profesoresCreados.add(prof);
	}

	// metodo que permite elegir cual de las dos clases de profesor podemos
	// utilizar

	public static void setUsarImplementacionMapProfesor(Boolean b) {
		usarImplementacionMapProfesor = b;
	}

	private static Boolean usarJava8 = true;

	public static void setUsarJava8(Boolean b) {
		usarJava8 = b;
	}

	public static Integer getNumProfesoresCreados() {
		return profesoresCreados.size();
	}

	public static Set<Profesor> getProfesoresCreados() {
		return new HashSet<Profesor>(profesoresCreados);
	}

}
