package br.org.ovelha.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.business.AlunoBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.constant.PESQUISA_TIPO;
import br.org.ovelha.domain.Aluno;
import br.org.ovelha.domain.dto.FiltroPesquisa;

@ViewController
@NextView(PAGES.ALUNO_EDIT)
@PreviousView(PAGES.ALUNO_FIND)
@RequiredPermission(resource="aluno" , operation="pesquisar")
public class AlunoFindMB extends AbstractListPageBean<Aluno, Long> {

	private static final long serialVersionUID = 1L;
	private int totalAlunos = 0;
	private FiltroPesquisa filtro = new FiltroPesquisa();
	private List<Aluno> alunos = new ArrayList<Aluno>();
	private List<String> tipos = new ArrayList<String>();
			
	@Inject
	private AlunoBC bc;
	
	 @PostConstruct
	 public void inicializar() {
		 for (PESQUISA_TIPO t: PESQUISA_TIPO.values()){
			 tipos.add(t.getDescricao());
		 }
			 
	 }
	 
	@Override
	protected List<Aluno> handleResultList() {
		alunos =  this.bc.obterAlunos(null);
		this.totalAlunos = alunos.size();
		return alunos;
	}
	
	public void pesquisarAlunos() {			
		alunos =  this.bc.obterAlunos(filtro);
		this.totalAlunos = alunos.size();
	}

	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);

			if (delete) {
				bc.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}
	
	public String alunoPresenca() {
		return PAGES.ALUNO_PRESENCA;
	}
	
	public int getTotalAlunos() {
		return totalAlunos;
	}

	public void setTotalAlunos(int totalAlunos) {
		this.totalAlunos = totalAlunos;
	}

	public FiltroPesquisa getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroPesquisa filtro) {
		this.filtro = filtro;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	public List<String> getTipos() {
		return tipos;
	}

	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}



	
	

}
