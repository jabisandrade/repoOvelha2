package br.org.ovelha.view;

import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.business.AlunoBC;
import br.org.ovelha.business.AulaBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Aluno;
import br.org.ovelha.domain.Aula;

@ViewController
@PreviousView(PAGES.ALUNO_LIST)
@RequiredPermission(resource="Aluno" , operation="editar")
public class AlunoEditMB extends AbstractEditPageBean<Aluno, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoBC alunoBC;
	
	@Inject
	private AulaBC aulaBC;
	
	@Override
	@Transactional
	public String delete() {
		this.alunoBC.delete(getId());		
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		Aluno a = getBean();
		a.setDataRegistro(new Date());
		this.alunoBC.insert(a);		
		return getPreviousView();		
	}

	@Override
	@Transactional
	public String update() {
		Aluno a = getBean();
		this.aulaBC.update(a.getAulas());
		a.setDataAtualizacaoRegistro(new Date());
		this.alunoBC.update(getBean());
		return getPreviousView();
	}

	@Override
	protected Aluno handleLoad(Long id) {
		Aluno aluno = this.alunoBC.load(id);
		
		if (aluno.getAulas()==null||aluno.getAulas().isEmpty()){
			Collection<Aula> aulas = this.alunoBC.inicializarAulas(aluno);
			aluno.setAulas(aulas);
		}
		return aluno;
	}
	
	
	




}
