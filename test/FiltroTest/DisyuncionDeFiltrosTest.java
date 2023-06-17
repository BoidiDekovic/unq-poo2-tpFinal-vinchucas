package FiltroTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Filtro.DisyuncionDeFiltros;
import Filtro.Filtro;
import Muestra.Muestra;

class DisyuncionDeFiltrosTest {
	
	DisyuncionDeFiltros disyuncionDeFiltros;
	Filtro filtro, filtro2;
	Muestra muestra1, muestra2, muestra3, muestra4;
	List<Muestra> muestras,lista1,lista2;
	
	@BeforeEach
	public void setUp() {
		filtro = mock(Filtro.class);
		filtro2 = mock(Filtro.class);
		disyuncionDeFiltros = new DisyuncionDeFiltros(filtro, filtro2);
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		muestras = Arrays.asList(muestra1, muestra2, muestra3, muestra4);
		lista1 = new ArrayList<Muestra>();
		lista2 = new ArrayList<Muestra>();
	}
	
	@Test
	void testCuandoLaDisyuncionDeFiltrosLePasanUnaListaDeMuestrasVaciaDevuelveUnaListaVacia() {
		assertTrue(disyuncionDeFiltros.buscar(Arrays.asList()).isEmpty());
	}
	
	@Test
	void testCuandoLaBusquedaDelPrimerFiltroDaUnaListaConMuestrasYLaDelSegundoUnaListaVaciaDevuelveLaListaDelPrimerFiltro() {
		when(filtro.buscar(muestras)).thenReturn(Arrays.asList(muestra1, muestra2));
		when(filtro2.buscar(muestras)).thenReturn(Arrays.asList());
		List<Muestra> resultado = disyuncionDeFiltros.buscar(muestras);
		assertTrue(resultado.contains(muestra1));
		assertTrue(resultado.contains(muestra2));
		assertEquals(2, resultado.size());
	}
	
	@Test
	void testCuandoLaBusquedaDelSegundoFiltroDaUnaListaConMuestrasYLaDeLaPrimeraUnaListaVaciaDevuelveLaListaDelSegundoFiltro() {
		when(filtro.buscar(muestras)).thenReturn(lista1);
		when(filtro2.buscar(muestras)).thenReturn(Arrays.asList(muestra3, muestra4));
		List<Muestra> resultado = disyuncionDeFiltros.buscar(muestras);
		assertTrue(resultado.contains(muestra3));
		assertTrue(resultado.contains(muestra4));
		assertEquals(2, resultado.size());
	}
	
	@Test
	void testCuandoLaBusquedaDelPrimerYSegundoFiltroDaUnaListaVaciaDevuelveUnaListaVacia() {
		when(filtro.buscar(muestras)).thenReturn(lista1);
		when(filtro2.buscar(muestras)).thenReturn(lista2);
		List<Muestra> resultado = disyuncionDeFiltros.buscar(muestras);
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	void testCuandoLaBusquedaDelPrimerYSegundoFiltroDaUnaListaConMuestrasDevuelveLasDosUnidas() {
		lista1.add(muestra1);
		lista2.add(muestra4);
		when(filtro.buscar(muestras)).thenReturn(lista1);
		when(filtro2.buscar(muestras)).thenReturn(lista2);
		List<Muestra> resultado = disyuncionDeFiltros.buscar(muestras);
		assertTrue(resultado.contains(muestra1));
		assertTrue(resultado.contains(muestra4));
		assertEquals(2, resultado.size());
	}
	
	@Test
	void testCuandoLaBusquedaDelPrimerYSegundoFiltroDaUnaListaConMuestrasDevuelveLasDosUnidasSinRepetidos() {
		lista1.add(muestra1);
		lista2.add(muestra4);
		lista1.add(muestra4);
		when(filtro.buscar(muestras)).thenReturn(lista1);
		when(filtro2.buscar(muestras)).thenReturn(lista2);
		List<Muestra> resultado = disyuncionDeFiltros.buscar(muestras);
		assertTrue(resultado.contains(muestra1));
		assertTrue(resultado.contains(muestra4));
		assertEquals(2, resultado.size());
	}
	
}
