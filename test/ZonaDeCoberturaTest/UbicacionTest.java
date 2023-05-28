package ZonaDeCoberturaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ZonaDeCobertura.Distancia;
import ZonaDeCobertura.Ubicacion;
import ZonaDeCobertura.Unidad;

class UbicacionTest {

	private Ubicacion ubicacion,ubicacion2;
	private Distancia distancia;
	@BeforeEach
	void setUp() throws Exception {
		
		ubicacion = new Ubicacion(10,10);
		ubicacion2 = new Ubicacion(30,30);
		distancia = mock(Distancia.class);	
		}

	@Test
	public void testCuandoUnaUbicacionSeCreaTieneUnaLatitud() {
		assertEquals(10,ubicacion.getLatitud());
	}
	
	@Test
	public void testCuandoUnaUbicacionSeCreaTieneUnaLongitud() {
		assertEquals(10,ubicacion.getLongitud());
	}
	
	@Test
	public void testCuandoUnaUbicacionCalculaSuDistanciaConOtraEnMetrosDaUnaDistanciaEnMetros() {
		distancia = ubicacion.calcularDistanciaMetrosA(ubicacion2);
		
		assertEquals(Unidad.METRO , distancia.getUnidad());
		assertEquals(28280.0 , distancia.getCantidad());
	}
	@Test
	public void testCuandoUnaUbicacionCalculaSuDistanciaConOtraEnMetrosDaUnaDistanciaEnKilometros() {
		distancia = ubicacion.calcularDistanciaKilometrosA(ubicacion2);
		
		assertEquals(Unidad.KILOMETRO , distancia.getUnidad());
		assertEquals(28.28 , distancia.getCantidad());
	}
	@Test
	public void testCuandoUnaUbicacionEstaALaDistanciaDadaEnMetrosDaVerdadero() {
		
		when(distancia.getUnidad()).thenReturn(Unidad.METRO);
		when(distancia.getCantidad()).thenReturn(30000.0);
		assertTrue(ubicacion.esUbicacionAMenosDe(ubicacion2, distancia));
	}
	
	public void testCuandoUnaUbicacionEstaALaDistanciaDadaEnMetrosDaFalso() {
		
		when(distancia.getUnidad()).thenReturn(Unidad.METRO);
		when(distancia.getCantidad()).thenReturn(10.0);
		assertFalse(ubicacion.esUbicacionAMenosDe(ubicacion2, distancia));
	}
	
	@Test
	public void testCuandoUnaUbicacionEstaALaDistanciaDadaEnKilometrosDaVerdadero() {
		
		when(distancia.getUnidad()).thenReturn(Unidad.KILOMETRO);
		when(distancia.getCantidad()).thenReturn(30.0);
		assertTrue(ubicacion.esUbicacionAMenosDe(ubicacion2, distancia));
	}
	
	@Test
	public void testCuandoUnaUbicacionEstaALaDistanciaDadaEnKilometrosDaFalso() {
		
		when(distancia.getUnidad()).thenReturn(Unidad.KILOMETRO);
		when(distancia.getCantidad()).thenReturn(10.0);
		assertFalse(ubicacion.esUbicacionAMenosDe(ubicacion2, distancia));
	}
	
	
}
