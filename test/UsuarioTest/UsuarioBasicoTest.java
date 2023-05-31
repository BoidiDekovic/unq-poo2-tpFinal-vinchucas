package UsuarioTest;

import static org.junit.Assert.assertFalse;
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

public class UsuarioBasicoTest {
	
	private Usuario usuario;
	private Muestra muestra;
	private UsuarioBasico estadoBasico;
	

	@BeforeEach
	void setUp() throws Exception {
	
	usuario = mock(Usuario.class);
	muestra = mock(Muestra.class);
	estadoBasico = new UsuarioBasico();
	
	}
	
	@Test 
	public void testCuandoUnUsuarioOpinaSobreUnaMuestraEsteLePidreAgregarseAsusOpiniones() {
		estadoBasico.opinarSobreMuestra(muestra, TipoOpinion.CHINCHEFOLIADA, usuario);
		verify(muestra,times(1)).agregarOpinion(any(Opinion.class));
		verify(muestra,never()).agregarOpinionAMuestra(any());
		verify(usuario, times(1)).agregarOpinionEnviada(any(Opinion.class));
	}
	
	@Test
	public void testCuandoSeLePreguntaAUnUsuarioBasicoSiEsExpertoDaFalso() {
		assertFalse(estadoBasico.esExperto());
	}

}
