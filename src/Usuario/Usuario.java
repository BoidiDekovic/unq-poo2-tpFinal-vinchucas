package Usuario;

import java.util.List;
import java.util.ArrayList;

import Muestra.Muestra;

public class Usuario {
	
	private EstadoUsuario estado;
	private List<Muestra> muestras;
	
	public Usuario(EstadoUsuario estado) {
		super();
		this.estado = estado;
		this.muestras = new ArrayList<Muestra>();
	}

}
