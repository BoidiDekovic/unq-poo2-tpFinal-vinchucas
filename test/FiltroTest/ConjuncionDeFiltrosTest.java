package FiltroTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Filtro.ConjuncionDeFiltros;
import Filtro.Filtro;
import Muestra.Muestra;

class ConjuncionDeFiltrosTest {
	ConjuncionDeFiltros conjucionDeFiltros;
	Filtro filtro1, filtro2;
	Muestra muestra1, muestra2, muestra3, muestra4;
	List<Muestra> muestras , lista1, lista2;

	
	@BeforeEach
	public void setUp() {
		filtro1 = mock(Filtro.class);
		filtro2 = mock(Filtro.class);
		conjucionDeFiltros = new ConjuncionDeFiltros(filtro1, filtro2);
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		muestras = Arrays.asList(muestra1, muestra2, muestra3, muestra4);
		lista1 = new ArrayList<Muestra>();
		lista2 = new ArrayList<Muestra>();
	}
	
	@Test
	void testCuandoLaConjuncionDeFiltrosLePasanUnaListaDeMuestrasVaciaDevuelveUnaListaVacia() {
		assertTrue(conjucionDeFiltros.buscar(new ArrayList<Muestra>()).isEmpty());
	}
	
	@Test
	void testCuandoLaBusquedaDelPrimerFiltroDaUnaListaConMuestrasYLaDelSegundoUnaListaVaciaDevuelveUnaListaVacia() {
		lista1.add(muestra1);
		lista1.add(muestra2);
		when(filtro1.buscar(muestras)).thenReturn(lista1);
		when(filtro2.buscar(muestras)).thenReturn(lista2);
		List<Muestra> resultado = conjucionDeFiltros.buscar(muestras);
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	void testCuandoLaBusquedaDelSegundoFiltroDaUnaListaConMuestrasYLaDeLaPrimeraUnaListaVaciaDevuelveUnaListaVacia() {
		lista2.add(muestra1);
		lista2.add(muestra2);
		when(filtro1.buscar(muestras)).thenReturn(lista1);
		when(filtro2.buscar(muestras)).thenReturn(lista2);
		List<Muestra> resultado = conjucionDeFiltros.buscar(muestras);
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	void testCuandoLaBusquedaDelPrimerYSegundoFiltroDaUnaListaVaciaDevuelveUnaListaVacia() {
		when(filtro1.buscar(muestras)).thenReturn(lista1);
		when(filtro2.buscar(muestras)).thenReturn(lista2);
		List<Muestra> resultado = conjucionDeFiltros.buscar(muestras);
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	void testCuandoLaBusquedaDelPrimerYSegundoFiltroDaUnaListaConMuestrasTienenMuestrasEnComunDevuelveUnaListaConEsasMuestras() {
		lista1.add(muestra1);
		lista2.add(muestra2);
		lista1.add(muestra2);
		lista2.add(muestra4);
		when(filtro1.buscar(muestras)).thenReturn(lista1);
		when(filtro2.buscar(muestras)).thenReturn(lista2);
		List<Muestra> resultado = conjucionDeFiltros.buscar(muestras);
		assertTrue(resultado.contains(muestra2));
		assertEquals(1, resultado.size());
	}
	
}
