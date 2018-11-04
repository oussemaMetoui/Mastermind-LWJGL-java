package tn.oussema.graphics;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Renderer{
	
	private static TrueTypeFont font;
	private static Font awtFont;

	public static void renderQuad( int x , int y , int width , int heiht, float[] color){
		glBegin(GL_QUADS);
			glColor3f(color[0],color[1],color[2]);
			glVertex2f(x, y);
			glVertex2f(x + width, y);
			glVertex2f(x + width, y + heiht);
			glVertex2f(x, y + heiht);
		glEnd();
	}

	public static void renderQuad(int x , int y , int width , int height, Texture texture){
		texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0f, 0f);
			glVertex2f(x, y);
			glTexCoord2f(1f, 0f);
			glVertex2f(x + width, y);
			glTexCoord2f(1f, 1f);
			glVertex2f(x + width, y + height);
			glTexCoord2f(0f, 1f);
			glVertex2f(x, y + height);
		glEnd();
	}
	
	public static void mainMenuRender(){
		try {
			glClearColor(1f, 0f, 0f, 1.0f);

			Texture titre = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/titre-1.png")));
			Renderer.renderQuad(50, 0, 100, 100, titre);

			titre = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/titre-2.png")));
			Renderer.renderQuad(150, 0, 100, 100, titre);

			Texture logo = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/logo.png")));
			Renderer.renderQuad(20, 25, 50, 50, logo);

//			Boutton Facile
			Texture gauche = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/gauche.png")));
			Renderer.renderQuad(200, 100, 100, 100, gauche);

			Texture easy = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/easy.png")));
			Renderer.renderQuad(300, 100, 100, 100, easy);

			Texture droit = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/droit.png")));
			Renderer.renderQuad(400, 100, 100, 100, droit);

//			Boutton normal
			gauche = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/gauche.png")));
			Renderer.renderQuad(200, 200, 100, 100, gauche);

			Texture normal = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/normal.png")));
			Renderer.renderQuad(300, 200, 100, 100, normal);

			droit = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/droit.png")));
			Renderer.renderQuad(400, 200, 100, 100, droit);

//			Boutton dificile
			gauche = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/gauche.png")));
			Renderer.renderQuad(200, 300, 100, 100, gauche);

			Texture hard = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/hard.png")));
			Renderer.renderQuad(300, 300, 100, 100, hard);

			droit = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/droit.png")));
			Renderer.renderQuad(400, 300, 100, 100, droit);
			
//			Boutton trés dificile
			gauche = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/gauche.png")));
			Renderer.renderQuad(200, 400, 100, 100, gauche);

			Texture vhard = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/vhard.png")));
			Renderer.renderQuad(300, 400, 100, 100, vhard);

			droit = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/droit.png")));
			Renderer.renderQuad(400, 400, 100, 100, droit);

		} catch (IOException e) {
			glClearColor(1f, 0f, 0f, 1.0f);
			e.printStackTrace();
		}
	}

	public static void GameRender(int level, int nbEssai){
		
		glClearColor(0f, 1f, 0f, 1.0f);
		switch (level) {
		case 1: for(int i=1;i<4;i++){
			EmptyBox.list.add(new EmptyBox(68*i+10, 100+ nbEssai*60 , i));
		}break;
		case 2: for(int i=1;i<5;i++){
			EmptyBox.list.add(new EmptyBox(68*i+10, 100+ nbEssai*60 , i));
		}break;
		case 3 : for(int i=1;i<6;i++){
			EmptyBox.list.add(new EmptyBox(68*i+10, 100+ nbEssai*60 , i));
		}break;
		case 4: for(int i=1;i<6;i++){
			EmptyBox.list.add(new EmptyBox(68*i+10, 100+ nbEssai*60 , i));
		}break;
		default:
			break;
		}
	}
	
	public static void finalResultRender(int r){
		if (r == 0)//perdu
		{
			glClearColor(0.55f, 0.55f, 0.55f, 1.0f);


			try {
				Texture gauche = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/perdu1.png")));
				Renderer.renderQuad(200, 100, 100, 100, gauche);

				Texture centre = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/perdu2.png")));
				Renderer.renderQuad(300, 100, 100, 100, centre);

				Texture droit = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/perdu3.png")));
				Renderer.renderQuad(400, 100, 100, 100, droit);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		else//gagnée
		{
			glClearColor(1.0f, 0f, 0f, 1.0f);
			try {
				Texture gauche = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/feli.png")));
				Renderer.renderQuad(200, 100, 100, 100, gauche);

				Texture centre = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/citati.png")));
				Renderer.renderQuad(300, 100, 100, 100, centre);

				Texture droit = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/on.png")));
				Renderer.renderQuad(400, 100, 100, 100, droit);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void resultRender(int blanc , int noir , int essai) throws FileNotFoundException, IOException{
		
		Texture blanche = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/boule-blanche.png")));
		Texture bNoir = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/boule-noir.png")));

		for(int i=0; i<blanc ; i++){
			Renderer.renderQuad(400 +i*50, 100+ essai*60, 50, 50, blanche);
		}

		for(int i=blanc; i<noir+blanc ; i++)
		{
			Renderer.renderQuad(400 +i*50, 100+ essai*60, 50, 50, bNoir);
		}
		
	}
//	TODO
	public static void drawString(int x , int y , String text){
		awtFont = new Font("Times New Roman" , Font.ITALIC, 58);
		font = new TrueTypeFont(awtFont, false);
		font.drawString(x, y, "hello");
	}
	
}