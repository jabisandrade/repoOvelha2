package br.org.ovelha.view;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.org.ovelha.business.AlunoBC;
import br.org.ovelha.constant.PAGES;
import br.org.ovelha.domain.Aluno;

@ViewController
@PreviousView(PAGES.RELATORIO_TOTAL_MACRO)
@RequiredPermission(resource="relatorio" , operation="total_macro")
public class RelatorioTotalMacroMB extends AbstractListPageBean<Aluno, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoBC bc;

	@Override
	protected List<Aluno> handleResultList() {
		return this.bc.obterQuantitativoAlunosPorMacro();
	}


}
