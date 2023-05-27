package UsuarioTest;

import static org.junit.jupiter.api.Assertions.*;
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

class UsuarioTest {

	private Usuario usuario;
	private Muestra muestra;
	private Opinion opinion;
	private UsuarioBasico estadoBasico;
	private UsuarioExperto estadoExperto;
	

	@BeforeEach
	void setUp() throws Exception {
	
	usuario = new Usuario();
	muestra = mock(Muestra.class);
	opinion = mock(Opinion.class);
	estadoBasico = mock(UsuarioBasico.class);
	estadoExperto = mock(UsuarioExperto.class);
	
	}
	@Test
	public void testCuandoSeCreaUnUsuarioNoTieneMuestras() {
		assertTrue(usuario.getMuestras().isEmpty());
	}
	
	@Test
	public void testCuandoUnUsuarioSeCreaEsBasico() {
		assertTrue(usuario.getEstado() instanceof UsuarioBasico);
	}
	
	@Test
	public void testCuandoUnUsuarioAgregaUnaMuestraEstaSeAgregaEnSuListaDeMuestra() {
		usuario.agregarMuestra(muestra);
		assertEquals(1 , usuario.getMuestras().size());
		assertTrue(usuario.getMuestras().contains(muestra));
	}
	@Test
	public void testCuandoUnUsuarioSeCreaSuListaDeOpinionesEnviadasEsVacia() {
		assertTrue(usuario.getOpinionesEnviadas().isEmpty());
	}
	
	@Test
	public void testCuandoUnUsuarioEnviaUnaMuestraSeAgregaAsuListaDeOpinionesEnviadas() {
		usuario.opinarSobreMuestra(muestra,TipoOpinion.CHINCHEFOLIADA);
		assertEquals(1, usuario.getOpinionesEnviadas().size());
	}
	
	@Test
	public void testCuandoUnUsuarioAgregaYEnviaUnaMuestraEstaSeAgregaAlSistema() {
		usuario.agregarYEnviar(muestra);
		
		verify(muestra,times(1)).enviarASistema();
		assertEquals(1 , usuario.getMuestras().size());
		assertTrue(usuario.getMuestras().contains(muestra));
		
	}
	
	@Test 
	public void testCuandoUnUsuarioOpinaSobreUnaMuestraEsteLePidreAgregarseAsusOpiniones() {
		usuario.opinarSobreMuestra(muestra, TipoOpinion.CHINCHEFOLIADA);
		verify(muestra,times(1)).agregarOpinion(any(Opinion.class));
		verify(muestra,never()).agregarOpinionAMuestraNoVerificada(any());
	}
	
	
}
