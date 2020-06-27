package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;

public class Event implements Comparable<Event>{
	
	public enum EventType {
		ARRIVO_CLIENTI, 
		USCITA_CLIENTI
	}

	private EventType type;
	private Duration tempo;
	private Integer numeroClienti;
	private Double tolleranza;
	private Tavolo t;
	
	public Event(EventType type, Duration tempo, Integer numeroClienti) {
		this.type = type;
		this.tempo = tempo;
		this.numeroClienti = numeroClienti;
		Random r = new Random();
		//this.tolleranza = 0.9*r.nextDouble();
		this.tolleranza = Math.random();
	}

	@Override
	public int compareTo(Event o) {
		return this.getTempo().compareTo(o.getTempo());
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Duration getTempo() {
		return tempo;
	}

	public void setTempo(Duration tempo) {
		this.tempo = tempo;
	}

	public Integer getNumeroClienti() {
		return numeroClienti;
	}

	public void setNumeroClienti(Integer numeroClienti) {
		this.numeroClienti = numeroClienti;
	}

	public Double getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(Double tolleranza) {
		this.tolleranza = tolleranza;
	}

	public Tavolo getT() {
		return t;
	}

	public void setT(Tavolo t) {
		this.t = t;
	}
	
}
