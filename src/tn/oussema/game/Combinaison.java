package tn.oussema.game;

import java.util.ArrayList;
import java.util.Iterator;

public class Combinaison {
	public static final int FACILE=1 , MOYEN=2 , DIFICILE=3 , DIFFICILE_2=4;

	public static ArrayList<Integer> listeCouleur(int level){
		ArrayList<Integer> liste = new ArrayList<Integer>();
		int nb;
		if(level<4){
			for(int i =0;i<level+2;i++){
				nb = (int) ((level+2-0)*Math.random() + 1);
				if(liste.contains(nb))
					i--;
				else liste.add(nb);
				}
		}
		else
			for(int i = 0; i<=4; i++)
			{
				nb = (int) ((5-0)*Math.random() + 1);
				liste.add(nb);
			}
		 return liste;
	}

	public static void main(String[] args){
		ArrayList<Integer> comb = listeCouleur(FACILE);
		 Iterator<Integer> it = comb.iterator();
		 while(it.hasNext())
			 System.out.println(it.next());
	}
}