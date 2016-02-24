package br.org.ovelha.util;

import br.gov.frameworkdemoiselle.util.Beans;
import br.org.ovelha.acesso.Credenciais;
import br.org.ovelha.domain.Usuario;
import br.org.ovelha.persistence.AlunoDAO;
import br.org.ovelha.persistence.AulaDAO;
import br.org.ovelha.persistence.EmailDAO;
import br.org.ovelha.persistence.UsuarioDAO;

public class CDIFactory {

	private CDIFactory() {
	}

	public static UsuarioDAO getUsuarioDAO() {
		return Beans.getReference(UsuarioDAO.class);
	}
	
	public static EmailDAO getEmailDAO() {
		return Beans.getReference(EmailDAO.class);
	}
	
	public static AlunoDAO getAlunoDAO() {
		return Beans.getReference(AlunoDAO.class);
	}
	
	public static AulaDAO getAulaDAO() {
		return Beans.getReference(AulaDAO.class);
	}
	
	
	public static Usuario getUsuarioLogado() {
		Credenciais credenciais = Beans.getReference(Credenciais.class);
		if(credenciais.getIdUsuario()!=null && credenciais.getIdUsuario()>0){
			return getUsuarioDAO().load(credenciais.getIdUsuario());	
		}else{
			return null;
		}
		
	}
	

}
