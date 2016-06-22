package br.org.ovelha.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.constant.MODULOS;
import br.org.ovelha.constant.PESQUISA_TIPO;
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
		if (filtro==null){
			return this.obterAlunos();			
		}else{
			return CDIFactory.getAlunoDAO().obterAlunos(filtro);
		}		
	}
	
	public Date obterUltimaDataAtualizacao() {
		return CDIFactory.getAlunoDAO().obterUltimaDataAtualizacao();
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

	public void atualizarInformacoesCurso(Aluno a, int licao, boolean licaoPresenca) {
		
		Collection<Aula> aulas = new ArrayList<Aula>();
		
		switch (a.getModulo()) {
		case 1:
			aulas = getAulasAlteradas(a.getAulasModulo1(), licao, licaoPresenca);
			a.getAulasModulo1().removeAll(a.getAulasModulo1());
			a.getAulasModulo1().addAll(aulas);
			break;
		
		case 2:
			aulas = getAulasAlteradas(a.getAulasModulo2(), licao, licaoPresenca);
			a.getAulasModulo2().removeAll(a.getAulasModulo2());
			a.getAulasModulo2().addAll(aulas);			
			break;
		
		case 3:
			aulas = getAulasAlteradas(a.getAulasModulo3(), licao, licaoPresenca);
			a.getAulasModulo3().removeAll(a.getAulasModulo3());
			a.getAulasModulo3().addAll(aulas);			
			break;
			
		case 4:
			aulas = getAulasAlteradas(a.getAulasModulo4(), licao, licaoPresenca);
			a.getAulasModulo4().removeAll(a.getAulasModulo4());
			a.getAulasModulo4().addAll(aulas);			
			break;
			
		case 5:
			aulas = getAulasAlteradas(a.getAulasModulo5(), licao, licaoPresenca);
			a.getAulasModulo5().removeAll(a.getAulasModulo5());
			a.getAulasModulo5().addAll(aulas);			
			break;
			
		case 6:
			aulas = getAulasAlteradas(a.getAulasModulo6(), licao, licaoPresenca);
			a.getAulasModulo1().removeAll(a.getAulasModulo1());
			a.getAulasModulo1().addAll(aulas);			
			break;
			
		}
			
		this.update(a);
		
	}
	
	private Collection<Aula> getAulasAlteradas(Collection<Aula> aulas, int licao, boolean licaoPresenca){
		
		Aula aulaAtual = new Aula();
		Aula aulaAlterada = new Aula();
		
		if (aulas.size()>0){
			aulaAtual = aulas.iterator().next();	
			aulaAlterada = aulaAtual;
		}
				
		switch (licao) {
		case 1:
			aulaAlterada.setA1(licaoPresenca);
			break;
		case 2:
			aulaAlterada.setA2(licaoPresenca);
			break;
		case 3:
			aulaAlterada.setA3(licaoPresenca);
			break;		
		case 4:
			aulaAlterada.setA4(licaoPresenca);
			break;		
		case 5:
			aulaAlterada.setA5(licaoPresenca);
			break;		
		case 6:
			aulaAlterada.setA6(licaoPresenca);
			break;		
		case 7:
			aulaAlterada.setA7(licaoPresenca);
			break;					
		case 8:
			aulaAlterada.setA8(licaoPresenca);
			break;		
		case 9:
			aulaAlterada.setA9(licaoPresenca);
			break;		
		case 10:
			aulaAlterada.setA10(licaoPresenca);
			break;					
		}
		
		aulas.remove(aulaAtual);
		aulas.add(aulaAlterada);
		return aulas;
	}


}
