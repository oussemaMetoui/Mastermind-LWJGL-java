package tn.oussema.game;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;

import static tn.oussema.game.Combinaison.DIFFICILE_2;
import static tn.oussema.game.Combinaison.DIFICILE;
import static tn.oussema.game.Combinaison.FACILE;
import static tn.oussema.game.Combinaison.MOYEN;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import tn.oussema.graphics.Box;
import tn.oussema.graphics.EmptyBox;
import tn.oussema.graphics.Renderer;

/**
 *
 * @author Oussema Metoui
 * 
 */
public class Game {

	private int score=0;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 700;
	private static final String TITRE = "Mastermind";
	private int currentLevel;
	private static int rep=0 , ligne = 0, nbEssai = 0 ;
	private int nbBlanc , nbNoir , res;

	private States state = States.MAIN_MENU;

	public static boolean[] verifColors = {false , false , false , false , false};
	private static ArrayList<Integer> comb;
	private List<Box> shapes = new ArrayList<Box>(16);
	private boolean isSelected = false;
	
	private long time = 0 , lastTime;

	private BufferedReader fileIn;
	private BufferedWriter fileOut;
	public Game(){
		try{
			   Display.setTitle(TITRE);
			   Display.setFullscreen(false);
			   Display.setResizable(false);
			   Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			   Display.create();
			   Keyboard.create();
			   Mouse.create();
			  } catch(LWJGLException e) {
			   System.out.println("Creation de la fenetre : " + e.getMessage());
			  }
		initGl();
		update();
		Display.destroy();
	}

	/*
	 * initialisation d'openGl
	 */
	private void initGl(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluOrtho2D(0, WIDTH, HEIGHT, 0);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
	}

	private void update(){
		while(!Display.isCloseRequested())
		{
			glClear(GL_COLOR_BUFFER_BIT);
			
			if(time>1000000) time = lastTime = 0;
			time ++;
//			System.out.println(time);

			render();
			for(Box box : shapes){
				if(Mouse.isButtonDown(0)&& box.inBounds(Mouse.getX(), HEIGHT-Mouse.getY())&& !isSelected){
					isSelected = true;
					box.selected=true;
				}
				if(Mouse.isButtonDown(1)){
					isSelected=false;
					box.selected=false;
				}
				if(box.selected){
					box.update(Mouse.getDX(), -Mouse.getDY());
				}
				box.draw();
			}
			Display.update();
			Display.sync(60);
		}
	}

	private void render(){
		
		switch(state){
		case MAIN_MENU: Renderer.mainMenuRender(); mainControls(); break;
		case GAME : Renderer.GameRender(currentLevel,nbEssai);try {
			Renderer.resultRender(nbBlanc , nbNoir, nbEssai-1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} gameControls(); break;
		case RESULT : Renderer.finalResultRender(res);if(Keyboard.isKeyDown(Keyboard.KEY_RETURN))state = States.MAIN_MENU;;break;
		}
	}

//	Controles du state Main
	private void mainControls(){
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)&& state==States.MAIN_MENU){
			  Display.destroy();
			  System.exit(0);
		  	}

		if(Mouse.isButtonDown(0) && Mouse.getX()>300 && Mouse.getX()<400){
			if(HEIGHT-Mouse.getY()>100 && HEIGHT-Mouse.getY()<200)
			{
				comb = Combinaison.listeCouleur(FACILE);
				System.out.println(comb);
				currentLevel = FACILE;
				state = States.GAME;
				lastTime = time;
			}
			else if(HEIGHT-Mouse.getY()>200 && HEIGHT-Mouse.getY()<300)
			{
				comb = Combinaison.listeCouleur(MOYEN);
				System.out.println(comb);
				currentLevel = MOYEN;
				state = States.GAME;
				lastTime = time;
			}
			else if (HEIGHT-Mouse.getY()>300 && HEIGHT-Mouse.getY()<400){
				comb = Combinaison.listeCouleur(DIFICILE);
				System.out.println(comb);
				currentLevel = DIFICILE;
				state = States.GAME;
				lastTime = time;
			}
			else if (HEIGHT-Mouse.getY()>400 && HEIGHT-Mouse.getY()<500){
				comb = Combinaison.listeCouleur(DIFFICILE_2);
				System.out.println(comb);
				currentLevel = DIFFICILE_2;
				state = States.GAME;
				lastTime = time;
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_1) && time>lastTime+50 ){
			comb = Combinaison.listeCouleur(FACILE);
			System.out.println(comb);
			currentLevel = FACILE;
			state = States.GAME;
			lastTime = time;
		  	}

