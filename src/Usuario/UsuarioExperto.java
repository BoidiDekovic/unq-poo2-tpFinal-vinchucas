package Usuario;

public class UsuarioExperto implements EstadoUsuario {

	@Override
	public boolean esExperto() {
		return true;
	}
	
	@Override
	public void calcularCategoria(Usuario usuario) {
		if (! usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias() 
		 || ! usuario.tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias()) {
			usuario.setEstado(new UsuarioBasico());
		}
	}

	@Override
	public void validarExpertoVotandoMuestraExpertos() {
		// Este usuario es experto
	}

}
