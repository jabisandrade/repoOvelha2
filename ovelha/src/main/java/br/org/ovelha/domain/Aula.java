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
import javax.persistence.Transient;

import br.org.ovelha.constant.MESES;


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
	private boolean a1;
	
	@Column(columnDefinition="boolean default false")
	private boolean a2;
	
	@Column(columnDefinition="boolean default false")
	private boolean a3;
	
	@Column(columnDefinition="boolean default false")
	private boolean a4;
	
	@Column
	private int modulo;//1,2,3,4,5,6
	
	@Column
	private int mes;
	
	@Transient
	private String nomeMes;
	
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

	public String getNomeMes() {
		this.nomeMes = MESES.obterMes(this.mes).getDescricao();
		return nomeMes;
	}

	public void setNomeMes(String nomeMes) {
		this.nomeMes = nomeMes;
	}

	public boolean isA1() {
		return a1;
	}

	public void setA1(boolean a1) {
		this.a1 = a1;
	}

	public boolean isA2() {
		return a2;
	}

	public void setA2(boolean a2) {
		this.a2 = a2;
	}

	public boolean isA3() {
		return a3;
	}

	public void setA3(boolean a3) {
		this.a3 = a3;
	}

	public boolean isA4() {
		return a4;
	}

	public void setA4(boolean a4) {
		this.a4 = a4;
	}


	
}
