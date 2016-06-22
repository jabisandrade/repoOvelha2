package br.org.ovelha.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.business.AlunoBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Aluno;

@ViewController
@NextView(PAGES.ALUNO_EDIT)
@PreviousView(PAGES.ALUNO_LIST)
@RequiredPermission(resource="aluno" , operation="listar")
public class AlunoListMB extends AbstractListPageBean<Aluno, Long> {

	private static final long serialVersionUID = 1L;
	private int totalAlunos = 0;

	@Inject
	private AlunoBC bc;

	@Override
	protected List<Aluno> handleResultList() {
		List<Aluno> alunos = this.bc.obterAlunos();
		this.totalAlunos = alunos.size();
		return alunos;
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
	
	

}
