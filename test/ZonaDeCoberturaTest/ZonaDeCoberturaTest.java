package ZonaDeCoberturaTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Muestra;
import Sistema.Sistema;
import ZonaDeCobertura.ObservadorZona;
import ZonaDeCobertura.Ubicacion;
import ZonaDeCobertura.ZonaDeCobertura;

class ZonaDeCoberturaTest {
	
	private ZonaDeCobertura zonaDeCobertura;
	private Muestra muestra;
	private Ubicacion ubicacion;
	private ObservadorZona observadorZona;
	
	@BeforeEach
	public void setUp() {
		muestra = mock(Muestra.class);
		ubicacion = mock(Ubicacion.class);
		observadorZona = mock(ObservadorZona.class);
		zonaDeCobertura = new ZonaDeCobertura("Bernal", ubicacion);
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaSeCreaTieneUnEpicentro() {
		assertEquals(ubicacion, zonaDeCobertura.getEpicentro());
	}

	@Test
	public void testCuandoUnaZonaDeCoberturaSeCreaSuListaDeObservadoresEsVacia() {
		assertTrue(zonaDeCobertura.getObservadores().isEmpty());
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaSeCreaSuListaDeMuestrasEsVacia() {
		assertTrue(zonaDeCobertura.getMuestras().isEmpty());
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaAgregaUnObservadorEstaEnSuListaDeObservadores() {
		zonaDeCobertura.agregarObservador(observadorZona);
		assertTrue(zonaDeCobertura.getObservadores().contains(observadorZona));
		assertEquals(1, zonaDeCobertura.getObservadores().size());
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaAgregaUnaMuestraEstaEnSuListaDeObservadores() {
		zonaDeCobertura.agregarMuestra(muestra);
		assertTrue(zonaDeCobertura.getMuestras().contains(muestra));
		assertEquals(1, zonaDeCobertura.getMuestras().size());
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaSacaUnObservadorNoEstaEnSuListaDeObservadores() {
		zonaDeCobertura.agregarObservador(observadorZona);
		assertTrue(zonaDeCobertura.getObservadores().contains(observadorZona));
		assertEquals(1, zonaDeCobertura.getObservadores().size());
		
		zonaDeCobertura.sacarObservador(observadorZona);
		assertFalse(zonaDeCobertura.getObservadores().contains(observadorZona));
		assertEquals(0, zonaDeCobertura.getObservadores().size());
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaNotificaUnaNuevaMuestraSeLesAvisaASusObservadores() {
		zonaDeCobertura.agregarObservador(observadorZona);
		zonaDeCobertura.notificarNuevaMuestra(muestra);
		verify(observadorZona, times(1)).actualizarNuevaMuestra(zonaDeCobertura, muestra);
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaNotificaUnaMuestraVerificadaSeLesAvisaASusObservadores() {
		zonaDeCobertura.agregarObservador(observadorZona);
		zonaDeCobertura.notificarVerificacionMuestra(muestra);
		verify(observadorZona, times(1)).actualizarMuestraVerificada(zonaDeCobertura, muestra);
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaSeLePideSusZonasSolapadasSeLasPideAlSistema() {
		Sistema sistema = mock(Sistema.class);
		zonaDeCobertura.zonasSolapadas(sistema);
		verify(sistema, times(1)).zonasSolapadasDe(zonaDeCobertura);
	}

}
