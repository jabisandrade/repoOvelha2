package br.org.ovelha.util;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ovelha.domain.MensagemEletronica;
import br.org.ovelha.domain.Perfil;
import br.org.ovelha.domain.Usuario;
 
@Transactional
public class Loader {
 
    @Startup
    public void load() {
        Usuario usuario;
 
        usuario = new Usuario();
        usuario.setLogin("admin@ovelha.com.br");
        usuario.setSenha("admin@");
        usuario.setPerfil(Perfil.ADM);
        
        Usuario usuarioExiste = CDIFactory.getUsuarioDAO().obterSenhaUsuario(usuario.getLogin());
        if(usuarioExiste == null){
        	CDIFactory.getUsuarioDAO().insert(usuario);	
        }
        
        
        if(CDIFactory.getEmailDAO().findAll() !=null ||CDIFactory.getEmailDAO().findAll().isEmpty()){
            MensagemEletronica msg = new MensagemEletronica();
            msg.setServidor("smtp.mail.yahoo.com.br");
            msg.setPorta("465");
            msg.setUsuario("sistema.ovelha@yahoo.com.br");
            msg.setSenha("jesus1234");
            CDIFactory.getEmailDAO().insert(msg);
        	
        }
        
        
        
    }
}
