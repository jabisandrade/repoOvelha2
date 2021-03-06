package br.org.ovelha.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
import br.org.ovelha.util.Data;

@ViewController
@NextView(PAGES.ALUNO_EDIT)
@PreviousView(PAGES.ALUNO_FIND)
@RequiredPermission(resource="aluno" , operation="pesquisar")
public class AlunoFindMB extends AbstractListPageBean<Aluno, Long> {

	private static final long serialVersionUID = 1L;
	private int totalAlunos = 0;
	private FiltroPesquisa filtro = new FiltroPesquisa();
	private List<Aluno> alunos = new ArrayList<Aluno>();
	private List<Aluno> alunosSelecionados = new ArrayList<Aluno>();
	private List<String> tipos = new ArrayList<String>();
	private ArrayList<String> modulos = new ArrayList<String>();
	private ArrayList<String> licoes = new ArrayList<String>();
	private int modulo;
	private int licao;
	private int licaoPresenca;
	private int licaoAtual;
	private Date dataUltimaAtualizacao;
	private boolean atualizou=false;
	private boolean deletou=false;
			
	@Inject
	private AlunoBC bc;
	
	 @PostConstruct
	 public void inicializar() {
		 for (PESQUISA_TIPO t: PESQUISA_TIPO.values()){
			 if (!t.equals(PESQUISA_TIPO.DATA_ATUALIZACAO)){
				 tipos.add(t.getDescricao());	 
			 }			 
		 }
		 
		 for (int i = 1; i < 7; i++) {
			 modulos.add(String.valueOf(i));			
		 }
		 
		 for (int i = 1; i < 11; i++) {
			 licoes.add(String.valueOf(i));			
		 }
		 dataUltimaAtualizacao = bc.obterUltimaDataAtualizacao();
		 alunos = handleResultList();
		 
		atualizou=false;
		deletou=false;
			 
	 }
	 
	 	 
	@Override
	protected List<Aluno> handleResultList() {
		filtro.setTipo(PESQUISA_TIPO.DATA_ATUALIZACAO);
		filtro.setDataAtualizacao(dataUltimaAtualizacao);
		alunos =  this.bc.obterAlunos(filtro);
		this.totalAlunos = alunos.size();
		return alunos;
	}
	
	public void pesquisarAlunos() {
		if (filtro != null && filtro.getTipo()!=null){
			alunos =  this.bc.obterAlunos(filtro);
			this.totalAlunos = alunos.size();
		}else{
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Selecione um tipo de busca e preencha o campo para pesquisa.",""));
		}

	}

	@Transactional
	public String deleteSelection() {
		for (Aluno a: alunosSelecionados){
			bc.delete(a.getId());
		}
		
		if (atualizou){
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,"Alunos removidos da base com sucesso.",""));
		}		
		return getPreviousView();
	}
	
	@Transactional
	public String updateSelection() {
		
		for (Aluno a: alunosSelecionados){
			a.setDataAtualizacaoRegistro(dataUltimaAtualizacao);
			a.setModulo(modulo);
			a.setLicao(licaoAtual);
			
			bc.atualizarInformacoesCurso(a, licao,licaoPresenca==1?true:false);
			atualizou = true;			
		}
		
		if (atualizou){
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,"Aluno(s) atualizado(s) recentemente em "+Data.dataHoraExtenso(dataUltimaAtualizacao)+".",""));

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

	public int getModulo() {
		return modulo;
	}

	public void setModulo(int modulo) {
		this.modulo = modulo;
	}

	public ArrayList<String> getModulos() {
		return modulos;
	}

	public void setModulos(ArrayList<String> modulos) {
		this.modulos = modulos;
	}

	public ArrayList<String> getLicoes() {
		return licoes;
	}

	public void setLicoes(ArrayList<String> licoes) {
		this.licoes = licoes;
	}

	public int getLicao() {
		return licao;
	}

	public void setLicao(int licao) {
		this.licao = licao;
	}

	public int getLicaoAtual() {
		return licaoAtual;
	}

	public void setLicaoAtual(int licaoAtual) {
		this.licaoAtual = licaoAtual;
	}


	public int getLicaoPresenca() {
		return licaoPresenca;
	}


	public void setLicaoPresenca(int licaoPresenca) {
		this.licaoPresenca = licaoPresenca;
	}


	public List<Aluno> getAlunosSelecionados() {
		return alunosSelecionados;
	}


	public void setAlunosSelecionados(List<Aluno> alunosSelecionados) {
		this.alunosSelecionados = alunosSelecionados;
	}


	public boolean isDeletou() {
		return deletou;
	}


	public void setDeletou(boolean deletou) {
		this.deletou = deletou;
	}



	
	

}
