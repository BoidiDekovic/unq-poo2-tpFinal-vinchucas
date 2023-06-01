package FiltroTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Filtro.DisyuncionDeFiltros;
import Filtro.Filtro;
import Filtro.OperadorDeFiltros;

class OperadorDeFiltrosTest {

	private OperadorDeFiltros operador;
	private Filtro filtro ;
	
	@BeforeEach
	public void setUp() throws Exception {
	
		operador = new DisyuncionDeFiltros();
		filtro = mock(Filtro.class);
	
	}

	@Test
	public void testCuandoUnOperadorDeFiltrosAgregaUnFiltroEsteSeAgregaALaLista() {
		operador.agregarFiltro(filtro);
	    assertEquals(filtro , operador.getFiltroHijo(0));
		
	}
	@Test
	public void testCuandoUnOperadorDeFiltrosEliminaUnFiltroEsteSeAgregaALaLista() {
		operador.agregarFiltro(filtro);
	    operador.quitarFiltro(filtro);
		assertEquals(filtro , operador.getFiltroHijo(0));

	}
}
