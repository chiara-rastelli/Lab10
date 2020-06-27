package it.polito.tdp.bar.model;

import java.util.Random;

public class TestModel {

	public static void main(String[] args) {
		
		Simulator s = new Simulator();
		System.out.println("Numero clienti totali: "+s.getNumClientiTotali()+"\n");
		System.out.println("Numero clienti soddisfatti: "+s.numClientiSoddisfatti+"\n");
		System.out.println("Numero clienti insoddisfatti: "+s.getNumClientiInsoddisfatti()+"\n");
		

	}

}
