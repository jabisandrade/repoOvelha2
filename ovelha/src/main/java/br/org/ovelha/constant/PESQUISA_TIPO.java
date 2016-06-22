package br.org.ovelha.constant;

public enum PESQUISA_TIPO {
    NOME_ALUNO(1,"Nome do Aluno"), 
    NOME_LIDER_MACRO(2,"Nome do Lider Macro"),
    NOME_LIDER_IMEDIATO(3,"Nomer do Líder Imediato"),   
    NOME_PROFESSOR(4,"Nome dos Professores"),
	MODULO(5,"Modulo do Curso"),
	DATA_ATUALIZACAO(6,"Data de Atualizacao"); 

    private int valor;
    private String descricao;

    PESQUISA_TIPO(int valor,String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }
    

    public int getValor() {
        return valor;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
	public static PESQUISA_TIPO obterTipo(int valor) {
		for (PESQUISA_TIPO modulo : values()) {
			if (modulo.getValor()==valor) {
				return modulo;
			}
		}
		return null;
	}
	
	public static PESQUISA_TIPO obterTipo(String descricao) {
		for (PESQUISA_TIPO modulo : values()) {
			if (modulo.getDescricao().equals(descricao)) {
				return modulo;
			}
		}
		return null;
	}


}

