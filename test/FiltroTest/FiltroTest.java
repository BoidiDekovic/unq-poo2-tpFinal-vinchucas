package FiltroTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Filtro.Filtro;
import Filtro.FiltroPorFecha;
import Filtro.FiltroPorTipoInsecto;
import Muestra.TipoOpinion;
import Sistema.Sistema;

public class FiltroTest {
	
	private Filtro filtro;
	private Sistema sistema;
	
	@BeforeEach
	public void setUp() {
		this.sistema = mock(Sistema.class);
		this.filtro = new FiltroPorTipoInsecto(TipoOpinion.CHINCHEFOLIADA, sistema);
	}
	
	@Test
	public void testCuandoUnFiltroSeCreaTieneUnSistema() {
		assertEquals(sistema, filtro.getSistema());
	}
	
	@Test
	public void testCuandoSeQuiereAgregarUnFiltroLanzaUnaExepcion() {
		assertThrowsExactly(UnsupportedOperationException.class, () -> {filtro.agregarFiltro(filtro);});
	}
	
	@Test
	public void testCuandoSeQuiereQuitarUnFiltroLanzaUnaExepcion( ) {
		assertThrowsExactly(UnsupportedOperationException.class, () -> {filtro.quitarFiltro(filtro);});
	}
	
	@Test
	public void testCuandoSeQuierePedirUnFiltroHijoLanzaUnaExepcion( ) {
		assertThrowsExactly(UnsupportedOperationException.class, () -> {filtro.getFiltroHijo(0);});
	}
	

}
