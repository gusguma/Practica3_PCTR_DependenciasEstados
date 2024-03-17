package src.p03.c01;

public class SistemaLanzador {

	private static final int PUERTAS = 4;

	public static void main(final String[] args) {

		Integer numeroDePuertas = PUERTAS;

		if (args != null && args.length > 0) {
			numeroDePuertas = Integer.parseInt(args[0]);
		}

		final IParque parque = new Parque();
		char letra_puerta = 'A';

		System.out.println("¡Parque abierto!");

		for (int i = 0; i < numeroDePuertas; i++) {

			final String puerta = "" + ((letra_puerta++));

			// Creación de hilos de entrada
			final ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			new Thread(entradas).start();

			// Creación de hilos de salida
			final ActividadSalidaPuerta salidas = new ActividadSalidaPuerta(puerta, parque);
			new Thread(salidas).start();

		}
	}
}