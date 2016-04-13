package br.org.ovelha.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.org.ovelha.constant.PESQUISA_TIPO;
import br.org.ovelha.domain.Aluno;
import br.org.ovelha.domain.Usuario;
import br.org.ovelha.domain.dto.FiltroPesquisa;

@PersistenceController
public class AlunoDAO extends AbstractDAO<Aluno, Long> {
	
	private static final long serialVersionUID = 1L;
	    
    public List<Aluno> obterQuantitativoAlunosPorMacro(){
    	List<Aluno> alunos = new ArrayList<Aluno>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select a.nomeLiderMacro, count(a.nomeLiderMacro) from Aluno a group by a.nomeLiderMacro");
		Query q = createQuery(jpql.toString());
			
		@SuppressWarnings("unchecked")
		List<Object[]> lista = q.getResultList();
		for (Object[] c:lista) {
			Aluno a = new Aluno();
			a.setNomeLiderMacro(c[0].toString());
			a.setTotalAlunos(Integer.parseInt(c[1].toString()));
			alunos.add(a);						
		}
		return alunos;
  
    }
    
	public List<String> obterLideresRelacionadosAlunos() {
    	ArrayList<String> lideres = new ArrayList<String>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select a.nomeLiderImediato, count(a.nomeLiderImediato) from Aluno a group by a.nomeLiderImediato");
		Query q = createQuery(jpql.toString());
			
		@SuppressWarnings("unchecked")
		List<Object[]> lista = q.getResultList();
		for (Object[] c:lista) {
			lideres.add(c[0].toString());
		}
		return lideres;
	}

	public List<String> obterProfessoresRelacionadosAlunos() {
    	ArrayList<String> professores = new ArrayList<String>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select a.professores, count(a.professores) from Aluno a group by a.professores");
		Query q = createQuery(jpql.toString());
			
		@SuppressWarnings("unchecked")
		List<Object[]> lista = q.getResultList();
		for (Object[] c:lista) {
			professores.add(c[0].toString());
		}
		return professores;
	}

	public void atualizarAlunosLiderMacro(Usuario usuario) {
		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" UPDATE Aluno a ");
		jpql.append(" SET    a.idUsuarioLiderMacro = :idLiderMacro ");
		jpql.append(" WHERE  a.nomeLiderMacro = :nomeliderMacro ");

		parametros.put("idLiderMacro", usuario.getId());
		parametros.put("nomeliderMacro", usuario.getNome());

		executeUpdate(jpql.toString(), parametros);
		
	}
	
	public Collection<Aluno> obterAlunosPorLiderMacro (Long idLiderMacro){
		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select a from Aluno a");		
		jpql.append(" where a.idUsuarioLiderMacro = :idLiderMacro ");
		
		parametros.put("idLiderMacro", idLiderMacro);

		return executeQuery(jpql.toString(), parametros); 
	}
	
	public Long obterIdUsuarioLiderMacro(String nome) {

		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select a from Aluno a");	
		jpql.append(" where a.nomeLiderMacro = :nomeLiderMacro");
		jpql.append(" and a.idUsuarioLiderMacro > 0");

		parametros.put("nomeLiderMacro", nome);
				
		Aluno aluno = executeForceSingleResultQuery(jpql.toString(), parametros,Aluno.class);
		if (aluno == null){
			return 0L;
		}else{
			return aluno.getIdUsuarioLiderMacro();	
		}		 
	}

	public List<Aluno> obterAlunos(FiltroPesquisa filtro) {

		StringBuilder jpql = new StringBuilder();
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		jpql.append(" select a from Aluno a");	
		if(filtro.getTipo().equals(PESQUISA_TIPO.NOME_ALUNO)){
			jpql.append(" where UPPER(a.nome) like UPPER(:nome)");	
		}else if(filtro.getTipo().equals(PESQUISA_TIPO.NOME_LIDER_MACRO)){
			jpql.append(" where UPPER(a.nomeLiderMacro) like UPPER(:nome)");
		}else if(filtro.getTipo().equals(PESQUISA_TIPO.NOME_LIDER_IMEDIATO)){
			jpql.append(" where UPPER(a.nomeLiderImediato) like UPPER(:nome)");
		}else if(filtro.getTipo().equals(PESQUISA_TIPO.NOME_PROFESSOR)){
			jpql.append(" where UPPER(a.professores) like UPPER(:nome)");						
		}
		
		parametros.put("nome", "%" + filtro.getNome() + "%");

		return executeQuery(jpql.toString(), parametros);
	}


}
