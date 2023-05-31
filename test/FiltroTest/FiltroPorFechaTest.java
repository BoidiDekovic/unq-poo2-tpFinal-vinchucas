package FiltroTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import Filtro.FiltroPorFecha;
import Filtro.FiltroPorFechaDeUltimaVotacion;
import Sistema.Sistema;

class FiltroPorFechaTest {
	
	FiltroPorFecha filtro;
	LocalDate fecha;
	Sistema sistema;
	
	@BeforeEach
	void setUp() throws Exception {
		sistema = mock(Sistema.class);
		fecha = LocalDate.of(2023, 6, 12);
		filtro = new FiltroPorFechaDeUltimaVotacion(fecha, sistema);
		
	}

	@Test
	void testCuandoSeCreaUnFiltroPorFechaEsteTieneUnSistema() {
		assertEquals(sistema , filtro.getSistema());
	}
	@Test
	void testCuandoSeCreaUnFiltroPorFechaEsteTieneUnaFecha() {
		assertEquals(fecha , filtro.getFecha());
	}
}
