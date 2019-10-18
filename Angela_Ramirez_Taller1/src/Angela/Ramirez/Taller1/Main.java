package Angela.Ramirez.Taller1;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import customThreads.enemigosThread;
import customThreads.objetosThread;
import customThreads.poderesThread;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet implements Observer, Runnable{

	//En clase el profesor Domiciano me ayudo a arreglar los hilos de vidas y de poderes
	Comunicacion com;
	// Arreglo de objetos, que me permitira crear dulces de puntaje
	public ArrayList<Objeto> objetos;
	// Arreglo de enemigos, que me permite generar tanto fantasmas como murcielagos
	public ArrayList<Enemigo> enemigos;
	// Arreglo de tipo poder, que me permite generar la habilidad especial de
	// telarana
	public ArrayList<Poder> poderes;
	// Variables de pantallas, correspondientes a las diferentes secciones del juego
	private PImage pantallaInicio;
	private PImage pantallaJuego;
	// diferentes imagenes de jugador para poder generar animaciones en que los
	// jugadores caminan
	private PImage jugador1Imagen;
	private PImage jugador1Imagen1;
	private PImage jugador1Imagen2;
	private PImage jugador1Imagen3;
	private PImage jugador1Imagen4;
	private PImage jugador2Imagen1;
	private PImage jugador2Imagen2;
	private PImage jugador2Imagen3;
	private PImage jugador2Imagen4;
	// imagenes de los dos enemigos existentes
	private PImage enemigo1Imagen;
	private PImage enemigo2Imagen;
	// imagen de las vidas
	private PImage vidasImagen;
	// imagen de los poderes especiales
	private PImage poderImagen;
	// imagen de los dulces: puntos
	private PImage dulceImagen;
	// imagenes de las dos pantallas de instrucciones
	private PImage instrucciones1;
	private PImage instrucciones2;
	// imagen de las dos posibilidades de ganar
	private PImage ganador1;
	private PImage ganador2;
	// variable que permitira cambiar la pantalla y definir en que pantalla se esta
	private int pantalla;
	// variables de puntaje de cada jugador tipo int
	private int puntaje1;
	private int puntaje2;
	// variable de tipo tiempo para el contador
	private int tiempo;
	// variables de tipo jugador que llaman elementos de la clase jugador para
	// identificar cada uno de los jugadores
	private Jugador jugador1;
	private Jugador jugador2;
	// variables de tipo objeto que llaman elementos de la clase objeto
	private Objeto objeto;
	// hilo que genera los enemigos
	private enemigosThread th;
	// hilo que genera los objetos
	private objetosThread ho;
	// hilo que genera los poderes
	private poderesThread hp;

	public static void main(String[] args) {
		PApplet.main("Angela.Ramirez.Taller1.Main");
	}

	@Override
	/**
	 * Metodo que establece la configuracion del lienzo
	 *
	 *
	 */
	public void settings() {
		size(480, 480);
	}

	@Override

	/**
	 * Metodo en el cual se definen las variables y sus imagenes correspondientes, y
	 * se generan los arreglos de cada tipo Se generan tambien los dos posibles
	 * jugadores y se crean los hilos
	 *
	 *
	 */
	public void setup() {
		

		com = Comunicacion.getSingleton();
		com.addObserver(this);
		Thread t = new Thread(com);
		t.start();
		
		

		objetos = new ArrayList<Objeto>();
		enemigos = new ArrayList<Enemigo>();
		poderes = new ArrayList<Poder>();

		pantallaInicio = loadImage("imagenes/pantallaInicio.png");
		pantallaJuego = loadImage("imagenes/pantallaJuego.png");
		jugador1Imagen1 = loadImage("imagenes/p1.png");
		jugador1Imagen2 = loadImage("imagenes/p2.png");
		jugador1Imagen3 = loadImage("imagenes/p3.png");
		jugador1Imagen4 = loadImage("imagenes/p4.png");
		jugador2Imagen1 = loadImage("imagenes/jugador2_1.png");
		jugador2Imagen2 = loadImage("imagenes/jugador2_2.png");
		jugador2Imagen3 = loadImage("imagenes/jugador2_3.png");
		jugador2Imagen4 = loadImage("imagenes/jugador2_4.png");
		dulceImagen = loadImage("imagenes/dulce.png");
		enemigo1Imagen = loadImage("imagenes/enemigo1.png");
		vidasImagen = loadImage("imagenes/vidaa.png");
		poderImagen = loadImage("imagenes/poder.png");
		instrucciones1 = loadImage("imagenes/instrucciones1.jpg");
		instrucciones2 = loadImage("imagenes/instrucciones2.jpg");
		ganador1 = loadImage("imagenes/ganador1.jpg");
		ganador2 = loadImage("imagenes/ganador2.jpg");

		jugador1 = new Jugador(this, 17, 4,313, jugador1Imagen1, jugador1Imagen2, jugador1Imagen3, jugador1Imagen4, 2);
		jugador2 = new Jugador(this, 17, 4,233, jugador2Imagen1, jugador2Imagen2, jugador2Imagen3, jugador2Imagen4, 0);

		th = new enemigosThread(this, jugador1, jugador2);
		th.setDaemon(true);

		ho = new objetosThread(this, jugador1, jugador2);
		ho.setDaemon(true);
		hp = new poderesThread(this, jugador1, jugador2);
		hp.setDaemon(true);

		pantalla = 0;
		
		th.start();
		ho.start();
		hp.start();
		
		//tt.start();
	}

	@Override
	/**
	 * Pinta todas las imagenes de acuerdo a casos que corresponden a las pantallas
	 * en las que uno se encuentre
	 *
	 *
	 */
	public void draw() {
		frameRate(30);
		switch (pantalla) {
		case 0:
			image(pantallaInicio, 0, 0);
			break;
		case 1:
			image(instrucciones1, 0, 0, 480, 480);
			break;
		case 2:
			image(instrucciones2, 0, 0, 480, 480);
			break;
		case 3:

			image(pantallaJuego, 0, 0);
			image(vidasImagen, 100, 9);
			image(vidasImagen, 353, 9);
			textSize(20);
			tiempo++;
			text(tiempo, 230, 45);
			text(puntaje1, 173, 60);
			text(puntaje2, 314, 60);
			text(jugador1.getVidas(), 187, 35);
			text(jugador2.getVidas(), 328, 35);
			for (int i = 0; i < enemigos.size(); i++) {
				enemigos.get(i).pintarEnemigo();
				enemigos.get(i).mover();
				if (!validarChoque1(enemigos.get(i))) {
					validarChoque2(enemigos.get(i));
						
				}
			}
			for (int i = 0; i < objetos.size(); i++) {
				objetos.get(i).pintarObjeto();
				objetos.get(i).moverObjeto();
				if (!validarChoque1(objetos.get(i))) {
					validarChoque2(objetos.get(i));
				}
			}
			for (int i = 0; i < poderes.size(); i++) {
				poderes.get(i).pintar();
				poderes.get(i).mover();
				if (!validarChoque1(poderes.get(i))) {
					validarChoque2(poderes.get(i));
				}
			}
			jugador2.pintar();
			jugador1.pintar();
			if (jugador1.getVidas() == 0) {
				pantalla = 5;
				System.out.println("PERDIO 1");
			}
			if (jugador2.getVidas() == 0) {
				pantalla = 4;
				System.out.println("PERDIO 2");
			}
			break;
		case 4:
			image(ganador1, 0, 0, 480, 480);
			text(puntaje1, 350, 360);
			break;
		case 5:
			image(ganador2, 0, 0, 480, 480);
			text(puntaje2, 350, 360);
			break;

		}
		
		System.out.println("1>>>>>" + jugador1.getVidas());

		System.out.println("2>>>>>" + jugador2.getVidas());
		
	}

	@Override
	/**
	 * Metodo que permite implementando el mouse iniciar el juego y utilizar
	 * opciones de tipo boton en el servidor
	 *
	 *
	 */
	public void mousePressed() {
		if (mouseX >= 310 && mouseX <= 417 && mouseY >= 409 && mouseY <= 446 && pantalla == 0) {
			pantalla = 3;
		}
		if (mouseX >= 212 && mouseX <= 320 && mouseY >= 405 && mouseY <= 456 && pantalla == 4 || pantalla == 5) {
			jugador1.setVid4s(4);
			jugador2.setVid4s(4);
			pantalla = 0;
		}
		if (mouseX >= 74 && mouseX <= 199 && mouseY >= 409 && mouseY <= 446 && pantalla == 0) {
			pantalla = 1;
		}
		if (mouseX >= 357 && mouseX <= 441 && mouseY >= 423 && mouseY <= 445 && pantalla == 1) {
			pantalla=2;
		}
		if (mouseX >= 39 && mouseX <= 125 && mouseY >= 400 && mouseY <= 456 && pantalla == 2) {
			pantalla=3;
		}
	}

	/**
	 * 
	 * Metodo que permite mover a los jugadores mediante teclas
	 *
	 */
	public void keyPressed() {
		// jugador1.moverJugador1();
		jugador2.moverJugador2();
	}

	/**
	 * Metodo que permite crear enemigos y agregarlos al arreglo generandolos de
	 * acuerdo a ciertas posiciones
	 *
	 */
	
	public void eliminarTodosLosEnemigos() {
		enemigos.clear();
	}
	
	public void crearYAnadirEnemigo() {
		int[] posiciones = { 327, 374, 415 };
		int y = (int) random(posiciones.length);
		Enemigo e = new Enemigo(this, 415, posiciones[y], enemigo1Imagen, y);
		enemigos.add(e);
	}

	public void moverEnemigos() {
		for (int i = 0; i < enemigos.size(); i++) {
			enemigos.get(i).mover();
		}
	}

	/**
	 * Metodo que permite crear objetos y agregarlos al arreglo generandolos de
	 * acuerdo a ciertas posiciones
	 *
	 */
	public void crearYAnadirObjeto() {
		int[] posiciones = { 327, 374, 415 };
		int num = (int) random(posiciones.length);
		Objeto o = new Objeto(this, 415, posiciones[num], dulceImagen, num);
		objetos.add(o);
	}

	/**
	 * Metodo que permite crear poderes y agregarlos al arreglo generandolos de
	 * acuerdo a ciertas posiciones
	 *
	 */
	public void crearYAnadirPoder() {
		int[] posiciones = { 327, 374, 415 };
		int num = (int) random(posiciones.length);
		Poder p = new Poder(this, 415, posiciones[num], poderImagen, num);
		poderes.add(p);
	}

	/**
	 * Metodo que permite validar choque cuando un personaje coalisiona con un
	 * objeto, enemigo o poder
	 */
	public boolean validarChoque1(Object e) {
		boolean var = false;
		if (e instanceof Objeto) {
			Objeto temp = (Objeto) e;
			if (jugador1.getX() + 116 > temp.getX() && jugador1.getX() < temp.getX()
					&& jugador1.getCarril() == temp.getCarril()) {
				puntaje1++;
				objetos.remove(temp);
				var = true;
			}
		}
		if (e instanceof Enemigo) {
			Enemigo temp = (Enemigo) e;
			if (jugador1.getX() + 116 > temp.getX() && jugador1.getX() < temp.getX()
					&& jugador1.getCarril() == temp.getCarril()) {
				jugador1.setVidas(jugador1.getVidas() - 1);
				enemigos.remove(temp);
				System.out.println("COLISION");
				var = true;
			}
		}
		if (e instanceof Poder) {
			Poder temp = (Poder) e;
			if (jugador1.getX() + 116 > temp.getX() && jugador1.getX() < temp.getX()
					&& jugador1.getCarril() == temp.getCarril()) {
				jugador2.setVidas(jugador2.getVidas() - 1);
				poderes.remove(temp);
				System.out.println("PODER");
				var = true;
			}
		}
		return var;
	}

	public void validarChoque2(Object e) {
		
		if (e instanceof Objeto) {
			Objeto temp = (Objeto) e;
			if (jugador2.getX() + 116 > temp.getX() && jugador2.getX() < temp.getX()
					&& jugador2.getCarril() == temp.getCarril()) {
				puntaje2++;
				objetos.remove(temp);
			}
		}
		if (e instanceof Enemigo) {
			Enemigo temp = (Enemigo) e;
			if (jugador2.getX() + 116 > temp.getX() && jugador2.getX() < temp.getX()
					&& jugador2.getCarril() == temp.getCarril()) {
				jugador2.setVid4s(jugador2.getVidas() - 1);
				enemigos.remove(temp);
			}
		}
		if (e instanceof Poder) {
			Poder temp = (Poder) e;
			if (jugador2.getX() + 116 > temp.getX() && jugador2.getX() < temp.getX()
					&& jugador2.getCarril() == temp.getCarril()) {
				jugador1.setVid4s(jugador1.getVidas() - 1);
				enemigos.remove(temp);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		String mensaje = (String) arg;
		System.out.println("Servidor lee: " + mensaje);

		if (mensaje.length() > 1) {

			jugador1.moverJugador(mensaje);
		}
	}
	
	public void calculos() {
		if(pantalla==0) {
	
			puntaje1=0;
			puntaje2=0;
			tiempo=0;
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			calculos();
			try {
				Thread.sleep((long) 16.66667);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void eliminarTodosLosPoderes() {
		poderes.clear();	
	}
}