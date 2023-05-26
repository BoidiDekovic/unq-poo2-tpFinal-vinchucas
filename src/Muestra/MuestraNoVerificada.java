package Muestra;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MuestraNoVerificada implements EstadoMuestra {

	@Override
	public void agregarOpinion(Opinion opinion, Muestra muestra) {
		muestra.agregarOpinionAMuestraNoVerificada(opinion);
		muestra.calcularVerificacion();
	}

	@Override
	public TipoOpinion resultadoActual(Muestra muestra) {
		List<Opinion> opiniones = muestra.getOpiniones();
		if(opiniones.isEmpty()) {
			return muestra.getVinchucaSegunAutor();
		}
		else if(opiniones.stream().anyMatch(o -> o.getEstadoAutor().esExperto())) {
			return resultadoEntre(opiniones.stream().filter(o-> o.getEstadoAutor().esExperto()).toList());
		}
		else {
			return resultadoEntre(opiniones);
		}

	}

	public TipoOpinion resultadoEntre(List<Opinion> opiniones) {
			   Map<Object, Long> mapOpiniones = opiniones.stream() //conteo de opiniones
								  .map(o-> o.getTipoOpinion())
			                      .collect(Collectors.groupingBy(
			                       o -> o, Collectors.counting()));
			   
			   TipoOpinion primerTipo = opiniones.get(0).getTipoOpinion(); 
			   
			   if(opiniones.size() > 1 && //Caso empate
					    mapOpiniones.entrySet().stream().allMatch(e -> e.getValue().equals(mapOpiniones.get(primerTipo)))) {
					                   return TipoOpinion.NODEFINIDO;
			   }
			   
			   return (TipoOpinion) mapOpiniones.entrySet().stream()
					                                       .max(Comparator.comparing((Map.Entry::getValue)))
					                                       .get().getKey();
	}
}