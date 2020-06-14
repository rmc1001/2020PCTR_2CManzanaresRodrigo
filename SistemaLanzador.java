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
	 * @throws InterruptedException 
	 */
		public static void main(String[] args) throws InterruptedException {
			
			int tipos=4;
			int max=0;
			for(int i=tipos;i>0;i--) {
				max=max+i;
			}
			//diccionario
			Hashtable<Integer,Integer>diccionario=new Hashtable<Integer,Integer>();
						int t=0;
			for(int i=max;i>0;i--) {
				diccionario.put(t, i);
				t++;
			}
			
			//inicializacion del juego
			IJuego juego= new Juego(max, tipos);
			
			//creación de los diferentes hilos
			
				for(int i=0;i<tipos-1;i++) {
					for(int k= diccionario.get(tipos);k>0;k--){
					Thread eliminar =new Thread(new ActividadAliada(i, juego));
					Thread generar = new Thread(new ActividadEnemiga(i,juego));
					generar.start();
					generar.join();
					eliminar.start();
					eliminar.join();
				}
			}
		}	

}
