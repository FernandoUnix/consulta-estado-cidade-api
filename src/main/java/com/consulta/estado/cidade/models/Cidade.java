package com.consulta.estado.cidade.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cidade extends ModelBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("microrregiao")
	private MicrorRegiao microrRegiao;

	public MicrorRegiao getMicrorregiao() {
		return microrRegiao;
	}

	public void setMicrorregiao(MicrorRegiao microrregiao) {
		this.microrRegiao = microrregiao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
}
