package UsuarioTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Muestra;
import Muestra.Opinion;
import Muestra.TipoOpinion;
import Usuario.Usuario;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;

public class UsuarioExpertoTest {
	
	private Usuario usuario;
	private Muestra muestra;
	private UsuarioExperto estadoExperto;
	

	@BeforeEach
	void setUp() throws Exception {
	
	usuario = mock(Usuario.class);
	muestra = mock(Muestra.class);
	estadoExperto = new UsuarioExperto();
	
	}
	
	@Test 
	public void testCuandoUnUsuarioOpinaSobreUnaMuestraEsteLePideAgregarseAsusOpiniones() {
		estadoExperto.opinarSobreMuestra(muestra, TipoOpinion.CHINCHEFOLIADA, usuario);
		verify(muestra,times(1)).agregarOpinion(any(Opinion.class));
		verify(muestra,never()).agregarOpinionAMuestra(any());
		verify(usuario, times(1)).agregarOpinionEnviada(any(Opinion.class));
	}
	
	@Test
	public void testCuandoSeLePreguntaAUnUsuarioExpertoSiEsExpertoDaVerdadero() {
		assertTrue(estadoExperto.esExperto());
	}

}
