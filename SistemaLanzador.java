package simulador_juego.enunciado;
import java.util.Hashtable;
/**
 * Clase del lanzador del juego
 * @author rodrigomanzanarescelis
 *
 */
public class SistemaLanzador {
	/**
	 * Función principal del programa
	 * @param args
	 */
		public static void main(String[] args) {
			int max=10;
			int tipos=4;
			/* 
			 * TODO Crear un diccionario con la estrutura: 
			 * 		Clave - tipo de enemigo
			 * 		Valor - número de enemigos de ese tipo que se deben generar
			 */
			Hashtable<Integer,Integer>diccionario=new Hashtable<Integer,Integer>();
			//hacer un foraso
			int t=0;
			for(int i=max;i>0;i--) {
				diccionario.put(t, i);
				t++;
			}
			
			/* 
			 * TODO Inicializar juego
			 */
			IJuego juego= new Juego(max, tipos);
			
			
			
			
			/* 
			 * TODO Crear los hilos para la actividad aliada y enemiga (por pares) iterando sobre el diccionario anterior.
			 */
			
				for(int i=0;i<tipos-1;i++) {
					for(int k= diccionario.get(tipos);k>0;k--){
					Thread eliminar =new Thread(new ActividadAliada(i, juego));
					Thread generar = new Thread(new ActividadEnemiga(i,juego));
					generar.start();
					eliminar.start();
				}
			}
		}	

}
