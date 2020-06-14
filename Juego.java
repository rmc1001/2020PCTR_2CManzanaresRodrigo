package simulador_juego.enunciado;

import java.util.Hashtable;
import java.util.Enumeration;
//import java.util.ArrayList;
import java.util.Set;

/**
 * Clase Juego
 * @author rodrigomanzanarescelis
 *
 */
public class Juego implements IJuego {

	private int contadorEnemigosTotales=0;
	private Hashtable<Integer,Integer>contadoresEnemigosTipo;
	private Hashtable<Integer,Integer>contadoresEliminadosTipo;
	private int MAXENEMIGOS=0;
	private final int MINENEMIGOS=0;
	private int tiposenemigos;
	/**
	 * Contructor clase juego
	 * @param MAXENEMIGOS
	 * @param tiposenemigos
	 */
	public Juego(int MAXENEMIGOS, int tiposenemigos) {
		this.tiposenemigos=tiposenemigos;
		this.MAXENEMIGOS=MAXENEMIGOS;
		contadoresEnemigosTipo = new Hashtable<Integer,Integer>();
		contadoresEliminadosTipo = new Hashtable<Integer,Integer>();
	};
	
	@Override
	/**
	 * Función generar enemigo
	 * @param tipoEnemigo
	 */
	public synchronized void generarEnemigo(int tipoEnemigo)throws InterruptedException {
		assert(tipoEnemigo<=tiposenemigos||tipoEnemigo>=0);//check de tipoEnemigo dentro de todos los enemigo		
		comprobarAntesDeGenerar(tipoEnemigo);
		int var;
		var=contadoresEnemigosTipo.get(tipoEnemigo);
		contadoresEnemigosTipo.put(tipoEnemigo, var+1);
		notificargeneracion();
		
		ImprimirInfo(tipoEnemigo,"A");
		checkInvariante();
		
		
	}
	public void notificargeneracion() {
		contadorEnemigosTotales++;
		notify();
	}
	
	@Override
	/**
	 * funcion eliminar enemigo
	 * @param tipoEnemigo
	 */
	public synchronized void eliminarEnemigo(int tipoEnemigo) throws InterruptedException{
		assert(tipoEnemigo<=tiposenemigos||tipoEnemigo>=0);//check de tipoEnemigo dentro de todos los enemigos
		
		comprobarAntesDeEliminar(tipoEnemigo);
		
			int var=contadoresEnemigosTipo.get(tipoEnemigo);
				contadoresEnemigosTipo.put(tipoEnemigo, var-1);
				//if(!contadoresEliminadosTipo.containsKey(tipoEnemigo)) {
				//	contadoresEliminadosTipo.put(tipoEnemigo, 0);
			//}
				int jdk=contadoresEliminadosTipo.get(tipoEnemigo);
				contadoresEliminadosTipo.put(tipoEnemigo, jdk+1);
				notificarsalida();
				ImprimirInfo(tipoEnemigo,"B");
			
		
		checkInvariante();
		
	}
	public void notificarsalida() {
		contadorEnemigosTotales--;
		notify();
	}
	/**
	 * funcion imprimir info
	 * @param tipoEnemigo
	 * @param movimiento
	 */
	private void ImprimirInfo(int tipoEnemigo, String movimiento) {
		if(movimiento=="A") {
		System.out.println(
				"Generado enemigo tipo "+tipoEnemigo+
				"--> Enemigos totales: "+contadorEnemigosTotales);
		for(int i=tiposenemigos;i>0;i--) {
			System.out.println("----> Enemigos tipo"+ (i-1) +":" + contadoresEnemigosTipo.get(i-1) +" ------ [Eliminados:"+contadoresEliminadosTipo.get(i-1)+"]");
		}
		}else {
			System.out.println(
					"Elminiado enemigo tipo "+tipoEnemigo+
					"--> Enemigos totales: "+sumarContadores());
			for(int i=tiposenemigos;i>0;i--) {
				System.out.println("----> Enemigos tipo"+ (i-1) +":" + contadoresEnemigosTipo.get(i-1) +" ------ [Eliminados:"+contadoresEliminadosTipo.get(i-1)+"]");
			}
		}
		
	};
	/**
	 * Sin uso
	 * @return 0
	 */
	public int sumarContadores() {
		return 0;
	};
	/**
	 * función check de invariantes
	 */
	protected void checkInvariante() {
		Set<Integer>keys=contadoresEnemigosTipo.keySet();
		int container=0;
		for(int k:contadoresEnemigosTipo.keySet()) {
			container=container+contadoresEnemigosTipo.get(k);
		}
		assert(contadorEnemigosTotales==container);
		assert(contadorEnemigosTotales<=MAXENEMIGOS);
		assert(contadorEnemigosTotales>=MINENEMIGOS);
	};
	/**
	 * Funcion para comprobar antes de generar
	 * @param tipoEnemigo
	 */
	protected void comprobarAntesDeGenerar(int tipoEnemigo) {
		if(!contadoresEnemigosTipo.containsKey(tipoEnemigo)) {
			for(int i=0;i<tiposenemigos;i++) {
				contadoresEnemigosTipo.put(i, 0);
				contadoresEliminadosTipo.put(i, 0);
			}
		}
		
	};
	/**
	 * Funcion para comprobar antes de eliminar
	 * @param tipoEnemigo
	 */
	protected void comprobarAntesDeEliminar(int tipoEnemigo) throws InterruptedException{
		if(!contadoresEliminadosTipo.containsKey(tipoEnemigo)) {
			wait();
			
		}
		while(contadorEnemigosTotales==MINENEMIGOS) {
			wait();
		}
		
	
	
	}
	}
