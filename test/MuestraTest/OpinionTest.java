package MuestraTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Opinion;
import Muestra.TipoOpinion;
import Usuario.EstadoUsuario;

public class OpinionTest {

	private Opinion opinion;
	private EstadoUsuario estadoUsuario;
	private LocalDate fecha ;
	
	@BeforeEach
	public void setUp() {
		estadoUsuario = mock(EstadoUsuario.class);
		fecha = LocalDate.of(2023,2,18);
		opinion = new Opinion(TipoOpinion.VINCHUCAGUASAYANA, estadoUsuario,fecha);
	}
	
	@Test
	public void testUnaOpinionTieneElEstadoAutorEsperado() {
		assertEquals(estadoUsuario, opinion.getEstadoAutor());
	}
	
	@Test
	public void testUnaOpinionTieneElTipoDeOpinionEsperado() {
		assertEquals(TipoOpinion.VINCHUCAGUASAYANA, opinion.getTipoOpinion());
	}
	
	@Test 
	public void testUnaOpinionTieneLaFechaDeEnvioEsperada(){
		
		assertEquals(fecha, opinion.getFechaDeEnvio());
	}

}
