// package fp.grados.tipos.test;
//
// import java.util.Set;
//
// import fp.grados.excepciones.ExcepcionDespachoNoValido;
// import fp.grados.tipos.Espacio;
// import fp.grados.tipos.EspacioImpl;
// import fp.grados.tipos.Despacho;
// import fp.grados.tipos.DespachoImpl;
// import fp.grados.tipos.Profesor;
//
// public class TestDespacho {
//
// public static void main(String[] args) {
//
// testConstructor1Normal();
// // testConstructor1Excepcion();
// //
// // testConstructor2Normal();
// // testConstructor2Excepcion();
// //
// // testSetFechaComienzoNormal();
// // testSetFechaComienzoExcepcion();
// //
// // testSetEmailExcepcion();
// }
//
// private static void testConstructor1Normal() {
// System.out.println("");
// System.out.println("\t=====test by Ubidragon========");
// System.out.println("Probando el primer constructor");
// testConstructor1("A0.12", 30, 4, 5);
// }
//
// //
// // private static void testConstructor1Excepcion() {
// // System.out
// // .println("\n==================================Probando el primer
// constructor, fecha de comienzo incorrecta");
// // testConstructor1("12345678Z", "Juan", "Nadie Nadie",
// // LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",
// // new BecaImpl("ABC1234", TipoBeca.ORDINARIA),
// // LocalDate.of(2010, 1, 1));
// // }
// //
// // private static void testConstructor2Normal() {
// // System.out
// // .println("\n==================================Probando el segundo
// constructor");
// // testConstructor2("12345678Z", "Juan", "Nadie Nadie",
// // LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es", "ABC1234",
// // 12000.0, 12, TipoBeca.ORDINARIA, LocalDate.of(2015, 1, 1));
// // }
// //
// // private static void testConstructor2Excepcion() {
// // System.out
// // .println("\n==================================Probando el segundo
// constructor, fecha de comienzo incorrecta");
// // testConstructor2("12345678Z", "Juan", "Nadie Nadie",
// // LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es", "ABC1234",
// // 12000.0, 12, TipoBeca.ORDINARIA, LocalDate.of(2010, 1, 1));
// // }
// //
// // private static void testSetFechaComienzoNormal() {
// // System.out
// // .println("\n==================================Probando setFechaComienzo");
// //
// // Becario b = new BecarioImpl("12345678Z", "Juan", "Nadie Nadie",
// // LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",
// // new BecaImpl("ABC1234", TipoBeca.ORDINARIA), LocalDate.of(2015,
// // 1, 1));
// // testsetFechaComienzo(b, LocalDate.of(2016, 1, 1));
// // }
// //
// // private static void testSetFechaComienzoExcepcion() {
// // System.out
// // .println("\n==================================Probando setFechaComienzo,
// fecha incorrecta");
// //
// // Becario b = new BecarioImpl("12345678Z", "Juan", "Nadie Nadie",
// // LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",
// // new BecaImpl("ABC1234", TipoBeca.ORDINARIA), LocalDate.of(2015,
// // 1, 1));
// // testsetFechaComienzo(b, LocalDate.of(2010, 1, 1));
// // }
// //
// // private static void testSetEmailExcepcion() {
// // System.out
// // .println("\n==================================Probando setEmail");
// //
// // Becario b = new BecarioImpl("12345678Z", "Juan", "Nadie Nadie",
// // LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",
// // new BecaImpl("ABC1234", TipoBeca.ORDINARIA), LocalDate.of(2015,
// // 1, 1));
// //
// // try {
// // b.setEmail("juan@alum.us.es");
// // } catch (UnsupportedOperationException e) {
// // System.out
// // .println("******************** Se ha capturado la excepción
// UnsupportedOperationException");
// // } catch (Exception e) {
// // System.out
// // .println("******************** Se ha capturado una excepción
// inesperada.");
// // }
// //
// // }
// //
// private static void testConstructor1(String aula, Integer capacidad,
// Integer Planta, Set<Profesor> profesores) {
//
// try {
// Despacho d = new DespachoImpl(aula, capacidad, Planta, profesores);
// mostrarDespacho(d);
// } catch (ExcepcionDespachoNoValido e) {
// System.out
// .println("******************** Se ha capturado la excepción
// ExcepcionDespachoNoValido");
// } catch (Exception e) {
// System.out
// .println("******************** Se ha capturado una excepción inesperada.");
// }
//
// }
//
// //
// // private static void testConstructor2(String dni, String nombre,
// // String apellidos, LocalDate fechaNacimiento, String email,
// // String codigo, Double cuantiaTotal, Integer duracion,
// // TipoBeca tipo, LocalDate fechaComienzo) {
// //
// // try {
// // Becario b = new BecarioImpl(dni, nombre, apellidos,
// // fechaNacimiento, email, codigo, cuantiaTotal, duracion,
// // tipo, fechaComienzo);
// // mostrarBecario(b);
// // } catch (ExcepcionBecarioNoValido e) {
// // System.out
// // .println("******************** Se ha capturado la excepción
// ExcepcionBecarioNoValido");
// // } catch (Exception e) {
// // System.out
// // .println("******************** Se ha capturado una excepción
// inesperada.");
// // }
// // }
// //
// // private static void testsetFechaComienzo(Becario b, LocalDate
// // fechaComienzo) {
// //
// // try {
// // System.out.println("La fecha de comienzo antes de la operación es: "
// // + b.getFechaComienzo());
// // System.out.println("La nueva fecha de comienzo es: " + fechaComienzo);
// // b.setFechaComienzo(fechaComienzo);
// // System.out
// // .println("La fecha de comienzo después de la operación es: "
// // + b.getFechaComienzo());
// // } catch (ExcepcionBecarioNoValido e) {
// // System.out
// // .println("******************** Se ha capturado la excepción
// ExcepcionBecarioNoValido");
// // } catch (Exception e) {
// // System.out
// // .println("******************** Se ha capturado una excepción
// inesperada.");
// // }
// // }
//
// private static void mostrarDespacho(Despacho d) {
// System.out.println("Despacho --> <" + d + ">");
// System.out.println("\tAula: <" + d.getNombre() + ">");
// System.out.println("\tCapacidad: <" + d.getCapacidad() + ">");
// System.out.println("\tPlanta: <" + d.getPlanta() + ">");
//
// }
// }