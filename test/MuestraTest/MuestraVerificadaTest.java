package MuestraTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Muestra;
import Muestra.MuestraVerificada;
import Muestra.Opinion;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;

public class MuestraVerificadaTest {
	
	private MuestraVerificada estadoMuestraVerificado;
	private Muestra muestra;
	private Opinion opinion;
	private UsuarioExperto estadoUsuarioExperto;
	private UsuarioBasico estadoUsuarioBasico;
	
	@BeforeEach
	public void setUp() {
		//DOC
		muestra = mock(Muestra.class);
		opinion = mock(Opinion.class);
		estadoUsuarioBasico = mock(UsuarioBasico.class);
		estadoUsuarioExperto = mock(UsuarioExperto.class);
		when(estadoUsuarioBasico.esExperto()).thenReturn(false);
		when(estadoUsuarioExperto.esExperto()).thenReturn(true);
		//SUT
		estadoMuestraVerificado = new MuestraVerificada();
	}
	
	@Test
	public void testAgregarOpinion() {
		try {
			estadoMuestraVerificado.agregarOpinion(opinion, muestra);
		} catch (Exception e) {
			assertEquals("No se pueden agregar opiniones a una muestra verificada", e.getMessage());
		}
	}
	
}
