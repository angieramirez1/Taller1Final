package customThreads;


import Angela.Ramirez.Taller1.Enemigo;
import Angela.Ramirez.Taller1.Jugador;
import Angela.Ramirez.Taller1.Main;

public class enemigosThread extends Thread{
	
	public final static long sleep = 4000;
	private Main m;
	private Jugador j1;
	private Jugador j2;
	
	public enemigosThread(Main m, Jugador j1, Jugador j2) {
		this.m = m;
		this.j1=j1;
		this.j2=j2;	
	}
	
	@Override 
	public void run() {
		
		boolean condicion = true;
		while(true) {
			condicion = (j1.getVidas()==0||j2.getVidas()==0)?false:true;
			m.crearYAnadirEnemigo();
			try {
				sleep(sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * if(j1.getVidas()==0||j2.getVidas()==0){
			 * 
			 *   condicion = false;
			 * }else{
			 * 	 condicion = true;
			 * }
			 */
			if(condicion) {
				m.eliminarTodosLosEnemigos();
			}
		}
	}

}