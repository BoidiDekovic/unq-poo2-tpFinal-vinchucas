package Usuario;

import java.time.LocalDate;

import Muestra.Muestra;
import Muestra.Opinion;
import Muestra.TipoOpinion;

public class UsuarioExperto extends EstadoUsuario {


	@Override
	public void opinarSobreMuestra(Muestra muestra, TipoOpinion tipoOpinion, Usuario usuario) {
		Opinion nuevaOpinion = new Opinion(tipoOpinion, usuario, LocalDate.now());
		usuario.agregarOpinionEnviada(nuevaOpinion);
		muestra.agregarOpinion(nuevaOpinion);
	}

	@Override
	public boolean esExperto() {
		return true;
	}


}
