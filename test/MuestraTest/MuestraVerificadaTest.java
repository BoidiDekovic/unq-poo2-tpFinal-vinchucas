package MuestraTest;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Muestra;
import Muestra.MuestraNoVerificada;
import Muestra.MuestraVerificada;
import Muestra.Opinion;
import Muestra.TipoOpinion;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;

public class MuestraVerificadaTest {
	
	private MuestraVerificada estadoMuestraVerificado;
	private Muestra muestra;
	private Opinion opinion , opinion2, opinion3;
	private UsuarioExperto estadoUsuarioExperto;
	private UsuarioBasico estadoUsuarioBasico;
	
	@BeforeEach
	public void setUp() {
		//DOC
		muestra = mock(Muestra.class);
		opinion = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		opinion3 = mock(Opinion.class);
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
			assertEquals("No se puede agregar opinion a una muestra verificada", e.getMessage());
		}
	}
	
}
