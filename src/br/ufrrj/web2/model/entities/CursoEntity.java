package br.ufrrj.web2.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CursoEntity {

	@Id
	@GeneratedValue//(strategy=GenerationType.SEQUENCE)
	private int id;
	private String nomeCurso;	
	//O mappedBy mapeia do curso(um para muitos), qual atributo em Disciplina que está relacionado ao curso
	//O cascadeType.ALL faz com que todas disciplinas associadas ao curso passem pelo mesma operação JPA. Se o curso é recuperado, as disciplinas vem do banco junto com ele.
	//O FetchType.EAGER traz todas as disciplinas com o curso quando ele for recuperado do banco. Ou seja, elas vão para o heap da memória. Cuidado, isso pode esgotar a memória primária. A alternativa é fetch Lazzy.Isso obriga o programador a recuperar as disciplinas sob demanda por meio de consultas. 
	@OneToMany(mappedBy="curso",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<DisciplinaEntity> disciplinas = new ArrayList<DisciplinaEntity>();

	//Qual o default para cacade e fetch?
	@OneToMany(mappedBy="curso", cascade=CascadeType.REMOVE)
	private List<AlunoEntity> alunos = new ArrayList<AlunoEntity>();
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public List<DisciplinaEntity> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<DisciplinaEntity> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	public void addDisciplina(DisciplinaEntity d) {
		this.disciplinas.add(d);
	}
	
	public void removeDisciplina(DisciplinaEntity d) {
		this.disciplinas.remove(d);
	}
	
	public List<AlunoEntity> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<AlunoEntity> alunos) {
		this.alunos = alunos;
	}
	
	public void addAluno(AlunoEntity a) {
		this.alunos.add(a);
	}
	
	public void removerAluno(AlunoEntity a) {
		this.alunos.remove(a);
	}
			
			
}
