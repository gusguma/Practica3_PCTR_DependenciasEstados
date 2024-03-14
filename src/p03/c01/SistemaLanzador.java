package src.p03.c01;

public class SistemaLanzador {
	
	public static void main(final String[] args) {

		final IParque parque = AdaptadorParqueSincronizado.getInstancia();
		char letra_puerta = 'A';

		System.out.println("¡Parque abierto!");

		for (int i = 0; i < Integer.parseInt(args[0]); i++) {

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