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

import Filtro.FiltroPorFechaDeCreacion;
import Muestra.Muestra;

class FiltroPorFechaDeCreacionTest {
	private FiltroPorFechaDeCreacion filtro;
	private LocalDate fecha, fecha1;
	private Muestra muestra1, muestra2, muestra3, muestra4;
	private List<Muestra> muestras;
	
	@BeforeEach
	void setUp() throws Exception {
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		muestras = Arrays.asList(muestra1, muestra2, muestra3, muestra4);
		fecha = LocalDate.of(2023, 6, 12);
		fecha1 = LocalDate.of(2023, 5, 23);
		filtro = new FiltroPorFechaDeCreacion(fecha);
	}
	
	@Test
	public void testCuandoUnFiltroPorFechaDeCreacionBuscaPorUnaFechaYNoCoincideNingunaMuestraPorListaVacia() {
		List<Muestra> resultado = filtro.buscar(Arrays.asList());
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	public void testCuandoUnFiltroPorFechaDeCreacionBuscaMuestrasConUnaFechaYNoEncuentraNinguna() {
		when(muestra1.getFechaCreacion()).thenReturn(fecha1);
		when(muestra2.getFechaCreacion()).thenReturn(fecha1);
		when(muestra3.getFechaCreacion()).thenReturn(fecha1);
		when(muestra4.getFechaCreacion()).thenReturn(fecha1);
		
		List<Muestra> resultado = filtro.buscar(muestras);
		
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	public void testCuandoUnFiltroPorFechaDeCreacionBuscaMuestrasConUnaFechaYEncuentraUna() {
		when(muestra1.getFechaCreacion()).thenReturn(fecha1);
		when(muestra2.getFechaCreacion()).thenReturn(fecha1);
		when(muestra3.getFechaCreacion()).thenReturn(fecha1);
		when(muestra4.getFechaCreacion()).thenReturn(fecha);
		
		List<Muestra> resultado = filtro.buscar(muestras);
		
		assertEquals(1, resultado.size());
		assertTrue(resultado.contains(muestra4));
	}
	
	@Test
	public void testCuandoUnFiltroPorFechaDeCreacionBuscaMuestrasConUnaFechaYEncuentraTodas() {
		when(muestra1.getFechaCreacion()).thenReturn(fecha);
		when(muestra2.getFechaCreacion()).thenReturn(fecha);
		when(muestra3.getFechaCreacion()).thenReturn(fecha);
		when(muestra4.getFechaCreacion()).thenReturn(fecha);
		
		List<Muestra> resultado = filtro.buscar(muestras);
		
		assertEquals(4, resultado.size());
		assertTrue(resultado.contains(muestra1));
		assertTrue(resultado.contains(muestra2));
		assertTrue(resultado.contains(muestra3));
		assertTrue(resultado.contains(muestra4));
	}
}
