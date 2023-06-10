package FiltroTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Filtro.FiltroPorNivelDeVerificacion;
import Muestra.EstadoMuestra;
import Muestra.Muestra;
import Muestra.MuestraNoVerificada;
import Muestra.MuestraVerificada;

class FiltroPorNivelDeVerificacionTest {

	private FiltroPorNivelDeVerificacion filtro;
	private Muestra muestra1, muestra2, muestra3, muestra4;
	private EstadoMuestra nivelVerificado, nivelNoVerificado;
	private List<Muestra> muestras;
	
	@BeforeEach
	public void setUp() {
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		muestras = Arrays.asList(muestra1, muestra2, muestra3, muestra4);
		nivelVerificado = mock(MuestraVerificada.class);
		nivelNoVerificado = mock(MuestraNoVerificada.class);
		filtro = new FiltroPorNivelDeVerificacion(nivelVerificado);
	}
	
	@Test
	public void testCuandoUnFiltroPorNivelDeVerificacionSeCreaTieneUnNivelDeVerificacion() {
		assertEquals(nivelVerificado, filtro.getNivelVerificacion());
	}
	
	@Test
	public void testCuandoUnFiltroPorNivelDeVerificacionSeSeteaCambiaSuNivelDeVerificacion() {
		assertEquals(nivelVerificado, filtro.getNivelVerificacion());
		filtro.setNivelVerificacion(nivelNoVerificado);
		assertEquals(nivelNoVerificado, filtro.getNivelVerificacion());
	}
	
	@Test
	public void testCuandoUnFiltroPorNivelDeVerificacionBuscaMuestrasConNivelDeVerificacionYNoEncuentraNinguna() {
		when(muestra1.getEstadoMuestra()).thenReturn(nivelNoVerificado);
		when(muestra2.getEstadoMuestra()).thenReturn(nivelNoVerificado);
		when(muestra3.getEstadoMuestra()).thenReturn(nivelNoVerificado);
		when(muestra4.getEstadoMuestra()).thenReturn(nivelNoVerificado);
		
		List<Muestra> resultado = filtro.buscar(muestras);
		
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	public void testCuandoUnFiltroPorNivelDeVerificacionBuscaMuestrasConNivelDeVerificacionYNoEncuentraNingunaPorListaVacia() {
		List<Muestra> resultado = filtro.buscar(Arrays.asList());
		
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	public void testCuandoUnFiltroPorNivelDeVerificacionBuscaMuestrasConNivelDeVerificacionYEncuentraUna() {
		when(muestra1.getEstadoMuestra()).thenReturn(nivelVerificado);
		when(muestra2.getEstadoMuestra()).thenReturn(nivelNoVerificado);
		when(muestra3.getEstadoMuestra()).thenReturn(nivelNoVerificado);
		when(muestra4.getEstadoMuestra()).thenReturn(nivelNoVerificado);
		
		List<Muestra> resultado = filtro.buscar(muestras);
		
		assertEquals(1, resultado.size());
		assertTrue(resultado.contains(muestra1));
	}
	
	@Test
	public void testCuandoUnFiltroPorNivelDeVerificacionBuscaMuestrasConNivelDeVerificacionYEncuentraTodas() {
		when(muestra1.getEstadoMuestra()).thenReturn(nivelVerificado);
		when(muestra2.getEstadoMuestra()).thenReturn(nivelVerificado);
		when(muestra3.getEstadoMuestra()).thenReturn(nivelVerificado);
		when(muestra4.getEstadoMuestra()).thenReturn(nivelVerificado);
		
		List<Muestra> resultado = filtro.buscar(muestras);
		
		assertEquals(4, resultado.size());
		assertTrue(resultado.contains(muestra1));
		assertTrue(resultado.contains(muestra2));
		assertTrue(resultado.contains(muestra3));
		assertTrue(resultado.contains(muestra4));
	}

}
