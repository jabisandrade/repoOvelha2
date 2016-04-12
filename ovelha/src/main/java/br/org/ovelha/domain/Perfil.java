package br.org.ovelha.domain;

public enum Perfil {
	
	ADM(1, "Administrador"),
	PASTOR(2, "Pastor"),
	PUB(3, "Público");

	private final Integer id;
	private final String descricao;

	private Perfil(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return getDescricao();
	}

	public static Perfil get(Integer id) {

		for (Perfil item : values()) {
			if (item.getId().equals(id)) {
				return item;
			}
		}

		return null;
	}
	
	public boolean isADM() {
		return this.equals(Perfil.ADM);
	}
	
	public boolean isPastor() {
		return this.equals(Perfil.PASTOR);
	}
	
	public boolean isPublico() {
		return this.equals(Perfil.PUB);
	}

}
