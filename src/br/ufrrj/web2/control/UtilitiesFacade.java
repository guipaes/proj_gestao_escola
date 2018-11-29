package br.ufrrj.web2.control;

import java.util.List;

import br.ufrrj.web2.model.dao.CursoDAO;
import br.ufrrj.web2.model.dao.DAO;
import br.ufrrj.web2.model.entities.CursoEntity;

public class UtilitiesFacade {
	
	public void abrirConexaoBanco() {
		DAO.open();
	}

	public void fecharConexaoBanco() {
		DAO.close();
	}
	
	public List<CursoEntity> carregarListaCursos() {
CursoDAO cursoDAO = new CursoDAO();
    	
    	cursoDAO.begin();
    	List<CursoEntity> cursos = (List<CursoEntity>)cursoDAO.findAll();
    	cursoDAO.commit();
    	
    	return cursos;
	}
}
