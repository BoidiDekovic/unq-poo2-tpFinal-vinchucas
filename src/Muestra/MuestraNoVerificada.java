package Muestra;

import Usuario.Usuario;

public class MuestraNoVerificada extends EstadoMuestra {

	@Override
	public void agregarOpinion(Opinion opinion, Muestra muestra) {
		this.validarUsuario(opinion, muestra);
		muestra.agregarOpinionAMuestra(opinion);
		muestra.calcularVerificacion();
	}
	
	private void validarUsuario(Opinion opinion, Muestra muestra) {
		if(muestra.esMuestraConOpinionDeExperto() && !opinion.esOpinionDeExperto()) {
			throw new UnsupportedOperationException
			("Un usuario basico no puede opinar una muestra que fue opinada por un experto");
		}
		else if(opinion.getUsuarioAutor().getMuestras().contains(muestra)) {
			throw new UnsupportedOperationException
			("Un usuario no puede opinar sobre sus propias muestras");
		}
		else if(muestra.esMuestraConOpinionDe(opinion.getUsuarioAutor())) {
			throw new UnsupportedOperationException
			("Un usuario no puede opinar dos veces la misma muestra");
		}
	}
	
}