//
// package fp.grados.tipos.test;
//
// import java.time.LocalDate;
// import java.util.HashSet;
// import java.util.Set;
//
// import fp.grados.tipos.Asignatura;
// import fp.grados.tipos.AsignaturaImpl;
// import fp.grados.tipos.Departamento;
// import fp.grados.tipos.DepartamentoImpl;
// import fp.grados.tipos.TipoAsignatura;
//
//// Este código se les da como material adjunto al boletín T3
//
// public class TestDepartamento {
//
// public static void main(String[] args) {
// testConstructorNormal();
//
// testNuevaAsignatura();
// testEliminaAsignatura();
// testNuevoProfesor();
// testEliminaProfesor();
// testBorraTutorias1();
// testBorraTutorias2();
// testExisteProfesorAsignado();
// testTodasAsignaturasAsignadas();
// testEliminaAsignacionProfesorado();
//
// }
//
// /******************************** CASOS DE PRUEBA **************************/
//
// private static void testConstructorNormal() {
// System.out
// .println("==================================Probando el constructor");
// testConstructor("Matematicas Aplicadas");
// }
//
// private static void testNuevaAsignatura() {
// //
//
// System.out
// .println("\n==================================Probando nuevaAsignatura");
//
// Asignatura asig = new AsignaturaImpl("Fundamentos de Programación",
// "2050001", 12.0, TipoAsignatura.ANUAL, 1, null);
// testNuevaAsignatura(asig);
// }
//
// private static void testEliminaAsignatura() {
// //
//
// }
//
// private static void testNuevoProfesor() {
// //
//
// }
//
// private static void testEliminaProfesor() {
// //
//
// }
//
// private static void testBorraTutorias1() {
// //
//
// }
//
// private static void testBorraTutorias2() {
// //
//
// }
//
// private static void testExisteProfesorAsignado() {
// //
//
// }
//
// private static void testTodasAsignaturasAsignadas() {
// //
//
// }
//
// private static void testEliminaAsignacionProfesorado() {
// //
//
// }
//
// /******************************** METODOS AUXILIARES
// **************************/
//
// private static void testConstructor(String nombre) {
// Departamento d = new DepartamentoImpl(nombre);
// mostrarDepartamento(d);
// }
//
// private static void testNuevaAsignatura(Asignatura asig) {
//
// System.out.println("Las asignaturas antes de la operación son: "
// + HashSet < Asignatura > (asig.getDepartamento()));
// System.out.println("Nueva asignatura a matricular: " + asig);
// asig.getDepartamento();
// System.out.println("Las asignaturas después de la operación son: "
// + asig.getDepartamento());
//
// }
//
// private static void mostrarDepartamento(Departamento d) {
// System.out.println("\tNombre: <" + d.getNombre() + ">");
//
// }
//
// }
