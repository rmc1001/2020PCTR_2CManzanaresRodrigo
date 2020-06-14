package simulador_juego.enunciado;
/**
 * interfaz del juego
 * @author rodrigomanzanarescelis
 *
 */
public interface IJuego {
	public void generarEnemigo(int tipoEnemigo) throws InterruptedException;
	public void eliminarEnemigo(int tipoEnemigo) throws InterruptedException;
}

