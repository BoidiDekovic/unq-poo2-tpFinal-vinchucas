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
	Filtro filtro1, filtro2;
	Muestra muestra1, muestra2, muestra3, muestra4;
	List<Muestra> muestras;
	
	@BeforeEach
	public void setUp() {
		disyuncionDeFiltros = new DisyuncionDeFiltros();
		filtro1 = mock(Filtro.class);
		filtro2 = mock(Filtro.class);
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		muestras = Arrays.asList(muestra1, muestra2, muestra3, muestra4);
		disyuncionDeFiltros.agregarFiltro(filtro1);
		disyuncionDeFiltros.agregarFiltro(filtro2);
	}
	
	@Test
	void testCuandoLaDisyuncionDeFiltrosLePasanUnaListaDeMuestrasVaciaDevuelveUnaListaVacia() {
		assertTrue(disyuncionDeFiltros.buscar(Arrays.asList()).isEmpty());
	}
	
	@Test
	void testCuandoLaBusquedaDelPrimerFiltroDaUnaListaConMuestrasYLaDelSegundoUnaListaVaciaDevuelveLaListaDelPrimerFiltro() {
		when(filtro1.buscar(muestras)).thenReturn(Arrays.asList(muestra1, muestra2));
		when(filtro2.buscar(muestras)).thenReturn(Arrays.asList());
		List<Muestra> resultado = disyuncionDeFiltros.buscar(muestras);
		assertTrue(resultado.contains(muestra1));
		assertTrue(resultado.contains(muestra2));
		assertEquals(2, resultado.size());
	}
	
	@Test
	void testCuandoLaBusquedaDelSegundoFiltroDaUnaListaConMuestrasYLaDeLaPrimeraUnaListaVaciaDevuelveLaListaDelSegundoFiltro() {
		when(filtro1.buscar(muestras)).thenReturn(new ArrayList<Muestra>());
		when(filtro2.buscar(muestras)).thenReturn(Arrays.asList(muestra3, muestra4));
		List<Muestra> resultado = disyuncionDeFiltros.buscar(muestras);
		assertTrue(resultado.contains(muestra3));
		assertTrue(resultado.contains(muestra4));
		assertEquals(2, resultado.size());
	}
	
	@Test
	void testCuandoLaBusquedaDelPrimerYSegundoFiltroDaUnaListaVaciaDevuelveUnaListaVacia() {
		when(filtro1.buscar(muestras)).thenReturn(new ArrayList<Muestra>());
		when(filtro2.buscar(muestras)).thenReturn(new ArrayList<Muestra>());
		List<Muestra> resultado = disyuncionDeFiltros.buscar(muestras);
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	void testCuandoLaBusquedaDelPrimerYSegundoFiltroDaUnaListaConMuestrasDevuelveLasDosUnidas() {
		List<Muestra> lista1 = new ArrayList<Muestra>(); lista1.add(muestra1);
		List<Muestra> lista2 = new ArrayList<Muestra>(); lista1.add(muestra4);
		when(filtro1.buscar(muestras)).thenReturn(lista1);
		when(filtro2.buscar(muestras)).thenReturn(lista2);
		List<Muestra> resultado = disyuncionDeFiltros.buscar(muestras);
		assertTrue(resultado.contains(muestra1));
		assertTrue(resultado.contains(muestra4));
		assertEquals(2, resultado.size());
	}

}
