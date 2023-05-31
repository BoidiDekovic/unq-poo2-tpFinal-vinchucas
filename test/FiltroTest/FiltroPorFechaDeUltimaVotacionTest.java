package FiltroTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Filtro.FiltroPorFecha;
import Filtro.FiltroPorFechaDeUltimaVotacion;
import Muestra.Muestra;
import Muestra.TipoOpinion;
import Sistema.Sistema;

class FiltroPorFechaDeUltimaVotacionTest {
	private FiltroPorFecha filtro;
	private LocalDate fecha, fecha1;
	private Sistema sistema;
	private Muestra muestra1, muestra2, muestra3, muestra4;
	@BeforeEach
	void setUp() throws Exception {
		sistema = mock(Sistema.class);
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		fecha = LocalDate.of(2023, 6, 12);
		fecha1 = LocalDate.of(2023, 5, 23);
		filtro = new FiltroPorFechaDeUltimaVotacion(fecha, sistema);
		when(sistema.getMuestras()).thenReturn(Arrays.asList(muestra1, muestra2, muestra3, muestra4));
	}
	
	@Test
	public void testCuandoUnFiltroPorFechaDeUltimaVotacionBuscaPorUnaFechaYNoCoincideNingunaMuestraPorListaVacia() {
		when(sistema.getMuestras()).thenReturn(Arrays.asList());
		
		List<Muestra> resultado = filtro.buscar();
		
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	public void testCuandoUnFiltroPorFechaDeUltimaVotacionBuscaMuestrasConUnaFechaYNoEncuentraNinguna() {
		when(muestra1.getFechaUltimaVotacion()).thenReturn(fecha1);
		when(muestra2.getFechaUltimaVotacion()).thenReturn(fecha1);
		when(muestra3.getFechaUltimaVotacion()).thenReturn(fecha1);
		when(muestra4.getFechaUltimaVotacion()).thenReturn(fecha1);
		
		List<Muestra> resultado = filtro.buscar();
		
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	public void testCuandoUnFiltroPorFechaDeUltimaVotacionBuscaMuestrasConUnaFechaYEncuentraUna() {
		when(muestra1.getFechaUltimaVotacion()).thenReturn(fecha1);
		when(muestra2.getFechaUltimaVotacion()).thenReturn(fecha1);
		when(muestra3.getFechaUltimaVotacion()).thenReturn(fecha1);
		when(muestra4.getFechaUltimaVotacion()).thenReturn(fecha);
		
		List<Muestra> resultado = filtro.buscar();
		
		assertEquals(1, resultado.size());
		assertTrue(resultado.contains(muestra4));
	}
	
	@Test
	public void testCuandoUnFiltroPorFechaDeUltimaVotacionBuscaMuestrasConUnaFechaYEncuentraTodas() {
		when(muestra1.getFechaUltimaVotacion()).thenReturn(fecha);
		when(muestra2.getFechaUltimaVotacion()).thenReturn(fecha);
		when(muestra3.getFechaUltimaVotacion()).thenReturn(fecha);
		when(muestra4.getFechaUltimaVotacion()).thenReturn(fecha);
		
		List<Muestra> resultado = filtro.buscar();
		
		assertEquals(4, resultado.size());
		assertTrue(resultado.contains(muestra1));
		assertTrue(resultado.contains(muestra2));
		assertTrue(resultado.contains(muestra3));
		assertTrue(resultado.contains(muestra4));
	}
}
