package com.consulta.estado.cidade.models;

import java.io.Serializable;

public class UF extends ModelBaseWithSigla implements Serializable {

	private static final long serialVersionUID = 1L;

	private Regiao regiao;

	public Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}
}
