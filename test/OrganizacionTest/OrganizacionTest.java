package OrganizacionTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import Muestra.Muestra;
import Organizacion.FuncionalidadExterna;
import Organizacion.Organizacion;
import Organizacion.TipoOrganizacion;
import ZonaDeCobertura.Ubicacion;
import ZonaDeCobertura.ZonaDeCobertura;

class OrganizacionTest {

	
	private ZonaDeCobertura zonaDeCobertura;
	private Muestra muestra;
	private Organizacion organizacion;
	private Ubicacion ubicacion;
	private FuncionalidadExterna funcionalidad1, funcionalidad2; 
	@BeforeEach
	public void setUp() throws Exception {
		zonaDeCobertura = mock(ZonaDeCobertura.class);
		muestra = mock(Muestra.class);
		ubicacion = mock(Ubicacion.class);
		funcionalidad2 = mock(FuncionalidadExterna.class);
		funcionalidad1 = mock(FuncionalidadExterna.class);
		organizacion = new Organizacion(10,TipoOrganizacion.CULTURAL,ubicacion);
		
	}

	@Test
	public void testCuandoUnaOrganizacionSeCreaTieneUnaUbicacion() {
		assertEquals(ubicacion , organizacion.getUbicacion());
	}

	@Test 
	public void testCuandoUnaOrganizacionSeCreaNoTieneUnaFuncionalidadParaNuevaMuestra(){
		assertEquals(null , organizacion.getFuncionalidadNuevaMuestra());
	}
	@Test 
	public void testCuandoUnaOrganizacionSeCreaNoTieneUnaFuncionalidadParaMuestraVerificada(){
		assertEquals(null , organizacion.getFuncionalidadMuestraVerificada());
	}
	
	@Test 
	public void testCuandoUnaOrganizacionSeteaUnaFuncionalidadParaNuevaMuestraSeSetea() {
		organizacion.setFuncionalidadNuevaMuestra(funcionalidad1);
		assertEquals(funcionalidad1 , organizacion.getFuncionalidadNuevaMuestra());
		organizacion.setFuncionalidadNuevaMuestra(funcionalidad2);
		assertEquals(funcionalidad2 , organizacion.getFuncionalidadNuevaMuestra());
	}
	@Test 
	public void testCuandoUnaOrganizacionSeteaUnaFuncionalidadParaMuestraVerificadaSeSetea() {
		organizacion.setFuncionalidadMuestraVerificada(funcionalidad1);
		assertEquals(funcionalidad1 , organizacion.getFuncionalidadMuestraVerificada());
		organizacion.setFuncionalidadMuestraVerificada(funcionalidad2);
		assertEquals(funcionalidad2 , organizacion.getFuncionalidadMuestraVerificada());
	}
	@Test
	public void testCuandoActualizaNuevaMuestraYNoHayFuncionalidadNoPasaNada() {
		organizacion.actualizarNuevaMuestra(zonaDeCobertura, muestra);
		verify(funcionalidad1, never()).nuevoEvento(organizacion, zonaDeCobertura, muestra);
	}
	@Test
	public void testCuandoActualizaMuestraVerificadaYNoHayFuncionalidadNoPasaNada() {
		organizacion.actualizarMuestraVerificada(zonaDeCobertura, muestra);
		verify(funcionalidad1, never()).nuevoEvento(organizacion, zonaDeCobertura, muestra);
		
	}
	@Test
	public void testCuandoActualizaNuevaMuestraSeEjecutaLaFuncionalidadCorrespondiente() {
		organizacion.setFuncionalidadNuevaMuestra(funcionalidad1);
		organizacion.actualizarNuevaMuestra(zonaDeCobertura, muestra);
		verify(funcionalidad1, times(1)).nuevoEvento(organizacion, zonaDeCobertura, muestra);
	}
	@Test
	public void testCuandoActualizaMuestraVerificadaSeEjecutaLaFuncionalidadCorrespondiente() {
		organizacion.setFuncionalidadMuestraVerificada(funcionalidad1);
		organizacion.actualizarMuestraVerificada(zonaDeCobertura, muestra);
		verify(funcionalidad1, times(1)).nuevoEvento(organizacion, zonaDeCobertura, muestra);
		
	}
}
