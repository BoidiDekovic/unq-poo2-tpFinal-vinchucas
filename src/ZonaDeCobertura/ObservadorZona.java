package ZonaDeCobertura;

import Muestra.Muestra;

public interface ObservadorZona {
	
	public void actualizarMuestraVerificada(ZonaDeCobertura zonaDeCobertura, Muestra muestra);
	
	public void actualizarNuevaMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra);

}
