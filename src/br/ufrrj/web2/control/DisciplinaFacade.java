package br.ufrrj.web2.control;

import br.ufrrj.web2.model.dao.DisciplinaDAO;
import br.ufrrj.web2.model.entities.DisciplinaEntity;

public class DisciplinaFacade {

	public boolean removerDisciplina(DisciplinaEntity disciplina) {
		
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		
		try {
			disciplinaDAO.begin();
			disciplina = disciplinaDAO.find(disciplina.getId());
			disciplinaDAO.remove(disciplina);
			disciplinaDAO.commit();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
