package br.org.ovelha.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.org.ovelha.domain.Aluno;

@PersistenceController
public class AlunoDAO extends AbstractDAO<Aluno, Long> {
	
	private static final long serialVersionUID = 1L;

	/*
	public Homem getHomem (Long idCasal){
		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select h from Homem h");		
		jpql.append(" where h.idCasal = :idCasal");

		parametros.put("idCasal", idCasal);

		return executeSingleResultQuery(jpql.toString(), parametros);
	}
	
    public Collection<Homem> obterAniversariantesMes(){
    	
		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select h from Homem h");		
		jpql.append(" where month(h.dataAniversario) in (:mes,:proximoMes)");

		parametros.put("mes", Data.mesAtual());
		parametros.put("proximoMes", Data.mesAtual()+1);

		return executeQuery(jpql.toString(), parametros);  
    }*/
	
}
