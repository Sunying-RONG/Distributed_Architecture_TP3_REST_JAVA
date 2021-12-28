package com.example.demo.models;

import java.util.List;

public class AgencePropose {
	private Agence agence;
	private Propose propose;

	public AgencePropose() {
		super();
	}

	public AgencePropose(Agence agence, Propose propose) {
		super();
		this.agence = agence;
		this.propose = propose;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public Propose getPropose() {
		return propose;
	}

	public void setPropose(Propose propose) {
		this.propose = propose;
	}

	@Override
	public String toString() {
		return "AgencePropose [agence=" + agence + ", propose=" + propose + "]";
	}

	
}
