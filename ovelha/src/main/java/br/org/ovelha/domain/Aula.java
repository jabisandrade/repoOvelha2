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
	private boolean a1;
	
	@Column(columnDefinition="boolean default false")
	private boolean a2;
	
	@Column(columnDefinition="boolean default false")
	private boolean a3;
	
	@Column(columnDefinition="boolean default false")
	private boolean a4;
	
	@Column(columnDefinition="boolean default false")
	private boolean a5;
	
	@Column(columnDefinition="boolean default false")
	private boolean a6;
	
	@Column(columnDefinition="boolean default false")
	private boolean a7;
	
	@Column(columnDefinition="boolean default false")
	private boolean a8;
	
	@Column(columnDefinition="boolean default false")
	private boolean a9;
	
	@Column(columnDefinition="boolean default false")
	private boolean a10;
	
	@Column
	private int modulo;//1,2,3,4,5,6
		
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

	public boolean isA5() {
		return a5;
	}

	public void setA5(boolean a5) {
		this.a5 = a5;
	}

	public boolean isA6() {
		return a6;
	}

	public void setA6(boolean a6) {
		this.a6 = a6;
	}

	public boolean isA7() {
		return a7;
	}

	public void setA7(boolean a7) {
		this.a7 = a7;
	}

	public boolean isA8() {
		return a8;
	}

	public void setA8(boolean a8) {
		this.a8 = a8;
	}

	public boolean isA9() {
		return a9;
	}

	public void setA9(boolean a9) {
		this.a9 = a9;
	}

	public boolean isA10() {
		return a10;
	}

	public void setA10(boolean a10) {
		this.a10 = a10;
	}


	
}
