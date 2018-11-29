package br.ufrrj.web2.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class DisciplinaEntity {

	@Id
	@GeneratedValue//(strategy=GenerationType.SEQUENCE)
	private int id;
	private String nomeDisciplina;	
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private CursoEntity curso;
	
	//O mappedBy de muitos para muitos precisa estar em apenas uma das classes.
	//O cascadeType.ALL faz com que todas disciplinas associadas ao curso passem pelo mesma operação JPA. Se o curso é recuperado, as disciplinas vem do banco junto com ele.
	//O FetchType.EAGER traz todas as disciplinas com o curso quando ele for recuperado do banco. Ou seja, elas vão para o heap da memória. Cuidado, isso pode esgotar a memória primária. A alternativa é fetch Lazzy.Isso obriga o programador a recuperar as disciplinas sob demanda por meio de consultas. 
	@ManyToMany(mappedBy="disciplinas", cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private List<AlunoEntity> alunos = new ArrayList<AlunoEntity>();
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	public CursoEntity getCurso() {
		return curso;
	}
	public void setCurso(CursoEntity curso) {
		this.curso = curso;
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
	
	public void removeAluno(AlunoEntity a) {
		this.alunos.remove(a);
	}
}
