package UsuarioTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
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
import Usuario.Usuario;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;

class UsuarioTest {

	private Usuario usuario;
	private Muestra muestra;
	private Opinion opinion;
	private UsuarioBasico estadoBasico;
	private UsuarioExperto estadoExperto;
	

	@BeforeEach
	void setUp() throws Exception {
	
	usuario = new Usuario();
	muestra = mock(Muestra.class);
	opinion = mock(Opinion.class);
	estadoBasico = mock(UsuarioBasico.class);
	estadoExperto = mock(UsuarioExperto.class);
	usuario.setEstado(estadoBasico);
	
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
		assertEquals(1 , usuario.getMuestras().size());
		assertTrue(usuario.getMuestras().contains(muestra));
	}
	
	@Test
	public void testCuandoUnUsuarioSeCreaSuListaDeOpinionesEnviadasEsVacia() {
		assertTrue(usuario.getOpinionesEnviadas().isEmpty());
	}
	
	@Test
	public void testCuandoUnUsuarioOpinaSobreUnaMuestraSeLeDelegaAlEstado() {
		usuario.opinarSobreMuestra(muestra,TipoOpinion.CHINCHEFOLIADA);
		verify(estadoBasico, times(1)).opinarSobreMuestra(muestra, TipoOpinion.CHINCHEFOLIADA, usuario);
	}
	
	@Test
	public void testCuandoUnUsuarioAgregaUnaOpinionSeAgregaASuListaDeOpinionesEnviadas() {
		usuario.agregarOpinionEnviada(opinion);
		assertEquals(1, usuario.getOpinionesEnviadas().size());
		assertTrue(usuario.getOpinionesEnviadas().contains(opinion));
	}
	
	@Test
	public void testCuandoUnUsuarioAgregaYEnviaUnaMuestraSeDelegaASuEstadoLaAccion() {
		usuario.agregarYEnviar(muestra);
		verify(estadoBasico, times(1)).agregarYEnviar(muestra, usuario);
	}
	
	@Test
	public void testTieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDiasDaFalsoPorPocasMuestras() {
		assertTrue(usuario.getMuestras().size() < 10);
		assertFalse(usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias());
	}
	
	@Test
	public void testTieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDiasDaFalsoPorMuestrasAntiguas() {
		Muestra muestra1, muestra2, muestra3, muestra4, muestra5, muestra6, muestra7, muestra8, muestra9, muestra10, muestra11;
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		muestra5 = mock(Muestra.class);
		muestra6 = mock(Muestra.class);
		muestra7 = mock(Muestra.class);
		muestra8 = mock(Muestra.class);
		muestra9 = mock(Muestra.class);
		muestra10 = mock(Muestra.class);
		muestra11 = mock(Muestra.class);
		
		usuario.agregarMuestra(muestra1);
		usuario.agregarMuestra(muestra2);
		usuario.agregarMuestra(muestra3);
		usuario.agregarMuestra(muestra4);
		usuario.agregarMuestra(muestra5);
		usuario.agregarMuestra(muestra6);
		usuario.agregarMuestra(muestra7);
		usuario.agregarMuestra(muestra8);
		usuario.agregarMuestra(muestra9);
		usuario.agregarMuestra(muestra10);
		usuario.agregarMuestra(muestra11);
		
		when(muestra1.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra2.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra3.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra4.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra5.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra6.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra7.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra8.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra9.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra10.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra11.getFechaCreacion()).thenReturn(LocalDate.of(2023, 02, 18));
		
		assertTrue(usuario.getMuestras().size() > 10);
		assertFalse(usuario.tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias());
	}
	
	@Test
	public void testTieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDiasDaVerdadero() {
		Muestra muestra1, muestra2, muestra3, muestra4, muestra5, muestra6, muestra7, muestra8, muestra9, muestra10, muestra11;
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		muestra5 = mock(Muestra.class);
		muestra6 = mock(Muestra.class);
		muestra7 = mock(Muestra.class);
		muestra8 = mock(Muestra.class);
		muestra9 = mock(Muestra.class);
		muestra10 = mock(Muestra.class);
		muestra11 = mock(Muestra.class);
		
		usuario.agregarMuestra(muestra1);
		usuario.agregarMuestra(muestra2);
		usuario.agregarMuestra(muestra3);
		usuario.agregarMuestra(muestra4);
		usuario.agregarMuestra(muestra5);
		usuario.agregarMuestra(muestra6);
		usuario.agregarMuestra(muestra7);
		usuario.agregarMuestra(muestra8);
		usuario.agregarMuestra(muestra9);
		usuario.agregarMuestra(muestra10);
		usuario.agregarMuestra(muestra11);
		
		when(muestra1.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra2.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra3.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra4.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra5.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra6.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra7.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra8.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra9.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra10.getFechaCreacion()).thenReturn(LocalDate.now());
		when(muestra11.getFechaCreacion()).thenReturn(LocalDate.now());
		
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
	
	@Test
	public void testCuandoUnUsuarioBasicoCalculaSuCategoriaYNoCumpleLosRequisitosNoCambiaDeEstado() {
		usuario.calcularCategoria();
		// falta hacer
	}
	
	
}
