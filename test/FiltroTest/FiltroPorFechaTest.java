package FiltroTest;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Filtro.FiltroPorFecha;
import Filtro.FiltroPorFechaDeUltimaVotacion;

class FiltroPorFechaTest {
	
	FiltroPorFecha filtro;
	LocalDate fecha;
	
	@BeforeEach
	void setUp() throws Exception {
		fecha = LocalDate.of(2023, 6, 12);
		filtro = new FiltroPorFechaDeUltimaVotacion(fecha);
		
	}
	
	@Test
	void testCuandoSeCreaUnFiltroPorFechaEsteTieneUnaFecha() {
		assertEquals(fecha, filtro.getFecha());
	}
	
}
