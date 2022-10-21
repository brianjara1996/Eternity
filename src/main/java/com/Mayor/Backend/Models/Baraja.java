package com.Mayor.Backend.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.Mayor.Backend.Utils.Metodos;

public class Baraja {
	

	private Carta cartas[];
	
	private int posSiguienteCarta;
	
	public static final int Num_cartas = 48;
	
	public Baraja() {
		this.cartas = new Carta[Num_cartas];
		this.posSiguienteCarta = 0;
		crearBaraja();
		

	}
	
	public void crearBaraja() {
		
		String[] palos = new String[4];
		
		palos[0] = "ESPADA";
		
		palos[1] = "ORO";
		
		palos[2] = "BASTO";
		
		palos[3] = "COPA";
		
		for(int i = 0; i < palos.length; i++) {
			
			for(int b = 0; b < 12 ; b++) {
				
				cartas[(i * 12  + b)] = new Carta(b+1 , palos[i]);
			}
			
		}
		
		
	}
	
	public List<Carta> Barajar_Repartir() {
		
		int posiAleatoria = 0;
		
		Carta c;
		

		
		for(int d = 0; d < 12;d++) {
			
			posiAleatoria = Metodos.generaNumeroEnteroAleatorio(0, 47);
			
			c = cartas[d];
			
			cartas[d] = cartas[posiAleatoria];
			
			cartas[posiAleatoria] = c;
			
		}
		
		
		  List<Carta> reparto = new ArrayList<Carta>();
		  
		  Carta[] repar =  new Carta[4];
		  
		
		for(int e = 0; e < 4; e++) {
			
			posiAleatoria = Metodos.generaNumeroEnteroAleatorio(0, 47);
			
			repar[e] =  cartas[posiAleatoria];
			
			repar[e].setId(152l);
			
		}
		
		reparto = Arrays.asList(repar);
		
		
		return reparto;	
	}
	
	
}
