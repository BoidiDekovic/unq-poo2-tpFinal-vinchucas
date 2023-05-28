package FiltroTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Filtro.FiltroPorTipoInsecto;
import Muestra.Muestra;
import Muestra.TipoOpinion;
import Sistema.Sistema;

public class FiltroPorTipoInsectoTest {
	
	private FiltroPorTipoInsecto filtro;
	private Sistema sistema;
	private Muestra muestra1, muestra2, muestra3, muestra4;
	
	@BeforeEach
	public void setUp() {
		sistema = mock(Sistema.class);
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		filtro = new FiltroPorTipoInsecto(TipoOpinion.CHINCHEFOLIADA, sistema);
		
		when(sistema.getMuestras()).thenReturn(Arrays.asList(muestra1, muestra2, muestra3, muestra4));
	}
	
	@Test
	public void testCuandoUnFiltroPorTipoInsectoSeCreaTieneUnTipoInsecto() {
		assertEquals(TipoOpinion.CHINCHEFOLIADA, filtro.getTipoInsecto());
	}
	
	@Test
	public void testCuandoUnFiltroPorTipoInsectoSeSeteaCambiaSuTipoInsecto() {
		assertEquals(TipoOpinion.CHINCHEFOLIADA, filtro.getTipoInsecto());
		filtro.setTipoInsecto(TipoOpinion.VINCHUCAGUASAYANA);
		assertEquals(TipoOpinion.VINCHUCAGUASAYANA, filtro.getTipoInsecto());
	}
	
	@Test
	public void testCuandoUnFiltroPorTipoInsectoBuscaMuestrasConTipoInsectoYNoEncuentraNingunaPorListaVacia() {
		when(sistema.getMuestras()).thenReturn(Arrays.asList());
		
		List<Muestra> resultado = filtro.buscar();
		
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	public void testCuandoUnFiltroPorTipoInsectoBuscaMuestrasConTipoInsectoYNoEncuentraNinguna() {
		when(muestra1.resultadoActual()).thenReturn(TipoOpinion.PHTIACHINCHE);
		when(muestra2.resultadoActual()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(muestra3.resultadoActual()).thenReturn(TipoOpinion.VINCHUCAINFESTANS);
		when(muestra4.resultadoActual()).thenReturn(TipoOpinion.VINCHUCASORDIDA);
		
		List<Muestra> resultado = filtro.buscar();
		
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	public void testCuandoUnFiltroPorTipoInsectoBuscaMuestrasConTipoInsectoYEncuentraUna() {
		when(muestra1.resultadoActual()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(muestra2.resultadoActual()).thenReturn(TipoOpinion.PHTIACHINCHE);
		when(muestra3.resultadoActual()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(muestra4.resultadoActual()).thenReturn(TipoOpinion.VINCHUCAINFESTANS);
		
		List<Muestra> resultado = filtro.buscar();
		
		assertEquals(1, resultado.size());
		assertTrue(resultado.contains(muestra1));
	}
	
	@Test
	public void testCuandoUnFiltroPorTipoInsectoBuscaMuestrasConTipoInsectoYEncuentraTodas() {
		when(muestra1.resultadoActual()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(muestra2.resultadoActual()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(muestra3.resultadoActual()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(muestra4.resultadoActual()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		
		List<Muestra> resultado = filtro.buscar();
		
		assertEquals(4, resultado.size());
		assertTrue(resultado.contains(muestra1));
		assertTrue(resultado.contains(muestra2));
		assertTrue(resultado.contains(muestra3));
		assertTrue(resultado.contains(muestra4));
	}

}
