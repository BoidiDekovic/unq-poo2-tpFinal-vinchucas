package SistemaTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Muestra;
import Sistema.Sistema;
import ZonaDeCobertura.Distancia;
import ZonaDeCobertura.Ubicacion;
import ZonaDeCobertura.ZonaDeCobertura;

public class SistemaTest {

	private Sistema sistema;
	private Muestra muestra1, muestra2, muestra3;
	private ZonaDeCobertura zona1, zona2, zona3;
	private Ubicacion ubicacion1, ubicacion2, ubicacion3;
	private Distancia distancia;
	
	@BeforeEach
	public void setUp() {
		sistema = new Sistema();
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		zona1 = mock(ZonaDeCobertura.class);
		zona2 = mock(ZonaDeCobertura.class);
		zona3 = mock(ZonaDeCobertura.class);
		ubicacion1 = mock(Ubicacion.class);
		ubicacion2 = mock(Ubicacion.class);
		ubicacion3 = mock(Ubicacion.class);
		distancia = mock(Distancia.class);
		
		sistema.agregarZonaDeCobertura(zona1);
		sistema.agregarZonaDeCobertura(zona2);
		sistema.agregarZonaDeCobertura(zona3);
		
		sistema.agregarMuestra(muestra1);
		sistema.agregarMuestra(muestra2);
		sistema.agregarMuestra(muestra3);
		
		when(muestra1.getUbicacion()).thenReturn(ubicacion1);
		when(muestra2.getUbicacion()).thenReturn(ubicacion2);
		when(muestra3.getUbicacion()).thenReturn(ubicacion3);
		
	}
	
	@Test
	public void testCuandoSeCreaUnSistemaSuListaDeMuestrasEsVacia() {
		sistema = new Sistema();
		assertTrue(sistema.getMuestras().isEmpty());
	}
	
	@Test
	public void testCuandoSeCreaUnSistemaSuListaDeZonasEsVacia() {
		sistema = new Sistema();
		assertTrue(sistema.getZonasDeCoberturas().isEmpty());
	}
	
	@Test
	public void testCuandoSeAgregaUnaMuestraAlSistemaSeAgregaALaListaDeMuestras() {
		sistema = new Sistema();
		sistema.agregarMuestra(muestra1);
		assertEquals(1, sistema.getMuestras().size());
		assertTrue(sistema.getMuestras().contains(muestra1));
	}
	
	@Test
	public void testCuandoSeAgregaUnaZonaAlSistemaSeAgregaALaListaDeZonas() {
		sistema = new Sistema();
		sistema.agregarZonaDeCobertura(zona1);
		assertEquals(1, sistema.getZonasDeCoberturas().size());
		assertTrue(sistema.getZonasDeCoberturas().contains(zona1));
	}
	
	@Test
public void testCuandoSeNotificaLaVerificacionDeUnaMuestraSeLeNotificaALaZonaQueLaTiene() {
		List<Muestra> muestrasDeZona = Arrays.asList(muestra1, muestra2);
		List<Muestra> muestrasDeZona2 = Arrays.asList(muestra3);
		when(zona1.getMuestras()).thenReturn(muestrasDeZona);
		when(zona2.getMuestras()).thenReturn(muestrasDeZona2);
		when(zona3.getMuestras()).thenReturn(new ArrayList<>());
		
		sistema.notificarVerificacionMuestraAZonas(muestra1);
		
		verify(zona1, times(1)).notificarVerificacionMuestra(muestra1);
		verify(zona2, never()).notificarVerificacionMuestra(muestra1);
		verify(zona3, never()).notificarVerificacionMuestra(muestra1);
	}
	
