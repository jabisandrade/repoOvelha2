package br.org.ovelha.acesso;

import java.security.Principal;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.InvalidCredentialsException;
import br.gov.frameworkdemoiselle.util.Locales;
import br.org.ovelha.business.UsuarioBC;
import br.org.ovelha.domain.Usuario;

public class Autenticacao implements Authenticator {

	private static final long serialVersionUID = 1L;

	@Inject
	private Credenciais credenciais;

	@Inject
	UsuarioBC usuarioBC;
	
	@Inject
	Locales locales;
	
	private FacesContext fc = FacesContext.getCurrentInstance();

	@Override
	public void authenticate() throws Exception {
		if(!isUsuarioValido()){
			unauthenticate();	
			throw new InvalidCredentialsException();
		}
		fc.getExternalContext().getSessionMap().put("logado", "true");
		fc.getExternalContext().getSessionMap().put("usuarioid", credenciais.getIdUsuario());
		fc.getExternalContext().getSessionMap().put("usuario", credenciais.getUsername());
		fc.getExternalContext().getSessionMap().put("perfil", credenciais.getPerfil());
		locales.setCurrentLocale("pt");
	}

	@Override
	public void unauthenticate() throws Exception {		
		fc.getExternalContext().getSessionMap().remove("logado");
		fc.getExternalContext().getSessionMap().remove("usuarioid");
		fc.getExternalContext().getSessionMap().remove("usuario");
		fc.getExternalContext().getSessionMap().remove("perfil");
		HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);     
		session.invalidate();
		credenciais.clear();
	}

	@Override
	public Principal getUser() {
		return new Principal(){
			public String getName(){
				return credenciais.getUsername();
			}
		}; 
	}

	public Credenciais getUsuario(){
		return credenciais;

	}

	private  boolean isUsuarioValido(){
	
		if(credenciais.getUsername()==null || credenciais.getUsername().isEmpty()){
			return Boolean.FALSE;	
		}
		if(credenciais.getPassword()==null || credenciais.getPassword().isEmpty()){
			return Boolean.FALSE;	
		}
		if (usuarioBC.findAll().size()>0){
			Long idUsuario = usuarioBC.isUsuarioValido(new Usuario(credenciais));

			if (idUsuario>0){
				Usuario user = usuarioBC.load(idUsuario);
				credenciais.setIdUsuario(idUsuario);
				credenciais.setPerfil(user.getPerfil().getId());
				credenciais.setLoggedIn(true);
				return Boolean.TRUE;			
			} else{
				return Boolean.FALSE;
			}
		}else{						
			return Boolean.FALSE;	
		}

	}

}
