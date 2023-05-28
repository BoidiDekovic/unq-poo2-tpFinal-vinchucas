package ZonaDeCoberturaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ZonaDeCobertura.Distancia;
import ZonaDeCobertura.Ubicacion;
import ZonaDeCobertura.Unidad;

public class DistanciaTest {

	private Distancia distancia;
	private Ubicacion ubicacion1, ubicacion2;
	
	@BeforeEach
	public void setUp() {
		distancia = new Distancia(Unidad.KILOMETRO, 10);
		ubicacion1 = mock(Ubicacion.class);
		ubicacion2 = mock(Ubicacion.class);
		
		when(ubicacion1.getLatitud()).thenReturn(10.0);
		when(ubicacion1.getLongitud()).thenReturn(10.0);
		when(ubicacion2.getLatitud()).thenReturn(30.0);
		when(ubicacion2.getLongitud()).thenReturn(30.0);
	}
	
	@Test
	public void testCuandoUnaDistanciaSeCreaTieneUnaCantidad() {
		assertEquals(10, distancia.getCantidad());
	}
	
	@Test
	public void testCuandoUnaDistanciaSeCreaTieneUnaUnidad() {
		assertEquals(Unidad.KILOMETRO, distancia.getUnidad());
	}
	
	@Test
	public void testCuandoSeSeteaUnaCantidadEnDistanciaSeCambia() {
		assertEquals(10, distancia.getCantidad());
		distancia.setCantidad(20);
		assertEquals(20, distancia.getCantidad());
	}
	
	@Test
	public void testCuandoSeSeteaUnaUnidadEnDistanciaSeCambia() {
		assertEquals(Unidad.KILOMETRO, distancia.getUnidad());
		distancia.setUnidad(Unidad.METRO);
		assertEquals(Unidad.METRO, distancia.getUnidad());
	}
	
	@Test
	public void testCuandoSeCalculaLaDistanciaEntreDosUbicacionesElResultadoEnKilometrosSeSetea() {
		distancia.calcularDistanciaEntreUbicacionesEnKilometros(ubicacion1, ubicacion2);
		assertEquals(28.28, distancia.getCantidad());
		assertEquals(Unidad.KILOMETRO, distancia.getUnidad());
	}
	
	@Test
	public void testCuandoSeCalculaLaDistanciaEntreDosUbicacionesElResultadoEnMetrosSeSetea() {
		distancia.calcularDistanciaEntreUbicacionesEnMetros(ubicacion1, ubicacion2);
		assertEquals(28280.0, distancia.getCantidad());
		assertEquals(Unidad.METRO, distancia.getUnidad());
	}
	
	
}
