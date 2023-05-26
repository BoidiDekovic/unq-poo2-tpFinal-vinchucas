package MuestraTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Opinion;
import Muestra.TipoOpinion;
import Usuario.EstadoUsuario;

public class OpinionTest {

	private Opinion opinion;
	private EstadoUsuario estadoUsuario;
	
	@BeforeEach
	public void setUp() {
		estadoUsuario = mock(EstadoUsuario.class);
		opinion = new Opinion(TipoOpinion.VINCHUCAGUASAYANA, estadoUsuario);
	}
	
	@Test
	public void testUnaOpinionTieneElEstadoAutorEsperado() {
		assertEquals(estadoUsuario, opinion.getEstadoAutor());
	}
	
	@Test
	public void testUnaOpinionTieneElTipoDeOpinionEsperado() {
		assertEquals(TipoOpinion.VINCHUCAGUASAYANA, opinion.getTipoOpinion());
	}
	
}
