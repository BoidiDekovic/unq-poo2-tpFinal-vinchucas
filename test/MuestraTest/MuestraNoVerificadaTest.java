package MuestraTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito.Then;

import Muestra.Muestra;
import Muestra.MuestraNoVerificada;
import Muestra.Opinion;
import Muestra.TipoOpinion;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;

public class MuestraNoVerificadaTest {

	private MuestraNoVerificada estadoMuestraNoVerificada;
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
		estadoMuestraNoVerificada = new MuestraNoVerificada();
	}
	
	@Test
	public void testAgregarOpinion() {
		estadoMuestraNoVerificada.agregarOpinion(opinion, muestra);
		verify(muestra, times(1)).agregarOpinionAMuestraNoVerificada(opinion);
		verify(muestra, times(1)).calcularVerificacion();
	}
	
}
