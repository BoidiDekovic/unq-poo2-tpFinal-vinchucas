package UsuarioTest;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Muestra;
import Muestra.Opinion;
import Muestra.TipoOpinion;
import Sistema.Sistema;
import Usuario.Usuario;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;

class UsuarioTest {

	private Usuario usuario;
	private Muestra muestra;
	private UsuarioBasico estadoBasico;
	private UsuarioExperto estadoExperto;
	private Sistema sistema;

	@BeforeEach
	void setUp() throws Exception {
	
	usuario = new Usuario();
	muestra = mock(Muestra.class);
	estadoBasico = mock(UsuarioBasico.class);
	estadoExperto = mock(UsuarioExperto.class);
	usuario.setEstado(estadoBasico);
	sistema = mock(Sistema.class);
	
	}
	
	@Test
	public void testCuandoSeCreaUnUsuarioNoTieneMuestras() {
		assertTrue(usuario.getMuestras().isEmpty());
	}
	
	@Test
	public void testCuandoUnUsuarioSeCreaEsBasico() {
		assertTrue(usuario.getEstado() instanceof UsuarioBasico);
	}
	
	@Test
	public void testCuandoSeSeteaElEstadoAlUsuarioEsElEstadoEsperado() {
		assertEquals(estadoBasico, usuario.getEstado());
		usuario.setEstado(estadoExperto);
		assertEquals(estadoExperto, usuario.getEstado());
	}
	
	@Test
	public void testCuandoUnUsuarioAgregaUnaMuestraEstaSeAgregaEnSuListaDeMuestra() {
		usuario.agregarMuestra(muestra);
		assertEquals(1, usuario.getMuestras().size());
		assertTrue(usuario.getMuestras().contains(muestra));
	}
	
	@Test
	public void testCuandoUnUsuarioSeCreaSuListaDeOpinionesEnviadasEsVacia() {
		assertTrue(usuario.getOpinionesEnviadas().isEmpty());
	}
	
	@Test
	public void testCuandoUnUsuarioOpinaSobreUnaMuestraSeLePideAgregarLaOpinionALaMuestraYSeAgregaALasOpinionesEnviadas() {
		assertEquals(0, usuario.getOpinionesEnviadas().size());
		
		usuario.opinarSobreMuestra(muestra,TipoOpinion.CHINCHEFOLIADA, sistema);
		
		assertEquals(1, usuario.getOpinionesEnviadas().size());
		verify(muestra, times(1)).agregarOpinion(any(Opinion.class), eq(sistema));
	}
	
	@Test
	public void testCuandoUnUsuarioAgregaYEnviaUnaMuestraEstaSeEnviaAlSistemaYSeAgregaASuListaDeMuestras() {
		assertEquals(0, usuario.getMuestras().size());
		
		usuario.agregarYEnviar(muestra, sistema);
		
		assertEquals(1, usuario.getMuestras().size());
		verify(sistema, times(1)).agregarMuestra(muestra);
	}
	
	@Test
	public void testTieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDiasDaFalsoPorPocasMuestras() {
		assertTrue(usuario.getMuestras().size() < 10);
		assertFalse(usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias());
	}
	
	@Test
	public void testTieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDiasDaFalsoPorMuestrasAntiguas() {
		Muestra muestra11 = mock(Muestra.class);
		usuario.agregarMuestra(muestra11);
	
		when(muestra11.getFechaCreacion()).thenReturn(LocalDate.of(2023, 02, 18));
		List<Muestra> muestras = this.listaDeMuestrasMockCreadas(10);
		this.setFechasAMuestrasMock(LocalDate.now(), muestras);
		this.agregarMuestrasMockEnviadasAUsuario(muestras,usuario);
		
		assertTrue(usuario.getMuestras().size() > 10);
		assertFalse(usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias());
	}
	
	@Test
	public void testTieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDiasDaVerdadero() {
		
		List<Muestra> muestras = this.listaDeMuestrasMockCreadas(11);
		this.setFechasAMuestrasMock(LocalDate.now(), muestras);
		this.agregarMuestrasMockEnviadasAUsuario(muestras,usuario);
		assertTrue(usuario.getMuestras().size() > 10);
		assertTrue(usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias());
	}
	
	@Test
	public void testTieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDiasDaFalsoPorPocasOpiniones() {
		assertTrue(usuario.getOpinionesEnviadas().size() < 20);
		assertFalse(usuario.tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias());
	}
	
	@Test
	public void testTieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDiasDaFalsoPorOpinionesAntiguas() {
		List<Opinion> opiniones = this.listaDeOpinionesMockCreadas(21);
		this.setFechasAOpinionesMock(LocalDate.of(2023, 02, 18), opiniones);
		this.agregarOpinionesMockEnviadasAUsuario(opiniones, usuario);
		
		assertTrue(usuario.getOpinionesEnviadas().size() > 20);
		assertFalse(usuario.tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias());
	}
	
	@Test
	public void testTieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDiasDaVerdadero() {
		List<Opinion> opiniones = this.listaDeOpinionesMockCreadas(21);
		this.setFechasAOpinionesMock(LocalDate.now(), opiniones);
		this.agregarOpinionesMockEnviadasAUsuario(opiniones, usuario);
		
		assertTrue(usuario.getOpinionesEnviadas().size() > 20);
		assertTrue(usuario.tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias());
	}
	
	public List<Opinion> listaDeOpinionesMockCreadas(int cantDeOpiniones) {
		List<Opinion> lista = new ArrayList<Opinion>();
		for (int i = 0; i < cantDeOpiniones; i++) {
			lista.add(mock(Opinion.class));
		}
		return lista;
	}
	
	public void setFechasAOpinionesMock(LocalDate fecha, List<Opinion> opiniones) {
		for (Opinion opinion : opiniones) {
			when(opinion.getFechaDeEnvio()).thenReturn(fecha);
		}
	}
	
	public void agregarOpinionesMockEnviadasAUsuario(List<Opinion> opiniones, Usuario usuario) {
		for (Opinion opinion : opiniones) {
			usuario.agregarOpinionEnviada(opinion);
		}
	}

	public List<Muestra> listaDeMuestrasMockCreadas(int cantDeMuestras) {
		List<Muestra> lista = new ArrayList<Muestra>();
		for (int i = 0; i < cantDeMuestras; i++) {
			lista.add(mock(Muestra.class));
		}
		return lista;
	}
	
	public void setFechasAMuestrasMock(LocalDate fecha, List<Muestra> muestras) {
		for (Muestra muestra : muestras) {
			
			when(muestra.getFechaCreacion()).thenReturn(fecha);
		}
	}
	
	public void agregarMuestrasMockEnviadasAUsuario(List<Muestra> muestras, Usuario usuario) {
		for (Muestra muestra : muestras) {
			usuario.agregarMuestra(muestra);
		}
	}
	
	@Test
	public void testCuandoAUnUsuarioSeLePideCalcularSuCategoriaEsteSeLoDelegaASuEstado() {
		usuario.calcularCategoria();
		
		verify(estadoBasico, times(1)).calcularCategoria(usuario);
	}
	
	@Test
	public void testCuandoUnUsuarioTieneUnaMuestraEsVerdaderoQueLaTiene() {
		usuario.agregarMuestra(muestra);
		assertTrue(usuario.tieneMuestra(muestra));
	}
	
	@Test
	public void testCuandoUnUsuarioNoTieneUnaMuestraEsFalsoQueLaTiene() {
		assertFalse(usuario.tieneMuestra(muestra));
	}

}
