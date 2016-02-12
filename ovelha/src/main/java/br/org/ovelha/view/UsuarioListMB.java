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
import br.org.ovelha.business.UsuarioBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Usuario;

@ViewController
@NextView(PAGES.USUARIO_EDIT)
@PreviousView(PAGES.USUARIO_LIST)
@RequiredPermission(resource="usuario" , operation="listar")
public class UsuarioListMB extends AbstractListPageBean<Usuario, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioBC bc;

	@Override
	protected List<Usuario> handleResultList() {
		return this.bc.findAll();
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

}
