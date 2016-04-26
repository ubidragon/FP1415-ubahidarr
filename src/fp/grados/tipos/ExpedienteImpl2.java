package fp.grados.tipos;

import java.util.stream.Collectors;

public class ExpedienteImpl2 extends ExpedienteImpl {

	public ExpedienteImpl2() {
		super();
	}

	public Double getNotaMedia() {
		// OptionalDouble result = getNotas().stream().filter(x -> x.getValor()
		// >= 5).mapToDouble(Nota::getValor).average();
		// if(result.isPresent()){
		// return result.getAsDouble();
		// }else{
		// return 0.0;
		// }
		// averagingDouble calcula de forma directa la media
		// doubleValue() obliga a que devuelva un double

		Double result = 0.;

		result = getNotas().stream().filter(x -> x.getValor() >= 5)
				.collect(Collectors.averagingDouble(Nota::getValor))
				.doubleValue();

		return result;
	}

}