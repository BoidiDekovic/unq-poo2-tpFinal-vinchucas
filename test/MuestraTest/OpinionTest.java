package MuestraTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Opinion;
import Muestra.TipoOpinion;
import Usuario.EstadoUsuario;
import Usuario.Usuario;

public class OpinionTest {

	private Opinion opinion;
	private EstadoUsuario estadoUsuario;
	private Usuario usuario;
	private LocalDate fecha ;
	
	@BeforeEach
	public void setUp() {
		estadoUsuario = mock(EstadoUsuario.class);
		fecha = LocalDate.of(2023,2,18);
		usuario = mock(Usuario.class);
		when(usuario.getEstado()).thenReturn(estadoUsuario);
		opinion = new Opinion(TipoOpinion.VINCHUCAGUASAYANA, usuario ,fecha);
	}

	@Test
	public void testCuandoSeCreaUnaOpinionTieneUnUsuarioAutor() {
		assertEquals(usuario, opinion.getUsuarioAutor());
	}
	
	@Test
	public void testUnaOpinionTieneElTipoDeOpinionEsperado() {
		assertEquals(TipoOpinion.VINCHUCAGUASAYANA, opinion.getTipoOpinion());
	}
	
	@Test 
	public void testUnaOpinionTieneLaFechaDeEnvioEsperada(){
		
		assertEquals(fecha, opinion.getFechaDeEnvio());
	}
	
	@Test
	public void testCuandoSeLePreguntaUnaOpinionSiEsExpertoSeLeDelegaAlEstadoDelAutor() {
		opinion.esOpinionDeExperto();
		
		verify(estadoUsuario, times(1)).esExperto();
	}
	
	@Test
	public void testCuandoSeValidaExpertoVotandoMuestraExpertosSeLeDelegaAlEstadoDelAutor() {
		opinion.validarExpertoVotandoMuestraExpertos();
		verify(estadoUsuario, times(1)).validarExpertoVotandoMuestraExpertos();
	}

}
