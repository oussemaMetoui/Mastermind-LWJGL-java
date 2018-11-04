package tn.oussema.graphics;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.util.ArrayList;
import java.util.List;

import tn.oussema.game.Game;

public class EmptyBox extends Box {
	public static List<EmptyBox> list = new ArrayList<EmptyBox>();
	private int index;
	public int numBox;
	public boolean isNotEmpty;

	public EmptyBox(int x, int y , int index) {
		super(x,y);
		texture = loadTexture("bloc");
		this.num=Game.getComb().get(index-1);
		this.index=index;
		this.numBox=0;
		this.isNotEmpty=false;
		draw();
	}

	@Override
	public void draw(){
		texture.bind();

		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);glVertex2f(x, y);
			glTexCoord2f(1, 0);glVertex2f(x + 50, y);
			glTexCoord2f(1, 1);glVertex2f(x + 50, y + 50);
			glTexCoord2f(0, 1);glVertex2f(x, y + 50);
		glEnd();
	}

	public boolean intersect(Box other){
		if(this.x<=other.x && this.y<=other.y && other.x<=this.x+50 && other.y<=this.y+50 && !isNotEmpty)
			{
			return setXY(other);
			}
		else
		return false;
	}
	
	private boolean setXY(Box other){
		other.setX(this.x);other.setY(this.y);
		return true;
	}
	public void setNumBox(int num){
		this.numBox=num;
	}

	public int getIndex(){
		return index;
	}
}