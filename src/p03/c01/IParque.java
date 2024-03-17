package src.p03.c01;

/**
 * Interfaz que permite crear implementaciones que representan entradas y
 * salidas a un parque
 * 
 * @author Gustavo Gutierrez Martin - ggm1007@alu.ubu.es
 * @since 1.8
 */
public interface IParque {

	void entrarAlParque(String puerta) throws InterruptedException;

	void salirDelParque(String puerta) throws InterruptedException;

}
