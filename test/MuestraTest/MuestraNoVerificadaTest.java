package MuestraTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito.Then;

import Muestra.Muestra;
import Muestra.MuestraNoVerificada;
import Muestra.Opinion;
import Muestra.TipoOpinion;
import Usuario.Usuario;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;

public class MuestraNoVerificadaTest {

	private MuestraNoVerificada estadoMuestraNoVerificada;
	private Muestra muestra;
	private Opinion opinion;
	private List<Muestra> muestras;
	private Usuario usuario;
	private List<Opinion> opiniones;
	
	@BeforeEach
	public void setUp() {
		//DOC
		muestra = mock(Muestra.class);
		opinion = mock(Opinion.class);
		usuario = mock(Usuario.class);
		muestras = new ArrayList<>();
		when(usuario.getMuestras()).thenReturn(muestras);
		when(opinion.getUsuarioAutor()).thenReturn(usuario);
		//SUT
		estadoMuestraNoVerificada = new MuestraNoVerificada();
	}
	
	@Test
	public void testSeAgregaOpinionDeUsuarioBasicoEnUnaMuestraSinOpinionesDeExperto() {
		when(opinion.esOpinionDeExperto()).thenReturn(false);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(false);
		estadoMuestraNoVerificada.agregarOpinion(opinion, muestra);
		verify(muestra, times(1)).agregarOpinionAMuestra(opinion);
		verify(muestra, times(1)).calcularVerificacion();
	}
	
	@Test
	public void testSeAgregaOpinionDeUsuarioBasicoEnUnaMuestraConOpinionesDeExperto() {
		when(opinion.esOpinionDeExperto()).thenReturn(false);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(true);
		
		assertThrows(UnsupportedOperationException.class, 
				() -> {estadoMuestraNoVerificada.agregarOpinion(opinion, muestra);}
				, "Un usuario basico no puede opinar una muestra que fue opinada por un experto");
	}
	
	@Test
	public void testSeAgregaOpinionDeUsuarioExpertoEnUnaMuestraConOpinionesDeExperto(){
		when(opinion.esOpinionDeExperto()).thenReturn(true);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(true);
		estadoMuestraNoVerificada.agregarOpinion(opinion, muestra);
		verify(muestra, times(1)).agregarOpinionAMuestra(opinion);
		verify(muestra, times(1)).calcularVerificacion();
	}
	
	@Test
	public void testSeAgregaOpinionDeUsuarioQueTieneLaMismaMuestraQueOpina(){
		when(opinion.esOpinionDeExperto()).thenReturn(false);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(false);
		when(usuario.getMuestras()).thenReturn(Arrays.asList(muestra));
		
		assertThrows(UnsupportedOperationException.class, 
				() -> {estadoMuestraNoVerificada.agregarOpinion(opinion, muestra);}
				, "Un usuario no puede opinar sobre sus propias muestras");
	}
	
	@Test
	public void testSeAgregaOpinionDeUsuarioQueYaOpinoLaMuestra(){
		when(opinion.esOpinionDeExperto()).thenReturn(false);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(false);
		when(muestra.esMuestraConOpinionDe(usuario)).thenReturn(true);
		
		assertThrows(UnsupportedOperationException.class, 
				() -> {estadoMuestraNoVerificada.agregarOpinion(opinion, muestra);}
				, "Un usuario no puede opinar dos veces la misma muestra");
	}
	
	
	
}
