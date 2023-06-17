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
	private Filtro filtro, filtro2, filtro3 ;
	
	@BeforeEach
	public void setUp() throws Exception {
	
		filtro = mock(Filtro.class);
		filtro2 = mock(Filtro.class);
		filtro3 = mock(Filtro.class);
		operador = new DisyuncionDeFiltros(filtro, filtro2);
	
	}

	
	@Test
	public void testCuandoSeCreaUnOperadorDeFiltrosTieneDosFiltros() {
		assertEquals(filtro, operador.getFiltro1());
		assertEquals(filtro2, operador.getFiltro2());
		
	}
	
	@Test
	public void testCuandoSeSeteaUnFiltro1EnElOperadorDeFiltrosEsteSeCambia() {
		operador.setFiltro1(filtro3);
		assertEquals(filtro3, operador.getFiltro1());
		
	}
	@Test
	public void testCuandoSeSeteaUnFiltro2EnElOperadorDeFiltrosEsteSeCambia() {
		operador.setFiltro2(filtro3);
		assertEquals(filtro3, operador.getFiltro2());
		
	}
	
}
