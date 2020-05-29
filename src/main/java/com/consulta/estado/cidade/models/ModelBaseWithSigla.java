package com.consulta.estado.cidade.models;

public abstract class ModelBaseWithSigla extends ModelBase {
	
	private String sigla;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}

