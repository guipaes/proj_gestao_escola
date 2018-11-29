package br.ufrrj.web2.control;

import java.util.List;

import br.ufrrj.web2.model.dao.CursoDAO;
import br.ufrrj.web2.model.entities.CursoEntity;

public class CursoFacade {

	private CursoEntity cursoEntity;
	
	public CursoEntity getNovoCurso(String nomeCurso) {		
		this.cursoEntity = new CursoEntity();
		this.cursoEntity.setNomeCurso(nomeCurso);		
		return this.cursoEntity;
	}
	
	public CursoEntity getCurso() {
		return this.cursoEntity;
	}

	/**
	 * 
	 * @param cursoEntity
	 * 
	 * Retorno a String com mensagem de erro ou sucesso. Poderia lançar uma exceção e deixar para que ela fosse tratada na cadada view.
	 */
	public String salvarCurso(CursoEntity cursoEntity) {
		String msg = "";
		try {
			
			CursoDAO cursoDAO = new CursoDAO();
			
			cursoDAO.begin();
			cursoDAO.persist(cursoEntity);
			cursoDAO.commit();
			
			this.cursoEntity = cursoEntity;
			msg = "Curso cadastrado com sucesso.";
									
		} catch (Exception e) {
			msg = "Erro ao cadastrar o curso.";
			this.cursoEntity = null;
		}
		return msg;
	}
	
	public List<CursoEntity> pegarTodosCursosComDisciplinas(){
	
		CursoDAO cursoDAO = new CursoDAO();
		
		cursoDAO.begin();
		List<CursoEntity> cursos = cursoDAO.pegarCursosComDisciplinas();
		cursoDAO.commit();
		
		return cursos;
	}
	
	public CursoEntity pegarCursoPorNomeComDisciplinas(String nomeCurso) {
	
		CursoDAO cursoDAO = new CursoDAO();
		
		cursoDAO.begin();
		CursoEntity cursoEntity = cursoDAO.cursoPorNomeComDisciplinas(nomeCurso);
		cursoDAO.commit();
		
		return cursoEntity;
	}
		
}
