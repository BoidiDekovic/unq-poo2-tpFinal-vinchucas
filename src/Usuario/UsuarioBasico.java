package Usuario;

public class UsuarioBasico implements EstadoUsuario {
	
	@Override
	public boolean esExperto() {
		return false;
	}
	
	@Override
	public void calcularCategoria(Usuario usuario) {
		if (usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias() 
		 && usuario.tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias()) {
			usuario.setEstado(new UsuarioExperto());
		}
	}

}
