package MuestraTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Muestra;
import Muestra.MuestraVerificada;
import Muestra.Opinion;
import Muestra.TipoOpinion;
import Sistema.Sistema;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;

public class MuestraVerificadaTest {
	
	private Sistema sistema;
	private MuestraVerificada estadoMuestraVerificado;
	private Muestra muestra;
	private Opinion opinion;
	private UsuarioExperto estadoUsuarioExperto;
	private UsuarioBasico estadoUsuarioBasico;
	private TipoOpinion tipoOpinion;
	
	@BeforeEach
	public void setUp() {
		//DOC
		sistema = mock(Sistema.class);
		muestra = mock(Muestra.class);
		opinion = mock(Opinion.class);
		estadoUsuarioBasico = mock(UsuarioBasico.class);
		estadoUsuarioExperto = mock(UsuarioExperto.class);
		when(estadoUsuarioBasico.esExperto()).thenReturn(false);
		when(estadoUsuarioExperto.esExperto()).thenReturn(true);
		//SUT
		estadoMuestraVerificado = new MuestraVerificada(tipoOpinion);
	}
	
	@Test
	public void testAgregarOpinion() {
		assertThrows(UnsupportedOperationException.class, 
				() -> {estadoMuestraVerificado.agregarOpinion(opinion, muestra, sistema);}
				, "No se pueden agregar opiniones a una muestra verificada");
	}
	@Test
	public void testCuandoUnaMuestraVerificadaSeLePideCambiarDeEstadoNoCambia() {
		estadoMuestraVerificado.calcularEstadoMuestra(muestra, sistema);
		verify(muestra, never()).setEstadoMuestra(any());
	}
	@Test
	public void testCuandoUnaMuestraVerificadaSeLePideSuResultadoActualEstaDevuelveElResultado() {
		assertEquals(tipoOpinion ,estadoMuestraVerificado.resultadoActual(muestra));
	}
	
}
