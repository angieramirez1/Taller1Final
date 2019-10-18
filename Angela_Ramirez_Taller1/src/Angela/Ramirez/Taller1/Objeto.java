package Angela.Ramirez.Taller1;

import processing.core.PApplet;
import processing.core.PImage;

public class Objeto {
	//posicion en el eje x del objeto
	private int x;
	//posicion en el eje y
	private int y;
	//puntos que otorga el objeto que se sumaran a cada personaje
	private int puntaje;
	//imagen correspondiente al dulce
	private PImage imagen;
	//Variable libreria de processing
	private PApplet app;
	//Carril en el cual el objeto es generado
	private int carril;
/**
 * metodo constructor del objeto
 * @param app
 * @param x
 * @param y
 * @param imagen
 * @param carril
 */
	public Objeto(PApplet app, int x, int y, PImage imagen, int carril) {
		this.x = x;
		this.y = y;
		this.imagen = imagen;
		this.app = app;
		this.carril = carril;
	}
//metodo que pinta la imagen
	public void pintarObjeto() {
		app.image(imagen, x, y);
	}
//metodo que mueve el objeto a lo largo del carril cuando el hilo esta activo
	public void moverObjeto() {
		x-=2;
	}
	//metodo que escribe el puntaje de acuerdo a la cantidad de dulces recolectadosb
	public void pintarPuntaje1() {
		app.textSize(24);
		app.text(puntaje, 163, 60);
	}
//metodo que escribe el puntaje de acuerdo a la cantidad de dulces recolectados
	public void pintarPuntaje2() {
		app.textSize(24);
		app.text(puntaje, 302, 60);
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

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public int getCarril() {
		return carril;
	}

	public void setCarril(int carril) {
		this.carril = carril;
	}
}