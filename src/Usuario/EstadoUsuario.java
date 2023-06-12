package Usuario;

import Muestra.Muestra;
import Muestra.TipoOpinion;
import Sistema.Sistema;

public abstract class EstadoUsuario {
	
	
	public abstract void opinarSobreMuestra(Muestra muestra, TipoOpinion tipoOpinion, Usuario usuario);

	public abstract boolean esExperto();

	public void agregarYEnviar(Muestra muestra, Usuario usuario, Sistema sistema) {
		usuario.agregarMuestra(muestra);
		muestra.enviarseASistema(sistema);
		
	
	}

	

	

}
