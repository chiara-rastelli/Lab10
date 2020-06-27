package it.polito.tdp.bar.model;

public class Tavolo implements Comparable<Tavolo>{

	private Integer numeroPosti;
	private boolean isOccupato;
	
	public Tavolo(Integer numeroPosti) {
		this.numeroPosti = numeroPosti;
		this.isOccupato = false;
	}

	public Integer getNumeroPosti() {
		return numeroPosti;
	}

	public void setOccupato(boolean isOccupato) {
		this.isOccupato = isOccupato;
	}

	public boolean isOccupato() {
		return isOccupato;
	}

	@Override
	public int compareTo(Tavolo o) {
		return this.getNumeroPosti().compareTo(o.getNumeroPosti());
	}
}
