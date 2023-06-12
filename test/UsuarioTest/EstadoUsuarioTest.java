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
import Sistema.Sistema;
import Usuario.EstadoUsuario;
import Usuario.Usuario;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;

public class EstadoUsuarioTest {
	
	private Usuario usuario;
	private Muestra muestra;
	private EstadoUsuario estadoUsuario;
	private Sistema sistema;

	@BeforeEach
	void setUp() throws Exception {
	
	sistema = mock(Sistema.class);
	usuario = mock(Usuario.class);
	muestra = mock(Muestra.class);
	estadoUsuario = new UsuarioBasico();
	}
	
	@Test
	public void testCuandoUnUsuarioAgregaYEnviaUnaMuestraEstaSeAgregaAlSistema() {
		estadoUsuario.agregarYEnviar(muestra, usuario, sistema);
		
		verify(muestra,times(1)).enviarseASistema(sistema);
		verify(usuario,times(1)).agregarMuestra(muestra);
	}

}
