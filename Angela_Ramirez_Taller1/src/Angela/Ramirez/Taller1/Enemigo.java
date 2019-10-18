package Angela.Ramirez.Taller1;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemigo {
	// variable de tipo x para la posicion horizontal del enemigo que se va ira desplazando hacia la izquierda
	private int x;
	// variable de tipo y para la posicion vertical del enemigo correspondiente al carril en el cual es generado
	private int y;
	//imagen que se carga en el ejecutable de acuerdo a cada enemigo
	private PImage imagen;
	// Variable de la libreria importada de processing para poder importar estos metodos
	private PApplet app;
	//variable para generar los elementos en 3 carriles
	private int carril;
	
/**
 * Metodo constructor de la clase enemigo que permite ubicarlo en el espacio y en un carril
 * @param app
 * @param x
 * @param y
 * @param imagen
 * @param carril
 */
	public Enemigo(PApplet app, int x, int y, PImage imagen, int carril) {
		
		this.x = x;
		this.y = y;
		this.imagen = imagen;
		this.app = app;
		this.carril = carril;
	}
	
	/**
	 * Metodo que pinta la imagen del enemigo en una posicion
	 *
	 */
	public void pintarEnemigo() {
		app.image(imagen,x,y);
	}
	
	/**
	 * Metodo que ira restando en posicion en eje x para que el objeto se mueva mientral el hilo esta activo
	 *
	 *
	 */
	public void mover() {
		x-=3;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getCarril() {
		return carril;
	}
	public void setCarril(int carril) {
		this.carril = carril;
	}
	
	
}
