package br.org.ovelha.domain.dto;

import br.org.ovelha.constant.PESQUISA_TIPO;

public class FiltroPesquisa {
	
	private String nome;
	private PESQUISA_TIPO tipo;
	private String tipoBusca;
	
	public PESQUISA_TIPO getTipo() {
		return tipo;
	}
	public void setTipo(PESQUISA_TIPO tipo) {
		this.tipo = tipo;
	}
	public String getTipoBusca() {		
		return tipoBusca;
	}
	public void setTipoBusca(String tipoBusca) {
		this.tipo = PESQUISA_TIPO.obterTipo(tipoBusca);
		this.tipoBusca = tipoBusca;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


}
