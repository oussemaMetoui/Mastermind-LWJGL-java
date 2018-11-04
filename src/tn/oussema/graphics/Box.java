package tn.oussema.graphics;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import tn.oussema.game.Game;

public class Box{

	public int x, y;
	public boolean selected = false, isCreated=true ;
	protected int num;
	public static int[] nbColors={0, 0 ,0 ,0 ,0};
	

		protected Texture texture;

		public Box(int x, int y){
			this.x = x;
			this.y = y;
		}

		public Box( int x , int y , String nom ){
			texture = loadTexture(nom);
			this.x=x;
			this.y=y;
			switch(nom){
				case "rouge" :this.num = 1;break;
				case "bleu" :this.num = 2;break;
				case "vert" :this.num = 3;break;
				case "jaune" :this.num = 4;break;
				case "violet" :this.num = 5;break;
			}
			Game.verifColors[num-1]=true;
			nbColors[num-1]=1;
		}

		public void update(int dx , int dy){
			x += dx;
			y +=dy;
		}

		public boolean inBounds(int mousex , int mousey){
			if(mousex > x && mousex < x +50 && mousey > y && mousey < y +50)
				return true;
			else
				return false;
		}

	public void draw(){
		texture.bind();

		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(x, y);
			glTexCoord2f(1, 0);
			glVertex2f(x + 50, y);
			glTexCoord2f(1, 1);
			glVertex2f(x + 50, y + 50);
			glTexCoord2f(0, 1);
			glVertex2f(x, y + 50);
		glEnd();
	}

	public void draw(float size){
		texture.bind();

		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(x, y);
			glTexCoord2f(1, 0);
			glVertex2f(x + size, y);
			glTexCoord2f(1, 1);
			glVertex2f(x + size, y + size);
			glTexCoord2f(0, 1);
			glVertex2f(x, y + size);
		glEnd();
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getNum(){
		return this.num;
	}

	protected Texture loadTexture(String nom){
			try {
				return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/"+nom+".png")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}
}