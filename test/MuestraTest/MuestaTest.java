package MuestraTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.EstadoMuestra;
import Muestra.Muestra;
import Muestra.MuestraNoVerificada;
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
	private Usuario usuarioAutor;
	private MuestraNoVerificada estadoMuestraNoVerificada;
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
		estadoMuestraNoVerificada = mock(MuestraNoVerificada.class);
		ubicacion = mock(Ubicacion.class);
		opinion = mock(Opinion.class);
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		
		// SUT
		muestra = new Muestra(sistema, posibleVinchuca, foto, ubicacion, usuarioAutor, estadoMuestraNoVerificada);

	}
	
	@Test
	public void testCuandoUnaMuestraSeCreaLaFechaDeCreacionEsCuandoSeCreo() {
		assertEquals(LocalDate.now(), muestra.getFechaCreacion());
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
		assertEquals(estadoMuestraNoVerificada, muestra.getEstadoMuestra());
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
		verify(estadoMuestraNoVerificada, times(1)).agregarOpinion(opinion, muestra);
	}
	
	@Test
	public void testUnaMuestraTieneSuResultadoActual() {
		muestra.resultadoActual();
		verify(estadoMuestraNoVerificada, times(1)).resultadoActual(muestra);
	}

	@Test
	public void testCuandoCalculoLaVerificacionYNoHayOpinionesLaMuestraNoEstaVerificada() {
		
		assertTrue(muestra.getOpiniones().isEmpty());
		assertEquals(estadoMuestraNoVerificada, muestra.getEstadoMuestra());
		
		muestra.calcularVerificacion();
		
		assertEquals(estadoMuestraNoVerificada, muestra.getEstadoMuestra());
	}
	
	@Test
	public void testCuandoCalculoLaVerificacionYHayDosExpertosLaMuestraEstaVerificada() {
		
		
		UsuarioExperto estado1 = mock(UsuarioExperto.class);
		
		when(opinion1.getEstadoAutor()).thenReturn(estado1);
		when(opinion2.getEstadoAutor()).thenReturn(estado1);
		when(opinion1.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(estadoMuestraNoVerificada.resultadoActual(muestra)).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(estado1.esExperto()).thenReturn(true);
		
		assertTrue(muestra.getOpiniones().isEmpty());
		assertEquals(estadoMuestraNoVerificada, muestra.getEstadoMuestra());
		
		muestra.agregarOpinionAMuestra(opinion1);
		muestra.agregarOpinionAMuestra(opinion2);
		
		muestra.calcularVerificacion();
		
		assertTrue(muestra.getEstadoMuestra() instanceof MuestraVerificada);
	}
	
	@Test
	public void testCuandoCalculoLaVerificacionYNoHayUsuariosExpertosLaMuestraNoEstaVerificada() {
		
		UsuarioBasico estado1 = mock(UsuarioBasico.class);
		
		when(opinion1.getEstadoAutor()).thenReturn(estado1);
		when(opinion2.getEstadoAutor()).thenReturn(estado1);
		when(opinion1.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(estadoMuestraNoVerificada.resultadoActual(muestra)).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(estado1.esExperto()).thenReturn(false);
		
		assertTrue(muestra.getOpiniones().isEmpty());
		assertEquals(estadoMuestraNoVerificada, muestra.getEstadoMuestra());
		
		muestra.agregarOpinionAMuestra(opinion1);
		muestra.agregarOpinionAMuestra(opinion2);
		
		muestra.calcularVerificacion();
		
		assertTrue(muestra.getEstadoMuestra() instanceof MuestraNoVerificada);
	}
	
	@Test
	public void testCuandoCalculoLaVerificacionYElResultadoEsEmpateLaMuestraNoEstaVerificada() {
		
		UsuarioExperto estado1 = mock(UsuarioExperto.class);
		
		when(opinion1.getEstadoAutor()).thenReturn(estado1);
		when(opinion2.getEstadoAutor()).thenReturn(estado1);
		when(opinion1.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(estadoMuestraNoVerificada.resultadoActual(muestra)).thenReturn(TipoOpinion.NODEFINIDO);
		when(estado1.esExperto()).thenReturn(true);
		
		assertTrue(muestra.getOpiniones().isEmpty());
		assertEquals(estadoMuestraNoVerificada, muestra.getEstadoMuestra());
		
		muestra.agregarOpinionAMuestra(opinion1);
		muestra.agregarOpinionAMuestra(opinion2);
		
		muestra.calcularVerificacion();
		
		assertTrue(muestra.getEstadoMuestra() instanceof MuestraNoVerificada);
	}
	
	@Test
	public void testCuandoSeLeEnviaAlSistemaUnaMuestraLoAgregaASuListaDeMuestras() {
		muestra.enviarseASistema();
		verify(sistema, times(1)).agregarMuestra(muestra);
	}
	
	
	
	
	
	
}
