package com.consulta.estado.cidade.models;

import java.io.Serializable;

public class CidadeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idEstado;
	private String siglaEstado;
	private String regiaoNome;
	private String nomeCidade;
	private String nomeMesorregiao;
	private String nomeFormatado;

	public CidadeDto(Cidade cidade) {
		this.idEstado = cidade.getMicrorregiao().getMesorregiao().getUf().getId();
		this.siglaEstado = cidade.getMicrorregiao().getMesorregiao().getUf().getSigla();
		this.regiaoNome = cidade.getMicrorregiao().getMesorregiao().getUf().getRegiao().getNome();
		this.nomeCidade = cidade.getNome();
		this.nomeMesorregiao = cidade.getMicrorregiao().getMesorregiao().getNome();
		this.nomeFormatado = this.nomeCidade + "/" + this.siglaEstado;
	}

	public CidadeDto() {
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public String getSiglaEstado() {
		return siglaEstado;
	}

	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}

	public String getRegiaoNome() {
		return regiaoNome;
	}

	public void setRegiaoNome(String regiaoNome) {
		this.regiaoNome = regiaoNome;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String getNomeMesorregiao() {
		return nomeMesorregiao;
	}

	public void setNomeMesorregiao(String nomeMesorregiao) {
		this.nomeMesorregiao = nomeMesorregiao;
	}

	public String getNomeFormatado() {
		return nomeFormatado;
	}

	public void setNomeFormatado(String nomeFormatado) {
		this.nomeFormatado = nomeFormatado;
	}
}
