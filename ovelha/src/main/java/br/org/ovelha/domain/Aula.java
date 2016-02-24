package br.org.ovelha.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@SequenceGenerator(name = "seqAula", sequenceName = "idAula_seq", allocationSize = 1)
@Entity
public class Aula implements EntidadeIf  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAula")
	@Column(name = "idAula")
	private Long idAula;
	
	 @ManyToOne
	 @JoinColumn(name="idAluno", referencedColumnName="idAluno")
	 private Aluno aluno;
		
	@Column(columnDefinition="boolean default false")
	private boolean presenca;
	
	@Column
	private int modulo;//1,2,3,4,5,6
	
	@Column
	private int mes;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistro;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacaoRegistro;
	
	public Aula() {
		super();
	}
		
	public Long getIdAula() {
		return idAula;
	}

	public void setIdAula(Long idAula) {
		this.idAula = idAula;
	}

	public boolean isPresenca() {
		return presenca;
	}

	public void setPresenca(boolean presenca) {
		this.presenca = presenca;
	}

	public int getModulo() {
		return modulo;
	}

	public void setModulo(int modulo) {
		this.modulo = modulo;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Date getDataAtualizacaoRegistro() {
		return dataAtualizacaoRegistro;
	}

	public void setDataAtualizacaoRegistro(Date dataAtualizacaoRegistro) {
		this.dataAtualizacaoRegistro = dataAtualizacaoRegistro;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}


	
}
