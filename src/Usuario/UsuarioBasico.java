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

	@Override
	public void validarExpertoVotandoMuestraExpertos() {
		throw new UnsupportedOperationException
        ("Un usuario basico no puede opinar una muestra que fue opinada por un experto");
	}

}
