package br.org.ovelha.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ovelha.constant.MESES;
import br.org.ovelha.constant.MODULOS;
import br.org.ovelha.domain.Aluno;
import br.org.ovelha.domain.Aula;
import br.org.ovelha.persistence.AlunoDAO;
import br.org.ovelha.util.CDIFactory;

@BusinessController
public class AlunoBC extends DelegateCrud<Aluno, Long, AlunoDAO> {

	private static final long serialVersionUID = 1L;

	public Collection<Aula> inicializarAulas(Aluno aluno) {
		
		Collection<Aula> aulas = new ArrayList<Aula>();
		Date dataRegistro = new Date();
		
		for (MODULOS modulo : MODULOS.values()) {
			for (MESES mes : MESES.values()) {
				Aula aula = new Aula();
				aula.setAluno(aluno);
				aula.setDataRegistro(dataRegistro);
				aula.setMes(mes.getValor());
				aula.setModulo(modulo.getValor());
				aula.setPresenca(false);
				aulas.add(aula);
			}			
		}
		CDIFactory.getAulaDAO().insert((List<Aula>) aulas);
		return aulas;
	}

	/*
	public void update(Casal bean) {
		Homem homem = CDIFactory.getHomemDAO().getHomem(bean.getId());
		Homem homemBean = bean.getMarido();
		homemBean.setIdHomem(homem.getIdHomem());
		homemBean.setIdCasal(homem.getIdCasal());
		homemBean.setDataAtualizacaoRegistro(new Date());
		update(homemBean);
	}

	public void delete(Casal bean) {
		Homem homem = CDIFactory.getHomemDAO().getHomem(bean.getId());
		delete(homem.getIdHomem());		
	}*/

}
