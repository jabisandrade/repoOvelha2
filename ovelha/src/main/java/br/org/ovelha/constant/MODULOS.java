package br.org.ovelha.constant;

public enum MODULOS {
    MODULO1(1,"Módulo 1"), MODULO2(2,"Módulo 2"),MODULO3(3,"Módulo 3"),   
    MODULO4(4,"Módulo 4"), MODULO5(5,"Módulo 5"),MODULO6(6,"Módulo 6");

    private int valor;
    private String descricao;

    MODULOS(int valor,String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }
    

    public int getValor() {
        return valor;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
	public static MODULOS obterModulo(int valor) {
		for (MODULOS modulo : values()) {
			if (modulo.getValor()==valor) {
				return modulo;
			}
		}
		return null;
	}

}

