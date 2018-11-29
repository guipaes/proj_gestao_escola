package br.ufrrj.web2.model.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AlunoEntity {

	/**
	 * Observe todos os métodos. Esta classe tem mais do que apenas gets e sets simples.
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	
	//O mappedBy só precisar ocorrer em uma das classes. Veja a classe Disciplina.
	@ManyToMany(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	private List<DisciplinaEntity> disciplinas = new ArrayList<DisciplinaEntity>();
	
	//aluno nao pode ser salvo se nao tiver um curso
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	//@Column(nullable=false)
	private CursoEntity curso;
	
	//Preciso mapear que este atributo é de tempo
	@Temporal(TemporalType.DATE)
	private Calendar dataNasc;
	
	//tem que ter construtor vazio
	public AlunoEntity() {
		
	}
	
	//Se voce sobrescrever o construtor, nao esqueca de fazer sobrecarga com construtor vazio
	public AlunoEntity(CursoEntity curso) {
		this.curso = curso;
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<DisciplinaEntity> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<DisciplinaEntity> disciplinas) {
		this.disciplinas = disciplinas;
	}
	//checa se disciplina é do curso no qual o aluno está inscrito
	public boolean addDisciplina(DisciplinaEntity d) {
		if (d.getCurso() == this.curso) {
			this.disciplinas.add(d);
			return true;
		}
		return false;
	}
	public boolean removeDisciplina(DisciplinaEntity d){
		if (d.getCurso() == this.curso) {
			this.disciplinas.remove(d);
			return true;
		}
		return false;
	}
	
	public CursoEntity getCurso() {
		return curso;
	}
	public void setCurso(CursoEntity curso) {
		this.curso = curso;
	}
	public Calendar getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Calendar dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Calendar configAlunoDataNasc(int dia, int mes, int ano) {
		
		Calendar c = Calendar.getInstance();		
		c.set(ano, mes, dia);
		this.dataNasc = c;
		return c;
	}
	
}
