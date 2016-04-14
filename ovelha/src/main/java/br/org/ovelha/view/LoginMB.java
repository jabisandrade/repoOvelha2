package br.org.ovelha.view;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.security.InvalidCredentialsException;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractPageBean;
import br.org.ovelha.acesso.Credenciais;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Perfil;
import br.org.ovelha.domain.Usuario;
import br.org.ovelha.util.CDIFactory;

@ViewController
@NextView(PAGES.INICIAL)
public class LoginMB extends AbstractPageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessageContext messageContext;
    
	@Inject
    private SecurityContext context;
	
	@Inject
	private Credenciais credenciais;
	
	private boolean exibeAtualizar = false;
	
	private Long idCasalUsuarioLogado;
	
	private boolean ADM;
	
	private Usuario usuarioLogado;
	
	@PostConstruct
	public void init(){
		if(credenciais.isLoggedIn()){
				exibeAtualizar = false;
			}			
		
	}
	
	public void login(){
		try{			
			context.login();
			messageContext.add("Usuário autenticado com sucesso!");			
		}catch(InvalidCredentialsException exception){
			messageContext.add(exception.getMessage());				
		}				
	}
	
	public void logout(){
		context.logout();			
	}

	public Credenciais getCredenciais() {
		return credenciais;
	}

	public void setCredenciais(Credenciais credenciais) {
		this.credenciais = credenciais;
	}

	public boolean isADM() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String perfil = fc.getExternalContext().getSessionMap().get("perfil").toString();
		if (perfil.equals(Perfil.ADM.getId().toString())){
			this.ADM = true;
		}else{
			this.ADM = false;
		}
		return ADM;
	}
	

	public boolean isExibeAtualizar() {
		return exibeAtualizar;
	}


	public void setExibeAtualizar(boolean exibeAtualizar) {
		this.exibeAtualizar = exibeAtualizar;
	}

	public Long getIdCasalUsuarioLogado() {
		return idCasalUsuarioLogado;
	}

	public void setIdCasalUsuarioLogado(Long idCasalUsuarioLogado) {
		this.idCasalUsuarioLogado = idCasalUsuarioLogado;
	}

	public Usuario getUsuarioLogado() {
		this.usuarioLogado = CDIFactory.getUsuarioLogado();
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}


	






}
