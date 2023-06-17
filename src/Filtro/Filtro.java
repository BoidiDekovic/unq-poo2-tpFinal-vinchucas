package Filtro;

import java.util.List;
import Muestra.Muestra;

public interface Filtro {

	public abstract List<Muestra> buscar(List<Muestra> muestras);
	
	
}
