package simulador_juego.enunciado;
import java.util.concurrent.TimeUnit;

/**
 * Clase actividad enemiga
 * @author rodrigomanzanarescelis
 *
 */
public class ActividadEnemiga implements Runnable{

	private int tipo;
	private IJuego juego;
	/**
	 * Construnctor actividad Enemiga
	 * @param tipo
	 * @param juego
	 */
	public ActividadEnemiga(int tipo,IJuego juego) {
		this.juego=juego;
		this.tipo=tipo;
	
	}
	
	
	/**
	 * Run de los enemigos
	 */
	public void run() {
		try {
			juego.generarEnemigo(tipo);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			TimeUnit.MILLISECONDS.sleep((long) (Math.random()*5));
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	

}
