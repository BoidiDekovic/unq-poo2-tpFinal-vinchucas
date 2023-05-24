package MuestraTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.EstadoMuestra;
import Muestra.Muestra;
import Muestra.Opinion;
import Muestra.TipoOpinion;
import Sistema.Sistema;
import Usuario.Usuario;
import ZonaDeCobertura.Ubicacion;

class MuestaTest {	
	
	private Muestra muestra;
	private String foto;
	private LocalDate fechaCreacion;
	private LocalDate fechaUltimaVotacion;
	private Sistema sistema;
	private TipoOpinion posibleVinchuca;
	private Usuario usuarioAutor;
	private EstadoMuestra estadoMuestra;
	private Ubicacion ubicacion;

	@BeforeEach
	void setUp() throws Exception {
		
		// DOC
		foto = "URL: 123";
		fechaCreacion = LocalDate.of(2023, 02, 10);
		fechaUltimaVotacion = LocalDate.of(2023, 03, 10);
		sistema = mock(Sistema.class);
		posibleVinchuca = TipoOpinion.VINCHUCAGUASAYANA;
		usuarioAutor = mock(Usuario.class);
		estadoMuestra = mock(EstadoMuestra.class);
		ubicacion = mock(Ubicacion.class);
		
		// SUT
		muestra = new Muestra(sistema, posibleVinchuca, foto, fechaCreacion, fechaUltimaVotacion, ubicacion, usuarioAutor, estadoMuestra);

	}
	
	@Test
	public void testCuandoUnaMuestraTieneUnaFechaDeCreacion() {
		assertEquals(fechaCreacion, muestra.getFechaCreacion());
	}
	
	@Test
	public void testCuandoUnaMuestraTieneUnaFechaDeUltimaVotacion() {
		assertEquals(fechaUltimaVotacion, muestra.getFechaUltimaVotacion());
	}
	
	@Test
	void testCuandoUnaMuestraIniciaNoTieneOpiniones() {
		assertTrue(muestra.getOpiniones().isEmpty());
	}
	
	@Test
	void testCuandoUnaMuestraTieneUbicacion() {
		assertEquals(ubicacion, muestra.getUbicacion());
	}
	
	@Test
	void testCuandoUnaMuestraTieneUnaPosibleVinchuca() {
		assertEquals(posibleVinchuca, muestra.getPosibleVinchuca());
	}
	
	@Test
	void testCuandoUnaMuestraTieneUnEstadoMuestra() {
		assertEquals(estadoMuestra, muestra.getEstadoMuestra());
	}
	
	@Test
	void testCuandoUnaMuestraTieneUnaUbicacion() {
		assertEquals(ubicacion, muestra.getUbicacion());
	}
	
	
	
	
	@Test
	void testCuandoUnaMuestraRecibeUnaOpinion() {
		Opinion opinion = mock(Opinion.class);
		muestra.agregarOpinion(opinion);
		assertEquals(1, muestra.getOpiniones().size());
		assertTrue(muestra.getOpiniones().contains(opinion));
	}

}
