package Usuario;

import java.time.LocalDate;

import Muestra.Muestra;
import Muestra.Opinion;
import Muestra.TipoOpinion;

public class UsuarioBasico extends EstadoUsuario {



	@Override
	public void opinarSobreMuestra(Muestra muestra, TipoOpinion tipoOpinion, Usuario usuario) {
		Opinion nuevaOpinion = new Opinion(tipoOpinion,this , LocalDate.now());
		muestra.agregarOpinion(nuevaOpinion);
		usuario.agregarOpinionEnviada(nuevaOpinion);
	}

	@Override
	public boolean esExperto() {
		return false;
	}



}
