package Usuario;

public class UsuarioEspecialista extends Usuario {

	public UsuarioEspecialista() {
		super();
		this.estado = new UsuarioExperto();
	}

	@Override
	public void setEstado(EstadoUsuario estado) {
		// El especialista siempre es un usuario experto
	}

}
