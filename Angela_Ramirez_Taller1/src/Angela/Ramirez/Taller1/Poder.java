package Angela.Ramirez.Taller1;

import processing.core.PApplet;
import processing.core.PImage;

public class Poder {

	//posicion eje x del poder
	private int x;
	//posicion eje y
	private int y;
	//imagen correspondiente a la telarana
	private PImage imagen;
	//libreria de processing
	private PApplet app;
	//carril en que se genera el objeto
	private int carril;

/**
 * Metodo constructor que le da una posicion, imagen y carril
 * @param app
 * @param x
 * @param y
 * @param imagen
 * @param carril
 */
	public Poder(PApplet app, int x, int y, PImage imagen, int carril) {
		this.x = x;
		this.y = y;
		this.imagen = imagen;
		this.app = app;
		this.carril = carril;
	}

	/**
	 * Metodo que pinta la imagen en la posicion que le corresponde
	 *
	 *
	 */
	
	public void pintar() {
		app.image(imagen, x, y);
	}

	/**
	 * Metodo que permite que la imagen se vaya moviendo hacia la izquierda
	 *
	 *
	 */
	public void mover() {
		x--;
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
