package ZonaDeCobertura;

import java.util.List;


public class Ubicacion {
	
	private double longitud ;
	private double latitud ;
	
	public Ubicacion(double longitud, double latitud) {
		super();
		this.longitud = longitud;
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public Distancia calcularDistanciaMetrosA(Ubicacion ubicacion) {
		
		 Distancia distancia = new Distancia(Unidad.METRO,0);
		 distancia.calcularDistanciaEntreUbicacionesEnMetros(this, ubicacion);
      return distancia;
	}
	
	public Distancia calcularDistanciaKilometrosA(Ubicacion ubicacion) {
	   
	 Distancia distancia = new Distancia(Unidad.KILOMETRO,0);
	 distancia.calcularDistanciaEntreUbicacionesEnKilometros(this, ubicacion);
	  
	 return distancia;
	    
	}


	public List<Ubicacion> ubicacionesAMenosDe(Distancia distancia, List<Ubicacion> ubicaciones){
		
		return ubicaciones.stream().filter(u -> this.esUbicacionAMenosDe(u, distancia)).toList();
	}
	
	public boolean esUbicacionAMenosDe(Ubicacion ubicacion , Distancia distancia) {
		if(distancia.getUnidad() == Unidad.KILOMETRO ) {
			 return this.calcularDistanciaKilometrosA(ubicacion).getCantidad() <  distancia.getCantidad();
		}else {
			return  this.calcularDistanciaMetrosA(ubicacion).getCantidad() < distancia.getCantidad();
		}
	}
	
	
}
	