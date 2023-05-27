package Usuario;

import Muestra.Muestra;
import Muestra.TipoOpinion;

public abstract class EstadoUsuario {
	
	
	public abstract void opinarSobreMuestra(Muestra muestra, TipoOpinion tipoOpinion);

	public abstract boolean esExperto();

	public void agregarYEnviar(Muestra muestra, Usuario usuario) {
		usuario.agregarMuestra(muestra);
		muestra.enviarASistema();
		
	
	}

	

	

}
