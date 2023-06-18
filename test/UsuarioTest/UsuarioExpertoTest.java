package UsuarioTest;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Usuario.Usuario;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;

public class UsuarioExpertoTest {
	
	private Usuario usuario;
	private UsuarioExperto estadoExperto;
	
	@BeforeEach
	void setUp() throws Exception {
	
	usuario = mock(Usuario.class);
	estadoExperto = new UsuarioExperto();
	
	}
	
	@Test
	public void testCuandoSeLePreguntaAUnUsuarioExpertoSiEsExpertoDaVerdadero() {
		assertTrue(estadoExperto.esExperto());
	}
	
	@Test
	public void testCuandoUnUsuarioExpertoCalculaSuCategoriaYNoCumpleNingunoDeLosRequisitosCambiaDeEstado() {
		when(usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias()).thenReturn(false);
		when(usuario.tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias()).thenReturn(false);
		
		estadoExperto.calcularCategoria(usuario);
		
		verify(usuario, times(1)).setEstado(any(UsuarioBasico.class));
	}
	
	@Test
	public void testCuandoUnUsuarioExpertoCalculaSuCategoriaYNoCumpleLosRequisitosDeMuestrasEnviadasCambiaDeEstado() {
		when(usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias()).thenReturn(false);
		when(usuario.tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias()).thenReturn(true);
		
		estadoExperto.calcularCategoria(usuario);
		
		verify(usuario, times(1)).setEstado(any(UsuarioBasico.class));
	}
	
	@Test
	public void testCuandoUnUsuarioExpertoCalculaSuCategoriaYNoCumpleLosRequisitosDeOpinionesEnviadasCambiaDeEstado() {
		when(usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias()).thenReturn(true);
		when(usuario.tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias()).thenReturn(false);
		
		estadoExperto.calcularCategoria(usuario);
		
		verify(usuario, times(1)).setEstado(any(UsuarioBasico.class));
	}	

	@Test
	public void testCuandoUnUsuarioExpertoCalculaSuCategoriaYCumpleLosRequisitosNoCambiaDeEstado() {
		when(usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias()).thenReturn(true);
		when(usuario.tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias()).thenReturn(true);
		
		estadoExperto.calcularCategoria(usuario);
		
		verify(usuario, never()).setEstado(any(UsuarioBasico.class));
	}

}
