package ZonaDeCoberturaTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Muestra;
import Sistema.Sistema;
import ZonaDeCobertura.Distancia;
import ZonaDeCobertura.ObservadorZona;
import ZonaDeCobertura.Ubicacion;
import ZonaDeCobertura.ZonaDeCobertura;

class ZonaDeCoberturaTest {
	
	private ZonaDeCobertura zonaDeCobertura1, zonaDeCobertura2;
	private Muestra muestra;
	private Ubicacion ubicacion1, ubicacion2;
	private ObservadorZona observadorZona;
	private Distancia radio1, radio2, distancia;
	
	@BeforeEach
	public void setUp() {
		muestra = mock(Muestra.class);
		ubicacion1 = mock(Ubicacion.class);
		ubicacion2 = mock(Ubicacion.class);
		observadorZona = mock(ObservadorZona.class);
		radio1 = mock(Distancia.class);
		radio2 = mock(Distancia.class);
		distancia = mock(Distancia.class);
		zonaDeCobertura1 = new ZonaDeCobertura("Bernal", ubicacion1, radio1);
		zonaDeCobertura2 = new ZonaDeCobertura("Bernal", ubicacion2, radio2);
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaSeCreaTieneUnEpicentro() {
		assertEquals(ubicacion1, zonaDeCobertura1.getEpicentro());
	}

	@Test
	public void testCuandoUnaZonaDeCoberturaSeCreaTieneUnRadio() {
		assertEquals(radio1, zonaDeCobertura1.getRadio());
	}

	@Test
	public void testCuandoUnaZonaDeCoberturaSeCreaSuListaDeObservadoresEsVacia() {
		assertTrue(zonaDeCobertura1.getObservadores().isEmpty());
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaSeCreaSuListaDeMuestrasEsVacia() {
		assertTrue(zonaDeCobertura1.getMuestras().isEmpty());
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaAgregaUnObservadorEstaEnSuListaDeObservadores() {
		zonaDeCobertura1.agregarObservador(observadorZona);
		assertTrue(zonaDeCobertura1.getObservadores().contains(observadorZona));
		assertEquals(1, zonaDeCobertura1.getObservadores().size());
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaAgregaUnaMuestraEstaEnSuListaDeObservadores() {
		zonaDeCobertura1.agregarMuestra(muestra);
		assertTrue(zonaDeCobertura1.getMuestras().contains(muestra));
		assertEquals(1, zonaDeCobertura1.getMuestras().size());
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaSacaUnObservadorNoEstaEnSuListaDeObservadores() {
		zonaDeCobertura1.agregarObservador(observadorZona);
		assertTrue(zonaDeCobertura1.getObservadores().contains(observadorZona));
		assertEquals(1, zonaDeCobertura1.getObservadores().size());
		
		zonaDeCobertura1.sacarObservador(observadorZona);
		assertFalse(zonaDeCobertura1.getObservadores().contains(observadorZona));
		assertEquals(0, zonaDeCobertura1.getObservadores().size());
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaNotificaUnaNuevaMuestraSeLesAvisaASusObservadores() {
		zonaDeCobertura1.agregarObservador(observadorZona);
		zonaDeCobertura1.notificarNuevaMuestra(muestra);
		verify(observadorZona, times(1)).actualizarNuevaMuestra(zonaDeCobertura1, muestra);
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaNotificaUnaMuestraVerificadaSeLesAvisaASusObservadores() {
		zonaDeCobertura1.agregarObservador(observadorZona);
		zonaDeCobertura1.notificarVerificacionMuestra(muestra);
		verify(observadorZona, times(1)).actualizarMuestraVerificada(zonaDeCobertura1, muestra);
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaSeLePideSusZonasSolapadasSeLasPideAlSistema() {
		Sistema sistema = mock(Sistema.class);
		zonaDeCobertura1.zonasSolapadas(sistema);
		verify(sistema, times(1)).zonasSolapadasDe(zonaDeCobertura1);
	}

	@Test
	public void testCuandoUnaZonaDeCoberturaEstaSolapadaConOtraEsVerdaderoQueEstaSolapada() {
		when(radio1.getCantidad()).thenReturn(10d);
		when(radio2.getCantidad()).thenReturn(10d);
		when(distancia.getCantidad()).thenReturn(2d);
		when(ubicacion1.calcularDistanciaKilometrosA(ubicacion2)).thenReturn(distancia);
		assertTrue(zonaDeCobertura1.esZonaSolapada(zonaDeCobertura2));
	}
	
	@Test
	public void testCuandoUnaZonaDeCoberturaEstaSolapadaConOtraEsFalsoQueEstaSolapada() {
		when(radio1.getCantidad()).thenReturn(0.5d);
		when(radio2.getCantidad()).thenReturn(0.5d);
		when(distancia.getCantidad()).thenReturn(2d);
		when(ubicacion1.calcularDistanciaKilometrosA(ubicacion2)).thenReturn(distancia);
		assertFalse(zonaDeCobertura1.esZonaSolapada(zonaDeCobertura2));
	}
	
	@Test
	public void testCuandoUnaUbicacionEstaDentroDeLaZonaEstaDentroDelRadio() {
		when(distancia.getCantidad()).thenReturn(10d);
		when(radio1.getCantidad()).thenReturn(20d);
		when(ubicacion1.calcularDistanciaKilometrosA(ubicacion2)).thenReturn(distancia);
		assertTrue(zonaDeCobertura1.tieneALaUbicacionDentroDelRadio(ubicacion2));
	}
	
	@Test
	public void testCuandoUnaUbicacionNoEstaDentroDeLaZonaNoEstaDentroDelRadio() {
		when(distancia.getCantidad()).thenReturn(30d);
		when(radio1.getCantidad()).thenReturn(20d);
		when(ubicacion1.calcularDistanciaKilometrosA(ubicacion2)).thenReturn(distancia);
		assertFalse(zonaDeCobertura1.tieneALaUbicacionDentroDelRadio(ubicacion2));
	}
}
