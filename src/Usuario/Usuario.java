package Usuario;

import java.util.List;
import java.util.ArrayList;

import Muestra.Muestra;

public class Usuario {
	
	private EstadoUsuario estado;
	private List<Muestra> muestras;
	
	public Usuario() {
		super();
		this.estado = new UsuarioBasico();
		this.muestras = new ArrayList<Muestra>();
	}

	
}
