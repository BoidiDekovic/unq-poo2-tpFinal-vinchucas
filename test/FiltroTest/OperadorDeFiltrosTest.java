package FiltroTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;

import Filtro.DisyuncionDeFiltros;
import Filtro.Filtro;
import Filtro.OperadorDeFiltros;
import Muestra.Muestra;
import Muestra.TipoOpinion;

class OperadorDeFiltrosTest {

	private OperadorDeFiltros operador;
	private Filtro filtro ;
	
	@BeforeEach
	public void setUp() throws Exception {
	
		filtro = mock(Filtro.class);
		operador = new DisyuncionDeFiltros();
	
	}

	@Test
	public void testCuandoUnOperadorDeFiltrosAgregaUnFiltroEsteSeAgregaALaLista() {
		operador.agregarFiltro(filtro);
	    assertEquals(filtro, operador.getFiltroHijo(0));  
	}
	
	@Test
	public void testCuandoUnOperadorDeFiltrosQuiereAgregarMasDeDosFiltrosSeLanzaExcepcion() {
		operador.agregarFiltro(filtro);
		operador.agregarFiltro(mock(Filtro.class));
		assertThrows(UnsupportedOperationException.class, 
				() -> {operador.agregarFiltro(mock(Filtro.class));}
				, "No se pueden agregar mas de dos filtros");
	}
	
	@Test
	public void testCuandoUnOperadorDeFiltrosQuiereBuscarConMenosDeDosFiltrosLanzaExcepcion() {
		operador.agregarFiltro(filtro);
		assertThrows(UnsupportedOperationException.class, 
				() -> {operador.buscar(new ArrayList<Muestra>());}
				, "No se puede operar si no hay dos filtros");
	}
	
	
	@Test
	public void testCuandoSeCreaUnOperadorDeFiltrosTieneUnaListaVaciaDeFiltrosHijos() {
		assertTrue(operador.getFiltrosHijos().isEmpty());
	}
	
	@Test
	public void testCuandoUnOperadorDeFiltrosQuitaUnFiltroEsteSeSacaDeLaLista() {
		operador.agregarFiltro(filtro);
	    assertEquals(filtro, operador.getFiltroHijo(0));
	    operador.quitarFiltro(filtro);
	    assertTrue(operador.getFiltrosHijos().isEmpty());
	    
	}
}
