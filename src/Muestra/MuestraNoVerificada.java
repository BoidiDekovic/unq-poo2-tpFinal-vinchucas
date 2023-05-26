package Muestra;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		}else{
			   Map<Object, Long> mapOpiniones = opiniones.stream()
								  .map(o-> o.getTipoOpinion())
			                      .collect(Collectors.groupingBy(
			                       o -> o, Collectors.counting()));                   
			   TipoOpinion primerTipo = opiniones.get(0).getTipoOpinion();
			   if(opiniones.size() > 1 &&
					    mapOpiniones.entrySet().stream().allMatch(e -> e.getValue().equals(mapOpiniones.get(primerTipo)))) {
					                   return TipoOpinion.NODEFINIDO;
					               }
					            return (TipoOpinion) mapOpiniones.entrySet().stream()
					                                          .max(Comparator.comparing((Map.Entry::getValue)))
					                                          .get().getKey();
		}
}
}