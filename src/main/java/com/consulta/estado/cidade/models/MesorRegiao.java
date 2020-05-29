package com.consulta.estado.cidade.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MesorRegiao extends ModelBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("UF")
	private UF uf;

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}
}
