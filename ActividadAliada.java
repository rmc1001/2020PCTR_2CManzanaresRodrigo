package simulador_juego.enunciado;
import java.util.concurrent.TimeUnit;
/**
 * Clase de Actividad Aliada
 * @author rodrigomanzanarescelis
 *
 */
public class ActividadAliada implements Runnable {

private int tipo;
private IJuego juego;
/**
 * Constructor de la clase
 * @param tipo
 * @param juego
 */
	public ActividadAliada(int tipo ,IJuego juego) {
		this.tipo=tipo;
		this.juego=juego;
	}
	
	/**
	 * Run de la clase
	 */
	public void run() {	
		juego.eliminarEnemigo(tipo);
		try {
			TimeUnit.MILLISECONDS.sleep((long) (Math.random()*5));
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
