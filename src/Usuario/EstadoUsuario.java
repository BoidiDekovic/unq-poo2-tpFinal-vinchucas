package Usuario;

import Muestra.Muestra;
import Muestra.TipoOpinion;

public interface EstadoUsuario {
	
	public void enviarMuestra(Muestra muestra);
	
	public void opinarSobreMuestra(Muestra muestra, TipoOpinion tipoOpinion);

}
