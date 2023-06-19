package MuestraTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.EstadoMuestra;
import Muestra.Muestra;
import Muestra.MuestraBasicos;
import Muestra.MuestraVerificada;
import Muestra.Opinion;
import Muestra.TipoOpinion;
import Sistema.Sistema;
import Usuario.Usuario;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;
import ZonaDeCobertura.Ubicacion;

class MuestaTest {	
	
	private Muestra muestra;
	private String foto;
	private LocalDate fechaCreacion;
	private LocalDate fechaUltimaVotacion;
	private Sistema sistema;
	private TipoOpinion posibleVinchuca;
	private Usuario usuarioAutor, autorOpinion;
	private MuestraBasicos estadoMuestra;
	private Ubicacion ubicacion;
	private Opinion opinion, opinion1, opinion2;

	@BeforeEach
	void setUp() throws Exception {
		
		// DOC
		foto = "URL: 123";
		fechaCreacion = LocalDate.of(2023, 02, 10);
		fechaUltimaVotacion = LocalDate.of(2023, 03, 10);
		sistema = mock(Sistema.class);
		posibleVinchuca = TipoOpinion.VINCHUCAGUASAYANA;
		usuarioAutor = mock(Usuario.class);
		autorOpinion = mock(Usuario.class);
		estadoMuestra = mock(MuestraBasicos.class);
		ubicacion = mock(Ubicacion.class);
		opinion = mock(Opinion.class);
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		when(opinion.getUsuarioAutor()).thenReturn(autorOpinion);
		
		// SUT
		muestra = new Muestra(posibleVinchuca, foto, ubicacion, usuarioAutor);
		muestra.setEstadoMuestra(estadoMuestra);
	}
	
	@Test
	public void testCuandoUnaMuestraSeCreaLaFechaDeCreacionEsCuandoSeCreo() {
		assertEquals(LocalDate.now(), muestra.getFechaCreacion());
	}
	
	@Test
	public void testCuandoUnaMuestraSeCreaTieneUnUsuarioAutor() {
		assertEquals(usuarioAutor, muestra.getUsuarioAutor());
	}
	
	@Test
	public void testCuandoUnaMuestraSeCreaNoTieneUnaFechaDeUltimaVotacion() {
		assertEquals(null, muestra.getFechaUltimaVotacion());
	}
	
	@Test
	public void testCuandoUnaMuestraIniciaNoTieneOpiniones() {
		assertTrue(muestra.getOpiniones().isEmpty());
	}
	
	@Test
	public void testCuandoUnaMuestraTieneUbicacion() {
		assertEquals(ubicacion, muestra.getUbicacion());
	}
	
	@Test
	public void testCuandoUnaMuestraTieneUnaPosibleVinchuca() {
		assertEquals(posibleVinchuca, muestra.getVinchucaSegunAutor());
	}
	
	@Test
	public void testCuandoUnaMuestraTieneUnEstadoMuestra() {
		assertEquals(estadoMuestra, muestra.getEstadoMuestra());
	}
	
	@Test
	public void testCuandoUnaMuestraSeteaSuEstadoCambiaSuEstado() {
		MuestraVerificada estadoVerificada = mock(MuestraVerificada.class);
		assertEquals(estadoMuestra, muestra.getEstadoMuestra());
		muestra.setEstadoMuestra(estadoVerificada);
		assertEquals(estadoVerificada, muestra.getEstadoMuestra());
	}
	
	@Test
	public void testCuandoUnaMuestraTieneUnaUbicacion() {
		assertEquals(ubicacion, muestra.getUbicacion());
	}
	
	@Test
	public void testCuandoUnaMuestraAgregaUnaOpinionSeAgregaALaListaDeOpinionesYSeSeteaLaFechaDeUltimaVotacion() {
		assertEquals(null, muestra.getFechaUltimaVotacion());
		LocalDate fecha = LocalDate.of(2021, 4, 10);
		when(opinion.getFechaDeEnvio()).thenReturn(fecha);
		muestra.agregarOpinionAMuestra(opinion);
		assertEquals(fecha, muestra.getFechaUltimaVotacion());
	}
	
	@Test
	public void testCuandoUnaMuestraSeLePideAgregarUnaOpinionSeLeMandaASuEstado() {
		muestra.agregarOpinion(opinion);
		verify(estadoMuestra, times(1)).agregarOpinion(opinion, muestra);
	}
	
	@Test
	public void testUnaMuestraTieneSuResultadoActual() {
		muestra.resultadoActual();
		verify(estadoMuestra, times(1)).resultadoActual(muestra);
	}
	
	@Test
	public void testCuandoUnaMuestraCalculaSuEstadoSeLeDelegaAlEstado() {
		muestra.calcularEstadoMuestra();
		verify(estadoMuestra, times(1)).calcularEstadoMuestra(muestra);
	}
	
	@Test
	public void testCuandoSeLeEnviaAlSistemaUnaMuestraLoAgregaASuListaDeMuestras() {
		muestra.enviarseASistema(sistema);
		verify(sistema, times(1)).agregarMuestra(muestra);
	}
	
	@Test
	public void testCuandoUnaMuestraTieneUnaOpinionDeExpertoEsVerdaderoQueLaTiene() {
		when(opinion1.esOpinionDeExperto()).thenReturn(true);
		muestra.agregarOpinionAMuestra(opinion1);
		assertTrue(muestra.esMuestraConOpinionDeExperto());
	}
	
	@Test
	public void testCuandoUnaMuestraNoTieneUnaOpinionDeExpertoEsFalsoQueLaTiene() {
		when(opinion1.esOpinionDeExperto()).thenReturn(false);
		muestra.agregarOpinionAMuestra(opinion1);
		assertFalse(muestra.esMuestraConOpinionDeExperto());
	}
	
	@Test
	public void testCuandoUnaMuestraTieneUnaOpinionDeUnUsuarioEsVerdaderoQueLoTiene() {
		when(opinion1.getUsuarioAutor()).thenReturn(usuarioAutor);
		muestra.agregarOpinionAMuestra(opinion1);
		assertTrue(muestra.esMuestraConOpinionDe(usuarioAutor));
	}	
	
	@Test
	public void testCuandoUnaMuestraNoTieneUnaOpinionDeUnUsuarioEsFalsoQueLoTiene() {
		when(opinion1.getUsuarioAutor()).thenReturn(mock(Usuario.class));
		muestra.agregarOpinionAMuestra(opinion1);
		assertFalse(muestra.esMuestraConOpinionDe(usuarioAutor));
	}	
	
	@Test
	public void testCuandoSeAgregaOpinionDeUsuarioQueTieneLaMismaMuestraQueOpinaSeLanzaExepcion(){
		when(autorOpinion.tieneMuestra(muestra)).thenReturn(true);
		assertThrows(UnsupportedOperationException.class, 
				() -> {muestra.agregarOpinion(opinion);}
				, "Un usuario no puede opinar sobre sus propias muestras");
	}
	
	@Test
	public void testCuandoSeAgregaOpinionDeUsuarioQueYaOpinoLaMuestraSeLanzaExepcion(){
		when(autorOpinion.tieneMuestra(muestra)).thenReturn(false);
		muestra.agregarOpinionAMuestra(opinion);;
		assertThrows(UnsupportedOperationException.class, 
				() -> {muestra.agregarOpinion(opinion);}
				, "Un usuario no puede opinar dos veces la misma muestra");
	}
	
}
