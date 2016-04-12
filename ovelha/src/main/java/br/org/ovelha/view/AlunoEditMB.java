package br.org.ovelha.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.business.AlunoBC;
import br.org.ovelha.business.AulaBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Aluno;
import br.org.ovelha.domain.Aula;
import br.org.ovelha.util.CDIFactory;

@ViewController
@PreviousView(PAGES.ALUNO_LIST)
@RequiredPermission(resource="Aluno" , operation="editar")
public class AlunoEditMB extends AbstractEditPageBean<Aluno, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoBC alunoBC;
	
	@Inject
	private AulaBC aulaBC;
	
	@SuppressWarnings("unused")
	@Inject
	private MessageContext messageContext;
	
	boolean exibeNivel1 = false;
	boolean exibeNivel2 = false;
	boolean exibeNivel3 = false;
	boolean exibeTodosNiveis = false;
	boolean exibeLiderMacro = false;
	boolean exibeLiderImediato = false;
	boolean exibeProfessor = false;
	
	private ArrayList<String> pastores = new ArrayList<String>();
	private ArrayList<String> lideres = new ArrayList<String>();
	private ArrayList<String> professores = new ArrayList<String>();
	
	
	 @PostConstruct
	 public void inicializar() {
		 pastores.addAll(CDIFactory.getAlunoDAO().obterPastoresRelacionadosAlunos());
		 lideres.addAll(CDIFactory.getAlunoDAO().obterLideresRelacionadosAlunos());
		 professores.addAll(CDIFactory.getAlunoDAO().obterProfessoresRelacionadosAlunos());
	 }
	
	@Override
	@Transactional
	public String delete() {
		this.alunoBC.delete(getId());		
		//messageContext.add("Registro removido com sucesso!", SeverityType.INFO);
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro removido com sucesso!",""));
		return PAGES.ALUNO_EDIT;		

	}

	@Override
	@Transactional
	public String insert() {		
		Aluno a = getBean();
		a.setDataRegistro(new Date());
		
		Long idPastor = CDIFactory.getAlunoDAO().obterIdUsuarioLiderMacro(getBean().getNomeLiderMacro());
		if(idPastor>0){
			a.setIdUsuarioLiderMacro(idPastor);	
		}
		
		this.alunoBC.insert(a);	
		//messageContext.add("Registro incluído com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro incluído com sucesso!",""));
		return PAGES.ALUNO_EDIT;		
	}

	@Override
	@Transactional
	public String update() {
		Aluno a = getBean();
		this.aulaBC.update(a.getAulas());
		a.setDataAtualizacaoRegistro(new Date());
		this.alunoBC.update(getBean());
		//messageContext.add("Registro atualizado com sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro atualizado com sucesso!",""));
		return PAGES.ALUNO_EDIT;		

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

	public boolean isExibeNivel1() {
		if (!exibeTodosNiveis){
			exibeNivel1 = getBean().getNivel()==1;	
		}
		
		return exibeNivel1;
	}

	public void setExibeNivel1(boolean exibeNivel1) {
		this.exibeNivel1 = exibeNivel1;
	}

	public boolean isExibeNivel2() {
		if (!exibeTodosNiveis){
			exibeNivel2 = getBean().getNivel()==2;	
		}		
		return exibeNivel2;
	}

	public void setExibeNivel2(boolean exibeNivel2) {
		this.exibeNivel2 = exibeNivel2;
	}

	public boolean isExibeNivel3() {
		if (!exibeTodosNiveis){
			exibeNivel3 = getBean().getNivel()==3;	
		}
		
		return exibeNivel3;
	}

	public void setExibeNivel3(boolean exibeNivel3) {
		this.exibeNivel3 = exibeNivel3;
	}

	public boolean isExibeTodosNiveis() {
		return exibeTodosNiveis;
	}

	public void setExibeTodosNiveis(boolean exibeTodosNiveis) {
		if (exibeTodosNiveis){
			this.exibeNivel1=true;
			this.exibeNivel2=true;
			this.exibeNivel3=true;
		}
		this.exibeTodosNiveis = exibeTodosNiveis;
	}
	
	public ArrayList<String> getPastores() {
		return pastores;
	}

	public void setPastores(ArrayList<String> pastores) {
		this.pastores = pastores;
	}
		
	public ArrayList<String> getLideres() {
		return lideres;
	}

	public void setLideres(ArrayList<String> lideres) {
		this.lideres = lideres;
	}

	public boolean isExibeProfessor() {
		return exibeProfessor;
	}

	public void setExibeProfessor(boolean exibeProfessor) {
		this.exibeProfessor = exibeProfessor;
	}

	public ArrayList<String> getProfessores() {
		return professores;
	}

	public void setProfessores(ArrayList<String> professores) {
		this.professores = professores;
	}

	public boolean isExibeLiderMacro() {		
		return exibeLiderMacro;
	}

	public void setExibeLiderMacro(boolean exibeLiderMacro) {
		this.exibeLiderMacro = exibeLiderMacro;
	}
	
	public boolean isExibeLiderImediato() {
		return exibeLiderImediato;
	}

	public void setExibeLiderImediato(boolean exibeLiderImediato) {
		this.exibeLiderImediato = exibeLiderImediato;
	}
	
	public void alternarLiderMacro(AjaxBehaviorEvent event) throws Exception{

		if (getBean().getNomeLiderMacro()!=null){
			setExibeLiderMacro(!getBean().getNomeLiderMacro().equals("Informe o nome do líder da macro")); ;
		} else{
			setExibeLiderMacro(true);
		}						
	}
	
	public void alternarLiderImediato(AjaxBehaviorEvent event) throws Exception{

		if (getBean().getNomeLiderImediato()!=null){
			setExibeLiderImediato(!getBean().getNomeLiderImediato().equals("Informe o nome do líder imediato")); ;
		} else{
			setExibeLiderImediato(true);
		}						
	}
	
	public void alternarProfessor(AjaxBehaviorEvent event) throws Exception{

		if (getBean().getProfessores()!=null){
			setExibeProfessor(!getBean().getProfessores().equals("Informe o nome do(s) professores(s)")); ;
		} else{
			setExibeProfessor(true);
		}						
	}	

	
}
