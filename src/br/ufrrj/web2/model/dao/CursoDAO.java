package br.ufrrj.web2.model.dao;



import java.util.List;

import javax.persistence.Query;

import br.ufrrj.web2.model.entities.CursoEntity;

public class CursoDAO extends DAO<CursoEntity> {

	
	public CursoEntity findByName(String nomeCurso) {
		
		Query q = getManager().createQuery("select c from CursoEntity c where c.nomeCurso = :atributo" );
		q.setParameter("atributo", nomeCurso);

		//Se mais de 1 com o mesmo nome tiver sido cadastrado, ele vai pegar só o primeiro.
		//Poderia ter marcado o nome do curso como sendo unico no mapeamento.
		//q.getResultList();
		CursoEntity cursoEntity = (CursoEntity)q.getResultList().get(0);
		
		return cursoEntity;
	}
	
	public CursoEntity cursoPorNomeComDisciplinas(String nomeCurso) {
		
		Query q = getManager().createQuery("select c from CursoEntity c JOIN FETCH c.disciplinas d where c.nomeCurso = :atributo" );
		q.setParameter("atributo", nomeCurso);

		//Se mais de 1 com o mesmo nome tiver sido cadastrado, ele vai pegar só o primeiro.
		//Poderia ter marcado o nome do curso como sendo unico no mapeamento.
		//q.getResultList();
		CursoEntity cursoEntity = (CursoEntity)q.getResultList().get(0);
		
		return cursoEntity;
	}
	public List<CursoEntity> pegarCursosComDisciplinas(){
		//o modelo tá EAGER, mas quis simular uma consultar caso tivesse LAZY
		//ref: https://thoughts-on-java.org/5-ways-to-initialize-lazy-relations-and-when-to-use-them/
		Query q = getManager().createQuery("select c from CursoEntity c JOIN FETCH c.disciplinas d" );
		List<CursoEntity> cursos = (List<CursoEntity>) q.getResultList();
		return cursos;
	}
	
}
