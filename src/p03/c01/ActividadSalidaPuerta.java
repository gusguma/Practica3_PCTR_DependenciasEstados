package src.p03.c01;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que permite crear hilos por cada una de las salidas por cada puerta
 * 
 * @author Gustavo Gutierrez Martin - ggm1007@alu.ubu.es
 * @since 1.8
 */
public class ActividadSalidaPuerta implements Runnable {

	private static final int NUMSALIDAS = 20;
	private final String puerta;
	private final IParque parque;

	public ActividadSalidaPuerta(final String puerta, final IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}

	@Override
	public void run() {
		for (int i = 0; i < NUMSALIDAS; i++) {
			try {
				this.parque.salirDelParque(this.puerta);
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(40) * 100);
			} catch (final InterruptedException e) {
				Logger.getGlobal().log(Level.INFO, "Salida interrumpida");
				Logger.getGlobal().log(Level.INFO, e.toString());
				return;
			}
		}
	}
}
