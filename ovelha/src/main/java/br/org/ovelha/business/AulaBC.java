package br.org.ovelha.business;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.domain.Aula;
import br.org.ovelha.persistence.AulaDAO;
import br.org.ovelha.util.CDIFactory;

@BusinessController
public class AulaBC extends DelegateCrud<Aula, Long, AulaDAO> {

	private static final long serialVersionUID = 1L;

	public void update(Collection<Aula> aulas){
		Date dataAtualizacaoRegistro = new Date();
		for (Aula aula:aulas){
			aula.setDataAtualizacaoRegistro(dataAtualizacaoRegistro);
		}
		CDIFactory.getAulaDAO().update((List<Aula>) aulas);

	}
}
