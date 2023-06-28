package UsuarioTest;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Usuario.Usuario;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;

public class UsuarioBasicoTest {
	
	private Usuario usuario;
	private UsuarioBasico estadoBasico;
	
	@BeforeEach
	void setUp() throws Exception {
	
	usuario = mock(Usuario.class);
	estadoBasico = new UsuarioBasico();
	
	}
	
	@Test
	public void testCuandoSeLePreguntaAUnUsuarioBasicoSiEsExpertoDaFalso() {
		assertFalse(estadoBasico.esExperto());
	}
	
	@Test
	public void testCuandoUnUsuarioBasicoCalculaSuCategoriaYNoCumpleNingunoDeLosRequisitosNoCambiaDeEstado() {
		when(usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias()).thenReturn(false);
		when(usuario.tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias()).thenReturn(false);
		
		estadoBasico.calcularCategoria(usuario);
		
		verify(usuario, never()).setEstado(any(UsuarioExperto.class));
	}
	
	@Test
	public void testCuandoUnUsuarioBasicoCalculaSuCategoriaYNoCumpleLosRequisitosDeMuestrasEnviadasNoCambiaDeEstado() {
		when(usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias()).thenReturn(false);
		when(usuario.tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias()).thenReturn(true);
		
		estadoBasico.calcularCategoria(usuario);
		
		verify(usuario, never()).setEstado(any(UsuarioExperto.class));
	}
	
	@Test
	public void testCuandoUnUsuarioBasicoCalculaSuCategoriaYNoCumpleLosRequisitosDeOpinionesEnviadasNoCambiaDeEstado() {
		when(usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias()).thenReturn(true);
		when(usuario.tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias()).thenReturn(false);
		
		estadoBasico.calcularCategoria(usuario);
		
		verify(usuario, never()).setEstado(any(UsuarioExperto.class));
	}	

	@Test
	public void testCuandoUnUsuarioBasicoCalculaSuCategoriaYCumpleLosRequisitosCambiaDeEstado() {
		when(usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias()).thenReturn(true);
		when(usuario.tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias()).thenReturn(true);
		
		estadoBasico.calcularCategoria(usuario);
		
		verify(usuario, times(1)).setEstado(any(UsuarioExperto.class));
	}

	@Test
	public void testCuandoSeValidaExpertoVotandoMuestraExpertosSeLanzaExcepcion() {
		assertThrows(UnsupportedOperationException.class, 
				() -> {estadoBasico.validarExpertoVotandoMuestraExpertos();}
				, "Un usuario basico no puede opinar una muestra que fue opinada por un experto");
	}
	
}
