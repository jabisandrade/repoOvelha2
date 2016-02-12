package br.org.ovelha.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


@SequenceGenerator(name = "seqAluno", sequenceName = "idAluno_seq", allocationSize = 1)
@Entity
public class Aluno implements EntidadeIf  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAluno")
	@Column(name = "idAluno")
	private Long idAluno;

	@NotNull
	@Column
	private String nome;
	
	@Column
	private String telefonesAluno;
	
	@Column
	private File foto;
	
	@Column
	private int nivel;//1,2,3
	
	@Column
	private String nomeLiderImediato;
	
	@Column
	private String telefonesLiderImediato;
	
	@Column
	private String nomeLiderMacro;
	
	@Column
	private String telefonesLiderMacro;
		
	@Column(columnDefinition="boolean default false")
	private boolean reEncontro;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistro;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacaoRegistro;
	
	@OneToMany(mappedBy = "idAluno", targetEntity = Aula.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Aula> aulas = new ArrayList<Aula>();	
	
	@Transient
	private Integer reencontro;
	
	public Aluno() {
		super();
	}
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefonesAluno() {
		return telefonesAluno;
	}

	public void setTelefonesAluno(String telefonesAluno) {
		this.telefonesAluno = telefonesAluno;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getNomeLiderImediato() {
		return nomeLiderImediato;
	}

	public void setNomeLiderImediato(String nomeLiderImediato) {
		this.nomeLiderImediato = nomeLiderImediato;
	}

	public String getTelefonesLiderImediato() {
		return telefonesLiderImediato;
	}

	public void setTelefonesLiderImediato(String telefonesLiderImediato) {
		this.telefonesLiderImediato = telefonesLiderImediato;
	}

	public String getNomeLiderMacro() {
		return nomeLiderMacro;
	}

	public void setNomeLiderMacro(String nomeLiderMacro) {
		this.nomeLiderMacro = nomeLiderMacro;
	}

	public String getTelefonesLiderMacro() {
		return telefonesLiderMacro;
	}

	public void setTelefonesLiderMacro(String telefonesLiderMacro) {
		this.telefonesLiderMacro = telefonesLiderMacro;
	}

	public boolean isReEncontro() {
		return reEncontro;
	}

	public void setReEncontro(boolean reEncontro) {
		this.reEncontro = reEncontro;
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

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public Long getId() {
		return idAluno;
	}

	public void setId(Long idAluno) {
		this.idAluno = idAluno;
	}
	public File getFoto() {
		return foto;
	}

	public void setFoto(File foto) {
		this.foto = foto;
	}

	public Collection<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(Collection<Aula> aulas) {
		this.aulas = aulas;
	}

	public Integer getReencontro() {
		if(reEncontro){
			this.reencontro=1;
		}else{
			this.reencontro=2;
		}
		return reencontro;
	}

	public void setReencontro(Integer reencontro) {
		if(reencontro==1){
			reEncontro=true;
		}else{
			reEncontro=false;
		}
		this.reencontro = reencontro;
	}

	
}
