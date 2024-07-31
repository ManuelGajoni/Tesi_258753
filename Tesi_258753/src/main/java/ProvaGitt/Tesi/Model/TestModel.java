package ProvaGitt.Tesi.Model;

import java.util.ArrayList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) 
	{
		Model model = new Model();
		//Lezione l1 = new Lezione (model.getContoLezioni()+1, 4,5,null,null,"Informatica", 0,0,0,0,0);
		Lezione l2 = new Lezione (model.getContoLezioni()+1,5,3,null,null,"Italiano", 0,0,0,0,0);
		Lezione l3 = new Lezione (model.getContoLezioni()+1, 1,2,null,null,"Informatica", 0,0,0,0,0);
		Lezione l4 = new Lezione (model.getContoLezioni()+1, 1,2,null,null,"Fisica", 0,0,0,0,0);
		List <Lezione> l = new ArrayList <Lezione> ();
		//l.add(l1);
		l.add(l2);
		l.add(l3);
		l.add(l4);
		List <Lezione> tutte = model.getLezioniGiorno(211, 2024);
		System.out.println("TUTTE LE LEZIONI: \n");
		for(int i=0; i<tutte.size(); i++)
		{
			System.out.println(tutte.get(i)+"\n");
		}
		System.out.println("\n");
		System.out.println("LEZIONI RICORSIONE	: \n");
		List <Lezione> ric = model.getSoluzioneMigliore(tutte);
		for(int i=0; i<ric.size(); i++)
		{
			System.out.println(ric.get(i)+"\n");
		}
		System.out.println(model.isCorretto(ric));
		
	}

}
