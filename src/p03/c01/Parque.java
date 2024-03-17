package src.p03.c01;

import java.util.Hashtable;

public class Parque implements IParque {

	private static final String ENTRADA = "Entrada";
	private static final String SALIDA = "Salida";
	private static final int CAPACIDAD = 50;
	private int contadorPersonasTotales;
	private final Hashtable<String, Integer> contadorEntradasPuerta;
	private final Hashtable<String, Integer> contadorSalidasPuerta;

	public Parque() {
		this.contadorPersonasTotales = 0;
		this.contadorEntradasPuerta = new Hashtable<String, Integer>();
		this.contadorSalidasPuerta = new Hashtable<String, Integer>();
	}

	@Override
	public synchronized void entrarAlParque(final String puerta) throws InterruptedException {
		comprobarAntesDeEntrar();
		// Aumentamos el contador total y el individual
		this.contadorPersonasTotales++;
		this.contadorEntradasPuerta.put(puerta, this.contadorEntradasPuerta.getOrDefault(puerta, 0) + 1);
		// Imprimimos el estado del parque
		imprimirInfo(puerta, ENTRADA);
		checkInvariante();
		notify();
	}

	@Override
	public synchronized void salirDelParque(final String puerta) throws InterruptedException {
		comprobarAntesDeSalir();
		// Aumentamos el contador total y el individual
		this.contadorPersonasTotales--;
		this.contadorSalidasPuerta.put(puerta, this.contadorSalidasPuerta.getOrDefault(puerta, 0) + 1);
		// Imprimimos el estado del parque
		imprimirInfo(puerta, SALIDA);
		checkInvariante();
		notify();
	}

	private void imprimirInfo(final String puerta, final String movimiento) {
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + this.contadorPersonasTotales);
		// Iteramos por todas las puertas e imprimimos sus entradas
		this.contadorEntradasPuerta.entrySet().forEach(
				entry -> System.out.println("----> Entradas por puerta " + entry.getKey() + " -> " + entry.getValue()));
		// Iteramos por todas las puertas e imprimimos sus salidas
		this.contadorSalidasPuerta.entrySet().forEach(
				entry -> System.out.println("----> Salidas por puerta " + entry.getKey() + " -> " + entry.getValue()));
		System.out.println(" ");
	}

	private int sumarEntradasPuerta() {
		return this.contadorEntradasPuerta.values().stream().reduce(Integer::sum).orElse(0);
	}

	private int sumarSalidasPuerta() {
		return this.contadorSalidasPuerta.values().stream().reduce(Integer::sum).orElse(0);
	}

	protected void checkInvariante() {
		assert sumarEntradasPuerta() - sumarSalidasPuerta() == this.contadorPersonasTotales
				: "INV: La sumarEntradasPuerta de contadores de las puertas debe ser igual al valor del contador del parte";
		assert this.contadorPersonasTotales <= CAPACIDAD : "INV: Superada la capacidad máxima del parque.";
		assert this.contadorPersonasTotales >= 0 : "INV: No puede haber mas salidas que entradas en el parque";

	}

	protected void comprobarAntesDeEntrar() throws InterruptedException {
		while (this.contadorPersonasTotales == CAPACIDAD) {
			wait();
		}
	}

	protected void comprobarAntesDeSalir() throws InterruptedException {
		while (this.contadorPersonasTotales == 0) {
			wait();
		}
	}

}
