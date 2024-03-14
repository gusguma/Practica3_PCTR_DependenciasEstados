package src.p03.c01;

public class AdaptadorParqueSincronizado implements IParque {

	private static final AdaptadorParqueSincronizado instancia = new AdaptadorParqueSincronizado();

	private final IParque parque;

	private AdaptadorParqueSincronizado() {
		this.parque = new Parque();
	}

	public static AdaptadorParqueSincronizado getInstancia() {
		return instancia;
	}

	@Override
	public synchronized void entrarAlParque(final String puerta) {
		this.parque.entrarAlParque(puerta);

	}

	@Override
	public synchronized void salirDelParque(final String puerta) {
		this.parque.salirDelParque(puerta);

	}

}
