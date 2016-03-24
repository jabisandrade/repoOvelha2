package br.org.ovelha.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.org.ovelha.constant.MODULOS;


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
	
	@Column(columnDefinition="integer default 1")
	private int nivel;//1,2,3
	
	@Column(columnDefinition="integer default 1")
	private int modulo;//1,2,3,4,5,6
	
	@Column(columnDefinition="integer default 0")
	private int licao;//1,2,3,4,5,6,7,8,9,10
	
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
	private String professores;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistro;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacaoRegistro;
		
	@OneToMany(mappedBy="aluno", cascade = CascadeType.ALL)
	private Collection<Aula> aulas = new ArrayList<Aula>();
	
	@Transient
	private Integer reencontro;
	
	@Transient
	private String ultimaPresenca = "";
	
	@Transient
	private Collection<Aula> aulasModulo1 = new ArrayList<Aula>();
	
	@Transient
	private Collection<Aula> aulasModulo2 = new ArrayList<Aula>();
	
	@Transient
	private Collection<Aula> aulasModulo3 = new ArrayList<Aula>();
	
	@Transient
	private Collection<Aula> aulasModulo4 = new ArrayList<Aula>();
		
	@Transient
	private Collection<Aula> aulasModulo5 = new ArrayList<Aula>();
	
	@Transient
	private Collection<Aula> aulasModulo6 = new ArrayList<Aula>();
	
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
	
	public String getReencontroStr() {
		if(reEncontro){
			return "SIM";
		}else{
			return "NÃO";
		}
	}

	public void setReencontro(Integer reencontro) {
		if(reencontro==1){
			reEncontro=true;
		}else{
			reEncontro=false;
		}
		this.reencontro = reencontro;
	}

	public Collection<Aula> getAulasModulo1() {
		this.aulasModulo1 = new ArrayList<Aula>();
		for(Aula aula:this.getAulas()){
			if (aula.getModulo() == MODULOS.MODULO1.getValor()){
				aulasModulo1.add(aula);
			}
		}
		return aulasModulo1;
	}

	public void setAulasModulo1(Collection<Aula> aulasModulo1) {
		this.aulasModulo1 = new ArrayList<Aula>();
		for(Aula aula:this.getAulas()){
			if (aula.getModulo() == MODULOS.MODULO1.getValor()){
				this.getAulas().remove(aula);
			}
		}
		this.aulas.addAll(aulasModulo1);
		this.aulasModulo1 = aulasModulo1;
	}

	public Collection<Aula> getAulasModulo2() {
		this.aulasModulo2 = new ArrayList<Aula>();
		for(Aula aula:this.getAulas()){
			if (aula.getModulo() == MODULOS.MODULO2.getValor()){
				aulasModulo2.add(aula);
			}
		}
		return aulasModulo2;
	}

	public void setAulasModulo2(Collection<Aula> aulasModulo2) {
		this.aulasModulo2 = new ArrayList<Aula>();
		for(Aula aula:this.getAulas()){
			if (aula.getModulo() == MODULOS.MODULO2.getValor()){
				this.getAulas().remove(aula);
			}
		}
		this.aulas.addAll(aulasModulo2);
		this.aulasModulo1 = aulasModulo2;

	}

	public Collection<Aula> getAulasModulo3() {
		this.aulasModulo3 = new ArrayList<Aula>();
		for(Aula aula:this.getAulas()){
			if (aula.getModulo() == MODULOS.MODULO3.getValor()){
				aulasModulo3.add(aula);
			}
		}
		return aulasModulo3;
	}

	public void setAulasModulo3(Collection<Aula> aulasModulo3) {
		this.aulasModulo3 = new ArrayList<Aula>();
		for(Aula aula:this.getAulas()){
			if (aula.getModulo() == MODULOS.MODULO3.getValor()){
				this.getAulas().remove(aula);
			}
		}
		this.aulas.addAll(aulasModulo3);
		this.aulasModulo1 = aulasModulo3;

	}

	public Collection<Aula> getAulasModulo4() {
		this.aulasModulo4 = new ArrayList<Aula>();
		for(Aula aula:this.getAulas()){
			if (aula.getModulo() == MODULOS.MODULO4.getValor()){
				aulasModulo4.add(aula);
			}
		}
		return aulasModulo4;
	}

	public void setAulasModulo4(Collection<Aula> aulasModulo4) {
		this.aulasModulo4 = new ArrayList<Aula>();
		for(Aula aula:this.getAulas()){
			if (aula.getModulo() == MODULOS.MODULO4.getValor()){
				this.getAulas().remove(aula);
			}
		}
		this.aulas.addAll(aulasModulo4);
		this.aulasModulo1 = aulasModulo4;

	}

	public Collection<Aula> getAulasModulo5() {
		this.aulasModulo5 = new ArrayList<Aula>();
		for(Aula aula:this.getAulas()){
			if (aula.getModulo() == MODULOS.MODULO5.getValor()){
				aulasModulo5.add(aula);
			}
		}
		return aulasModulo5;
	}

	public void setAulasModulo5(Collection<Aula> aulasModulo5) {
		this.aulasModulo5 = new ArrayList<Aula>();
		for(Aula aula:this.getAulas()){
			if (aula.getModulo() == MODULOS.MODULO5.getValor()){
				this.getAulas().remove(aula);
			}
		}
		this.aulas.addAll(aulasModulo5);
		this.aulasModulo1 = aulasModulo5;

	}

	public Collection<Aula> getAulasModulo6() {
		this.aulasModulo6 = new ArrayList<Aula>();
		for(Aula aula:this.getAulas()){
			if (aula.getModulo() == MODULOS.MODULO6.getValor()){
				aulasModulo6.add(aula);
			}
		}
		return aulasModulo6;
	}

	public void setAulasModulo6(Collection<Aula> aulasModulo6) {
		this.aulasModulo6 = new ArrayList<Aula>();
		for(Aula aula:this.getAulas()){
			if (aula.getModulo() == MODULOS.MODULO6.getValor()){
				this.getAulas().remove(aula);
			}
		}
		this.aulas.addAll(aulasModulo6);
		this.aulasModulo1 = aulasModulo6;

	}

	public String getProfessores() {
		return professores;
	}

	public void setProfessores(String professores) {
		this.professores = professores;
	}
	
	public int getModulo() {
		return modulo;
	}

	public void setModulo(int modulo) {
		this.modulo = modulo;
	}

	public int getLicao() {
		return licao;
	}

	public void setLicao(int licao) {
		this.licao = licao;
	}

	public String getUltimaPresenca() {
		Collection<Aula> aulas = new ArrayList<Aula>();
		if(this.getModulo()== MODULOS.MODULO1.getValor()){
			aulas = this.getAulasModulo1();
		}else if(this.getModulo()== MODULOS.MODULO2.getValor()){
			aulas = this.getAulasModulo2();
		}else if(this.getModulo()== MODULOS.MODULO3.getValor()){
			aulas = this.getAulasModulo3();
		}else if(this.getModulo()== MODULOS.MODULO4.getValor()){
			aulas = this.getAulasModulo4();
		}else if(this.getModulo()== MODULOS.MODULO5.getValor()){
			aulas = this.getAulasModulo5();
		}else if(this.getModulo()== MODULOS.MODULO6.getValor()){
			aulas = this.getAulasModulo6();
		}
		Aula aula = new Aula();
		if (aulas.size()>0){
			aula = aulas.iterator().next();	
		}
		
		boolean presenca = false;
		
		if (this.getLicao()==1){
			presenca = aula.isA1();
		}else if (this.getLicao()==2){
			presenca = aula.isA2();
		}else if (this.getLicao()==3){
			presenca = aula.isA3();
		}else if (this.getLicao()==4){
			presenca = aula.isA4();
		}else if (this.getLicao()==5){
			presenca = aula.isA5();
		}else if (this.getLicao()==6){
			presenca = aula.isA6();
		}else if (this.getLicao()==7){
			presenca = aula.isA7();
		}else if (this.getLicao()==8){
			presenca = aula.isA8();
		}else if (this.getLicao()==9){
			presenca = aula.isA9();
		}else if (this.getLicao()==10){
			presenca = aula.isA10();
		}
		
		ultimaPresenca = presenca?"P":"F";
		
		return ultimaPresenca;
	}

	public void setUltimaPresenca(String ultimaPresenca) {
		this.ultimaPresenca = ultimaPresenca;
	}

	
}
