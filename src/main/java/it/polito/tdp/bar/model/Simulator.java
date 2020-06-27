package it.polito.tdp.bar.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {
	
	//numero di arrivo clienti
	int numEventiIniziali = 2000;
	int numClientiSoddisfatti;
	int numClientiInsoddisfatti;
	int numClientiTotali;
	
	PriorityQueue<Event> queue = new PriorityQueue<>();
	
	//mappa tavoli
	List<Tavolo> listaTavoli = new ArrayList<Tavolo>();
	
	public Simulator() {
		this.aggiungiTavoli(2, 10);
		this.aggiungiTavoli(4, 8);
		this.aggiungiTavoli(4, 6);
		this.aggiungiTavoli(5, 4);
		Collections.sort(listaTavoli);
		
		this.numClientiInsoddisfatti = 0;
		this.numClientiSoddisfatti = 0;
		this.numClientiTotali = 0;
		
		Duration oraArrivo = Duration.ofMinutes(0);
		
		for (int i = 0; i<2000; i++) {
			int numeroClienti = (int) (Math.random()*(10))+1;
			Event eTemp = new Event(EventType.ARRIVO_CLIENTI, oraArrivo, numeroClienti);
			this.queue.add(eTemp);
			oraArrivo = oraArrivo.plusMinutes(1 + (int)Math.random()*10);
		}
		
		this.run();
	}

	private void run() {
		while (!queue.isEmpty()) {
			Event e = queue.poll();
		//	System.out.println(e);
			processEvent(e);
		}
	}

	private void processEvent(Event e) {
		switch (e.getType()){
			case ARRIVO_CLIENTI:
				this.numClientiTotali += e.getNumeroClienti();
				Tavolo t = this.postiDisponibili(e.getNumeroClienti());
				if(t != null) {
					this.numClientiSoddisfatti += e.getNumeroClienti();
					t.setOccupato(true);
					int permanenza = 60 + (int)Math.random()*60;
					Event eTemp = new Event(EventType.USCITA_CLIENTI, e.getTempo().plusMinutes(permanenza), e.getNumeroClienti());	
					eTemp.setT(t);
				}else {
					if (Math.random()<=e.getTolleranza()) {
						this.numClientiSoddisfatti += e.getNumeroClienti();
					}else {
						this.numClientiInsoddisfatti += e.getNumeroClienti();
					}
				}
			break;
			case USCITA_CLIENTI:
				e.getT().setOccupato(false);
			break;
		}
		
	}

	private Tavolo postiDisponibili(Integer numeroClienti) {
		for (int i = 0 ; i < this.listaTavoli.size(); i++) {
			Tavolo t = this.listaTavoli.get(i);
			if(t.getNumeroPosti()>=numeroClienti && numeroClienti >= t.getNumeroPosti()*0.5 && t.isOccupato()==false)
				return t;
		}
		return null;
	}

	public int getNumClientiSoddisfatti() {
		return numClientiSoddisfatti;
	}

	public int getNumClientiInsoddisfatti() {
		return numClientiInsoddisfatti;
	}

	public int getNumClientiTotali() {
		return numClientiTotali;
	}

	private void aggiungiTavoli(int numeroTavoli, int numeroPosti) {
		for (int i = 0; i<numeroTavoli; i++) {
			this.listaTavoli.add(new Tavolo(numeroPosti));
		}
	}
	

}
