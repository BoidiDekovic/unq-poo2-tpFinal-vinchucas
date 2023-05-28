package UsuarioTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Muestra;
import Muestra.Opinion;
import Usuario.EstadoUsuario;
import Usuario.Usuario;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;

public class EstadoUsuarioTest {
	
	private Usuario usuario;
	private Muestra muestra;
	private EstadoUsuario estadoUsuario;
	

	@BeforeEach
	void setUp() throws Exception {
	
	usuario = mock(Usuario.class);
	muestra = mock(Muestra.class);
	estadoUsuario = new UsuarioBasico();
	}
	
	@Test
	public void testCuandoUnUsuarioAgregaYEnviaUnaMuestraEstaSeAgregaAlSistema() {
		estadoUsuario.agregarYEnviar(muestra, usuario);
		
		verify(muestra,times(1)).enviarseASistema();
		verify(usuario,times(1)).agregarMuestra(muestra);
	}

}
