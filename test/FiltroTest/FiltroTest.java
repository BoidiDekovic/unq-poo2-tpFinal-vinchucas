package FiltroTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Filtro.Filtro;
import Filtro.FiltroPorTipoInsecto;
import Muestra.TipoOpinion;

public class FiltroTest {
	
	private Filtro filtro;
	
	@BeforeEach
	public void setUp() {
		this.filtro = new FiltroPorTipoInsecto(TipoOpinion.CHINCHEFOLIADA);
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