	@Test
	public void testCuandoSeNotificaLaVerificacionDeUnaMuestraNoSeLeNotificaANingunaZonaDadoQueNoLaTienen() {
		List<Muestra> muestrasDeZona = Arrays.asList(muestra3, muestra2);
		List<Muestra> muestrasDeZona2 = Arrays.asList(muestra3);
		when(zona1.getMuestras()).thenReturn(muestrasDeZona);
		when(zona2.getMuestras()).thenReturn(muestrasDeZona2);
		when(zona3.getMuestras()).thenReturn(new ArrayList<>());
		
		sistema.notificarVerificacionMuestraAZonas(muestra1);
		
		verify(zona1, never()).notificarVerificacionMuestra(muestra1);
		verify(zona2, never()).notificarVerificacionMuestra(muestra1);
		verify(zona3, never()).notificarVerificacionMuestra(muestra1);
	}
	
	@Test
	public void testCuandoSeNotificaLaVerificacionDeUnaMuestraSeLeNotificaATodasZonasDadoQueTodasLaTienen() {
		List<Muestra> muestrasDeZona = Arrays.asList(muestra1, muestra2);
		List<Muestra> muestrasDeZona2 = Arrays.asList(muestra1);
		when(zona1.getMuestras()).thenReturn(muestrasDeZona);
		when(zona2.getMuestras()).thenReturn(muestrasDeZona2);
		when(zona3.getMuestras()).thenReturn(muestrasDeZona2);
		
		sistema.notificarVerificacionMuestraAZonas(muestra1);
		
		verify(zona1, times(1)).notificarVerificacionMuestra(muestra1);
		verify(zona2, times(1)).notificarVerificacionMuestra(muestra1);
		verify(zona3, times(1)).notificarVerificacionMuestra(muestra1);
	}
	
	@Test
	public void testCuandoCalculaMuestrasQueEstanAUnaDistanciaConRespectoAUnaMuestraNingunaCumple() {
		when(ubicacion1.esUbicacionAMenosDe(ubicacion2, distancia)).thenReturn(false);
		when(ubicacion1.esUbicacionAMenosDe(ubicacion3, distancia)).thenReturn(false);
		
		List<Muestra> resultado = sistema.muestrasAMenosDe(distancia, muestra1);
		
		assertTrue(resultado.isEmpty());
		verify(ubicacion1, atLeastOnce()).esUbicacionAMenosDe(ubicacion2, distancia);
		verify(ubicacion1, atLeastOnce()).esUbicacionAMenosDe(ubicacion3, distancia);
	}
	
	@Test
	public void testCuandoCalculaMuestrasQueEstanAUnaDistanciaConRespectoAUnaMuestraUnaCumple() {
		when(ubicacion1.esUbicacionAMenosDe(ubicacion2, distancia)).thenReturn(true);
		when(ubicacion1.esUbicacionAMenosDe(ubicacion3, distancia)).thenReturn(false);
		
		List<Muestra> resultado = sistema.muestrasAMenosDe(distancia, muestra1);
		
		assertEquals(1, resultado.size());
		assertTrue(resultado.contains(muestra2));
		verify(ubicacion1, atLeastOnce()).esUbicacionAMenosDe(ubicacion2, distancia);
		verify(ubicacion1, atLeastOnce()).esUbicacionAMenosDe(ubicacion3, distancia);
	}
	
	@Test
	public void testCuandoCalculaMuestrasQueEstanAUnaDistanciaConRespectoAUnaMuestraTodasCumplen() {
		when(ubicacion1.esUbicacionAMenosDe(ubicacion2, distancia)).thenReturn(true);
		when(ubicacion1.esUbicacionAMenosDe(ubicacion3, distancia)).thenReturn(true);
		
		List<Muestra> resultado = sistema.muestrasAMenosDe(distancia, muestra1);
		
		assertEquals(2, resultado.size());
		assertTrue(resultado.contains(muestra2));
		assertTrue(resultado.contains(muestra3));
		verify(ubicacion1, atLeastOnce()).esUbicacionAMenosDe(ubicacion2, distancia);
		verify(ubicacion1, atLeastOnce()).esUbicacionAMenosDe(ubicacion3, distancia);
	}
	
	
	
	
	
	
}
