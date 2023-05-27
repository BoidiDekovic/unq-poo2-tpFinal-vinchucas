package Usuario;

import Muestra.Muestra;
import Muestra.TipoOpinion;

public class UsuarioExperto extends EstadoUsuario {


	@Override
	public void opinarSobreMuestra(Muestra muestra, TipoOpinion tipoOpinion) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean esExperto() {
		return true;
	}


}
