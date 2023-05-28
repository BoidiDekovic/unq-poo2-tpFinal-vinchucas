package ZonaDeCobertura;

import java.text.DecimalFormat;

public class Distancia {
	
	private Unidad unidad;
	private double cantidad;
	
	
	public Distancia(Unidad unidad, double cantidad) {
		super();
		this.unidad = unidad;
		this.cantidad = cantidad;
	}


	public Unidad getUnidad() {
		return unidad;
	}


	public double getCantidad() {
		return cantidad;
	}


	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}


	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public void calcularDistanciaEntreUbicacionesEnMetros(Ubicacion ubicacion,Ubicacion ubicacion2) {
		this.calcularDistanciaEntreUbicacionesEnKilometros(ubicacion,ubicacion2);
		this.setCantidad(this.getCantidad()*1000);
		this.setUnidad(Unidad.METRO);
		
	}
	
	public void calcularDistanciaEntreUbicacionesEnKilometros(Ubicacion ubicacion , Ubicacion ubicacion2) {
	    double distancia = Math.sqrt(Math.pow((ubicacion.getLongitud() - ubicacion2.getLongitud()), 2) + Math.pow((ubicacion.getLatitud() - ubicacion2.getLatitud()), 2));
	    double resultado = limitadorDeDecimales(distancia);
	   this.setCantidad(resultado);
	   this.setUnidad(Unidad.KILOMETRO);
	}

	private double limitadorDeDecimales(double numero) {
		DecimalFormat decimalFormat = new DecimalFormat("#.00"); // Establece dos decimales
        String formattedNumber = decimalFormat.format(numero); //  limita a dos decimales
        double resultado = Double.parseDouble(formattedNumber); // transforma el string a double
		return resultado;
	}
	
	
	
	
}
