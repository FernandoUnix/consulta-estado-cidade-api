package com.consulta.estado.cidade.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MicrorRegiao extends ModelBase implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("mesorregiao")
	private MesorRegiao mesorRegiao;

	public MesorRegiao getMesorregiao() {
		return mesorRegiao;
	}

	public void setMesorregiao(MesorRegiao mesorregiao) {
		this.mesorRegiao = mesorregiao;
	}
}
