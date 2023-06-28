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
	public void setUp() throws Exception {
		fecha = LocalDate.of(2023, 6, 12);
		filtro = new FiltroPorFechaDeUltimaVotacion(fecha);
		
	}
	
	@Test
	public void testCuandoSeCreaUnFiltroPorFechaEsteTieneUnaFecha() {
		assertEquals(fecha, filtro.getFecha());
	}
	
	@Test
	public void testCuandoSeSeteaUnaFechaEnElFiltroCambia() {
		assertEquals(fecha, filtro.getFecha());
		
		LocalDate fecha1 = LocalDate.of(2023, 7, 12);
		filtro.setFecha(fecha1);
		
		assertEquals(fecha1, filtro.getFecha());
	}
	
}
