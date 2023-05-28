package UsuarioTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import Usuario.UsuarioBasico;
import Usuario.UsuarioEspecialista;

class EspecialistaTest {
	private UsuarioEspecialista especialista;
	
	
	@BeforeEach
	void setUp() throws Exception {
		especialista = new UsuarioEspecialista();
	}

	@Test
	public void testCuandoUnEspecialistaInciaEsExperto() {
		assertTrue(especialista.getEstado().esExperto());
	}

	@Test 
	public void testUnEspecialistaNoCambiaDeEstado() {
		assertTrue(especialista.getEstado().esExperto());
		this.especialista.setEstado(mock(UsuarioBasico.class));
		assertTrue(especialista.getEstado().esExperto());
	
	}
}
