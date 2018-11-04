package tn.oussema.graphics;

import java.util.ArrayList;
import java.util.List;

public class LitleBox extends Box{

	public int num;
	public static List<Box> historiqueEssai = new ArrayList<Box>();
	public LitleBox(int x, int y ,int num) {
		super(x, y);
		this.num=num;
		switch(num){
		case 1 : texture = loadTexture("rouge"); break;
		case 2 : texture = loadTexture("bleu"); break;
		case 3 : texture = loadTexture("vert"); break;
		case 4 : texture = loadTexture("violet"); break;
		case 5 : texture = loadTexture("jaune"); break;
		}
		draw(10);
	}

}