		if(Keyboard.isKeyDown(Keyboard.KEY_2) && time>lastTime+50 ){
			comb = Combinaison.listeCouleur(MOYEN);
			System.out.println(comb);
			currentLevel = MOYEN;
			state = States.GAME;
			lastTime = time;
		  	}

		if(Keyboard.isKeyDown(Keyboard.KEY_3) && time>lastTime+50 ){
			comb = Combinaison.listeCouleur(DIFICILE);
			System.out.println(comb);
			currentLevel = DIFICILE;
			state = States.GAME;
			lastTime = time;
		  	}

		if(Keyboard.isKeyDown(Keyboard.KEY_4) && time>lastTime+50 ){
			comb = Combinaison.listeCouleur(DIFFICILE_2);
			System.out.println(comb);
			currentLevel = DIFFICILE_2;
			state = States.GAME;
			lastTime = time;
		  	}
	}

/*
 * les controles du state game
 */
	private void gameControls(){
		String[] colors={"rouge","bleu","vert","jaune","violet"};

		for (int i=0 ; i<5;i++)//ajouter les box des couleurs
		{
			if(!verifColors[i] && Box.nbColors[i]==0){
				shapes.add(new Box(30+(100*i),30,colors[i]));
			}
		}
		for (Box box : shapes) {//parcourir shapes
				for (EmptyBox emBox : EmptyBox.list) {//parcourir liste des EmptyBox
		 			if(emBox.intersect(box) && !emBox.isNotEmpty){
						emBox.isNotEmpty=true;
		 				box.selected=false;
						isSelected=false;
						emBox.setNumBox(box.getNum());
						verifColors[box.getNum()-1]=false;
						Box.nbColors[box.getNum()-1]=0;
					}
				}
		}
		if(nbEssai<6){
			if (verif(EmptyBox.list)){//test de choix terminée
				if(!evaluation()){// test sur jeu global
					nbEssai++;//incrémentation nbr d essai si stage pas terminée
					verifLigne();//calcule , affichage noir et blanc
				}
				else{
					clean();
					score=(6-nbEssai)*currentLevel+oldScore();
					System.out.println("gagnée de score : "+score + " ancien score "+oldScore());
					save();
					res=1;
					state=States.RESULT;
				}
			}
			}
		else
		{
			clean();
			res=0;
			state=States.RESULT;
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)&& state==States.GAME)
			state = States.MAIN_MENU;
	}

	public boolean verif(List<EmptyBox> list){
		boolean v=true;
		for (EmptyBox emBox : list) {
			if(emBox.numBox==0)
			{
				v=false;
				break;
			}
		}
		return v;
	}

	private boolean evaluation(){
		int i =0;boolean v=true;
		int pas =0;
		switch(currentLevel){
		case 1 : pas = 3;break;
		case 2 : pas = 4;break;
		case 3 : pas = 5;break;
		case 4 : pas = 5;break;
		}
		while(i<comb.size() && v){
			if(comb.get(i)!=EmptyBox.list.get(EmptyBox.list.size()-pas+i).numBox)
				v=false;
			i++;
		}
		return v;
	}

	private void verifLigne(){
		nbBlanc=0; nbNoir=0;
		int pas =0;
		switch(currentLevel){
		case 1 : pas = 3;break;
		case 2 : pas = 4;break;
		case 3 : pas = 5;break;
		case 4 : pas = 5;break;
		}

		for (int i = 0; i<comb.size(); i++) {
			if(comb.get(i)==EmptyBox.list.get(EmptyBox.list.size()-pas+i).numBox)
			{
				nbBlanc++;
				System.out.println(EmptyBox.list.get(i).numBox);
			}
			else
			{
				for (Integer c : comb) {
					if(c==EmptyBox.list.get(i).numBox){
						nbNoir++;
						System.out.println(EmptyBox.list.get(EmptyBox.list.size()-pas+i).numBox);

					}
				}
			}
		}

		System.out.println(nbBlanc + " " + nbNoir +" "+ nbEssai);
	}

	private void clean(){
		EmptyBox.list.removeAll(EmptyBox.list);
		shapes.removeAll(shapes);
	}
	
	private void save(){
		try {
			fileOut = new BufferedWriter(new FileWriter(new File("historique.txt")));
			fileOut.write(((Integer)score).toString());
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int oldScore(){
		try {
			fileIn = new BufferedReader(new FileReader(new File("historique.txt")));
			return Integer.parseInt(fileIn.readLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		finally{
			try {
				fileIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

//	les getters
	public int GetWidth(){
		return Game.WIDTH;
	}

	public int getHeight(){
		return Game.HEIGHT;
	}

	public static ArrayList<Integer> getComb(){
		return comb;
	}
}