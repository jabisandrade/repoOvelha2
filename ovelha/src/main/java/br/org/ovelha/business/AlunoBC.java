package br.org.ovelha.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.constant.MODULOS;
import br.org.ovelha.domain.Aluno;
import br.org.ovelha.domain.Aula;
import br.org.ovelha.domain.Usuario;
import br.org.ovelha.domain.dto.FiltroPesquisa;
import br.org.ovelha.persistence.AlunoDAO;
import br.org.ovelha.util.CDIFactory;

@BusinessController
public class AlunoBC extends DelegateCrud<Aluno, Long, AlunoDAO> {

	private static final long serialVersionUID = 1L;

	public Collection<Aula> inicializarAulas(Aluno aluno) {
		
		Collection<Aula> aulas = new ArrayList<Aula>();
		Date dataRegistro = new Date();
		
		for (MODULOS modulo : MODULOS.values()) {
			
				Aula aula = new Aula();
				aula.setAluno(aluno);
				aula.setDataRegistro(dataRegistro);
				aula.setModulo(modulo.getValor());
				aula.setA1(false);
				aula.setA2(false);
				aula.setA3(false);
				aula.setA4(false);
				aula.setA5(false);
				aula.setA6(false);
				aula.setA7(false);
				aula.setA8(false);
				aula.setA9(false);
				aula.setA10(false);
				
				aulas.add(aula);
						
		}
		CDIFactory.getAulaDAO().insert((List<Aula>) aulas);
		return aulas;
	}

	public List<Aluno> obterAlunos() {
		Usuario usuarioLogado = CDIFactory.getUsuarioLogado();
		if (usuarioLogado.getPerfil().isPastor()){
			return (List<Aluno>) CDIFactory.getAlunoDAO().obterAlunosPorLiderMacro(usuarioLogado.getId());
		}else{
			return CDIFactory.getAlunoDAO().obterTodos();
		}					
	}
	
	public List<Aluno> obterAlunos(FiltroPesquisa filtro) {
		if (filtro.getTipo()==null){
			return this.obterAlunos();			
		}else{
			return CDIFactory.getAlunoDAO().obterAlunos(filtro);
		}		
	}
	
	public List<Aluno> obterQuantitativoAlunosPorMacro(){
		return CDIFactory.getAlunoDAO().obterQuantitativoAlunosPorMacro();
	}
	
	public List<String> obterPastoresRelacionadosAlunos(){
		ArrayList<String> pastores = new ArrayList<String>();
		List<Aluno> alunos = new ArrayList<Aluno>();
		alunos = CDIFactory.getAlunoDAO().obterQuantitativoAlunosPorMacro();
		for (Aluno a: alunos){
			pastores.add(a.getNomeLiderMacro());			
		}
		return pastores;
	}
	
	public List<String> obterLideresRelacionadosAlunos() {
		return CDIFactory.getAlunoDAO().obterLideresRelacionadosAlunos();
	}
	
	public List<String> obterProfessoresRelacionadosAlunos() {
		return CDIFactory.getAlunoDAO().obterProfessoresRelacionadosAlunos();
		
	}
	
	public Long obterIdUsuarioLiderMacro(String nome) {
		return CDIFactory.getAlunoDAO().obterIdUsuarioLiderMacro(nome);
	}


}
