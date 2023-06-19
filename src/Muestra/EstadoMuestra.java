package Muestra;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class EstadoMuestra {

	
	public abstract void agregarOpinion(Opinion opinion, Muestra muestra);
	
	public abstract void calcularEstadoMuestra(Muestra muestra);
	
	public TipoOpinion resultadoActual(Muestra muestra) {
		List<Opinion> opiniones = muestra.getOpiniones();
		if(opiniones.isEmpty()) { //Caso opiones vacias
			return muestra.getVinchucaSegunAutor();
		}
		else if(muestra.esMuestraConOpinionDeExperto()) { //filtra expertos y calcula resultado
			return resultadoEntre(opiniones.stream().filter(o-> o.esOpinionDeExperto()).toList());
		}
		else {
			return resultadoEntre(opiniones); //Calcula el resultado con basicos
		}

	}

	public TipoOpinion resultadoEntre(List<Opinion> opiniones) {
		Map<Object, Long> mapOpiniones = conteoPorTipoDeOpinion(opiniones);
		return opinionConMaximoDeVotos(mapOpiniones);
	}

	private TipoOpinion opinionConMaximoDeVotos(Map<Object, Long> mapOpiniones) {
		TipoOpinion maximo = (TipoOpinion) mapOpiniones.entrySet()
								.stream()
								.max(Comparator.comparing((Map.Entry::getValue)))
								.get()
								.getKey();
		
		//Caso empate
		if(mapOpiniones.size() > 1 && mapOpiniones.entrySet()
				.stream()
				.allMatch(e -> e.getValue().equals(mapOpiniones.get(maximo)))) {
			return TipoOpinion.NODEFINIDO;
	   }
		return maximo;
	}

	private Map<Object, Long> conteoPorTipoDeOpinion(List<Opinion> opiniones) {
		return opiniones.stream()
						.map(o-> o.getTipoOpinion())
						.collect(Collectors.groupingBy(o -> o, Collectors.counting()));
	}

}
